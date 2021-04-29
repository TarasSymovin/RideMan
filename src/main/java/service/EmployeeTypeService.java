package service;

import bl.SessionUtil;
import dao.EmployeeDAO;
import dao.EmployeeTypeDAO;
import entity.Driver;
import entity.EmployeeType;
import org.hibernate.Session;

public class EmployeeTypeService extends SessionUtil implements EmployeeTypeDAO {
    @Override
    public EmployeeType getEmployeeType(int id) {
        openTransactionSession();

        try {
            Session session = getSession();
            return session.get(EmployeeType.class, id);
        }
        finally {
            closeTransactionSession();
        }
    }
}
