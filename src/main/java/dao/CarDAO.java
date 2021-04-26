package dao;

import entity.Car;

import java.util.List;

public interface CarDAO {
    public void add(Car car);

    public List<Car> getAllCars();
}
