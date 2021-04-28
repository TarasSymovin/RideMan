package service;

import bl.SessionUtil;
import dao.DriverDAO;
import entity.Car;
import entity.Driver;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class DriverService extends SessionUtil implements DriverDAO {
    @Override
    public void add(Driver driver) {
        openTransactionSession();

        Session session = getSession();
        session.save(driver);

        closeTransactionSession();
    }

    @Override
    public List<Driver> getAllDrivers() {
        openTransactionSession();

        try {
            Session session = getSession();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Driver> cq = cb.createQuery(Driver.class);
            Root<Driver> rootEntry = cq.from(Driver.class);
            CriteriaQuery<Driver> all = cq.select(rootEntry);

            TypedQuery<Driver> allQuery = session.createQuery(all);

            return allQuery.getResultList();
        }
        finally {
            closeTransactionSession();
        }
    }

    @Override
    public void remove(Driver driver) {
        openTransactionSession();

        Session session = getSession();
        session.delete(driver);

        closeTransactionSession();
    }

    @Override
    public void update(Driver driver) {
        openTransactionSession();

        Session session = getSession();
        session.update(driver);

        closeTransactionSession();
    }


}
