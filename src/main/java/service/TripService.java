package service;

import bl.SessionUtil;
import dao.TripDAO;
import entity.Driver;
import entity.Trip;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class TripService extends SessionUtil implements TripDAO {
    @Override
    public void add(Trip trip) {
        openTransactionSession();

        Session session = getSession();
        session.save(trip);

        closeTransactionSession();
    }

    @Override
    public List<Trip> getAllTrips() {
        openTransactionSession();

        try {
            Session session = getSession();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Trip> cq = cb.createQuery(Trip.class);
            Root<Trip> rootEntry = cq.from(Trip.class);
            CriteriaQuery<Trip> all = cq.select(rootEntry);

            TypedQuery<Trip> allQuery = session.createQuery(all);

            return allQuery.getResultList();
        }
        finally {
            closeTransactionSession();
        }
    }

    @Override
    public void remove(Trip trip) {
        openTransactionSession();

        Session session = getSession();
        session.delete(trip);

        closeTransactionSession();
    }
}
