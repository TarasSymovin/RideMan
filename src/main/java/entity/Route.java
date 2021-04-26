package entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Route")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Trip")
    private int tripId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "Trip")
    private Trip trip;

    @Column(name = "TimeOfDeparture")
    private Date timeOfDeparture;

    @Column(name = "TimeOfArrival")
    private Date timeOfArrival;

    @Column(name = "Price")
    private BigDecimal price;

    @Column(name = "Distance")
    private int distance;

    @Column(name = "Cost")
    private BigDecimal cost;

    @ManyToOne()
    @JoinColumn(name = "CityOfDeparture")
    private City cityOfDeparture;

    @ManyToOne()
    @JoinColumn(name = "CityOfArrival")
    private City cityOfArrival;

}
