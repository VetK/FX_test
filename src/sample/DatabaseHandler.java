package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler extends Configs{
    //Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException{
        String connectionUrl = "jdbc:mysql://" + connectionHost + ":" +
                connectionPort + "/" + dbName;

        Connection dbConnection = DriverManager.getConnection(connectionUrl, userName, password_db);

        return dbConnection;
    }

    public void regNewUser(String name, String password, String Email){

    }


}
