package dao;

import entity.Driver;
import entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    Employee getEmployeeByLoginAndPass(String login, String pass);
    List<Employee> getAllEmployees();
    void remove(Employee employee);
    void update(Employee employee);
    Employee findById(String login);
    void add(Employee employee);
}
