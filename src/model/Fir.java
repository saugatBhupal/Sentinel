package model;

import java.sql.Date;
import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Fir {

    @NonNull
    private Long firID;
    private Long filedBy;
    private Long filedAgainst;
    private Long witness;
    @NonNull
    private Date filedDate;
    @NonNull
    private Time filedTime;
    @NonNull
    private String description;
    @NonNull
    private String category;
    private String evidence;
    @NonNull
    private Long registeredBy;
    private Date registeredDate;
    private Time registeredTime;
    @NonNull
    private Integer status;

    private Citizen victimCitizen;
    private Citizen offenderCitizen;
    private Police police;
}
