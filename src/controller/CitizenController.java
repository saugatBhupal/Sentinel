package controller;

import java.util.List;

import model.Citizen;

public interface CitizenController {
    Citizen search(Long citizenID);
    List<Citizen> getAllCitizens();
}
