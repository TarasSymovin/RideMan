package controllers;

import entity.Driver;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
import javafx.util.StringConverter;
import javafx.util.converter.BigDecimalStringConverter;
import javafx.util.converter.IntegerStringConverter;
import service.DriverService;

import java.math.BigDecimal;
import java.util.ArrayList;
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

    private final DriverService driverService = new DriverService();
    private final ObservableList<Driver> drivers = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        initializeDriversTable();

        deleteDriverBtn.setOnAction(e -> {
            Driver selectedItem = driversTable.getSelectionModel().getSelectedItem();
            driversTable.getItems().remove(selectedItem);
            drivers.remove(selectedItem);
            driverService.remove(selectedItem);
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
                driversTable.setItems(filterList(drivers, newValue))
        );

        FilteredList<Driver> filteredData = new FilteredList<>(FXCollections.observableList(drivers));
        driversTable.setItems(filteredData);

        filterFieldDriver.textProperty().addListener((observable, oldValue, newValue) ->
                filteredData.setPredicate(createPredicate(newValue))
        );
    }

    private Predicate<Driver> createPredicate(String searchText){
        return driver -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return searchFindsOrder(driver, searchText);
        };
    }

    private boolean searchFindsOrder(Driver driver, String searchText){
        return (driver.getDriverSurname().toLowerCase().contains(searchText.toLowerCase())) ||
                (driver.getDriverName().toLowerCase().contains(searchText.toLowerCase()));
    }

    private ObservableList<Driver> filterList(List<Driver> list, String searchText){
        List<Driver> filteredList = new ArrayList<>();
        for (Driver driver : list){
            if(searchFindsOrder(driver, searchText)) filteredList.add(driver);
        }
        return FXCollections.observableList(filteredList);
    }

    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnDrivers) {
            labTitle.setText("Водії");
            pnTitle.setBackground(new Background(new BackgroundFill(Color.rgb(43, 63, 99), CornerRadii.EMPTY, Insets.EMPTY)));
            pnDrivers.toFront();
            loadDriverList();
        }
        else if (actionEvent.getSource() == btnManagers) {
            labTitle.setText("Менеджери");
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

    public void registerDriver(MouseEvent mouseEvent) { }

    public void registerOperator(MouseEvent mouseEvent) { }

    private void loadDriverList(){ }
}
