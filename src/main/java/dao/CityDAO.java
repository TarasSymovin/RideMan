package dao;

import entity.City;
import entity.Driver;

import java.util.List;

public interface CityDAO {
    City getCityById(int id);
    public City getByCity(String city);
}
