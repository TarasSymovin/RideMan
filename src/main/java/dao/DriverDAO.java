package dao;

import entity.Car;
import entity.Driver;

import java.util.List;

public interface DriverDAO {
    void add(Driver driver);
    List<Driver> getAllDrivers();
    void remove(Driver driver);
    void update(Driver driver);
}
