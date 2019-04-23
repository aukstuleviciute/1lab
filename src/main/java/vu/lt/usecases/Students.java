package vu.lt.usecases;

import vu.lt.entities.Student;
import vu.lt.persistence.StudentsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Model
public class Students implements Serializable {

    @Inject
    private StudentsDAO studentsDAO;

    private Student studentToCreate = new Student();

    private List<Student> allStudents;

    @PostConstruct
    public void init(){
        loadStudents();
    }

    public void loadStudents() {
        this.allStudents = studentsDAO.loadAll();
    }

    public List<Student> getAllStudents(){
        return allStudents;
    }

    @Transactional
    public String createStudent(){
        this.studentsDAO.persist(studentToCreate);
        return "success";
    }

    public Student getStudentToCreate() {
        return studentToCreate;
    }

    public void setStudentToCreate(Student studentToCreate) {
        this.studentToCreate = studentToCreate;
    }
}