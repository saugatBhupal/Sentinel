package dao;

import java.sql.SQLException;
import java.util.List;

import model.Log;
import model.Case;

public interface CaseDao {
    Case findByID(Long caseID);
    Log findLogByID(Long logID);
    List<Case> findAll();
    List<Log> findAllLogs(Long caseID);
    Long save(Case cs);
    Long saveLog(Log log);
    List<Case> findByPoliceID(Long policeID);
    List<Case> search(String keyword);
    Case findByFirID(Long firID);
}
