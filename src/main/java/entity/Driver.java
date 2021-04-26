package entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Driver")
public class Driver {
    @Id
    @Column(name = "DriverLicense")
    private String driverLicense;

    @Column(name = "DriverName")
    private String driverName;

    @Column(name = "DriverSurname")
    private String driverSurname;

    @Column(name = "DriverPhone")
    private String driverPhone;

    @Column(name = "DriverEmail")
    private String driverEmail;

    @Column(name = "DriverSalary")
    private BigDecimal driverSalary;

    @Column(name = "DriverBankCard")
    private long driverBankCard;

    @ManyToOne()
    @JoinColumn(name = "Departament")
    private Department department;

    @OneToMany(mappedBy = "driver", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Trip> trips;

    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverSurname() {
        return driverSurname;
    }

    public void setDriverSurname(String driverSurname) {
        this.driverSurname = driverSurname;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public String getDriverEmail() {
        return driverEmail;
    }

    public void setDriverEmail(String driverEmail) {
        this.driverEmail = driverEmail;
    }

    public BigDecimal getDriverSalary() {
        return driverSalary;
    }

    public void setDriverSalary(BigDecimal driverSalary) {
        this.driverSalary = driverSalary;
    }

    public long getDriverBankCard() {
        return driverBankCard;
    }

    public void setDriverBankCard(long driverBankCard) {
        this.driverBankCard = driverBankCard;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
