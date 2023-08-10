package Test;

import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.Test;

import dao.VerdictDao;
import dao.daoImpl.VerdictDaoImpl;
import model.Case;
import model.Fir;
import model.Verdict;

public class VerdictTest {
    private final VerdictDao dao = new VerdictDaoImpl();
    
    @Test
    public void saveTest(){
        Verdict verdict = new Verdict(Long.valueOf(5), Long.valueOf("100105"), Date.valueOf(LocalDate.now()), "hbvxnmkx", new Case(), new Fir());
        assertTrue(dao.save(verdict) != null);
    }

    @Test
    public void findByIdTest(){
        Verdict verdict = dao.findByID(Long.parseLong("3"));
        assertTrue(verdict != null);
    }

    @Test
    public void findByCaseIdTest(){
        Verdict verdict = dao.findByCaseID(Long.parseLong("100105"));
        assertTrue(verdict != null);
    }
}
