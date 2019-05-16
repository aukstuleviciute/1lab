package vu.lt.usecases;


import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Student;
import vu.lt.persistence.StudentsDAO;
//import vu.lt.interceptors.LoggedInvocation;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@ViewScoped
@Named
@Getter @Setter
public class UpdateStudentDetails implements Serializable {

    private Student student;

    @Inject
    private StudentsDAO studentsDAO;

    @PostConstruct
    private void init() {
        System.out.println("UpdateStudentDetails INIT CALLED");
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer studentId = Integer.parseInt(requestParameters.get("studentId"));
        this.student = studentsDAO.findOne(studentId);
    }

    @Transactional
    //@LoggedInvocation
    public String updateStudentNumber() {
        try{
            studentsDAO.update(this.student);
        } catch (OptimisticLockException e) {
            return "/studentDetails.xhtml?faces-redirect=true&studentId=" + this.student.getId() + "&error=optimistic-lock-exception";
        }
        return "students.xhtml?teamId=" + this.student.getTeam().getId() + "&faces-redirect=true";
    }
}