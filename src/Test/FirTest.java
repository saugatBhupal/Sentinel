package Test;

import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.Test;

import dao.FirDao;
import dao.daoImpl.FirDaoImpl;
import model.Citizen;
import model.Fir;
import model.Police;

public class FirTest {

    private final FirDao dao = new FirDaoImpl();
    
    @Test
    public void createFirTest(){ 
        Fir fir = new Fir(Long.valueOf("2000100"), Long.valueOf("20080614"), Long.valueOf("20102376"), Long.valueOf("20110016"), Date.valueOf(LocalDate.of(2023, 06, 02)), Time.valueOf(LocalTime.of(3,30)), "Create Fir Testing", "Cybercrime", null, Long.valueOf(200023), Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()), 0, new Citizen(), new Citizen(), new Police());
        assertTrue(dao.save(fir) != null);
    }

    @Test
    public void findByIdTest(){
        Fir fir = dao.findById(Long.parseLong("100123"));
        assertTrue(fir != null);
    }
    
    @Test
    public void findAllFirTest(){
        List<Fir> firs = dao.findAll();
        assertTrue(firs.size()>0);
    }

    @Test
    public void updateFirTest(){
        Fir fir = dao.findById(Long.parseLong("100123"));
        fir.setWitness(Long.valueOf("20101197"));
        assertTrue(dao.update(fir)==1);
    }

    @Test
    public void searchFirTest(){
        List<Fir> firs  = dao.search("200019");
        assertTrue(firs.size() > 0);
    }

    @Test 
    public void findRegisteredFirTest(){
        List<Fir> firs = dao.findRegisteredFir(Long.valueOf("200019"));
        assertTrue(firs.size() > 0);
    }

    @Test 
    public void filterFirTest(){
        List<Fir> firs = dao.filter(1,Long.valueOf("20102376"));
        assertTrue(firs.size() > 0);
    }
    
    @Test 
    public void findAllByCitizenIdTest(){
        List<Fir> firs = dao.findAllByID(Long.valueOf("20102376"));
        assertTrue(firs.size() > 0);
    }

}
