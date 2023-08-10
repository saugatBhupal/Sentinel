package dao.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import config.sentinelErrorConfig.SentinelError;
import config.sentinelErrorConfig.Exceptions.SentinelExceptions;
import config.sentinelErrorConfig.Exceptions.ExceptionsImpl.SentinelExceptionsImpl;
import dao.FirDao;
import database.JdbcConnection;
import model.Fir;
import utils.orm.Orm;

public class FirDaoImpl implements FirDao {

    private final JdbcConnection jdbcConnection;
    private final SentinelExceptions sentinelExceptions;

    public FirDaoImpl() {
        this.jdbcConnection = new JdbcConnection();
        this.sentinelExceptions = new SentinelExceptionsImpl();
    }

    @Override
    public Fir findById(Long FirID) {
        String query = "Select Fir.*," +
                "CitizenBy.*," +
                "CitizenAgainst.firstName AS CAGfirstName," +
                "CitizenAgainst.middleName AS CAGmiddleName," +
                "CitizenAgainst.lastName AS CAGlastName," +
                "CitizenAgainst.citizenshipNo AS CAGcitizenshipNo," +
                "CitizenAgainst.DOB AS CAGDOB," +
                "CitizenAgainst.contact AS CAGContact," +
                "CitizenAgainst.permanentAddress AS CAGPermanentAddress," +
                "CitizenAgainst.temporaryAddress AS CAGTemporaryAddress," +
                "Police.* " +
                "FROM Fir " +
                "JOIN Citizen AS CitizenBy ON filedBy = CitizenBy.citizenshipNo " +
                "JOIN Citizen AS CitizenAgainst ON filedAgainst = CitizenAgainst.citizenshipNo " +
                "JOIN Police ON registeredBy = Police.policeID " +
                "WHERE firID = ?";
        try (PreparedStatement preparedStatement = jdbcConnection.connection.prepareStatement(query)) {
            preparedStatement.setLong(1, FirID);
            ResultSet resultSet = jdbcConnection.retrieve(preparedStatement);
            return (Optional.ofNullable(Orm.mapToFir(resultSet).get(0))
                    .orElseThrow(() -> sentinelExceptions.FirNotFound(FirID)));
        } catch (SQLException e) {
            throw new RuntimeException("SQLException: " + e);
        }
    }

