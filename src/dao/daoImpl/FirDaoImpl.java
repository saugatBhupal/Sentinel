package dao.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import config.sentinelErrorConfig.Exceptions.SentinelExceptions;
import config.sentinelErrorConfig.Exceptions.ExceptionsImpl.SentinelExceptionsImpl;
import dao.FirDao;
import database.JdbcConnection;
import model.Fir;
import utils.orm.Orm;

public class FirDaoImpl implements FirDao {

    private final JdbcConnection jdbcConnection;
    private final SentinelExceptions sentinelExceptions;
    public FirDaoImpl(){
        this.jdbcConnection = new JdbcConnection();
        this.sentinelExceptions = new SentinelExceptionsImpl();
    }


    @Override
    public Fir findById(Long FirID) {
        String query = "Select Fir.*,"+
		"CitizenBy.*,"+
        "CitizenAgainst.firstName AS CAGfirstName,"+
        "CitizenAgainst.middleName AS CAGmiddleName,"+
        "CitizenAgainst.lastName AS CAGlastName,"+
        "CitizenAgainst.citizenshipNo AS CAGcitizenshipNo,"+
        "CitizenAgainst.DOB AS CAGDOB,"+
        "CitizenAgainst.contact AS CAGContact,"+
        "CitizenAgainst.permanentAddress AS CAGPermanentAddress,"+
        "CitizenAgainst.temporaryAddress AS CAGTemporaryAddress,"+
        "Police.* "+
        "FROM Fir "+
        "JOIN Citizen AS CitizenBy ON filedBy = CitizenBy.citizenshipNo "+
        "JOIN Citizen AS CitizenAgainst ON filedAgainst = CitizenAgainst.citizenshipNo "+
        "JOIN Police ON registeredBy = Police.policeID "+
        "WHERE firID = ?";
        try(PreparedStatement preparedStatement = jdbcConnection.connection.prepareStatement(query)){
            preparedStatement.setLong(1, FirID);
            ResultSet resultSet = jdbcConnection.retrieve(preparedStatement);
            return(Optional.ofNullable(Orm.mapToFir(resultSet).get(0))
                .orElseThrow(() -> sentinelExceptions.FirNotFound(FirID)));
        }
        catch(SQLException e){
            throw new RuntimeException("SQLException: " + e);
        }
    }

    @Override
    public List<Fir> findAll() {
        String query = "Select Fir.*,"+
		"CitizenBy.*,"+
        "CitizenAgainst.firstName AS CAGfirstName,"+
        "CitizenAgainst.middleName AS CAGmiddleName,"+
        "CitizenAgainst.lastName AS CAGlastName,"+
        "CitizenAgainst.citizenshipNo AS CAGcitizenshipNo,"+
        "CitizenAgainst.DOB AS CAGDOB,"+
        "CitizenAgainst.contact AS CAGContact,"+
        "CitizenAgainst.permanentAddress AS CAGPermanentAddress,"+
        "CitizenAgainst.temporaryAddress AS CAGTemporaryAddress,"+
        "Police.* "+
        "FROM Fir "+
        "JOIN Citizen AS CitizenBy ON filedBy = CitizenBy.citizenshipNo "+
        "JOIN Citizen AS CitizenAgainst ON filedAgainst = CitizenAgainst.citizenshipNo "+
        "JOIN Police ON registeredBy = Police.policeID";
        List<Fir> firs = new ArrayList<>();
        try(PreparedStatement preparedStatement = jdbcConnection.connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)){
            ResultSet resultSet = jdbcConnection.retrieve(preparedStatement);
            firs = Orm.mapToFir(resultSet);
        }
        catch(SQLException e){
            throw new RuntimeException("SQLException: " + e);
        }
        return(firs);
    }

    @Override
    public Long save(Fir fir) {
        String query = "insert into Fir(filedBy, filedAgainst, filedDate, filedTime, description,category,evidence,registeredBy,witness) values(?,?,?,?,?,?,?,?,?)";
        try(PreparedStatement preparedStatement = jdbcConnection.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setLong(1, fir.getFiledBy());
            preparedStatement.setLong(2, fir.getFiledAgainst());
            preparedStatement.setDate(3, fir.getFiledDate());
            preparedStatement.setTime(4, fir.getFiledTime());
            preparedStatement.setString(5, fir.getDescription());
            preparedStatement.setString(6, fir.getCategory());
            preparedStatement.setString(7, fir.getEvidence());
            preparedStatement.setLong(8, fir.getRegisteredBy());
            preparedStatement.setLong(9, fir.getWitness());
            Long generatedKey = jdbcConnection.manipulate(preparedStatement);
            return(generatedKey != null? generatedKey : null);
        }
        catch(SQLException e){
            throw new RuntimeException("SQLException: " + e);
        }
    }
    
}
