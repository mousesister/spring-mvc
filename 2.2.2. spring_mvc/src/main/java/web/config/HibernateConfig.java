package web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
@ComponentScan("web")
public class HibernateConfig {
    private final Environment environment;
    @Autowired
    public HibernateConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(environment.getRequiredProperty("db.entity.package"));
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(getHibernateProperties());
        return em;
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        InputStream is = getClass().getClassLoader().getResourceAsStream("hibernate.properties");
        try {
            properties.load(is);
        } catch (IOException e) {
            System.out.println("Can't find hibernate.properties");
            e.printStackTrace();
        }
        return properties;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(environment.getRequiredProperty("db.driver"));
        ds.setUrl(environment.getRequiredProperty("db.url"));
        ds.setUsername(environment.getRequiredProperty("db.login"));
        ds.setPassword(environment.getRequiredProperty("db.password"));

//        bds.setInitialSize(Integer.parseInt(environment.getRequiredProperty("db.initialSize = 30")));
//        bds.setMinIdle(Integer.parseInt(environment.getRequiredProperty("db.minIdle = 30")));
//        bds.setMaxIdle(Integer.parseInt(environment.getRequiredProperty("db.maxIdle = 60")));
//        bds.setTimeBetweenEvictionRunsMillis(Long.parseLong(environment.getRequiredProperty("db.timeBetweenEvictionRunsMillis = 30000")));
//        bds.setMinEvictableIdleTimeMillis(Long.parseLong(environment.getRequiredProperty("db.minEvictableIdleTimeMillis = 60000")));
//        bds.setTestOnBorrow(Boolean.parseBoolean(environment.getRequiredProperty("db.testOnBorrow = true")));
//        bds.setValidationQuery(environment.getRequiredProperty("db.validationQuery = select version()"));

        return ds;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }
}
