package dao;

import java.util.List;

import model.Fir;

public interface FirDao {
    Fir findById(Long id);
    List<Fir> findAll();
    Long save(Fir fir);
}
