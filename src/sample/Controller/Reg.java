package sample.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Reg {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button setRegNew;

    @FXML
    private Button setBeck;

    @FXML
    void initialize() {

        setBeck.setOnAction(event -> {
            setBeck.getScene().getWindow().hide(); //запрятать активное окно

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/Samples/sample.fxml")); //указывает место расположения нужного нам файла
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            //stage.showAndWait();
            stage.show();
        });

    }
}
