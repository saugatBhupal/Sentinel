package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Police {

    private Long policeID;
    @NonNull
    private String password;
    private String department;
    private String Position;
    private Long citizenshipID;
    private Citizen citizen;

    public Police(Long policeID, String password ){
        this.policeID = policeID;
        this.password = password;
    }
}
