package service;

import bl.SessionUtil;
import dao.CityDAO;
import entity.City;
import org.hibernate.Session;

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
}
