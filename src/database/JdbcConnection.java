package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import config.sentinelErrorConfig.SentinelError;

public class JdbcConnection {
    public Connection connection;
    Statement statement;
    ResultSet resultSet;
    Long generatedKey;

    public JdbcConnection(){
        try {
            String username = "root";
            String password = "20441988";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sentinel",username,password);
            if (connection !=null){
                System.out.println("#    Connected to Database. \n");
            }else {
                throw new SentinelError("Database Exception:  Error establishing a connection. \n Using url: "
                                        +connection.getMetaData().getURL());
            }
            statement = connection.createStatement();
        }catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public Long manipulate(PreparedStatement ps) {
        try {
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                ResultSet resultSet = ps.getGeneratedKeys();
                    while (resultSet.next()) {
                        generatedKey = resultSet.getLong(1);
                    }
                    return generatedKey;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet retrieve(PreparedStatement ps){
        try {
            resultSet = ps.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;

    }
    public void close() throws SQLException{
        connection.close();
    }
}
