package controllers;

import bl.TabUtil;
import entity.Employee;
import entity.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import service.EmployeeService;

import java.io.IOException;

public class LoginController {

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField loginField;

    public static User user;
    private TabUtil tab;

    public void login(MouseEvent mouseEvent) throws IOException {
        EmployeeService employeeService = new EmployeeService();
        Employee employee = employeeService.getEmployeeByLoginAndPass(loginField.getText(), passwordField.getText());

        if (employee == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Помилка входу!!!");
            alert.setHeaderText("Невірний логін або пароль");
            alert.setContentText("Введіть коректні дані");

            alert.showAndWait();
        } else {
            user = new User(employee);

            switch (employee.getEmployeeType().getEmployeeType()) {
                case "Менеджер": {
                    tab = new TabUtil("view/manager_scene.fxml", "Менеджер");
                    tab.openNewTab();
                    break;
                }
                case "Оператор": {
                    tab = new TabUtil("view/operator_scene.fxml", "Оператор");
                    tab.openNewTab();
                    break;
                }
            }
        }
    }
}
