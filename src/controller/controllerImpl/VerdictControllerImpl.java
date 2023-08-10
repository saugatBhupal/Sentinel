package controller.controllerImpl;

import javax.swing.JOptionPane;

import controller.CaseController;
import controller.FirController;
import controller.PoliceController;
import controller.VerdictController;
import dao.CaseDao;
import dao.VerdictDao;
import dao.daoImpl.CaseDaoImpl;
import dao.daoImpl.VerdictDaoImpl;
import model.Case;
import model.Fir;
import model.Police;
import model.Verdict;
import views.App;
import views.AssignedCaseDetailPanel;

public class VerdictControllerImpl implements VerdictController {

    private final VerdictDao verdictDao;
    private final FirController firController;
    private final CaseController caseController;
    private final App app;

    public VerdictControllerImpl(App app) {
        verdictDao = new VerdictDaoImpl();
        this.firController = new FirControllerImpl(app);
        this.caseController = new CaseControllerImpl(app);
        this.app = app;
    }

    @Override
    public Verdict saveVerdict(Verdict verdict) {
        try {
            Long verdictID = verdictDao.save(verdict);
            if(verdictID !=null){
                Verdict v = verdictDao.findByID(verdictID);
                Fir fir = v.getFir();
                fir.setStatus(2);
                firController.updateFir(fir);
                app.addPanel(new AssignedCaseDetailPanel(app, v.getCs(), caseController.getAllLogs(v.getCaseID())).getFrame());
                return verdictDao.findByID(verdictID);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Verdict getVerdict(Case cs) {
        try{
            Verdict verdict = verdictDao.findByCaseID(cs.getCaseID());
            return verdict;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
