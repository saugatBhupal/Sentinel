package controller;

import model.Police;

public interface PoliceController {
    Police register(Police police);
    int authenticate(Police police);
    
}
