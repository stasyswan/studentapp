package com.gd.studentapp.controller;

import com.gd.studentapp.dto.Student;
import com.gd.studentapp.dto.StudentWithGroupName;
import com.gd.studentapp.exception.ResourceNotFoundEx;
import com.gd.studentapp.exception.RestException;
import com.gd.studentapp.service.StudentService;
import org.glassfish.jersey.process.internal.RequestScoped;


import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/api/v1/students")
@RequestScoped
public class StudentController {

    @Inject
    private StudentService studentService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public StudentWithGroupName getStudent(@PathParam("id") int id) throws RestException, ResourceNotFoundEx {
        try {
            final StudentWithGroupName student = studentService.getStudent(id);
            if (student == null) {
                throw new ResourceNotFoundEx("No student found with id = " + id);
            }
            return student;
        } catch (SQLException e) {
            throw new RestException("Error retrieving student with id = " + id, e);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<StudentWithGroupName> getAllStudents() throws ResourceNotFoundEx, RestException {
        try {
            final List<StudentWithGroupName> allStudents = studentService.getAllStudents();
            if (allStudents.size() == 0) {
                throw new ResourceNotFoundEx("No student found");
            }
            return allStudents;
        } catch (SQLException e) {
            throw new RestException("Error retrieving all students", e);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addStudent(Student student) throws RestException {
        try {
            studentService.addStudent(student);
        } catch (SQLException e) {
            throw new RestException("Error adding student", e);
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteStudent(@PathParam("id") int id) throws RestException {
        try {
            studentService.deleteStudent(id);
        } catch (SQLException e) {
            throw new RestException("Error deleting student with id = " + id, e);
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateStudent(Student student) throws RestException {
        try {
            studentService.updateStudent(student);
        } catch (SQLException e) {
            throw new RestException("Error updating student with id = " + student.getId() + e.getMessage(), e);
        }
    }
}
