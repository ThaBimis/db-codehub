package gr.codehub.universitydb.repository;

import gr.codehub.universitydb.model.Student;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

    private final Connection connection; //prosthesa final

    private final String studentDepartment;

    public StudentRepository(Connection connection, String studentDepartment) {
        this.connection = connection;
        this.studentDepartment = studentDepartment;


    }

    public Student getStudent(int studentId) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "select * from student where id=" + studentId;
        ResultSet rs = statement.executeQuery(sql);
        Student student = null;
        while(rs.next()){

            String studentName = rs.getString("name");
            student = new Student(studentId, studentName, Integer.parseInt(studentDepartment));
            String departmentName = studentDepartment(connection, studentDepartment);
            student.setStudentDepartmentName(departmentName);
        }
        rs.close();
        return student;
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
            //System.out.println( studentId + " = " + studentName + " "+studentDepartment);
            student = new Student(Integer.parseInt(studentId), studentName, Integer.parseInt(studentDepartment));
            String departmentName = studentDepartment(connection, studentDepartment);
            student.setStudentDepartmentName(departmentName);
            System.out.println(student);
            allStudents.add(student);
        }
        rs.close();
        return allStudents;
    }


    public void updateStudents(int studentId, String studentName, String sql) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, studentName);
        statement.setInt(2,studentId);
        statement.executeUpdate();

    }

    public void deleteStudents(int studentId) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "delete from student where id="+studentId;
        statement.executeUpdate(sql);

    }

    public static String studentDepartment(Connection connection, String studentDepartment) throws SQLException {
        String departmentName = null;
        Statement statement = connection.createStatement();
        String sql = "select name from department where id="+studentDepartment;
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()){
            departmentName = rs.getString("name");

        }
        return departmentName;
    }





    public Student createStudent(int studentId, String name, int departmentId, String sql) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, studentId);
        statement.setString(2,name);
        statement.setInt(3, departmentId);
        statement.executeUpdate();

        String departmentName = studentDepartment(connection,Integer.toString(departmentId));
        Student student =  new Student(studentId, name, departmentId);
        student.setStudentDepartmentName(departmentName);
        return student;
    }
}
