package dao.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import config.sentinelErrorConfig.Exceptions.SentinelExceptions;
import config.sentinelErrorConfig.Exceptions.ExceptionsImpl.SentinelExceptionsImpl;
import dao.VerdictDao;
import database.JdbcConnection;
import model.Verdict;
import utils.orm.Orm;

public class VerdictDaoImpl implements VerdictDao {

    private final JdbcConnection jdbcConnection;
    private final SentinelExceptions sentinelExceptions;

    public VerdictDaoImpl() {
        this.jdbcConnection = new JdbcConnection();
        this.sentinelExceptions = new SentinelExceptionsImpl();
    }

    @Override
    public Long save(Verdict verdict) {
        String query = "insert into Verdict(verdict, verdictDate, caseID) values(?,?,?)";
        try (PreparedStatement preparedStatement = jdbcConnection.connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, verdict.getVerdict());
            preparedStatement.setDate(2, verdict.getVerdictDate());
            preparedStatement.setLong(3, verdict.getCaseID());
            Long generatedKey = jdbcConnection.manipulate(preparedStatement);
            return (generatedKey != null ? generatedKey : null);
        } catch (SQLException e) {
            throw new RuntimeException("SQLException: " + e);
        }
    }

    @Override
    public Verdict findByID(Long verdictID) {
        String query = "SELECT * " +
                "FROM Verdict " +
                "JOIN Cases ON Verdict.caseID = Cases.caseID " +
                "WHERE Verdict.verdictID = ?";
        try (PreparedStatement preparedStatement = jdbcConnection.connection.prepareStatement(query)) {
            preparedStatement.setLong(1, verdictID);
            ResultSet resultSet = jdbcConnection.retrieve(preparedStatement);
            return (Optional.ofNullable(Orm.mapToVerdict(resultSet).get(0))
                    .orElseThrow(() -> sentinelExceptions.VerdictNotFound(verdictID)));
        } catch (SQLException e) {
            throw new RuntimeException("SQLException: " + e);
        }
    }

    @Override
    public Verdict findByCaseID(Long caseID) {
        String query = "SELECT * " +
                "FROM Verdict " +
                "JOIN Cases ON Verdict.caseID = Cases.caseID " +
                "WHERE Verdict.caseID = ?";
        try (PreparedStatement preparedStatement = jdbcConnection.connection.prepareStatement(query)) {
            preparedStatement.setLong(1, caseID);
            ResultSet resultSet = jdbcConnection.retrieve(preparedStatement);
            return (Optional.ofNullable(Orm.mapToVerdict(resultSet).get(0))
                    .orElseThrow(() -> sentinelExceptions.VerdictNotFound(caseID)));
        } catch (SQLException e) {
            throw new RuntimeException("SQLException: " + e);
        }
    }
}
