package entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Car")
public class Car {
    @Id
    @Column(name = "CarNumber")
    private String carNumber;

    @Column(name = "CarModel")
    private String carModel;

    @Column(name = "NumberOfSeats")
    private int numberOfSeats;

    @Column(name = "FuelConsumption")
    private int fuelConsumption;

    @ManyToOne()
    @JoinColumn(name = "CarType")
    private CarType carType;

    @ManyToOne()
    @JoinColumn(name = "Garage")
    private Garage garage;

    @OneToMany(mappedBy = "car", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Trip> trips;


    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public int getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(int fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public Garage getGarage() {
        return garage;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }
}
