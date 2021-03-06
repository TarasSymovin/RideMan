package entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Client")
public class Client {
    @Id
    @Column(name = "ClientPhone")
    private String clientPhone;

    @Column(name = "ClientName")
    private String clientName;

    @Column(name = "ClientSurname")
    private String clientSurname;

    @Column(name = "ClientEmail")
    private String clientEmail;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Trip> trips;

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    @Override
    public String toString() {
        return clientName + ": " + clientPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(clientPhone, client.clientPhone) && Objects.equals(clientName, client.clientName) && Objects.equals(clientSurname, client.clientSurname) && Objects.equals(clientEmail, client.clientEmail) && Objects.equals(trips, client.trips);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientPhone, clientName, clientSurname, clientEmail, trips);
    }
}
