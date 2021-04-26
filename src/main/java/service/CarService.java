package service;

import bl.SessionUtil;
import dao.CarDAO;
import entity.Car;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CarService extends SessionUtil implements CarDAO {
    @Override
    public void add(Car car) {
        openTransactionSession();

        Session session = getSession();
        session.save(car);

        closeTransactionSession();
    }

    @Override
    public List<Car> getAllCars() {
        openTransactionSession();

        try {
            Session session = getSession();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Car> cq = cb.createQuery(Car.class);
            Root<Car> rootEntry = cq.from(Car.class);
            CriteriaQuery<Car> all = cq.select(rootEntry);

            TypedQuery<Car> allQuery = session.createQuery(all);

            return allQuery.getResultList();
        }
        finally {
            closeTransactionSession();
        }
    }

    @Override
    public Car getByCarNumber(String number) {
        openTransactionSession();

        try {
            Session session = getSession();

            return session.get(Car.class, number);
        }
        finally {
            closeTransactionSession();
        }
    }
}
