package vu.lt.persistence;

import vu.lt.decorators.Persist;
import vu.lt.entities.Student;
import javax.inject.Inject;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class StudentsDAO implements Persist {
    @Inject
    private EntityManager em;
    public List<Student> loadAll() {
        return em.createNamedQuery("Student.findAll", Student.class).getResultList();
    }
    public void persist(Student student){
        this.em.persist(student);
    }

    public Student findOne(Integer id){
        return em.find(Student.class, id);
    }

    public Student update(Student student){
        return em.merge(student);
    }
}