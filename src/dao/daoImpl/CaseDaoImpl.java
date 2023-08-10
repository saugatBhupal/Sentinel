package dao.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import config.sentinelErrorConfig.Exceptions.SentinelExceptions;
import config.sentinelErrorConfig.Exceptions.ExceptionsImpl.SentinelExceptionsImpl;
import dao.CaseDao;
import database.JdbcConnection;
import model.Case;
import model.Log;
import utils.orm.Orm;

public class CaseDaoImpl implements CaseDao {

    private final JdbcConnection jdbcConnection;
    private final SentinelExceptions sentinelExceptions;
    private Long logID;

    public CaseDaoImpl() {
        this.jdbcConnection = new JdbcConnection();
        this.sentinelExceptions = new SentinelExceptionsImpl();
    }

    @Override
    public Case findByID(Long caseID) {
        String query = "SELECT * " +
                "FROM Cases " +
                "JOIN Fir ON Cases.firID = Fir.firID " +
                "JOIN Police ON Cases.assignedTo = Police.PoliceID " +
                "WHERE Cases.caseID = ?";
        try (PreparedStatement preparedStatement = jdbcConnection.connection.prepareStatement(query)) {
            preparedStatement.setLong(1, caseID);
            ResultSet resultSet = jdbcConnection.retrieve(preparedStatement);
            return (Optional.ofNullable(Orm.mapToCase(resultSet).get(0))
                    .orElseThrow(() -> sentinelExceptions.CaseNotFound(caseID)));
        } catch (SQLException e) {
            throw new RuntimeException("SQLException: " + e);
        }
    }

    @Override
    public List<Case> findAll() {
        String query = "Select * from Cases " +
                "Join Fir on Cases.firID = Fir.firID " +
                "Join Police on Cases.assignedTo = Police.PoliceID";
        List<Case> cases = new ArrayList<>();
        try (PreparedStatement preparedStatement = jdbcConnection.connection.prepareStatement(query)) {
            ResultSet resultSet = jdbcConnection.retrieve(preparedStatement);
            cases = Orm.mapToCase(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cases;
    }

    @Override
    public Long save(Case cs) {
        String query = "Insert into Cases(registeredDate, registeredTime, firID, assignedTo) values(?,?,?,?)";
        try (PreparedStatement preparedStatement = jdbcConnection.connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setDate(1, cs.getRegisteredDate());
            preparedStatement.setTime(2, cs.getRegisteredTime());
            preparedStatement.setLong(3, cs.getFirID());
            preparedStatement.setLong(4, cs.getAssignedTo());
            Long caseID = jdbcConnection.manipulate(preparedStatement);
            return (caseID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (null);

    }

    @Override
    public List<Case> findByPoliceID(Long policeID){
        String query = "Select * from Cases " +
                "Join Police on Cases.assignedTo = Police.policeID " +
                "where Cases.assignedTo = ?";
        List<Case> cases = new ArrayList<>();
        try (PreparedStatement preparedStatement = jdbcConnection.connection.prepareStatement(query)) {
            preparedStatement.setLong(1, policeID);
            ResultSet resultSet = jdbcConnection.retrieve(preparedStatement);
            cases = Orm.mapToCase(resultSet);
            return (cases);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Long saveLog(Log log) {
        String query = "Insert into Logs(message, logDate, logTime, media, caseID) values(?,?,?,?,?)";
        try (PreparedStatement preparedStatement = jdbcConnection.connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, log.getMessage());
            preparedStatement.setDate(2, log.getLogDate());
            preparedStatement.setTime(3, log.getLogTime());
            preparedStatement.setString(4, log.getMedia());
            preparedStatement.setLong(5, log.getCaseID());
            Long logID = jdbcConnection.manipulate(preparedStatement);
            return logID;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Log findLogByID(Long logID) {
        String query = "SELECT * " +
                "FROM Logs " +
                "JOIN Cases ON Logs.caseID = Cases.caseID " +
                "WHERE Logs.logID = ?";
        try (PreparedStatement preparedStatement = jdbcConnection.connection.prepareStatement(query)) {
            preparedStatement.setLong(1, logID);
            ResultSet resultSet = jdbcConnection.retrieve(preparedStatement);
            return (Optional.ofNullable(Orm.mapToLog(resultSet).get(0))
                    .orElseThrow(() -> sentinelExceptions.CitizenNotFound(logID)));
        } catch (SQLException e) {
            throw new RuntimeException("SQLException: " + e);
        }
    }

    @Override
    public List<Log> findAllLogs(Long caseID) {
        String query = "SELECT * " +
                "FROM Logs " +
                "JOIN Cases ON Logs.caseID = Cases.caseID " +
                "WHERE Logs.caseID = ?";
        List<Log> logs = new ArrayList<>();
        try (PreparedStatement preparedStatement = jdbcConnection.connection.prepareStatement(query)) {
            preparedStatement.setLong(1, caseID);
            ResultSet resultSet = jdbcConnection.retrieve(preparedStatement);
            logs = Orm.mapToLog(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logs;
    }

    @Override
    public List<Case> search(String keyword) {
        String query = "SELECT * FROM Cases WHERE firID = ?";
        try (PreparedStatement preparedStatement = jdbcConnection.connection.prepareStatement(query)) {
            preparedStatement.setString(1, keyword);
            ResultSet resultSet = jdbcConnection.retrieve(preparedStatement);
            return Optional.ofNullable(Orm.mapToCase(resultSet))
                    .orElseThrow(() -> sentinelExceptions.CaseNotFound(Long.parseLong("1234")));
        } catch (SQLException e) {
            throw new RuntimeException("SQLException: " + e);
        }
    }

    @Override
    public Case findByFirID(Long firID) {
        String query = "SELECT * " +
                "FROM Cases " +
                "JOIN Fir ON Cases.firID = Fir.firID " +
                "JOIN Police ON Cases.assignedTo = Police.PoliceID " +
                "WHERE Cases.firID = ?";
        try (PreparedStatement preparedStatement = jdbcConnection.connection.prepareStatement(query)) {
            preparedStatement.setLong(1, firID);
            ResultSet resultSet = jdbcConnection.retrieve(preparedStatement);
            return (Optional.ofNullable(Orm.mapToCase(resultSet).get(0))
                    .orElseThrow(() -> sentinelExceptions.CaseNotFound(firID)));
        } catch (SQLException e) {
            throw new RuntimeException("SQLException: " + e);
        }
    }

}
