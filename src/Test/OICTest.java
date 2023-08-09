package Test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import dao.OICDao;
import dao.daoImpl.OICDaoImpl;
import model.OIC;

public class OICTest {
    private final OICDao dao = new OICDaoImpl();
    @Test
    public void findById() {
        OIC oic = dao.findByPoliceID(Long.parseLong("200020"));
        assertTrue(oic != null);
    }
}