    @Override
    public List<Fir> findAll() {
        String query = "Select Fir.*," +
                "CitizenBy.*," +
                "CitizenAgainst.firstName AS CAGfirstName," +
                "CitizenAgainst.middleName AS CAGmiddleName," +
                "CitizenAgainst.lastName AS CAGlastName," +
                "CitizenAgainst.citizenshipNo AS CAGcitizenshipNo," +
                "CitizenAgainst.DOB AS CAGDOB," +
                "CitizenAgainst.contact AS CAGContact," +
                "CitizenAgainst.permanentAddress AS CAGPermanentAddress," +
                "CitizenAgainst.temporaryAddress AS CAGTemporaryAddress," +
                "Police.* " +
                "FROM Fir " +
                "JOIN Citizen AS CitizenBy ON filedBy = CitizenBy.citizenshipNo " +
                "JOIN Citizen AS CitizenAgainst ON filedAgainst = CitizenAgainst.citizenshipNo " +
                "JOIN Police ON registeredBy = Police.policeID";
        List<Fir> firs = new ArrayList<>();
        try (PreparedStatement preparedStatement = jdbcConnection.connection.prepareStatement(query,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet resultSet = jdbcConnection.retrieve(preparedStatement);
            firs = Orm.mapToFir(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("SQLException: " + e);
        }
        return (firs);
    }

    @Override
    public Long save(Fir fir) {
        String query = "insert into Fir(filedBy, filedAgainst, filedDate, filedTime, description,category,evidence,registeredBy,registeredDate,registeredTime,witness,status) values(?,?,?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement = jdbcConnection.connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, fir.getFiledBy());
            preparedStatement.setLong(2, fir.getFiledAgainst());
            preparedStatement.setDate(3, fir.getFiledDate());
            preparedStatement.setTime(4, fir.getFiledTime());
            preparedStatement.setString(5, fir.getDescription());
            preparedStatement.setString(6, fir.getCategory());
            preparedStatement.setString(7, fir.getEvidence());
            preparedStatement.setLong(8, fir.getRegisteredBy());
            preparedStatement.setDate(9, fir.getRegisteredDate());
            preparedStatement.setTime(10, fir.getRegisteredTime());
            preparedStatement.setLong(11, fir.getWitness());
            preparedStatement.setInt(12, 0);
            Long generatedKey = jdbcConnection.manipulate(preparedStatement);
            return (generatedKey != null ? generatedKey : null);
        } catch (SQLException e) {
            throw new RuntimeException("SQLException: " + e);
        }
    }

    @Override
    public List<Fir> findRegisteredFir(Long id) {
        String query = "Select Fir.*," +
                "CitizenBy.*," +
                "CitizenAgainst.firstName AS CAGfirstName," +
                "CitizenAgainst.middleName AS CAGmiddleName," +
                "CitizenAgainst.lastName AS CAGlastName," +
                "CitizenAgainst.citizenshipNo AS CAGcitizenshipNo," +
                "CitizenAgainst.DOB AS CAGDOB," +
                "CitizenAgainst.contact AS CAGContact," +
                "CitizenAgainst.permanentAddress AS CAGPermanentAddress," +
                "CitizenAgainst.temporaryAddress AS CAGTemporaryAddress," +
                "Police.* " +
                "FROM Fir " +
                "JOIN Citizen AS CitizenBy ON filedBy = CitizenBy.citizenshipNo " +
                "JOIN Citizen AS CitizenAgainst ON filedAgainst = CitizenAgainst.citizenshipNo " +
                "JOIN Police ON registeredBy = Police.policeID " +
                "WHERE registeredBy = ?";
        List<Fir> firs = new ArrayList<>();
        try (PreparedStatement preparedStatement = jdbcConnection.connection.prepareStatement(query,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = jdbcConnection.retrieve(preparedStatement);
            firs = Orm.mapToFir(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException("SQLException: " + e);
        }
        return (firs);
    }

    @Override
    public int update(Fir fir) {
        int status = 0;
        String query = "update Fir set filedBy = ?, filedAgainst = ?, filedDate = ?, filedTime =?, description =?, category =?, "
                +
                "evidence =?, registeredBy =?, witness =?,status =? where firID = ?";
        try (PreparedStatement preparedStatement = jdbcConnection.connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, fir.getFiledBy());
            preparedStatement.setLong(2, fir.getFiledAgainst());
            preparedStatement.setDate(3, fir.getFiledDate());
            preparedStatement.setTime(4, fir.getFiledTime());
            preparedStatement.setString(5, fir.getDescription());
            preparedStatement.setString(6, fir.getCategory());
            preparedStatement.setString(7, fir.getEvidence());
            preparedStatement.setLong(8, fir.getRegisteredBy());
            preparedStatement.setLong(9, fir.getWitness());
            preparedStatement.setInt(10, fir.getStatus());
            preparedStatement.setLong(11, fir.getFirID());
            status = jdbcConnection.update(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (status);
    }

    @Override
    public List<Fir> search(String keyword) {
        String query = "SELECT * FROM Fir WHERE category = ? OR firID = ? OR filedBy = ? OR filedAgainst = ? OR registeredBy = ?";
        try (PreparedStatement preparedStatement = jdbcConnection.connection.prepareStatement(query)) {
            preparedStatement.setString(1, keyword);
            preparedStatement.setString(2, keyword);
            preparedStatement.setString(3, keyword);
            preparedStatement.setString(4, keyword);
            preparedStatement.setString(5, keyword);
            ResultSet resultSet = jdbcConnection.retrieve(preparedStatement);
            return Optional.ofNullable(Orm.mapToFir(resultSet))
                    .orElseThrow(() -> sentinelExceptions.FirNotFound(Long.parseLong("1234")));
        } catch (SQLException e) {
            throw new RuntimeException("SQLException: " + e);
        }
    }

    @Override
    public List<Fir> filter(int status, Long citizenID) {
        String query = "Select Fir.*," +
                "CitizenBy.*," +
                "CitizenAgainst.firstName AS CAGfirstName," +
                "CitizenAgainst.middleName AS CAGmiddleName," +
                "CitizenAgainst.lastName AS CAGlastName," +
                "CitizenAgainst.citizenshipNo AS CAGcitizenshipNo," +
                "CitizenAgainst.DOB AS CAGDOB," +
                "CitizenAgainst.contact AS CAGContact," +
                "CitizenAgainst.permanentAddress AS CAGPermanentAddress," +
                "CitizenAgainst.temporaryAddress AS CAGTemporaryAddress," +
                "Police.* " +
                "FROM Fir " +
                "JOIN Citizen AS CitizenBy ON filedBy = CitizenBy.citizenshipNo " +
                "JOIN Citizen AS CitizenAgainst ON filedAgainst = CitizenAgainst.citizenshipNo " +
                "JOIN Police ON registeredBy = Police.policeID " +
                "WHERE Fir.status = ? AND (FIR.filedBy = ? OR FIR.filedAgainst = ? OR FIR.witness = ?)";
        List<Fir> firs = new ArrayList<>();
        try (PreparedStatement preparedStatement = jdbcConnection.connection.prepareStatement(query,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {     
            preparedStatement.setInt(1, status);
            System.out.println(status);
            preparedStatement.setLong(2, citizenID);
            preparedStatement.setLong(3, citizenID);
            preparedStatement.setLong(4, citizenID);
            ResultSet resultSet = jdbcConnection.retrieve(preparedStatement);
            firs = Orm.mapToFir(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException("SQLException: " + e);
        }
        return (firs);
    }

    @Override
    public List<Fir> findAllByID(Long citizenID) {
        String query = "Select Fir.*," +
                "CitizenBy.*," +
                "CitizenAgainst.firstName AS CAGfirstName," +
                "CitizenAgainst.middleName AS CAGmiddleName," +
                "CitizenAgainst.lastName AS CAGlastName," +
                "CitizenAgainst.citizenshipNo AS CAGcitizenshipNo," +
                "CitizenAgainst.DOB AS CAGDOB," +
                "CitizenAgainst.contact AS CAGContact," +
                "CitizenAgainst.permanentAddress AS CAGPermanentAddress," +
                "CitizenAgainst.temporaryAddress AS CAGTemporaryAddress," +
                "Police.* " +
                "FROM Fir " +
                "JOIN Citizen AS CitizenBy ON filedBy = CitizenBy.citizenshipNo " +
                "JOIN Citizen AS CitizenAgainst ON filedAgainst = CitizenAgainst.citizenshipNo " +
                "JOIN Police ON registeredBy = Police.policeID " +
                "WHERE FIR.filedBy = ? OR FIR.filedAgainst = ? OR FIR.witness = ?";
        List<Fir> firs = new ArrayList<>();
        try (PreparedStatement preparedStatement = jdbcConnection.connection.prepareStatement(query,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {     
            preparedStatement.setLong(1, citizenID);
            preparedStatement.setLong(2, citizenID);
            preparedStatement.setLong(3, citizenID);
            ResultSet resultSet = jdbcConnection.retrieve(preparedStatement);
            firs = Orm.mapToFir(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException("SQLException: " + e);
        }
        return (firs);
    }

}
