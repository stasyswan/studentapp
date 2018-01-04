package com.gd.studentapp.service;

import com.gd.studentapp.dto.Student;
import com.gd.studentapp.dto.StudentWithGroupName;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.jvnet.hk2.annotations.Contract;

import java.sql.SQLException;
import java.util.List;

@Contract
@RequestScoped
public interface StudentService {
    void updateStudent(Student student) throws SQLException;

    void deleteStudent(int id) throws SQLException;

    void addStudent(Student student) throws SQLException;

    List<StudentWithGroupName> getAllStudents() throws SQLException;

    StudentWithGroupName getStudent(int id) throws SQLException;
}
