package gr.codehub.universitydb.repository;

import gr.codehub.universitydb.DatabaseMain;
import gr.codehub.universitydb.model.Student;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class StudentRepository {

    private final Connection connection; //prosthesa final

    private static  final Properties dbProperties = null;
    private final String studentDepartment;

    public StudentRepository(Connection connection, String studentDepartment) {
        this.connection = connection;
        this.studentDepartment = studentDepartment;

    }

    public Student getStudent(int studentId) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "select * from student where id=" + studentId;
        ResultSet rs = statement.executeQuery(sql);
        Student actor = null;
        while(rs.next()){

            String studentName = rs.getString("name");
            System.out.println( studentId + " = " + studentName + ":"+studentDepartment);
            actor = new Student(studentId, studentName, Integer.parseInt(studentDepartment));
        }
        rs.close();
        return actor;
    }

    public List<Student> getAllStudents() throws SQLException {
        List<Student> allStudents = new ArrayList<>();
        Statement statement = connection.createStatement();
        String sql = "select * from student";
        ResultSet rs = statement.executeQuery(sql);
        Student student;
        while(rs.next()){
            String studentId = rs.getString("id");
            String studentName = rs.getString("name");
            String studentDepartment = rs.getString("depid");
            System.out.println( studentId + " = " + studentName + " "+studentDepartment);
            student = new Student(Integer.parseInt(studentId), studentName, Integer.parseInt(studentDepartment));
            allStudents.add(student);
        }
        rs.close();
        return allStudents;
    }


    public void updateStudents(int studentId, String studentName, String sql) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, studentName);
        statement.setInt(2,studentId);

        System.out.println(statement);


        int rs = statement.executeUpdate();

    }

    public void deleteStudents(int studentId) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "delete from student where id="+studentId;
        int rs = statement.executeUpdate(sql);

    }





    public Student createStudent(int studentId, String name, int departmentId, String sql) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, studentId);
        statement.setString(2,name);
        statement.setInt(3, departmentId);
        System.out.println(statement);
        int rs = statement.executeUpdate();
        return new Student(studentId, name, departmentId);
    }
}
