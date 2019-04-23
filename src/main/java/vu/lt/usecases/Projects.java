package vu.lt.usecases;
import vu.lt.entities.Project;
import vu.lt.persistence.ProjectsDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Projects {
    @Inject
    private ProjectsDAO projectsDAO;

    @Getter @Setter
    private Project projectToCreate = new Project();

    @Getter
    private List<Project> allProjects;

    @PostConstruct
    public void init() {
        loadAllProjects();
    }

    @Transactional
    public String createProject() {
        projectsDAO.persist(projectToCreate);
        return "index?faces-redirect=true";
    }

    private void loadAllProjects() {
        allProjects = projectsDAO.loadAll();
    }
}
