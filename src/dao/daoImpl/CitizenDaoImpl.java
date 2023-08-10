package dao.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import config.sentinelErrorConfig.Exceptions.SentinelExceptions;
import config.sentinelErrorConfig.Exceptions.ExceptionsImpl.SentinelExceptionsImpl;
import dao.CitizenDao;
import database.JdbcConnection;
import model.Citizen;
import utils.orm.Orm;

public class CitizenDaoImpl implements CitizenDao {
    
    private final JdbcConnection jdbcConnection;
    private final SentinelExceptions sentinelExceptions;
    public CitizenDaoImpl(){
        this.jdbcConnection = new JdbcConnection();
        this.sentinelExceptions = new SentinelExceptionsImpl();
    }

    @Override
    public Citizen findByCitizenID(Long citizenID) {
        String query = "select * from Citizen where citizenshipNo = ?";
        try(PreparedStatement preparedStatement = jdbcConnection.connection.prepareStatement(query)){
            preparedStatement.setLong(1, citizenID);
            ResultSet resultSet = jdbcConnection.retrieve(preparedStatement);
            return(Optional.ofNullable(Orm.mapToCitizen(resultSet).get(0))
                .orElseThrow(() -> sentinelExceptions.CitizenNotFound(citizenID)));
        }
        catch(SQLException e){
            throw new RuntimeException("SQLException: " + e);
        }
    }

    @Override
    public List<Citizen> findAll() {
        String query = "select * from Citizen";
        List<Citizen> citizens = new ArrayList<>();
        try(PreparedStatement preparedStatement = jdbcConnection.connection.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE)){
            ResultSet resultSet = jdbcConnection.retrieve(preparedStatement);
            citizens = Orm.mapToCitizen(resultSet);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return(citizens);
    }

    @Override
    public List<Citizen> search(String keyword) {
        String query = "select * from Citizen where firstName = ? or middleName = ? or lastName = ? or contact = ? or citizenshipNo = ?";
        try(PreparedStatement preparedStatement = jdbcConnection.connection.prepareStatement(query)){
            preparedStatement.setString(1, keyword);
            preparedStatement.setString(2, keyword);
            preparedStatement.setString(3, keyword);
            preparedStatement.setString(4, keyword);
            preparedStatement.setString(5, keyword );
            ResultSet resultSet = jdbcConnection.retrieve(preparedStatement);
            return(Optional.ofNullable(Orm.mapToCitizen(resultSet))
                .orElseThrow(() -> sentinelExceptions.CitizenNotFound(Long.parseLong("1234"))));
        }
        catch(SQLException e){
            throw new RuntimeException("SQLException: " + e);
        }
    }
}

