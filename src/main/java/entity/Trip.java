package entity;

import javax.persistence.*;

@Entity
@Table(name = "Trip")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TripId")
    private int tripId;

    @OneToOne(mappedBy = "trip", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
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
}
