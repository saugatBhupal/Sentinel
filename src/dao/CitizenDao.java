package dao;

import java.util.List;

import model.Citizen;

public interface CitizenDao {
    Citizen findByCitizenID(Long citizenID);
    List<Citizen> findAll();
}
