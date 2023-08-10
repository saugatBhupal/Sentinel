package dao;

import java.util.List;

import model.Police;

public interface PoliceDao {
    Long save(Police police);
    Police findById(Long policeID);
    List<Police> findAll();
    int update(Police police);
    Police findByPoliceID(Long policeID);
    List<Police> search(String keyword);
}
