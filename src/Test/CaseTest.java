package Test;

import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.Test;

import dao.CaseDao;
import dao.daoImpl.CaseDaoImpl;
import model.Case;
import model.Fir;
import model.Log;
import model.Police;

public class CaseTest {
    private final CaseDao dao = new CaseDaoImpl();

    @Test
    public void findByIdTest(){
        Case cs = dao.findByID(Long.parseLong("100105"));
        assertTrue(cs != null);
    }

    @Test
    public void findLogsByIdTest(){
        Log log = dao.findLogByID(Long.parseLong("3"));
        assertTrue(log != null);
    }

    @Test
    public void findAllTest(){
        List<Case> cases = dao.findAll();
        assertTrue(cases.size()>0);
    }

    @Test
    public void findAllLogsTest(){
        List<Log> logs = dao.findAllLogs(Long.valueOf("100105"));
        assertTrue(logs.size()>0);
    }

    @Test
    public void saveTest(){
        Case cs = new Case(Long.valueOf("100107"), Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()), Long.valueOf("100104"), Long.valueOf("200023"), new Fir(), new Police());
        assertTrue(dao.save(cs) != null);
    }
    @Test
    public void saveLogTest(){
        Log log = new Log("Save log test", Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()),Long.valueOf("100107"));
        assertTrue(dao.saveLog(log) != null);
    }

    @Test
    public void findAssignedTest(){
        List<Case> cases = dao.findByPoliceID(Long.valueOf("200023"));
        assertTrue(cases.size()>0);
    }

    @Test
    public void searchCaseTest(){
        List<Case> cases  = dao.search("100104");
        assertTrue(cases.size() > 0);
    }

    @Test
    public void findByFIRIdTest(){
        Case cs = dao.findByFirID(Long.parseLong("100110"));
        assertTrue(cs != null);
    }

}
