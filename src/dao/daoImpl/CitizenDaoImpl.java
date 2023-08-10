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
            return(Optional.ofNullable(EntityBuilder.objectOfCitizen(resultSet))
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
            while(resultSet.next()){
                resultSet.previous();
                citizens.add(EntityBuilder.objectOfCitizen(resultSet));
                resultSet.next();
            }
            System.out.println(citizens);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return(citizens);
    }

}

