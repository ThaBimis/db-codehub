package gr.codehub.actorsdb.repository;

import gr.codehub.actorsdb.model.Department;
import gr.codehub.actorsdb.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

    private final Connection connection; //prosthesa final
    private final String studentDepartment;

    public StudentRepository(Connection connection, String studentDepartment){
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
            //String courseName = rs.getString("test_fname");
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
            String studentDepartment = rs.getString("department");
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
