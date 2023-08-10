package controller;

import java.util.List;

import model.Citizen;

public interface CitizenController {
    Citizen search(Long citizenID);
    void getAllCitizens();
    void getDetail(Citizen citizen);
    void fullTextSearch(String keyword);
    void getAllCriminals();
}
