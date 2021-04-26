package entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Employee")
public class Employee {
    @Id
    @Column(name = "EmployeeLogin")
    private String employeeLogin;

    @Column(name = "EmployePassword")
    private String employeePassword;

    @Column(name = "EmployeeName")
    private String employeeName;

    @Column(name = "EmployeeSurname")
    private String employeeSurname;

    @Column(name = "EmployeePhone")
    private String employeePhone;

    @Column(name = "EmployeeEmail")
    private String employeeEmail;

    @Column(name = "EmployeeSalary")
    private BigDecimal employeeSalary;

    @Column(name = "EmployeeBankCard")
    private long employeeBankCard;

    @ManyToOne()
    @JoinColumn(name = "EmployeeType")
    private EmployeeType employeeType;

    @ManyToOne()
    @JoinColumn(name = "DepartamentId")
    private Department department;

    public String getEmployeeLogin() {
        return employeeLogin;
    }

    public void setEmployeeLogin(String employeeLogin) {
        this.employeeLogin = employeeLogin;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeSurname() {
        return employeeSurname;
    }

    public void setEmployeeSurname(String employeeSurname) {
        this.employeeSurname = employeeSurname;
    }

    public String getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public BigDecimal getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(BigDecimal employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public long getEmployeeBankCard() {
        return employeeBankCard;
    }

    public void setEmployeeBankCard(long employeeBankCard) {
        this.employeeBankCard = employeeBankCard;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}