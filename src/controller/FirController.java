package controller;

import java.util.List;

import model.Fir;

public interface FirController {
    Fir save(Fir fir);
    List<Fir> getAllFir();
    Fir getFirByID(Long firID);
    void getCreateFirPage();
    void getDetail(Fir fir);
    void getUpdate(Fir fir);
    void updateFir(Fir fir);
    void fullTextSearch(String keyword);
    <T> List<Fir> filterByStatus(T status, Long citizenID);
}
