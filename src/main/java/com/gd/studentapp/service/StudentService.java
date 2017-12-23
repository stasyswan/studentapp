package com.gd.studentapp.service;

import com.gd.studentapp.dao.StudentDao;
import com.gd.studentapp.dto.Student;
import com.gd.studentapp.dto.StudentWithGroupName;

import java.sql.SQLException;
import java.util.List;

public class StudentService {
    private final StudentDao studentDAO;

    public StudentService(StudentDao studentDAO) {
        this.studentDAO = studentDAO;
    }

    public void addStudent(Student student) throws SQLException {
        studentDAO.addStudent(student);
    }

    public void deleteStudent(int id) throws SQLException {
        studentDAO.deleteStudent(id);
    }

    public void updateStudent(Student student) throws SQLException {
        studentDAO.updateStudent(student);
    }

    public StudentWithGroupName getStudent(int id) throws SQLException {
        return studentDAO.getStudent(id);
    }

    public List<StudentWithGroupName> getAllStudents() throws SQLException {
        return studentDAO.getAllStudents();
    }
}
