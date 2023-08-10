package controller.controllerImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import controller.FirController;
import dao.FirDao;
import dao.daoImpl.FirDaoImpl;
import model.Fir;
import views.App;
import views.CitizenList;
import views.CreateFirPanel;
import views.ListFirPanel;
import views.UpdateFir;
import views.ViewFirPanel;

public class FirControllerImpl implements FirController {
    private final FirDao dao;
    private final App app;

    public FirControllerImpl(App app) {
        this.dao = new FirDaoImpl();
        this.app = app;
    }

    @Override
    public Fir save(Fir f) {
        Fir fir = null;
        try {
            Long firID = dao.save(f);
            System.out.println(firID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fir;
    }

    @Override
    public List<Fir> getAllFir() {
        try {
            if (app.context.getRole().equals("police")) {
                app.addPanel(
                        new ListFirPanel(dao.findRegisteredFir(app.context.getPolice().getPoliceID()), app).getFrame());
            } else {
                app.addPanel(new ListFirPanel(dao.findAll(), app).getFrame());
            }
            return (dao.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void getCreateFirPage() {
        try {
            app.addPanel(new CreateFirPanel(app).getFrame());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(app, "Error redirecting to create F.I.R Page");
        }
    }

    @Override
    public void getDetail(Fir fir) {
        app.addPanel(new ViewFirPanel(app, fir).getFrame());
    }

    @Override
    public void getUpdate(Fir fir) {
        app.addPanel(new UpdateFir(app, fir).getFrame());
    }

    @Override
    public void updateFir(Fir fir) {
        try {
            Fir targetFir = dao.findById(fir.getFirID());
            if (targetFir != null) {
                dao.update(fir);
                app.addPanel(new ViewFirPanel(app, fir).getFrame());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Fir getFirByID(Long firID) {
        try {
            return dao.findById(firID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void fullTextSearch(String keyword) {
        List<Fir> firs = new ArrayList<>();
        try{
            firs = dao.search(keyword.strip());
            if (firs == null || firs.size() == 0) {
                JOptionPane.showMessageDialog(app, "No results for" + keyword + "found. Showing all firs.");
            }
            app.addPanel(new ListFirPanel(firs, app).getFrame());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(app, "No results for " + keyword + " found.");
            e.printStackTrace();
        }
    }

    @Override
    public <T> List<Fir> filterByStatus(T status, Long citizenID){
        try{
            if(status == "*"){
                return(dao.findAllByID(citizenID));
            }
            List<Fir> filteredFirs = dao.filter(Integer.parseInt(status.toString()), citizenID);
            if(Integer.parseInt(status.toString()) == 2){
                filteredFirs = filteredFirs.stream()
                    .filter(fir -> fir.getFiledAgainst().equals(citizenID))
                    .collect(Collectors.toList());
            }
            return(filteredFirs);
        }
        catch(Exception e){
            return new ArrayList<>();
        }
    }
}
