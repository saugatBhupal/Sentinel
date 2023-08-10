package Test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import dao.CitizenDao;
import dao.daoImpl.CitizenDaoImpl;
import model.Citizen;

public class CitizenTest {
    
    private final CitizenDao dao = new CitizenDaoImpl();

    @Test
    public void findByIdTest(){
        Citizen citizen = dao.findByCitizenID(Long.parseLong("20101197"));
        assertTrue(citizen != null);
    }
        
    @Test
    public void findAllCitizenTest(){
        List<Citizen> citizens = dao.findAll();
        assertTrue(citizens.size()>0);
    }

    @Test
    public void searchFirTest(){
        List<Citizen> citizens  = dao.search("Abhishek");
        assertTrue(citizens.size() > 0);
    }
}
