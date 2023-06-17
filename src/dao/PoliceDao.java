package dao;

import model.Police;

public interface PoliceDao {
    Long save(Police police);
    Police findById(Long policeID);
}
