package vu.lt.persistence;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import vu.lt.entities.Project;
@ApplicationScoped
public class ProjectsDAO {
    @Inject
    private EntityManager em;

    public List<Project> loadAll() {
        return em.createNamedQuery("Project.findAll", Project.class).getResultList();
    }


    public void persist(Project project) {
        em.persist(project);
    }

    public void remove(Project project) {
        em.remove(project);
    }
}
