package sample.services.database;

import sample.configs.DBConfig;
import sample.java_beans.BDConnectionStringBean;
import sample.java_beans.DataBaseConfigBean;

import java.sql.*;

public class DatabaseConnection extends DBConfig implements TestConnectionTrait{

    private BDConnectionStringBean connectionString;
    private DataBaseConfigBean dataBaseConfig;
    private Connection dbConnection;
    private static Statement statement;

    /**
     * Constructor
     */
    public DatabaseConnection(){
        this.dataBaseConfig = new DataBaseConfigBean();
        this.dataBaseConfig.setDbHost(this.dbHost);
        this.dataBaseConfig.setDbName(this.dbName);
        this.dataBaseConfig.setDbPort(this.dbPort);
        this.dataBaseConfig.setDbUser(this.dbUser);
        this.dataBaseConfig.setDbPassword(this.dbPassword);

        this.connectionString = new BDConnectionStringBean();
        this.connectionString.setConnectionString("jdbc:mysql://"
                + this.dataBaseConfig.getDbHost() + ":"
                + this.dataBaseConfig.getDbPort() + "/"
                + this.dataBaseConfig.getDbName()
        );
    }

    private void setDbConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        this.dbConnection = DriverManager.getConnection(
                this.connectionString.getConnectionString(),
                this.dataBaseConfig.getDbUser(),
                this.dataBaseConfig.getDbPassword()
        );
    }

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        if(dbConnection == null){
            this.setDbConnection();
        }
        return dbConnection;
    }


    @Override
    public void testConnection() {
        String selectTableSQL = "SELECT 2 + 2";

        try {
            Connection connection = this.getDbConnection();
            this.statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(selectTableSQL);

            while (rs.next()) {
                String data = rs.getString(1);
                System.out.println("From DB: " + selectTableSQL + " = " + data);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
