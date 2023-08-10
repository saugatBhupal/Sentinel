package dao;

import model.OIC;

public interface OICDao {
    OIC findByPoliceID(Long policeID);
}
