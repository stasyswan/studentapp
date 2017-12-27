package com.gd.studentapp.repository;


import com.gd.studentapp.dto.Student;
import com.gd.studentapp.dto.StudentWithGroupName;
import org.jvnet.hk2.annotations.Contract;

import java.sql.SQLException;
import java.util.List;

@Contract
public interface StudentRepository {
    List<StudentWithGroupName> getAllStudents() throws SQLException;

    StudentWithGroupName getStudent(int id) throws SQLException;

    void updateStudent(Student student) throws SQLException;

    void deleteStudent(int id) throws SQLException;

    void addStudent(Student student) throws SQLException;
}
