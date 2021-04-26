package entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Departament")
public class Department {
    @Id
    @Column(name = "DepartamentId")
    private int departmentId;

    @Column(name = "DepartamentName")
    private String departmentName;

    @Column(name = "DepartamentEmail")
    private String departmentEmail;

    @Column(name = "DepartamentPhone")
    private String departmentPhone;

    @ManyToOne()
    @JoinColumn(name = "DepartamentCity")
    private City city;

    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Garage> garages;

    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Driver> drivers;

    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Employee> employees;

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentEmail() {
        return departmentEmail;
    }

    public void setDepartmentEmail(String departmentEmail) {
        this.departmentEmail = departmentEmail;
    }

    public String getDepartmentPhone() {
        return departmentPhone;
    }

    public void setDepartmentPhone(String departmentPhone) {
        this.departmentPhone = departmentPhone;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Garage> getGarages() {
        return garages;
    }

    public void setGarages(List<Garage> garages) {
        this.garages = garages;
    }
}
