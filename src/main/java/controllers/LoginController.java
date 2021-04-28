package controllers;

import entity.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import service.EmployeeService;

public class LoginController {

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField loginField;

    public void login(MouseEvent mouseEvent) {
        EmployeeService employeeService = new EmployeeService();
        Employee employee = employeeService.getEmployeeByLoginAndPass(loginField.getText(), passwordField.getText());

        if (employee == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Помилка входу!!!");
            alert.setHeaderText("Невірний логін або пароль");
            alert.setContentText("Введіть коректні дані");

            alert.showAndWait();
        }
        else {
            System.out.println(employee.getEmployeeLogin() + " " + employee.getEmployeePassword());
        }
    }
}
