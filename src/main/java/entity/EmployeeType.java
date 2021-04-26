package entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "EmployeeType")
public class EmployeeType {
    @Id
    @Column(name = "EmployeeTypeId")
    private int employeeTypeId;

    @Column(name = "EmployeeType")
    private String employeeType;

    @OneToMany(mappedBy = "employeeType", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Employee> employees;

    public int getEmployeeTypeId() {
        return employeeTypeId;
    }

    public void setEmployeeTypeId(int employeeTypeId) {
        this.employeeTypeId = employeeTypeId;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }
}
