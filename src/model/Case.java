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
public class Case {
    private Long caseID;
    private Date registeredDate;
    private Time registeredTime;
    private Long firID;
    private Long assignedTo;

    private Fir fir;
    private Police assignedPolice;
}
