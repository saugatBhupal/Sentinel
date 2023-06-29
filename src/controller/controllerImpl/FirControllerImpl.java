package controller.controllerImpl;

import java.util.List;

import controller.FirController;
import dao.FirDao;
import dao.daoImpl.FirDaoImpl;
import model.Fir;

public class FirControllerImpl implements FirController {
    private final FirDao dao;

    public FirControllerImpl(){
        this.dao = new FirDaoImpl();
    }

    @Override
    public Fir save(Fir f) {
        Fir fir = null;
        try{
            Long firID = dao.save(f);
            System.out.println(firID);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return fir;
    }

    @Override
    public List<Fir> getAllFir() {
        try{
            return(dao.findAll());
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
}
