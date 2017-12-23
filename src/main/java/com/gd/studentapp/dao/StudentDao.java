package com.gd.studentapp.dao;

import com.gd.studentapp.db.JDBCConnection;
import com.gd.studentapp.dto.Student;
import com.gd.studentapp.dto.StudentWithGroupName;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    private Connection connection;

    public StudentDao() {
        JDBCConnection jdbcConnection = new JDBCConnection();
        connection = jdbcConnection.getConnnection();
    }

    public void addStudent(Student student) throws SQLException {
        String sql = "INSERT INTO student (name, surname, iq_level, group_id, birthday) VALUES (?, ?, ?, ?, ?)";
        final PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, student.getName());
        ps.setString(2, student.getSurname());
        ps.setInt(3, student.getIqLevel());
        ps.setInt(3, student.getIqLevel());
        ps.setInt(4, student.getGroupId());
        ps.setDate(5, student.getBirthday());
        ps.execute();
//        connection.commit();
        ps.close();
    }

    public void deleteStudent(int id) throws SQLException {
        String sql = "DELETE FROM student WHERE id = ?";
        final PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ps.execute();
//        connection.commit();
        ps.close();
    }

    public void updateStudent(Student student) throws SQLException {
        String sql = "UPDATE student SET name = ?, surname = ?, iq_level = ?, group_id = ?, birthday = ? WHERE id = ?";

        final PreparedStatement ps = connection.prepareStatement(sql);

        ps.setString(1, student.getName());
        ps.setString(2, student.getSurname());
        ps.setInt(3, student.getIqLevel());
        ps.setInt(4, student.getGroupId());
        ps.setDate(5, student.getBirthday());
        ps.setInt(6, student.getId());
        ps.execute();
//        connection.commit();
        ps.close();
    }

    public StudentWithGroupName getStudent(int id) throws SQLException {
        String sql = "SELECT `student`.id, `student`.name, `student`.surname, `student`.iq_level, `student`.birthday, `group`.name AS group_name FROM `student` INNER JOIN `group` ON (`student`.group_id = `group`.group_id)WHERE id = ?";
        final PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        final ResultSet rs = ps.executeQuery();
        StudentWithGroupName student = null;
        while (rs.next()) {
            student = new StudentWithGroupName();
            student.setId(rs.getInt("id"));
            student.setName(rs.getString("name"));
            student.setSurname(rs.getString("surname"));
            student.setIqLevel(rs.getInt("iq_level"));
            student.setBirthday(rs.getDate("birthday"));
            student.setGroupName(rs.getString("group_name"));
            break;
        }
        ps.close();
        return student;
    }

    public List<StudentWithGroupName> getAllStudents() throws SQLException {
        String sql = "SELECT `student`.id, `student`.name, `student`.surname, `student`.iq_level, `student`.birthday, `group`.name AS group_name FROM `student` INNER JOIN `group` ON (`student`.group_id = `group`.group_id);";
        final PreparedStatement ps = connection.prepareStatement(sql);
        final ResultSet rs = ps.executeQuery();
        List<StudentWithGroupName> list = new ArrayList<>();
        while (rs.next()) {
            StudentWithGroupName student = new StudentWithGroupName();
            student.setId(rs.getInt("id"));
            student.setName(rs.getString("name"));
            student.setSurname(rs.getString("surname"));
            student.setIqLevel(rs.getInt("iq_level"));
            student.setBirthday(rs.getDate("birthday"));
            student.setGroupName(rs.getString("group_name"));
            list.add(student);
        }
        ps.close();
        return list;
    }
}
