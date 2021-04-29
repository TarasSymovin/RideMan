package service;

import bl.SessionUtil;
import dao.ClientDAO;
import entity.Car;
import entity.Client;
import org.hibernate.Session;

public class ClientService extends SessionUtil implements ClientDAO {
    @Override
    public void add(Client client) {
        openTransactionSession();

        Session session = getSession();
        session.save(client);

        closeTransactionSession();
    }

    @Override
    public Client getById(String number) {
        openTransactionSession();

        try {
            Session session = getSession();

            return session.get(Client.class, number);
        }
        finally {
            closeTransactionSession();
        }
    }

    @Override
    public void update(Client client) {
        openTransactionSession();

        Session session = getSession();
        session.update(client);

        closeTransactionSession();
    }
}
