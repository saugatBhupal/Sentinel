package utils.builder;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.protocol.Resultset;

import model.Citizen;
import model.Fir;
import model.Police;

public class EntityBuilder {

    public static Citizen objectOfCitizen(ResultSet resultSet) throws SQLException{
        Citizen citizen = new Citizen();
        int size = 0;
        while(resultSet.next()){
            citizen.setCitizenshipNo(resultSet.getLong("citizenshipNo"));
            citizen.setContact(resultSet.getString("contact"));
            citizen.setFirstName(resultSet.getString("firstName"));
            citizen.setMiddleName(resultSet.getString("middleName"));
            citizen.setLastName(resultSet.getString("lastName"));
            citizen.setDOB(resultSet.getDate("DOB"));
            citizen.setPermanentAddress(resultSet.getString("permanentAddress"));
            citizen.setTemporaryAddress(resultSet.getString("temporaryAddress"));
            size ++;
        }
        System.out.println(size);
        return(size > 0 ? citizen : null);
    }

    public static Police objectOfPolice(ResultSet resultSet) throws SQLException{
        Police police = new Police();
        int size = 0;
        while(resultSet.next()){
            police.setPoliceID(resultSet.getLong("policeID"));
            police.setDepartment(resultSet.getString("Department"));
            police.setPosition(resultSet.getString("Position"));
            police.setPassword(resultSet.getString("password"));
            police.setCitizenshipID(resultSet.getLong("citizenshipID"));
            resultSet.previous();
            police.setCitizen(objectOfCitizen(resultSet));
            resultSet.next();
            size ++;
        }
        return(size > 0 ? police : null);
    }

    public static Fir objectOfFir(ResultSet resultSet) throws SQLException{
        Fir fir = new Fir();
        int size = 0;
        while(resultSet.next()){
            fir.setFirID(resultSet.getLong("firID"));
            fir.setFiledBy(resultSet.getLong("filedBy"));
            fir.setFiledAgainst(resultSet.getLong("filedAgainst"));
            fir.setFiledDate(resultSet.getDate("filedDate"));
            fir.setFiledTime(resultSet.getTime("filedTime"));
            fir.setDescription(resultSet.getString("description"));
            fir.setCategory(resultSet.getString("category"));
            fir.setEvidence(resultSet.getString("evidence"));
            fir.setRegisteredBy(resultSet.getLong("registeredBy"));
            fir.setPolice(objectOfPolice(resultSet));
            size ++;
        }
        return(size > 0 ? fir : null);
    }
    
}
