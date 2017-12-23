package com.gd.studentapp.controller;

import com.gd.studentapp.dao.StudentDao;
import com.gd.studentapp.dto.Student;
import com.gd.studentapp.dto.StudentWithGroupName;
import com.gd.studentapp.exception.ResourceNotFoundEx;
import com.gd.studentapp.exception.RestException;
import com.gd.studentapp.service.StudentService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/api/v1/students")
public class StudentController {

    private StudentService studentService = new StudentService(new StudentDao());

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public StudentWithGroupName getStudent(@PathParam("id") int id) throws RestException, ResourceNotFoundEx {
        try {
            final StudentWithGroupName employee = studentService.getStudent(id);
            if (employee == null) {
                throw new ResourceNotFoundEx("No student found with id = " + id);
            }
            return employee;
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
            throw new RestException("Error retrieving all students" + e.getMessage(), e);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addEmployee(Student student) throws RestException {
        try {
            studentService.addStudent(student);
        } catch (SQLException e) {
            throw new RestException("Error adding student", e);
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteEmployee(@PathParam("id") int id) throws RestException {
        try {
            studentService.deleteStudent(id);
        } catch (SQLException e) {
            throw new RestException("Error deleting student with id = " + id, e);
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateEmployee(Student student) throws RestException {
        try {
            studentService.updateStudent(student);
        } catch (SQLException e) {
            throw new RestException("Error updating student with id = " + student.getId() + e.getMessage(), e);
        }
    }
}
