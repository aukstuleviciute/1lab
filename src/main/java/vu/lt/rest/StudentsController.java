package vu.lt.rest;

import lombok.*;
import vu.lt.entities.Student;
import vu.lt.persistence.StudentsDAO;
import vu.lt.rest.contracts.StudentDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/students")
public class StudentsController {

    @Inject
    @Setter @Getter
    private StudentsDAO studentsDAO;

    @Inject
    private EntityManager em;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Student student = studentsDAO.findOne(id);
        if (student == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        StudentDto studentDto = new StudentDto();
        studentDto.setName(student.getName());
        studentDto.setStudentNumber(student.getStudentNumber());
        studentDto.setTeamName(student.getTeam().getName());
        studentDto.setScolarship(student.getValue());

        return Response.ok(studentDto).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(
            @PathParam("id") final Integer studentId,
            StudentDto studentData) {
        try {
            Student existingStudent = studentsDAO.findOne(studentId);
            if (existingStudent == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingStudent.setName(studentData.getName());
            existingStudent.setStudentNumber(studentData.getStudentNumber());
            studentsDAO.update(existingStudent);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @Path("/create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(
            @QueryParam("name") String name) {
        Student student = new Student();
        student.setName(name);

        studentsDAO.persist(student);

        return Response.ok().build();

    }
}