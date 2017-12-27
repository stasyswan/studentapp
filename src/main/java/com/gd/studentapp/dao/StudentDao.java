package com.gd.studentapp.dao;

import com.gd.studentapp.db.JDBCConnection;
import com.gd.studentapp.dto.Student;
import com.gd.studentapp.dto.StudentWithGroupName;
import com.gd.studentapp.repository.StudentRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jvnet.hk2.annotations.Service;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentDao implements StudentRepository {

    private static final Logger logger = LogManager.getLogger(StudentDao.class);

    @Inject
    private JDBCConnection jdbcConnection;

    DataSource dataSource;

    @PostConstruct
    private void init() {
        dataSource = jdbcConnection.setUp();
    }

    public void addStudent(Student student) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = dataSource.getConnection();

            String sql = "" +
                    "INSERT INTO student (name, surname, iq_level, group_id, birthday) " +
                    "VALUES (?, ?, ?, ?, ?)";

            ps = connection.prepareStatement(sql);

            ps.setString(1, student.getName());
            ps.setString(2, student.getSurname());
            ps.setInt(3, student.getIqLevel());
            ps.setInt(3, student.getIqLevel());
            ps.setInt(4, student.getGroupId());
            ps.setDate(5, student.getBirthday());

            ps.execute();

            logger.info("Added student: " + student.toString());
        } finally {
            if (ps != null) ps.close();
            if (connection != null) connection.close();
        }
    }

    public void deleteStudent(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = dataSource.getConnection();

            String sql = "" +
                    "DELETE FROM student " +
                    "WHERE id = ?";

            ps = connection.prepareStatement(sql);

            ps.setInt(1, id);

            ps.execute();

            logger.info("Deleted student with id: " + id);
        } finally {
            if (ps != null) ps.close();
            if (connection != null) connection.close();
        }
    }

    public void updateStudent(Student student) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = dataSource.getConnection();

            String sql = "" +
                    "UPDATE student " +
                    "SET name = ?, surname = ?, iq_level = ?, group_id = ?, birthday = ? " +
                    "WHERE id = ?";

            ps = connection.prepareStatement(sql);

            ps.setString(1, student.getName());
            ps.setString(2, student.getSurname());
            ps.setInt(3, student.getIqLevel());
            ps.setInt(4, student.getGroupId());
            ps.setDate(5, student.getBirthday());
            ps.setInt(6, student.getId());

            ps.execute();

            logger.info("Updated student: " + student.toString());
        } finally {
            if (ps != null) ps.close();
            if (connection != null) connection.close();
        }
    }

    public StudentWithGroupName getStudent(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        StudentWithGroupName student = null;

        try {
            connection = dataSource.getConnection();

            String sql = "" +
                    "SELECT `student`.id, " +
                    "   `student`.name, " +
                    "   `student`.surname, " +
                    "   `student`.iq_level, " +
                    "   `student`.birthday, " +
                    "   `group`.name AS group_name " +
                    "FROM `student` " +
                    "   INNER JOIN `group` ON (`student`.group_id = `group`.group_id)" +
                    "WHERE id = ?";

            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            final ResultSet rs = ps.executeQuery();

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

        } finally {
            if (ps != null) ps.close();
            if (connection != null) connection.close();
        }

        logger.info("Found student: " + student.toString());

        return student;
    }

    public List<StudentWithGroupName> getAllStudents() throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        List<StudentWithGroupName> list = new ArrayList<>();

        try {
            connection = dataSource.getConnection();

            String sql = "" +
                    "SELECT `student`.id, " +
                    "   `student`.name, " +
                    "   `student`.surname, " +
                    "   `student`.iq_level, " +
                    "   `student`.birthday, " +
                    "   `group`.name AS group_name " +
                    "FROM `student` " +
                    "   INNER JOIN `group` ON (`student`.group_id = `group`.group_id);";

            ps = connection.prepareStatement(sql);
            final ResultSet rs = ps.executeQuery();

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

        } finally {
            if (ps != null) ps.close();
            if (connection != null) connection.close();
        }

        logger.info("Updated student: " + list.toString());

        return list;
    }
}
