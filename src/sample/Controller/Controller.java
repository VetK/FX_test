package sample.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import sample.DatabaseHandler;



public class Controller extends DatabaseHandler {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField logUpName;

    @FXML
    private PasswordField logUpPassword;


    @FXML
    private Button setQuit;

    @FXML
    private Button setLog;

    @FXML
    private Button setReg;

    @FXML
    private ScrollPane show_scroll_avt;


    @FXML
    void initialize() {

        setLog.setOnAction(event -> {
            String loginText = logUpName.getText().trim();
            String loginPassword = logUpPassword.getText().trim();

            if (!loginText.equals("") && !loginPassword.equals("")){
                try {
                    loginUser(loginText, loginPassword);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
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

    private void loginUser(String loginText, String loginPassword) throws SQLException, ClassNotFoundException {

        PreparedStatement as2 = getDbConnection().prepareStatement("SELECT name, password, type FROM clients WHERE (name ='"+loginText+"' AND password = '"+loginPassword+"') ");
        ResultSet a2 = as2.executeQuery();

        if (a2.next()){
            logUpName.setVisible(false);
            logUpPassword.setVisible(false);
            setLog.setVisible(false);

            if(a2.getInt("Type")==1){
                show_scroll_avt.setContent(new Text("Вы админ! Вы можете видеть всех зарегистрированных пользователей:"));

                PreparedStatement as3 = getDbConnection().prepareStatement("SELECT * FROM clients");
                ResultSet a3 = as3.executeQuery();
                while(a3.next()){
                    for (int i = 1; i <= a3.getMetaData().getColumnCount(); i++){
                        System.out.print(a3.getString(i) + "\t");
                        //show_scroll_avt.setContent(new Text(a3.getString(i) + "\t"));
                    }
                    System.out.println();
                }
            }
            else{
                System.out.printf("Добро пожаловать, %s!", loginText);
            }
        }
        else{
            System.out.println("Проверьте логин и пароль, либо зарегистрируйтесь и попробуйте заново!");
        }

    }
}
