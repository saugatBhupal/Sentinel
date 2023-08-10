package controller;

import model.Police;

public interface PoliceController {
    Police register(Police police);
    void authenticate(Police police);
    void getAllPolice();
    Police getPoliceByID(Long id);
    void logout();
    Police resetPassword(Long policeID, String password);
    Police search(Long policeID);
    void fullTextSearch(String keyword);
}
