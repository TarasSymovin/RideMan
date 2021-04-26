package entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "City")
public class City {
    @Id
    @Column(name = "CityPostCode")
    private int cityPostCode;

    @Column(name = "Oblast")
    private String oblast;

    @Column(name = "Region")
    private String region;

    @Column(name = "City")
    private String city;

    @OneToMany(mappedBy = "city", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Department> departments;

    @OneToMany(mappedBy = "city", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Garage> garages;

    @OneToMany(mappedBy = "cityOfDeparture", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Route> departureCities;

    @OneToMany(mappedBy = "cityOfArrival", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Route> arrivalCities;

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public List<Garage> getGarages() {
        return garages;
    }

    public void setGarages(List<Garage> garages) {
        this.garages = garages;
    }

    public int getCityPostCode() {
        return cityPostCode;
    }

    public void setCityPostCode(int cityPostCode) {
        this.cityPostCode = cityPostCode;
    }

    public String getOblast() {
        return oblast;
    }

    public void setOblast(String oblast) {
        this.oblast = oblast;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
