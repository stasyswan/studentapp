package com.gd.studentapp.service;

import com.gd.studentapp.dto.Student;
import com.gd.studentapp.dto.StudentWithGroupName;
import com.gd.studentapp.repository.StudentRepository;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Inject
    private StudentRepository studentRepository;

    public void addStudent(Student student) throws SQLException {
        studentRepository.addStudent(student);
    }

    public void deleteStudent(int id) throws SQLException {
        studentRepository.deleteStudent(id);
    }

    public void updateStudent(Student student) throws SQLException {
        studentRepository.updateStudent(student);
    }

    public StudentWithGroupName getStudent(int id) throws SQLException {
        return studentRepository.getStudent(id);
    }

    public List<StudentWithGroupName> getAllStudents() throws SQLException {
        return studentRepository.getAllStudents();
    }
}
