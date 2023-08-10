package utils.orm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.CaseDao;
import dao.CitizenDao;
import dao.FirDao;
import dao.PoliceDao;
import dao.daoImpl.CaseDaoImpl;
import dao.daoImpl.CitizenDaoImpl;
import dao.daoImpl.FirDaoImpl;
import dao.daoImpl.PoliceDaoImpl;
import model.Case;
import model.Citizen;
import model.Fir;
import model.Log;
import model.OIC;
import model.Police;
import model.Verdict;

public class Orm {

    private static final PoliceDao policeDao = new PoliceDaoImpl();
    private static final CitizenDao citizenDao = new CitizenDaoImpl();
    private static final CaseDao caseDao = new CaseDaoImpl();
    private static final FirDao firDao = new FirDaoImpl();

    public static List<Citizen> mapToCitizen(ResultSet resultSet) throws SQLException {
        List<Citizen> citizens = new ArrayList<>();
        while (resultSet.next()) {
            Citizen citizen = new Citizen();
            citizen.setCitizenshipNo(resultSet.getLong("citizenshipNo"));
            citizen.setContact(resultSet.getString("contact"));
            citizen.setFirstName(resultSet.getString("firstName"));
            citizen.setMiddleName(resultSet.getString("middleName"));
            citizen.setLastName(resultSet.getString("lastName"));
            citizen.setDOB(resultSet.getDate("DOB"));
            citizen.setPermanentAddress(resultSet.getString("permanentAddress"));
            citizen.setTemporaryAddress(resultSet.getString("temporaryAddress"));
            citizens.add(citizen);
        }
        return (citizens.size() > 0 ? citizens : null);
    }

    public static List<Police> mapToPolice(ResultSet resultSet) throws SQLException {
        List<Police> polices = new ArrayList<>();
        while (resultSet.next()) {
            Police police = new Police();
            police.setPoliceID(resultSet.getLong("policeID"));
            police.setDepartment(resultSet.getString("Department"));
            police.setPosition(resultSet.getString("Position"));
            police.setPassword(resultSet.getString("password"));
            police.setCitizenshipID(resultSet.getLong("citizenshipID"));
            police.setCitizen(citizenDao.findByCitizenID(resultSet.getLong("citizenshipID")));
            polices.add(police);
        }
        return (polices.size() > 0 ? polices : null);
    }

    public static List<Fir> mapToFir(ResultSet resultSet) throws SQLException {
        List<Fir> firs = new ArrayList<>();
        while (resultSet.next()) {
            Fir fir = new Fir();
            fir.setFirID(resultSet.getLong("firID"));
            fir.setFiledBy(resultSet.getLong("filedBy"));
            fir.setFiledAgainst(resultSet.getLong("filedAgainst"));
            fir.setFiledDate(resultSet.getDate("filedDate"));
            fir.setFiledTime(resultSet.getTime("filedTime"));
            fir.setDescription(resultSet.getString("description"));
            fir.setCategory(resultSet.getString("category"));
            fir.setEvidence(resultSet.getString("evidence"));
            fir.setRegisteredBy(resultSet.getLong("registeredBy"));
            fir.setPolice(policeDao.findById(resultSet.getLong("registeredBy")));
            fir.setRegisteredDate(resultSet.getDate("registeredDate"));
            fir.setRegisteredTime(resultSet.getTime("registeredTime"));
            fir.setWitness(resultSet.getLong("witness"));
            fir.setStatus(resultSet.getInt("status"));
            fir.setOffenderCitizen(citizenDao.findByCitizenID(fir.getFiledAgainst()));
            fir.setVictimCitizen(citizenDao.findByCitizenID(fir.getFiledBy()));
            firs.add(fir);
        }
        return (firs.size() > 0 ? firs : null);
    }

    public static List<Case> mapToCase(ResultSet resultSet) throws SQLException {
        List<Case> cases = new ArrayList<>();
        while (resultSet.next()) {
            Case cs = new Case();
            cs.setCaseID(resultSet.getLong("caseID"));
            cs.setRegisteredDate(resultSet.getDate("registeredDate"));
            cs.setRegisteredTime(resultSet.getTime("registeredTime"));
            cs.setAssignedTo(resultSet.getLong("assignedTo"));
            cs.setAssignedPolice(policeDao.findById(resultSet.getLong("assignedTo")));
            cs.setFirID(resultSet.getLong("firID"));
            cs.setFir(firDao.findById(resultSet.getLong("firID")));
            cases.add(cs);
        }
        return (cases.size() > 0 ? cases : null);
    }

    public static List<OIC> mapToOIC(ResultSet resultSet) throws SQLException {
        List<OIC> oics = new ArrayList<>();
        while (resultSet.next()) {
            OIC oic = new OIC();
            oic.setOicID(resultSet.getLong("oicID"));
            oic.setPoliceID(resultSet.getLong("policeID"));
            oic.setPolice(policeDao.findById(resultSet.getLong("policeID")));
            oics.add(oic);
        }
        return (oics.size() > 0 ? oics : null);

    }

    public static List<Log> mapToLog(ResultSet resultSet) throws SQLException {
        List<Log> logs = new ArrayList<>();
        while (resultSet.next()) {
            Log log = new Log();
            log.setLogsID(resultSet.getLong("logID"));
            log.setMessage(resultSet.getString("message"));
            log.setLogDate(resultSet.getDate("logDate"));
            log.setLogTime(resultSet.getTime("logTime"));
            log.setMedia(resultSet.getString("media"));
            log.setCaseID(resultSet.getLong("caseID"));
            logs.add(log);
        }
        return (logs.size() > 0 ? logs : null);
    }

    public static List<Verdict> mapToVerdict(ResultSet resultSet) throws SQLException{
        List<Verdict> verdicts = new ArrayList<>();
        while(resultSet.next()){
            Verdict verdict = new Verdict();
            verdict.setVerdictID(resultSet.getLong("verdictID"));
            verdict.setVerdict(resultSet.getString("verdict"));
            verdict.setVerdictDate(resultSet.getDate("verdictDate"));
            verdict.setCaseID(resultSet.getLong("caseID"));
            verdict.setCs(caseDao.findByID(resultSet.getLong("caseID")));
            verdict.setFir(verdict.getCs().getFir());
            verdicts.add(verdict);
        }
        return(verdicts.size() >0 ? verdicts : null);
    }
}