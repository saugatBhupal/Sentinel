package controller.controllerImpl;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import config.passwordEncoderConfig.Encoder;
import controller.PoliceController;
import dao.PoliceDao;
import dao.daoImpl.PoliceDaoImpl;
import model.Police;

public class PoliceControllerImpl implements PoliceController{

    private final PoliceDao dao;
    private final JPanel panel;
    
    public PoliceControllerImpl(JPanel panel){
        this.dao = new PoliceDaoImpl();
        this.panel = panel;
    }

    @Override
    public Police register(Police police) {
        try{
            police.setPassword(Encoder.toHash(police.getPassword()));
            Long policeID = dao.save(police);
            Police savedPolice = dao.findById(policeID);
            if(savedPolice != null){
                JOptionPane.showMessageDialog(panel,"Successfully Registered Police: "+savedPolice.getPoliceID() +", Name : " +savedPolice.getCitizen().getFirstName());
            }
            return savedPolice;
        }

        catch(Exception e){
            JOptionPane.showMessageDialog(panel,"Error Registering police,ID: "+police.getCitizenshipID()+" is already registered.");
            return(null);
        }
    }

    @Override
    public int authenticate(Police police) {
        try{
            Police targetPolice = dao.findById(police.getPoliceID());
            return(Encoder.verify(police.getPassword(), targetPolice.getPassword()));
        }
        catch(Exception e){
            e.printStackTrace();
            return(0);

        }
    }

    
}
