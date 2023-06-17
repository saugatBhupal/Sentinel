package dao.daoImpl;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import config.sentinelErrorConfig.Exceptions.SentinelExceptions;
import config.sentinelErrorConfig.Exceptions.ExceptionsImpl.SentinelExceptionsImpl;
import dao.PoliceDao;
import database.JdbcConnection;
import model.Police;
import utils.builder.EntityBuilder;

public class PoliceDaoImpl implements PoliceDao {

    private final JdbcConnection jdbcConnection;
    private final SentinelExceptions sentinelExceptions;
    public PoliceDaoImpl(){
        this.jdbcConnection = new JdbcConnection();
        this.sentinelExceptions = new SentinelExceptionsImpl();
    }

    @Override
    public Police findById(Long policeID){
        String query = "select * from Police full join Citizen on citizenshipID = citizen.citizenshipNo " +
        "where policeID = ?";
        try(PreparedStatement preparedStatement = jdbcConnection.connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE)){
            preparedStatement.setLong(1, policeID);
            ResultSet resultSet = jdbcConnection.retrieve(preparedStatement);
            return(Optional.ofNullable(EntityBuilder.objectOfPolice(resultSet))
                .orElseThrow(() -> sentinelExceptions.PoliceNotFound(policeID)));
        }
        catch(SQLException e){
            throw new RuntimeException("SQLException: " + e);
        }
    }

    @Override
    public Long save(Police police) {
        String query = "insert into Police(department, position, citizenshipID, password) values(?,?,?,?)";
        try(PreparedStatement preparedStatement = jdbcConnection.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, police.getDepartment());
            preparedStatement.setString(2, police.getPosition());
            preparedStatement.setLong(3, police.getCitizenshipID());
            preparedStatement.setString(4, police.getPassword());
            Long generatedKey = jdbcConnection.manipulate(preparedStatement);
            return(generatedKey != null? generatedKey : null);
        }
        catch(SQLException e){
            throw new RuntimeException("SQLException: " + e);
        }
    }
    
}
