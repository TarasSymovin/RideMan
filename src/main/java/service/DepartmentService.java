package service;

import bl.SessionUtil;
import dao.DepartmentDAO;
import entity.Car;
import entity.Department;
import org.hibernate.Session;

public class DepartmentService extends SessionUtil implements DepartmentDAO {
    @Override
    public Department getDepartmentById(int id) {
        openTransactionSession();

        try {
            Session session = getSession();

            return session.get(Department.class, id);
        }
        finally {
            closeTransactionSession();
        }
    }
}
