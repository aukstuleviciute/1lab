package vu.lt.rest.contracts;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StudentDto {

    private String Name;

    private Integer StudentNumber;

    private String TeamName;

    private String Scolarship;
}