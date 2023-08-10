package controller.controllerImpl;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import config.passwordEncoderConfig.Encoder;
import controller.PoliceController;
import dao.OICDao;
import dao.PoliceDao;
import dao.daoImpl.OICDaoImpl;
import dao.daoImpl.PoliceDaoImpl;
import model.Police;
import views.App;
import views.ListFirPanel;
import views.LoginPanel;
import views.OICDashboard;
import views.PoliceDashboard;
import views.PoliceList;

public class PoliceControllerImpl implements PoliceController {

    private final OICDao oicDao;
    private final PoliceDao policeDao;
    private final JPanel panel;
    private final App app;

    public PoliceControllerImpl(JPanel panel, App app) {
        this.policeDao = new PoliceDaoImpl();
        this.oicDao = new OICDaoImpl();
        this.app = app;
        this.panel = panel;
    }

    @Override
    public Police register(Police police) {
        try {
            police.setPassword(Encoder.toHash(police.getPassword()));
            Long policeID = policeDao.save(police);
            Police savedPolice = policeDao.findById(policeID);
            if (savedPolice != null) {
                JOptionPane.showMessageDialog(panel, "Successfully Registered Police: " + savedPolice.getPoliceID()
                        + ", Name : " + savedPolice.getCitizen().getFirstName());
            }
            return savedPolice;
        }

        catch (Exception e) {
            JOptionPane.showMessageDialog(panel,
                    "Error Registering police,ID: " + police.getCitizenshipID() + " is already registered.");
            return (null);
        }
    }

    @Override
    public void authenticate(Police police) {
        try {
            Police targetPolice = policeDao.findById(police.getPoliceID());
            if (targetPolice != null) {
                if (Encoder.verify(police.getPassword(), targetPolice.getPassword())) {
                    app.context.setPolice(targetPolice);
                    if (oicDao.findByPoliceID(police.getPoliceID()) != null) {
                        app.context.setRole("oic");
                        app.addPanel(new OICDashboard(app).getFrame());
                        return;
                    }
                    app.context.setRole("police");
                    app.addPanel(new PoliceDashboard(app).getFrame());
                    return;
                }
                JOptionPane.showMessageDialog(panel, "Incorrect Password. Please try again");
            } else {
                JOptionPane.showMessageDialog(panel, "The given police ID does not exist !");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getAllPolice() {
        try {
            app.addPanel(new PoliceList(app, policeDao.findAll()).getFrame());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(panel, "Error Retrieving List of Police");
            e.printStackTrace();
        }
    }

    @Override
    public void logout() {
        try {
            app.context = null;
            app.addPanel(new LoginPanel(app).getFrame());
        } catch (Exception e) {

        }
    }

    @Override
    public Police resetPassword(Long policeID, String password) {
        try {
            Police police = policeDao.findById(policeID);
            if (police != null) {
                police.setPassword(Encoder.toHash(password));
                policeDao.update(police);
                JOptionPane.showMessageDialog(panel,
                        "Password of Police: " + policeID + " successfully reset.");
                return (policeDao.findById(policeID));
            } else {
                JOptionPane.showMessageDialog(panel,
                        "No Police Of ID: " + policeID + " exists.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(panel,
                    "Error resetting password");
            e.printStackTrace();
        }
        return (null);
    }

    @Override
    public Police search(Long policeID) {
        try {
            return (policeDao.findByPoliceID(policeID));
        } catch (Exception e) {
            e.printStackTrace();
            return (null);
        }
    }

    @Override
    public void fullTextSearch(String keyword) {
        List<Police> policeList = new ArrayList<>();
        try {
            policeList = policeDao.search(keyword);
            if (policeList == null || policeList.size() == 0) {
                JOptionPane.showMessageDialog(app, "No results for" + keyword + "found. Showing all firs.");
            }
            app.addPanel(new PoliceList(app, policeList).getFrame());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(app, "No results for " + keyword + " found.");
            e.printStackTrace();
        }
    }

    @Override
    public Police getPoliceByID(Long id) {
        try {
            return policeDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
