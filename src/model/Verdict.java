package model;

import java.sql.Date;
import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Verdict {
    private Long verdictID;
    private Long caseID;
    private Date verdictDate;
    private String verdict;
    private Case cs;
    private Fir fir;
}
