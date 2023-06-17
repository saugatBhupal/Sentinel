package controller.controllerImpl;

import java.util.List;

import controller.CitizenController;
import dao.CitizenDao;
import dao.daoImpl.CitizenDaoImpl;
import model.Citizen;

public class CitizenControllerImpl implements CitizenController{
    private final CitizenDao dao;
    
    public CitizenControllerImpl(){
        this.dao = new CitizenDaoImpl();
    }

    @Override
    public Citizen search(Long citizenID) {
        try{
            return(dao.findByCitizenID(citizenID));
        }
        catch(Exception e){
            e.printStackTrace();
            return(null);
        }
        
    }

    @Override
    public List<Citizen> getAllCitizens() {
        try{
            return(dao.findAll());
        }
        catch(Exception e){
            e.printStackTrace();
            return(null);

        }
    }

    
    
}
