package controller.controllerImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import controller.CitizenController;
import dao.CitizenDao;
import dao.FirDao;
import dao.daoImpl.CitizenDaoImpl;
import dao.daoImpl.FirDaoImpl;
import model.Citizen;
import model.Fir;
import views.App;
import views.CitizenDetails;
import views.CitizenList;

public class CitizenControllerImpl implements CitizenController {
    private final CitizenDao dao;
    private final FirDao firDao;
    private final App app;

    public CitizenControllerImpl(App sentApp) {
        this.dao = new CitizenDaoImpl();
        this.firDao = new FirDaoImpl();
        this.app = sentApp;
    }

    @Override
    public Citizen search(Long citizenID) {
        try {
            return (dao.findByCitizenID(citizenID));
        } catch (Exception e) {
            e.printStackTrace();
            return (null);
        }

    }

    @Override
    public void getAllCitizens() {
        try {
            app.addPanel(new CitizenList(app, dao.findAll()).getFrame());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    public void getDetail(Citizen citizen) {
        app.addPanel(new CitizenDetails(app, citizen).getFrame());
    }

    @Override
    public void fullTextSearch(String keyword) {
        List<Citizen> citizens = new ArrayList<>();
        try {
            citizens = dao.search(keyword.strip());
            System.out.println(citizens);
            if (citizens == null || citizens.size() == 0) {
                JOptionPane.showMessageDialog(app, "No results for" + keyword + "found. Showing all citizens.");
            }
            app.addPanel(new CitizenList(app, citizens).getFrame());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(app, "No results for " + keyword + " found.");
            e.printStackTrace();
        }
    }

    @Override
    public void getAllCriminals() {
        try{
            List<Citizen> citizens = dao.findAll();
            List<Citizen> criminals = new ArrayList<>();
            for(Citizen criminal : citizens){
                List<Fir> filteredFirs = new ArrayList<>();
                filteredFirs = firDao.filter(2, criminal.getCitizenshipNo());
                if(filteredFirs != null){
                    filteredFirs = filteredFirs.stream()
                                .filter(fir -> fir.getFiledAgainst().equals(criminal.getCitizenshipNo()))
                                .collect(Collectors.toList());
                    if(filteredFirs.size()>0){
                        criminals.add(criminal);
                    }
                }
                
            }
            app.addPanel(new CitizenList(app, criminals).getFrame());
        }
        catch(Exception e){
            e.printStackTrace();
            app.addPanel(new CitizenList(app, new ArrayList<>()).getFrame());
        }
    }
}
