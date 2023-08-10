package dao.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.sentinelErrorConfig.Exceptions.SentinelExceptions;
import config.sentinelErrorConfig.Exceptions.ExceptionsImpl.SentinelExceptionsImpl;
import dao.OICDao;
import database.JdbcConnection;
import model.OIC;
import utils.orm.Orm;

public class OICDaoImpl implements OICDao {

    private final JdbcConnection jdbcConnection;
    private final SentinelExceptions sentinelExceptions;

    public OICDaoImpl(){
        this.jdbcConnection = new JdbcConnection();
        this.sentinelExceptions = new SentinelExceptionsImpl();
    }
    
    @Override
    public OIC findByPoliceID(Long policeID) {
        String query = "Select * from OIC where policeID = ?";
        try(PreparedStatement preparedStatement = jdbcConnection.connection.prepareStatement(query,
            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)){
            preparedStatement.setLong(1, policeID);
            ResultSet resultSet = jdbcConnection.retrieve(preparedStatement);
            try{
                return (Orm.mapToOIC(resultSet).get(0));
            }
            catch(Exception e){
                System.out.println(sentinelExceptions.OICNotFound(policeID));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("SQLException: " + e);
        }
    return(null);
    }
    
}
