import entity.Car;
import entity.CarType;
import org.hibernate.Session;
import service.CarService;
import service.CarTypeService;

import java.util.List;

public class Demo {
    public static void main(String[] args) {
//        CarType carType = new CarType();
//        carType.setCarTypeId(6);
//        carType.setCarType("Бус");
//
//        CarTypeService carTypeService = new CarTypeService();
//        List<CarType> carTypes = carTypeService.getAllTypes();
//
//        System.out.println(carTypes.get(0).getCarType());

//        carTypeService.add(carType);

        CarService carService = new CarService();
        List<Car> cars = carService.getAllCars();

        for (Car car : cars){
            System.out.println(car.getCarNumber() + " " + car.getCarModel() + " "
                    + car.getCarType().getCarType() + " " + car.getNumberOfSeats() + " "
                    + car.getGarage().getCity().getCity() + " "
                    + car.getGarage().getDepartment().getDepartmentPhone());
        }
    }
}
