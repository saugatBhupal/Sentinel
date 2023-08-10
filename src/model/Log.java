package model;

import java.sql.Date;
import java.sql.Time;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class Log {
    private Long logsID;
    @NonNull
    private String message;
    @NonNull
    private Date logDate;
    @NonNull
    private Time logTime;
    private String media;
    @NonNull
    private Long caseID;
}
