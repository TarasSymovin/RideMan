package entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Trip")
public class Trip {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "TripId")
    private int tripId;

    @ManyToOne()
    @JoinColumn(name = "Route")
    private Route route;

    @ManyToOne()
    @JoinColumn(name = "CarNumber")
    private Car car;

    @ManyToOne()
    @JoinColumn(name = "DriverLicense")
    private Driver driver;

    @ManyToOne()
    @JoinColumn(name = "Client")
    private Client client;

    @Column(name = "Cost")
    private BigDecimal cost;

    @Column(name = "Price")
    private BigDecimal price;

    public Client getClient() {
        return client;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
