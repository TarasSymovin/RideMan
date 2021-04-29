package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entity.Driver;
import entity.Employee;
import entity.Trip;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.converter.BigDecimalStringConverter;
import service.DriverService;
import service.EmployeeService;
import service.EmployeeTypeService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ManagerController {
    @FXML
    private Button btnDrivers;

    @FXML
    private Button btnManagers;

    @FXML
    private Button btnRegEmployee;

    @FXML
    private Button btnDelEmployee;

    @FXML
    private Button btnExit;

    @FXML
    private Pane pnTitle;

    @FXML
    private Pane menuPane;

    @FXML
    private Label labTitle;

    @FXML
    private GridPane pnRegEmployee;

    @FXML
    private GridPane paneDelEmployee;

    @FXML
    private GridPane pnManagers;

    @FXML
    private GridPane pnDrivers;

    @FXML
    private TableView<Driver> driversTable;

    @FXML
    private TableColumn<Driver, String> driverLicenseCol;

    @FXML
    private TableColumn<Driver, String> driverSurnameCol;

    @FXML
    private TableColumn<Driver, String> driverNameCol;

    @FXML
    private TableColumn<Driver, String> driverPhoneCol;

    @FXML
    private TableColumn<Driver, String> driverEmailCol;

    @FXML
    private TableColumn<Driver, BigDecimal> driverSalaryCol;

    @FXML
    private TextField filterFieldDriver;

    @FXML
    private Button deleteDriverBtn;

    @FXML
    private TableView<Employee> employeesTable;

    @FXML
    private TableColumn<Employee, String> employeeLoginCol;

    @FXML
    private TableColumn<Employee, String> employeePassCol;

    @FXML
    private TableColumn<Employee, String> employeeNameCol;

    @FXML
    private TableColumn<Employee, String> employeePhoneCol;

    @FXML
    private TableColumn<Employee, String> employeeEmailCol;

    @FXML
    private TableColumn<Employee, BigDecimal> employeeSalaryCol;

    @FXML
    private TextField filterFieldEmployee;

    @FXML
    private Button deleteEmployeeBtn;

    @FXML
    private JFXTextField driverLicenceField;

    @FXML
    private JFXTextField driverNameField;

    @FXML
    private JFXTextField driverSurnameField;

    @FXML
    private JFXTextField driverPhoneField;

    @FXML
    private JFXTextField driverEmailField;

    @FXML
    private JFXTextField DriverBankField;

    @FXML
    private JFXTextField driverSalaryField;

    @FXML
    private JFXButton addDriverBtn;

    @FXML
    private JFXTextField employeePassField;

    @FXML
    private JFXTextField employeeNameField;

    @FXML
    private JFXTextField employeeSurnameField;

    @FXML
    private JFXTextField employeePhoneField;

    @FXML
    private JFXTextField employeeEmailField;

    @FXML
    private JFXTextField employeeBankField;

    @FXML
    private JFXTextField employeeSalaryField;

    @FXML
    private JFXButton addEmployeeBtn;

    @FXML
    private JFXTextField employeeLoginField;

    private final DriverService driverService = new DriverService();
    private final EmployeeService employeeService  = new EmployeeService();
    private final EmployeeTypeService employeeTypeService = new EmployeeTypeService();
    private final ObservableList<Driver> drivers = FXCollections.observableArrayList();
    private final ObservableList<Employee> operators = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        initializeDriversTable();
        initializeOperatorsTable();

        deleteDriverBtn.setOnAction(e -> {
            Driver selectedItem = driversTable.getSelectionModel().getSelectedItem();
            deleteDriver(selectedItem);
        });

        deleteEmployeeBtn.setOnAction(e -> {
            Employee selectedItem = employeesTable.getSelectionModel().getSelectedItem();
            employeesTable.getItems().remove(selectedItem);
            operators.remove(selectedItem);
            employeeService.remove(selectedItem);
        });
    }

    private void initializeDriversTable(){
        drivers.setAll(driverService.getAllDrivers()
                .stream()
                .filter(x -> x.getDepartment().getDepartmentId() ==
                        LoginController.user.value.getDepartment().getDepartmentId())
                .collect(Collectors.toList()));

        driverLicenseCol.setCellValueFactory(new PropertyValueFactory<>("driverLicense"));
        driverNameCol.setCellValueFactory(new PropertyValueFactory<>("driverName"));
        driverSurnameCol.setCellValueFactory(new PropertyValueFactory<>("driverSurname"));
        driverEmailCol.setCellValueFactory(new PropertyValueFactory<>("driverEmail"));
        driverPhoneCol.setCellValueFactory(new PropertyValueFactory<>("driverPhone"));
        driverSalaryCol.setCellValueFactory(new PropertyValueFactory<>("driverSalary"));

        driversTable.getItems().setAll(drivers);

        driverLicenseCol.setCellFactory(TextFieldTableCell.forTableColumn());
        driverLicenseCol.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setDriverLicense(e.getNewValue());
            driverService.update(e.getTableView().getItems().get(e.getTablePosition().getRow()));
        });

        driverNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        driverNameCol.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setDriverName(e.getNewValue());
            driverService.update(e.getTableView().getItems().get(e.getTablePosition().getRow()));
        });

        driverSurnameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        driverSurnameCol.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setDriverSurname(e.getNewValue());
            driverService.update(e.getTableView().getItems().get(e.getTablePosition().getRow()));
        });

        driverPhoneCol.setCellFactory(TextFieldTableCell.forTableColumn());
        driverPhoneCol.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setDriverPhone(e.getNewValue());
            driverService.update(e.getTableView().getItems().get(e.getTablePosition().getRow()));
        });

        driverEmailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        driverEmailCol.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setDriverEmail(e.getNewValue());
            driverService.update(e.getTableView().getItems().get(e.getTablePosition().getRow()));
        });

        driverSalaryCol.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
        driverSalaryCol.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setDriverSalary(e.getNewValue());
            driverService.update(e.getTableView().getItems().get(e.getTablePosition().getRow()));
        });

        filterFieldDriver.textProperty().addListener((observable, oldValue, newValue) ->
                driversTable.setItems(filterDriverList(drivers, newValue))
        );

        FilteredList<Driver> filteredData = new FilteredList<>(FXCollections.observableList(drivers));
        driversTable.setItems(filteredData);

        filterFieldDriver.textProperty().addListener((observable, oldValue, newValue) ->
                filteredData.setPredicate(createDriverPredicate(newValue))
        );
    }

    private void deleteDriver(Driver driver){
        boolean isFree = true;

        for (Trip t : driver.getTrips()){
            if (t.getRoute().getTimeOfDeparture().compareTo(new Date()) > 0) {
                isFree = false;
                break;
            };
        }

        if (isFree){
            driversTable.getItems().remove(driver);
            drivers.remove(driver);
            driverService.remove(driver);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Успіх!!!");
            alert.setHeaderText("ОК");
            alert.setContentText("Водія виділено успішно");

            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Помилка видалення!!!");
            alert.setHeaderText("Водій має заплановані поїдки");
            alert.setContentText("Скасуйте поїздки або повторіть після їх виконання");

            alert.showAndWait();
        }
    }

    private Predicate<Driver> createDriverPredicate(String searchText){
        return driver -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return searchFindsDrivers(driver, searchText);
        };
    }

    private boolean searchFindsDrivers(Driver driver, String searchText){
        return (driver.getDriverSurname().toLowerCase().contains(searchText.toLowerCase())) ||
                (driver.getDriverName().toLowerCase().contains(searchText.toLowerCase())) ||
                (driver.getDriverEmail().toLowerCase().contains(searchText.toLowerCase()));
    }

    private ObservableList<Driver> filterDriverList(List<Driver> list, String searchText){
        List<Driver> filteredList = new ArrayList<>();
        for (Driver driver : list){
            if(searchFindsDrivers(driver, searchText)) filteredList.add(driver);
        }
        return FXCollections.observableList(filteredList);
    }

    private void initializeOperatorsTable(){
        operators.setAll(employeeService.getAllEmployees()
                .stream()
                .filter(x -> x.getDepartment().getDepartmentId() ==
                        LoginController.user.value.getDepartment().getDepartmentId()
                        && x.getEmployeeType().getEmployeeType().equals("Оператор"))
                .collect(Collectors.toList()));

        employeeLoginCol.setCellValueFactory(new PropertyValueFactory<>("employeeLogin"));
        employeePassCol.setCellValueFactory(new PropertyValueFactory<>("employeePassword"));
        employeeNameCol.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        employeePhoneCol.setCellValueFactory(new PropertyValueFactory<>("employeePhone"));
        employeeEmailCol.setCellValueFactory(new PropertyValueFactory<>("employeeEmail"));
        employeeSalaryCol.setCellValueFactory(new PropertyValueFactory<>("employeeSalary"));

        employeesTable.getItems().setAll(operators);

        employeeLoginCol.setCellFactory(TextFieldTableCell.forTableColumn());
        employeeLoginCol.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setEmployeeLogin(e.getNewValue());
            employeeService.update(e.getTableView().getItems().get(e.getTablePosition().getRow()));
        });

        employeePassCol.setCellFactory(TextFieldTableCell.forTableColumn());
        employeePassCol.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setEmployeePassword(e.getNewValue());
            employeeService.update(e.getTableView().getItems().get(e.getTablePosition().getRow()));
        });

        employeeNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        employeeNameCol.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setEmployeeName(e.getNewValue());
            employeeService.update(e.getTableView().getItems().get(e.getTablePosition().getRow()));
        });

        employeePhoneCol.setCellFactory(TextFieldTableCell.forTableColumn());
        employeePhoneCol.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setEmployeePhone(e.getNewValue());
            employeeService.update(e.getTableView().getItems().get(e.getTablePosition().getRow()));
        });

        employeeEmailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        employeeEmailCol.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setEmployeeEmail(e.getNewValue());
            employeeService.update(e.getTableView().getItems().get(e.getTablePosition().getRow()));
        });

        employeeSalaryCol.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
        employeeSalaryCol.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setEmployeeSalary(e.getNewValue());
            employeeService.update(e.getTableView().getItems().get(e.getTablePosition().getRow()));
        });

        filterFieldEmployee.textProperty().addListener((observable, oldValue, newValue) ->
                employeesTable.setItems(filterOperatorList(operators, newValue))
        );

        FilteredList<Employee> filteredData = new FilteredList<>(FXCollections.observableList(operators));
        employeesTable.setItems(filteredData);

        filterFieldEmployee.textProperty().addListener((observable, oldValue, newValue) ->
                filteredData.setPredicate(createOperatorPredicate(newValue))
        );
    }

    private Predicate<Employee> createOperatorPredicate(String searchText){
        return employee -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return searchFindsOperators(employee, searchText);
        };
    }

    private ObservableList<Employee> filterOperatorList(List<Employee> list, String searchText){
        List<Employee> filteredList = new ArrayList<>();
        for (Employee employee : list){
            if(searchFindsOperators(employee, searchText)) filteredList.add(employee);
        }
        return FXCollections.observableList(filteredList);
    }

    private boolean searchFindsOperators(Employee employee, String searchText){
        return (employee.getEmployeeName().toLowerCase().contains(searchText.toLowerCase())) ||
                (employee.getEmployeeEmail().toLowerCase().contains(searchText.toLowerCase())) ||
                (employee.getEmployeeLogin().toLowerCase().contains(searchText.toLowerCase()));
    }

    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnDrivers) {
            labTitle.setText("Водії");
            pnTitle.setBackground(new Background(new BackgroundFill(Color.rgb(43, 63, 99), CornerRadii.EMPTY, Insets.EMPTY)));
            pnDrivers.toFront();
        }
        else if (actionEvent.getSource() == btnManagers) {
            labTitle.setText("Оператори");
            pnTitle.setBackground(new Background(new BackgroundFill(Color.rgb(113, 86, 221), CornerRadii.EMPTY, Insets.EMPTY)));
            pnManagers.toFront();
        }
        else if (actionEvent.getSource() == btnRegEmployee) {
            labTitle.setText("Оформлення працівників");
            pnTitle.setBackground(new Background(new BackgroundFill(Color.rgb(104, 221, 116), CornerRadii.EMPTY, Insets.EMPTY)));
            pnRegEmployee.toFront();
        }
        else if (actionEvent.getSource() == btnDelEmployee) {
            labTitle.setText("Надіслати сповіщення");
            pnTitle.setBackground(new Background(new BackgroundFill(Color.rgb(104, 221, 116), CornerRadii.EMPTY, Insets.EMPTY)));
            paneDelEmployee.toFront();
        }
    }

    @FXML
    void addDriver(MouseEvent event) {
        Driver driver = new Driver();
        driver.setDriverLicense(driverLicenceField.getText());
        driver.setDriverName(driverNameField.getText());
        driver.setDriverSurname(driverSurnameField.getText());
        driver.setDriverEmail(driverEmailField.getText());
        driver.setDriverPhone(driverPhoneField.getText());
        driver.setDriverBankCard(DriverBankField.getText());
        driver.setDepartment(LoginController.user.value.getDepartment());
        driver.setDriverSalary(BigDecimal.valueOf(Long.parseLong(driverSalaryField.getText())));


        if (driverService.findById(driver.getDriverLicense()) != null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Помилка реєстрації!!!");
            alert.setHeaderText("Такий водій вже існує");
            alert.setContentText("Введіть інші дані");

            alert.showAndWait();
        } else {
            driverService.add(driver);
            drivers.add(driver);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Успіх!!!");
            alert.setHeaderText("ОК");
            alert.setContentText("Водія додано успішно");

            alert.showAndWait();
        }
    }

    @FXML
    void addEmployee(MouseEvent event) {
        Employee employee = new Employee();
        employee.setEmployeeLogin(employeeLoginField.getText());
        employee.setEmployeePassword(employeePassField.getText());
        employee.setEmployeeSurname(employeeSurnameField.getText());
        employee.setEmployeeName(employeeNameField.getText());
        employee.setEmployeeEmail(employeeEmailField.getText());
        employee.setEmployeePhone(employeePhoneField.getText());
        employee.setEmployeeBankCard(employeeBankField.getText());
        employee.setDepartment(LoginController.user.value.getDepartment());
        employee.setEmployeeSalary(BigDecimal.valueOf(Long.parseLong(employeeSalaryField.getText())));
        employee.setEmployeeType(employeeTypeService.getEmployeeType(3));


        if (employeeService.findById(employee.getEmployeeLogin()) != null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Помилка реєстрації!!!");
            alert.setHeaderText("Такий оператор вже існує");
            alert.setContentText("Введіть інші дані");

            alert.showAndWait();
        } else {
            employeeService.add(employee);
            operators.add(employee);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Успіх!!!");
            alert.setHeaderText("ОК");
            alert.setContentText("Оператора додано успішно");

            alert.showAndWait();
        }
    }

    @FXML
    void exit(MouseEvent event) {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }
}
