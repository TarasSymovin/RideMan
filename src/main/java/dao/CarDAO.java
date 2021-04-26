package dao;

import entity.Car;

import java.util.List;

public interface CarDAO {
    void add(Car car);

    List<Car> getAllCars();

    Car getByCarNumber(String number);
}
