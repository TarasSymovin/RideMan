package service;

import bl.SessionUtil;
import dao.TripDAO;
import entity.Trip;
import org.hibernate.Session;

public class TripService extends SessionUtil implements TripDAO {
    @Override
    public void add(Trip trip) {
        openTransactionSession();

        Session session = getSession();
        session.save(trip);

        closeTransactionSession();
    }
}
