package service;

import bl.SessionUtil;
import dao.DriverDAO;
import entity.Driver;
import org.hibernate.Session;

public class DriverService extends SessionUtil implements DriverDAO {
    @Override
    public void add(Driver driver) {
        openTransactionSession();

        Session session = getSession();
        session.save(driver);

        closeTransactionSession();
    }
}
