package vu.lt.usecases;

import javax.transaction.Transactional;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Map;

import vu.lt.entities.Student;
import vu.lt.persistence.StudentsDAO;
import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Team;
import vu.lt.persistence.TeamsDAO;

@Model
public class StudentsForTeam implements Serializable {

    @Inject
    private TeamsDAO teamsDAO;

    @Inject
    private StudentsDAO studentsDAO;

    @Getter
    @Setter
    private Team team;

    @Getter
    @Setter
    private Student studentToCreate = new Student();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer teamId = Integer.parseInt(requestParameters.get("teamId"));
        this.team = teamsDAO.findOne(teamId);
    }

    @Transactional
    public String createStudent() {
        studentToCreate.setTeam(this.team);
        studentsDAO.persist(studentToCreate);
        return "students?faces-redirect=true&teamId=" + this.team.getId();
    }
}