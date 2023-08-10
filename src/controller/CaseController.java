package controller;

import java.util.List;
import model.Case;
import model.Log;

public interface CaseController {
    Case searchById(Long caseID);
    List<Case> getAllCases();
    List<Log> getAllLogs(Long caseID);
    Case registerCase(Case cs);
    void getDetail(Case cs, List<Log> logs);
    List<Case> getAssignedCases(Long policeID);
    Log registerLog(Log log);
    void fullTextSearch(String keyword);
    Case findByFirID(Long firID);
}
 