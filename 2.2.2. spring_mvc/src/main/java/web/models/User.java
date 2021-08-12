package web.models;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name="User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int count;

    @Column
    @NotEmpty(message = "Введите имя")
    @Size(min = 3, max = 30, message = "имя должно быть от 3 до 30 символов")
    private String name;

    @Column
    @NotEmpty(message = "Введите фамилию")
    @Size(min = 3, max = 30, message = "фамилия должна быть от 3 до 30 символов")
    private String surname;

    @Column
    @Min(value = 0, message = "введите корректный возраст")
    @Max(value = 120, message = "введите корректный возраст")
    private int age;

    @Column
    @Email(message = "email должен быть валидным")
    private String email;


    public User() {}

    public User(int count, String name, String surname, int age, String email) {
        this.count = count;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
    }

    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

}
