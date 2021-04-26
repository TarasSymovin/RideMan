package service;

import bl.SessionUtil;
import dao.RouteDAO;
import entity.Route;
import org.hibernate.Session;

public class RouteService extends SessionUtil implements RouteDAO {
    @Override
    public void add(Route route) {
        openTransactionSession();

        Session session = getSession();
        session.save(route);

        closeTransactionSession();
    }
}
