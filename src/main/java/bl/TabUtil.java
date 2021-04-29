package bl;

import controllers.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class TabUtil {

    private String path;
    private String title;

    public TabUtil(String path, String title){
        this.path = path;
        this.title = title;
    }

    public void openNewTab() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getClassLoader().getResource(path));

        Parent root;
        root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOpacity(1);
        stage.setTitle(title);
        stage.setScene(new Scene(root, 1150, 600));
        stage.showAndWait();
    }
}
