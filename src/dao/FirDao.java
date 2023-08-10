package dao;

import java.util.List;

import model.Fir;

public interface FirDao {
    Fir findById(Long id);
    List<Fir> findAll();
    Long save(Fir fir);
    int update(Fir firID);
    List<Fir> findRegisteredFir(Long id);
    List<Fir> search(String keyword);
    List<Fir> filter(int status, Long citizenID);
    List<Fir> findAllByID(Long citizenID);
}
