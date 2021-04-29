package service;

import bl.SessionUtil;
import dao.EmployeeDAO;
import entity.Department;
import entity.Driver;
import entity.Employee;
import org.hibernate.Criteria;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
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

    @Override
    public List<Employee> getAllEmployees() {
        openTransactionSession();

        try {
            Session session = getSession();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
            Root<Employee> rootEntry = cq.from(Employee.class);
            CriteriaQuery<Employee> all = cq.select(rootEntry);

            TypedQuery<Employee> allQuery = session.createQuery(all);

            return allQuery.getResultList();
        }
        finally {
            closeTransactionSession();
        }
    }

    @Override
    public void remove(Employee employee) {
        openTransactionSession();

        Session session = getSession();
        session.delete(employee);

        closeTransactionSession();
    }

    @Override
    public void update(Employee employee) {
        openTransactionSession();

        Session session = getSession();
        session.update(employee);

        closeTransactionSession();
    }

    @Override
    public Employee findById(String login) {
        openTransactionSession();

        try {
            Session session = getSession();
            return session.get(Employee.class, login);
        }
        finally {
            closeTransactionSession();
        }
    }

    @Override
    public void add(Employee employee) {
        openTransactionSession();

        Session session = getSession();
        session.save(employee);

        closeTransactionSession();
    }
}
