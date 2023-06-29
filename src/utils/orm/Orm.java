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
import model.Police;

public class Orm {

        private static final PoliceDao policeDao = new PoliceDaoImpl();
        private static final CitizenDao citizenDao = new CitizenDaoImpl();
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


    public static List<Fir> mapToFir(ResultSet resultSet) throws SQLException{
        List<Fir> firs = new ArrayList<>();
        while(resultSet.next()){
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
            firs.add(fir);
        }
        return(firs.size() > 0 ? firs : null);
    }

    public static List<Case> mapToCase(ResultSet resultSet) throws SQLException{
        List<Case> cases = new ArrayList<>();
        while(resultSet.next()){
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
}
