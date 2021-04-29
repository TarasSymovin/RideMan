package controllers;

import com.jfoenix.controls.*;
import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import service.CityService;
import service.ClientService;
import service.RouteService;
import service.TripService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class OperatorController {
    @FXML
    private GridPane pnTrips;

    @FXML
    private GridPane pnCreateTrip;

    @FXML
    private TableView<Trip> tripsTable;

    @FXML
    private TableColumn<Trip, Driver> driverCol;

    @FXML
    private TableColumn<Trip, Car> carCol;

    @FXML
    private TableColumn<Trip, Client> clientCol;

    @FXML
    private TableColumn<Trip, BigDecimal> tripPriceCol;

    @FXML
    private TableColumn<Trip, Route> routeCol;

    @FXML
    private TextField filterFieldTrip;

    @FXML
    private Button deleteTripBtn;

    @FXML
    private JFXTextField clientNameField;

    @FXML
    private JFXTextField clientSurnameField;

    @FXML
    private JFXTextField clientPhoneField;

    @FXML
    private JFXTextField clientEmailField;

    @FXML
    private JFXButton addClientAndNext;

    @FXML
    private Pane pnTitle;

    @FXML
    private Label labTitle;

    @FXML
    private Button btnCreateTrip;

    @FXML
    private Button btnTrips;

    @FXML
    private Tab routeTab;

    @FXML
    private TabPane tabPane;

    @FXML
    private JFXTextField cityOfDepartureField;

    @FXML
    private JFXDatePicker dateOfDepartureField;

    @FXML
    private JFXTimePicker timeOfDepartureField;

    @FXML
    private JFXTextField cityOfArrivalField;

    @FXML
    private JFXDatePicker dateOfArrivalField;

    @FXML
    private JFXTimePicker timeOfArrivalField;

    @FXML
    private JFXTextField tripDistanceField;

    @FXML
    private JFXComboBox<Driver> driverCombo;

    @FXML
    private JFXComboBox<Car> carCombo;

    private final ObservableList<Trip> trips = FXCollections.observableArrayList();
    private final TripService tripService = new TripService();
    private final ClientService clientService = new ClientService();
    private final CityService cityService = new CityService();
    private final RouteService routeService = new RouteService();

    @FXML
    void initialize() {
        initializeTripsTable();

        deleteTripBtn.setOnAction(e -> {
            Trip selectedItem = tripsTable.getSelectionModel().getSelectedItem();
            tripsTable.getItems().remove(selectedItem);
            trips.remove(selectedItem);
            tripService.remove(selectedItem);
        });
    }

    private void initializeTripsTable(){
        trips.setAll(tripService.getAllTrips()
                .stream()
                .filter(x -> x.getDriver().getDepartment().getDepartmentId() ==
                        LoginController.user.value.getDepartment().getDepartmentId())
                .collect(Collectors.toList()));

        driverCol.setCellValueFactory(new PropertyValueFactory<>("driver"));
        carCol.setCellValueFactory(new PropertyValueFactory<>("car"));
        clientCol.setCellValueFactory(new PropertyValueFactory<>("client"));
        tripPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        routeCol.setCellValueFactory(new PropertyValueFactory<>("route"));

        tripsTable.getItems().setAll(trips);

        filterFieldTrip.textProperty().addListener((observable, oldValue, newValue) ->
                tripsTable.setItems(filterTripList(trips, newValue))
        );

        FilteredList<Trip> filteredData = new FilteredList<>(FXCollections.observableList(trips));
        tripsTable.setItems(filteredData);

        filterFieldTrip.textProperty().addListener((observable, oldValue, newValue) ->
                filteredData.setPredicate(createTripPredicate(newValue))
        );
    }

    private Predicate<Trip> createTripPredicate(String searchText){
        return trip -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return searchFindsTrips(trip, searchText);
        };
    }

    private boolean searchFindsTrips(Trip trip, String searchText){
        return (trip.getDriver().toString().toLowerCase().contains(searchText.toLowerCase())) ||
                (trip.getRoute().toString().toLowerCase().contains(searchText.toLowerCase())) ||
                (trip.getClient().toString().toLowerCase().contains(searchText.toLowerCase()));
    }

    private ObservableList<Trip> filterTripList(List<Trip> list, String searchText){
        List<Trip> filteredList = new ArrayList<>();
        for (Trip trip : list){
            if(searchFindsTrips(trip, searchText)) filteredList.add(trip);
        }
        return FXCollections.observableList(filteredList);
    }

    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnTrips) {
            labTitle.setText("Поїздки");
            pnTitle.setBackground(new Background(new BackgroundFill(Color.rgb(43, 63, 99), CornerRadii.EMPTY, Insets.EMPTY)));
            pnTrips.toFront();
        }
        else if (actionEvent.getSource() == btnCreateTrip) {
            labTitle.setText("Створення поїздки");
            pnTitle.setBackground(new Background(new BackgroundFill(Color.rgb(104, 221, 116), CornerRadii.EMPTY, Insets.EMPTY)));
            pnCreateTrip.toFront();
        }
    }

    public void exit(MouseEvent mouseEvent) {
        Stage stage = (Stage) btnTrips.getScene().getWindow();
        stage.close();
    }

    @FXML
    void addClient(MouseEvent event) {
        Client client = new Client();
        client.setClientPhone(clientPhoneField.getText());
        client.setClientName(clientNameField.getText());
        client.setClientSurname(clientSurnameField.getText());
        client.setClientEmail(clientEmailField.getText());

        Client tempClient = clientService.getById(clientPhoneField.getText());

        if (tempClient != null && tempClient.equals(client)){
            tabPane.getSelectionModel().select(routeTab);
        }
        else if (tempClient != null && !client.equals(tempClient)){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Такий клієнт вже існує");
            alert.setHeaderText("Такий клієнт вже міститься в базі даних.");
            alert.setContentText(" Оновити дані?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                clientService.update(client);
            } else {
                tabPane.getSelectionModel().select(routeTab);
            }
        }
        else {
            clientService.add(client);
            tabPane.getSelectionModel().select(routeTab);
        }
    }

    @FXML
    void addRoute(MouseEvent event) {
        LocalDate localDateDeparture = dateOfDepartureField.getValue();
        LocalTime localTimeDeparture = timeOfDepartureField.getValue();

        LocalDate localDateArrival = dateOfArrivalField.getValue();
        LocalTime localTimeArrival = timeOfArrivalField.getValue();

        Date dateOfDeparture = new Date(localDateDeparture.getYear() - 1900, localDateDeparture.getMonth().getValue(),
                localDateDeparture.getDayOfMonth(), localTimeDeparture.getHour(),
                localTimeDeparture.getMinute(), localTimeDeparture.getSecond());

        Date dateOfArrival = new Date(localDateArrival.getYear() - 1900, localDateArrival.getMonth().getValue(),
                localDateArrival.getDayOfMonth(), localTimeArrival.getHour(),
                localTimeArrival.getMinute(), localTimeArrival.getSecond());

        Route route = new Route();
        route.setCityOfArrival(cityService.getByCity(cityOfDepartureField.getText()));
        route.setCityOfDeparture(cityService.getByCity(cityOfArrivalField.getText()));
        route.setTimeOfDeparture(dateOfDeparture);
        route.setTimeOfArrival(dateOfArrival);
        route.setDistance(Integer.parseInt(tripDistanceField.getText()));

        routeService.add(route);
    }
}

