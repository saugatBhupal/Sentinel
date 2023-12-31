package model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class Citizen {

    @NonNull
    private Long citizenshipNo;
    @NonNull
    private String firstName;
    private String middleName;
    @NonNull
    private String lastName;
    @NonNull
    private Date DOB;
    @NonNull
    private String contact;
    @NonNull
    private String permanentAddress;
    @NonNull
    private String temporaryAddress;

    public String getFullName() {
        StringBuilder fullName = new StringBuilder();
        if (firstName != null) {
            fullName.append(firstName);
        }
        if (middleName != null) {
            fullName.append(fullName.length() > 0 ? " " + middleName : middleName);
        }
        if (lastName != null) {
            fullName.append(fullName.length() > 0 ? " " + lastName : lastName);
        }
        return fullName.toString();
    }   
}
