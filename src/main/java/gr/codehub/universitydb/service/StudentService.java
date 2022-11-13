package gr.codehub.universitydb.service;

import gr.codehub.universitydb.model.Student;
import gr.codehub.universitydb.repository.StudentRepository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class StudentService {
    private StudentRepository repository = null;


    public StudentService(Connection connection, String studentDepartment) {
        repository = new StudentRepository(connection, studentDepartment);
    }

    public Student findStudentWithId(int studentId) throws SQLException {
        return repository.getStudent(2);
    }

    public List<Student> findAllStudents() throws SQLException {
        return repository.getAllStudents();
    }

    public Student createStudent(int studentId, String name, int deparmentId, String sql) throws SQLException {
        return repository.createStudent(studentId, name, deparmentId, sql);
    }

    public void updateStudent(int studentId, String studentName, String sql) throws SQLException {
        repository.updateStudents(studentId, studentName, sql);
    }

    public void deleteStudent(int studentId) throws SQLException {
        repository.deleteStudents(studentId);
    }
}
