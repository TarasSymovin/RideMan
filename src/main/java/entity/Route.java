package entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Route")
public class Route {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "RouteId")
    private int routeId;

    @Column(name = "TimeOfDeparture")
    private Date timeOfDeparture;

    @Column(name = "TimeOfArrival")
    private Date timeOfArrival;

    @Column(name = "Distance")
    private int distance;

    @OneToMany(mappedBy = "route", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Trip> trips;

    @ManyToOne()
    @JoinColumn(name = "CityOfDeparture")
    private City cityOfDeparture;

    @ManyToOne()
    @JoinColumn(name = "CityOfArrival")
    private City cityOfArrival;

    public Date getTimeOfDeparture() {
        return timeOfDeparture;
    }

    public void setTimeOfDeparture(Date timeOfDeparture) {
        this.timeOfDeparture = timeOfDeparture;
    }

    public Date getTimeOfArrival() {
        return timeOfArrival;
    }

    public void setTimeOfArrival(Date timeOfArrival) {
        this.timeOfArrival = timeOfArrival;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public City getCityOfDeparture() {
        return cityOfDeparture;
    }

    public void setCityOfDeparture(City cityOfDeparture) {
        this.cityOfDeparture = cityOfDeparture;
    }

    public City getCityOfArrival() {
        return cityOfArrival;
    }

    public void setCityOfArrival(City cityOfArrival) {
        this.cityOfArrival = cityOfArrival;
    }
}
