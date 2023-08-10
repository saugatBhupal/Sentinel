package controller;

import model.Case;
import model.Verdict;

public interface VerdictController {
    Verdict saveVerdict(Verdict verdict);
    Verdict getVerdict(Case cs);
}
