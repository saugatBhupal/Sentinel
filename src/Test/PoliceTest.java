package Test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import dao.PoliceDao;
import dao.daoImpl.PoliceDaoImpl;
import model.Citizen;
import model.Police;


public class PoliceTest {
    private final PoliceDao dao = new PoliceDaoImpl();

    @Test
    public void registerTest(){
        Police police = new Police(Long.parseLong("200100"), "12345", "Kathmandu", "Inspector", Long.parseLong("2000201"),new Citizen());
        assertTrue(dao.save(police)!=null);
    }

    @Test
    public void findAllPoliceTest(){
        List<Police> police = dao.findAll();
        assertTrue(police.size()>0);
    }
    
    @Test
    public void findById(){
        Police police = dao.findByPoliceID(Long.parseLong("200020"));
        assertTrue(police != null);
    }

    @Test
    public void searchPolice(){
        List<Police> police  = dao.search("200019");
        assertTrue(police.size() > 0);
    }
    
    @Test
    public void updatePolice(){
        Police police = dao.findById(Long.parseLong("200020"));
        String originalPassword = police.getPassword();
        police.setPassword("abcd");
        dao.update(police);
        assertTrue(dao.findById(police.getPoliceID()).getPassword() != originalPassword);
    }
}
