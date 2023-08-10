package dao.daoImpl;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import config.sentinelErrorConfig.Exceptions.SentinelExceptions;
import config.sentinelErrorConfig.Exceptions.ExceptionsImpl.SentinelExceptionsImpl;
import dao.PoliceDao;
import database.JdbcConnection;

import model.Police;
import utils.orm.Orm;

public class PoliceDaoImpl implements PoliceDao {

    private final JdbcConnection jdbcConnection;
    private final SentinelExceptions sentinelExceptions;

    public PoliceDaoImpl() {
        this.jdbcConnection = new JdbcConnection();
        this.sentinelExceptions = new SentinelExceptionsImpl();
    }

    @Override
    public Police findById(Long policeID) {
        String query = "select * from Police full join Citizen on citizenshipID = Citizen.citizenshipNo " +
                "where policeID = ?";
        try (PreparedStatement preparedStatement = jdbcConnection.connection.prepareStatement(query,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            preparedStatement.setLong(1, policeID);
            ResultSet resultSet = jdbcConnection.retrieve(preparedStatement);
            try{
                return (Optional.ofNullable(Orm.mapToPolice(resultSet).get(0))
                        .orElseThrow(() -> sentinelExceptions.PoliceNotFound(policeID)));
            }catch(Exception e){
                System.out.println(sentinelExceptions.OICNotFound(policeID));
            }
        }
         catch (SQLException e) {
            throw new RuntimeException("SQLException: " + e);
        }
        return(null);
    }

    @Override
    public Long save(Police police) {
        String query = "insert into Police(department, position, citizenshipID, password) values(?,?,?,?)";
        try (PreparedStatement preparedStatement = jdbcConnection.connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, police.getDepartment());
            preparedStatement.setString(2, police.getPosition());
            preparedStatement.setLong(3, police.getCitizenshipID());
            preparedStatement.setString(4, police.getPassword());
            Long generatedKey = jdbcConnection.manipulate(preparedStatement);
            return (generatedKey != null ? generatedKey : null);
        } catch (SQLException e) {
            throw new RuntimeException("SQLException: " + e);
        }
    }

    @Override
    public List<Police> findAll() {
        String query = "select * from Police full join Citizen on citizenshipID = Citizen.CitizenshipNo";
        List<Police> policeOfficers = new ArrayList<>();
        try (PreparedStatement preparedStatement = jdbcConnection.connection.prepareStatement(query,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet resultSet = jdbcConnection.retrieve(preparedStatement);
            policeOfficers = Orm.mapToPolice(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (policeOfficers);
    }

    @Override
    public int update(Police police) {
        int status = 0;
        String query = "update Police set department = ?, position = ?, citizenshipID = ?, password =? where policeID = ?";
        try (PreparedStatement preparedStatement = jdbcConnection.connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, police.getDepartment());
            preparedStatement.setString(2, police.getPosition());
            preparedStatement.setLong(3, police.getCitizenshipID());
            preparedStatement.setString(4, police.getPassword());
            preparedStatement.setLong(5, police.getPoliceID());
            status = jdbcConnection.update(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (status);
    }

    @Override
    public Police findByPoliceID(Long policeID) {
        String query = "select * from Police where policeID = ?";
        try (PreparedStatement preparedStatement = jdbcConnection.connection.prepareStatement(query)) {
            preparedStatement.setLong(1, policeID);
            ResultSet resultSet = jdbcConnection.retrieve(preparedStatement);
            return (Optional.ofNullable(Orm.mapToPolice(resultSet).get(0))
                    .orElseThrow(() -> sentinelExceptions.PoliceNotFound(policeID)));
        } catch (SQLException e) {
            throw new RuntimeException("SQLException: " + e);
        }
    }

    @Override
    public List<Police> search(String keyword) {
        String query = "select * from Police where policeID = ? or department = ? or position = ? or citizenshipID = ?";
        try (PreparedStatement preparedStatement = jdbcConnection.connection.prepareStatement(query)) {
            preparedStatement.setString(1, keyword);
            preparedStatement.setString(2, keyword);
            preparedStatement.setString(3, keyword);
            preparedStatement.setString(4, keyword);
            ResultSet resultSet = jdbcConnection.retrieve(preparedStatement);
            return (Optional.ofNullable(Orm.mapToPolice(resultSet))
                    .orElseThrow(() -> sentinelExceptions.PoliceNotFound(Long.parseLong("1234"))));
        } catch (SQLException e) {
            throw new RuntimeException("SQLException: " + e);
        }

    }

}
