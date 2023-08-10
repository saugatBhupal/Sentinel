package dao;

import model.Verdict;

public interface VerdictDao {
    Long save(Verdict verdict);
    Verdict findByID(Long verdictID);
    Verdict findByCaseID(Long caseID);
}
