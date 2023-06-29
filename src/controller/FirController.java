package controller;

import java.util.List;

import model.Fir;

public interface FirController {
    Fir save(Fir fir);
    List<Fir> getAllFir();

}
