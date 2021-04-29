import entity.*;
import org.hibernate.Session;
import service.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
//        CarService carService = new CarService();
//        DepartmentService departmentService = new DepartmentService();
//        DriverService driverService = new DriverService();
        CityService cityService = new CityService();
//        ClientService clientService = new ClientService();
//        TripService tripService = new TripService();
//        RouteService routeService = new RouteService();
//
//        Driver driver = new Driver();
//        driver.setDriverLicense("000025648");
//        driver.setDriverEmail("driver@gmail.com");
//        driver.setDriverName("John");
//        driver.setDriverSurname("Klinton");
//        driver.setDriverBankCard("1234567812345678");
//        driver.setDriverPhone("380681234987");
//        driver.setDepartment(departmentService.getDepartmentById(1));
//
////        driverService.add(driver);
//
//        Route route = new Route();
//
//        route.setCityOfArrival(cityService.getCityById(76002));
//        route.setCityOfDeparture(cityService.getCityById(79002));
//        route.setTimeOfDeparture(new Date(2021-1900, Calendar.MAY, 28, 8, 0, 0));
//        route.setTimeOfArrival(new Date(2021-1900, Calendar.MAY, 28, 11, 0, 0));
//        route.setDistance(150);
//
//
//        Trip trip = new Trip();
//
//        trip.setCar(carService.getByCarNumber("BC7070AA"));
//        trip.setClient(clientService.getById("380684279209"));
//        trip.setDriver(driver);
//        trip.setPrice(BigDecimal.valueOf(600));
//        trip.setCost(BigDecimal.valueOf(200));
//        trip.setRoute(route);
//
//        routeService.add(route);
//        tripService.add(trip);
    }
}
