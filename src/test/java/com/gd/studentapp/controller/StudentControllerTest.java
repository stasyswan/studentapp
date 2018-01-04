package com.gd.studentapp.controller;

import com.gd.studentapp.dto.Student;
import com.gd.studentapp.dto.StudentWithGroupName;
import com.gd.studentapp.service.StudentService;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

import org.junit.*;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StudentControllerTest  extends JerseyTest {

    static StudentService studentService = mock(StudentService.class);

    @Override
    protected Application configure() {
        return new ResourceConfig(StudentController.class).register(new AbstractBinder(){
            @Override
            public void configure() {
                bind(studentService).to(StudentService.class);

//                bind(JDBCConnectionImpl.class).to(JDBCConnection.class);
//                bind(StudentServiceImpl.class).to(StudentService.class);
//                bind(StudentDao.class).to(StudentRepository.class);
            }
        });
    }

    @Test
    public void getStudentResponse() throws SQLException {
        when(studentService.getStudent(1)).thenReturn(
                new StudentWithGroupName(
                        1,
                        "John",
                        "Doe",
                        10,
                        "lasy",
                        Date.valueOf("2017-12-22")));

        Response response = target("/api/v1/students/1").request().get();
            Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void getStudent() throws SQLException {
        when(studentService.getStudent(1)).thenReturn(
                new StudentWithGroupName(
                        1,
                        "John",
                        "Doe",
                        10,
                        "lasy",
                        Date.valueOf("2017-12-22")));

        StudentWithGroupName response = target("/api/v1/students/1").request().get(StudentWithGroupName.class);

        Assert.assertEquals(response.getId(), 1);
        Assert.assertEquals(response.getGroupName(), "lasy");
        Assert.assertEquals(response.getBirthday().toString().trim(), Date.valueOf("2017-12-22").toString());
        Assert.assertEquals(response.getName(), "John");
        Assert.assertEquals(response.getSurname(), "Doe");
        Assert.assertEquals(response.getIqLevel(), 10);
    }

    @Test
    public void getAllStudents() throws SQLException {
        List<StudentWithGroupName> students = new ArrayList<>();
        students.add(new StudentWithGroupName(
                1,
                "John",
                "Doe",
                10,
                "lasy",
                Date.valueOf("2017-12-22")));
        students.add(new StudentWithGroupName(
                2,
                "Jane",
                "Doe",
                100,
                "middle",
                Date.valueOf("2017-12-22")));
        when(studentService.getAllStudents()).thenReturn(students);
        students.get(0).getBirthday();

        String response = target("/api/v1/students/").request().get(String.class);
        Assert.assertEquals(response,
                "[{\"groupName\":\"lasy\"," +
                        "\"birthday\":\"2017-12-22\"," +
                        "\"id\":1," +
                        "\"name\":\"John\"," +
                        "\"surname\":\"Doe\"," +
                        "\"iqLevel\":10}," +
                        "{\"groupName\":\"middle\"," +
                        "\"birthday\":\"2017-12-22\"," +
                        "\"id\":2," +
                        "\"name\":\"Jane\"," +
                        "\"surname\":\"Doe\"," +
                        "\"iqLevel\":100}]");
    }

    @Test
    public void addStudent() throws SQLException {
        Student student = new Student(
                1,
                "John",
                "Doe",
                10,
                1,
                Date.valueOf("2017-12-22"));

        Response response = target("/api/v1/students")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(student), Response.class);

        Assert.assertEquals(204, response.getStatus());
    }

    @Test
    public void deleteStudent() throws SQLException {
        doNothing().when(studentService).deleteStudent(1);

        Response response = target("/api/v1/students/1").request().delete();

        Assert.assertEquals(204, response.getStatus());
    }

    @Test
    public void updateStudent() throws SQLException {
        Student student = new Student(
                1,
                "John",
                "Doe",
                10,
                1,
                Date.valueOf("2017-12-22"));

        Response response = target("/api/v1/students")
                .request()
                .put(Entity.entity(student, MediaType.APPLICATION_JSON));;

        Assert.assertEquals(204, response.getStatus());
    }

}