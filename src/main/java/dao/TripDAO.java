package dao;

import entity.Driver;
import entity.Trip;

import java.util.List;

public interface TripDAO {
    void add(Trip trip);
    List<Trip> getAllTrips();
    void remove(Trip trip);
}
