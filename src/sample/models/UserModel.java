package sample.models;

import sample.java_beans.User;
import sample.services.database.DatabaseConnection;

import java.sql.*;

public class UserModel {

    private static Connection dbConnection;
    private static Statement statement;
    private static String name;
    private static String role;

    private static void getDbConnection() throws SQLException, ClassNotFoundException {
        Connection dbConnection = new DatabaseConnection().getDbConnection();
    }

    public static User find(String name, String password){
        String selectTableSQL = "SELECT * FROM users WHERE name = ? AND password = ? limit 1";

        try {
            Connection dbConnection = new DatabaseConnection().getDbConnection();

            PreparedStatement statement = dbConnection.prepareStatement(selectTableSQL);
            statement.setString(1, name);
            statement.setString(2, password);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                String userName = rs.getString("name");
                System.out.println("UserModel name: " + userName);
                String userPassword = rs.getString("password");
                System.out.println("UserModel password: " + userPassword);
                String userRole = rs.getString("role");
                System.out.println("UserModel role: " + userRole);

                User user = new User();
                user.setName(userName);
                user.setRole(userRole);

                return user;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
