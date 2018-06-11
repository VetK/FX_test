package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.ResultSet;

import static sample.Const.show_res_reg;


public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException{    //ClassNotFoundException
        String connectionUrl = "jdbc:mysql://" + connectionHost + ":" +
                connectionPort + "/" + dbName +"?useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";

        dbConnection = DriverManager.getConnection(connectionUrl, userName, password_db);

        return dbConnection;
    }

    public void regNewUser(String name, String password, String email, String type){
        String insert = "INSERT INTO " + Const.USER_TABLE + "("+ Const.USERS_NAME + ", " + Const.USER_PASSWORD + ", " +
                Const.USER_EMAIL + ", " + Const.USER_TYPE + ")" + "VALUES(?, ?, ?, ?)";

        try {

            PreparedStatement res = getDbConnection().prepareStatement("Select * FROM clients WHERE name = '"+name+"' ");
            ResultSet a = res.executeQuery();
            if(a.next()) {
                show_res_reg = "Такой пользователь уже существует, попробуйте снова!";
            }
            else{
                PreparedStatement prSt = getDbConnection().prepareStatement(insert);
                prSt.setString(1, name);
                prSt.setString(2, password);
                prSt.setString(3, email);
                prSt.setString(4, type);
                prSt.executeUpdate();
                show_res_reg = "Вы успешно зарегистрировались.";
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
