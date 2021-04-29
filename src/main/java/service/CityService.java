package service;

import bl.SessionUtil;
import dao.CityDAO;
import entity.City;
import entity.Driver;
import entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CityService extends SessionUtil implements CityDAO {
    @Override
    public City getCityById(int id) {
        openTransactionSession();

        try {
            Session session = getSession();

            return session.get(City.class, id);
        }
        finally {
            closeTransactionSession();
        }
    }

    @Override
    public City getByCity(String city) {
        openTransactionSession();

        try {
            Session session = getSession();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<City> cq = cb.createQuery(City.class);
            Root<City> rootEntry = cq.from(City.class);
            CriteriaQuery<City> all = cq.select(rootEntry).where(cb.equal(rootEntry.get("city"), city));

            TypedQuery<City> allQuery = session.createQuery(all);

            return allQuery.getResultList().get(0);
        }
        finally {
            closeTransactionSession();
        }
    }
}
