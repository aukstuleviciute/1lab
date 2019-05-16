package vu.lt.entities;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.apache.johnzon.mapper.JohnzonIgnore;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Student.findAll", query = "select a from Student as a")
})
@Table(name = "STUDENT")
@Getter
@Setter

public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "NAME")

    private String name;

    @Column(name = "STUDENT_NUMBER")
    private Integer studentNumber;

    @Column(name = "SCOLARSHIP")
    private String value;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    //@JohnzonIgnore
    private Team team;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;

    @ManyToMany
    @JoinTable(name = "STUDENT_PROJECT")
    //@JohnzonIgnore
    private List<Project> projects = new ArrayList<>();


    public Student() {
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) &&
                Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}