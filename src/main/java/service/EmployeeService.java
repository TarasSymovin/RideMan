package service;

import bl.SessionUtil;
import dao.EmployeeDAO;
import entity.Department;
import entity.Employee;
import org.hibernate.Criteria;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class EmployeeService extends SessionUtil implements EmployeeDAO {
    @Override
    public Employee getEmployeeByLoginAndPass(String login, String pass) {
        openTransactionSession();

        try {
            Session session = getSession();

            Employee employee = session.get(Employee.class, login);

            if (employee != null && employee.getEmployeePassword().equals(pass)){
                return employee;
            }
            else {
                return null;
            }
        }
        finally {
            closeTransactionSession();
        }
    }
}
