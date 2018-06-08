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
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login_Field;

    @FXML
    private PasswordField password_Field;

    @FXML
    private Button setQuit;

    @FXML
    private Button setLog;

    @FXML
    private Button setReg;

    @FXML
    void initialize() {

        setLog.setOnAction(event -> {
            String loginText = login_Field.getText().trim();
            String loginPassword = password_Field.getText().trim();

            if (!loginText.equals("") && !loginPassword.equals("")){
                loginUser(loginText, loginPassword);
            }
            else {
                System.out.println("Login and password is empty");
            }
        });



        setReg.setOnAction(event -> {
            setReg.getScene().getWindow().hide(); //запрятать активное окно

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/Samples/RegLE.fxml")); //указывает место расположения нужного нам файла
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));   // stage.setScene(new Scene(root));
            stage.show();
        });

        setQuit.setOnAction(event -> {
            setReg.getScene().getWindow().hide();
        });


    }

    private void loginUser(String loginText, String loginPassword) {

    }
}
