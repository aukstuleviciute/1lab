package vu.lt.usecases;

import vu.lt.alternatives.Scolarship;
import vu.lt.entities.Student;
import vu.lt.persistence.StudentsDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Model
public class Students implements Serializable {

    @Inject
    private StudentsDAO studentsDAO;

    @Inject
    private Scolarship scolarship;

    @Getter
    @Setter
    private Student studentToCreate = new Student();

    private List<Student> allStudents;

    @PostConstruct
    public void init() {
        loadStudents();
    }

    private void loadStudents() {
        this.allStudents = studentsDAO.loadAll();
    }

    public List<Student> getAllStudents() {
        return allStudents;
    }

    @Transactional
    public String createStudent() {
        studentToCreate.setValue(scolarship.ScolarshipType());
        studentsDAO.persist(studentToCreate);
        return "index?faces-redirect=true";
    }
}
