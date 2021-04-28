package dao;

import entity.Employee;

public interface EmployeeDAO {
    Employee getEmployeeByLoginAndPass(String login, String pass);
}
