package dao;

import entity.CarType;

import java.util.List;

public interface CarTypeDAO {
    public void add(CarType carType);

    public List<CarType> getAllTypes();
}
