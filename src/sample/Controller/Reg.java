package sample.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.DatabaseHandler;

import static sample.Const.show_res_reg;

public class Reg {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField regUpName;

    @FXML
    private PasswordField regUpPassword;

    @FXML
    private TextField regUpEmail;

    @FXML
    private Button setRegNew;

    @FXML
    private Button setBeck;

    @FXML
    private Text show_text;

    @FXML
    void initialize() {
        DatabaseHandler dbHandler = new DatabaseHandler();

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

        setRegNew.setOnAction(event -> {
            dbHandler.regNewUser(regUpName.getText(), regUpPassword.getText(), regUpEmail.getText(), "0");
            regUpName.setVisible(false);
            regUpPassword.setVisible(false);
            regUpEmail.setVisible(false);

            show_text.setText(show_res_reg);
            System.out.println(show_res_reg);


        });

    }
}
