package vu.lt.decorators;

import vu.lt.entities.Student;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Decorator
public abstract class StudentsDecorator implements Persist {

    @Inject @Delegate @Any
    Persist studentsD;

    @Transactional
    public void persist(Student student) {
        System.out.println("Decorator called");
        studentsD.persist(student);

    }

}
