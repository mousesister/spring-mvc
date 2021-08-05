package web.service;

import web.model.Car;

import java.util.ArrayList;
import java.util.List;

public class CarService {
    List<Car> list = new ArrayList<>();
    public List<Car> getAllList() {
        list.add(new Car("bently continental", 2008, 5600));
        list.add(new Car("porsche 911", 1999, 2900));
        list.add(new Car("ЗАЗ 968", 1985, 32));
        list.add(new Car("honda hrv", 2003, 400));
        list.add(new Car("toyota mark II", 2000, 375));
        return list;
    }

}
