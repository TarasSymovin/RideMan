package entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Garage")
public class Garage {
    @Id
    @Column(name = "GarageId")
    private int garageId;

    @Column(name = "VehiclesNumber")
    private int vehiclesNumber;

    @ManyToOne()
    @JoinColumn(name = "GarageCity")
    private City city;

    @ManyToOne()
    @JoinColumn(name = "DepartamentId")
    private Department department;

    @OneToMany(mappedBy = "garage", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Car> cars;

    public int getGarageId() {
        return garageId;
    }

    public void setGarageId(int garageId) {
        this.garageId = garageId;
    }

    public int getVehiclesNumber() {
        return vehiclesNumber;
    }

    public void setVehiclesNumber(int vehiclesNumber) {
        this.vehiclesNumber = vehiclesNumber;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
