package controller.controllerImpl;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.CaseController;
import controller.FirController;
import dao.CaseDao;
import dao.daoImpl.CaseDaoImpl;
import model.Case;
import model.Fir;
import model.Log;
import model.Police;
import views.App;
import views.AssignedCaseDetailPanel;
import views.AssignedCasesPanel;

public class CaseControllerImpl implements CaseController {

    private final CaseDao caseDao;
    private final App app;
    private final FirController firController;

    public CaseControllerImpl(App app) {
        caseDao = new CaseDaoImpl();
        this.firController = new FirControllerImpl(app);
        this.app = app;
    }

    @Override
    public Case searchById(Long caseID) {
        throw new UnsupportedOperationException("Unimplemented method 'searchById'");
    }

    @Override
    public List<Case> getAllCases() {
        try {
            if (app.context.getRole().equals("police")) {
                app.addPanel(new AssignedCasesPanel(app, caseDao.findByPoliceID(app.context.getPolice().getPoliceID()))
                        .getFrame());
            } else {
                app.addPanel(new AssignedCasesPanel(app, caseDao.findAll()).getFrame());
            }
            return (caseDao.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Case registerCase(Case cs) {
        try {
            Police police =  cs.getAssignedPolice();
            if (police != null)  {
                Long caseID = caseDao.save(cs);
                if (caseID != null) {
                    System.out.println(cs.getFirID());
                    Fir fir = cs.getFir();
                    fir.setStatus(1);
                    firController.updateFir(fir);
                    JOptionPane.showMessageDialog(
                            app, "Case assigned to Police Of ID: " + cs.getAssignedTo() + " .");
                    return caseDao.findByID(caseID);
                }
            } else {
                JOptionPane.showMessageDialog(
                        app, "No Police Of ID: " + cs.getAssignedTo() + " exists.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Case> getAssignedCases(Long policeID) {
        try {
            List<Case> cases = caseDao.findByPoliceID(policeID);
            return cases;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void getDetail(Case cs, List<Log> logs) {
        app.addPanel(new AssignedCaseDetailPanel(app, cs, logs).getFrame());
    }

    @Override
    public Log registerLog(Log log) {
        try {
            Long logID = caseDao.saveLog(log);
            System.out.println("log");
            return (caseDao.findLogByID(logID));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Log> getAllLogs(Long caseID) {
        try {
            return caseDao.findAllLogs(caseID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void fullTextSearch(String keyword) {
        List<Case> cases = new ArrayList<>();
        try {
            cases = caseDao.search(keyword.strip());
            if (cases == null || cases.size() == 0) {
                JOptionPane.showMessageDialog(app, "No results for" + keyword + "found. Showing all cases.");
            }
            app.addPanel(new AssignedCasesPanel(app, cases).getFrame());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(app, "No results for " + keyword + " found.");
            e.printStackTrace();
        }
    }

    @Override
    public Case findByFirID(Long firID) {
        try{
            Case cs = caseDao.findByFirID(firID);
            return cs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
