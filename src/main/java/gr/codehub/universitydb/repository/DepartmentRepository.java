package gr.codehub.universitydb.repository;

import gr.codehub.universitydb.DatabaseMain;
import gr.codehub.universitydb.model.Department;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DepartmentRepository {
    private static Properties dbProperties = null;
    private final Connection connection; //prosthesa final

    public DepartmentRepository(Connection connection)  {
        this.connection = connection;

    }

    public Department getDepartment(int actorId) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "select * from department where id=" + actorId;
        ResultSet rs = statement.executeQuery(sql);
        Department department = null;
        while(rs.next()){

            String firstName = rs.getString("name");
            String lastName = rs.getString("numberoftrucks");
            System.out.println( actorId + " = " + firstName + " "+lastName);
            department = new Department(actorId, firstName, lastName);
        }
        rs.close();
        return department;
    }

    private static void readProperties() throws IOException {
        InputStream inStream = DatabaseMain.class.getClassLoader().getResourceAsStream("mysql.properties");
        dbProperties = new Properties();
        dbProperties.load(inStream);

    }

    public List<Department> getAllDepartments() throws SQLException {
        List<Department> allDepartments = new ArrayList<>();
        Statement statement = connection.createStatement();
        String sql = "select * from department";
        ResultSet rs = statement.executeQuery(sql);
        Department department;
        while(rs.next()){
            String departmentId = rs.getString("id");
            String departmentName = rs.getString("name");
            String numberOfTrucks = rs.getString("numberoftrucks");
            System.out.println( departmentId+ " = " + departmentName + " "+numberOfTrucks);
            department = new Department(Integer.parseInt(departmentId), departmentName, numberOfTrucks);
            allDepartments.add(department);
        }
        rs.close();
        return allDepartments;
    }

    public Department createDepartment(int departmentId, String name, String noOfTrucks, String sql) throws SQLException {
        //Statement statement = connection.createStatement();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, departmentId);
        statement.setString(2, name);
        statement.setString(3, noOfTrucks);

        statement.executeUpdate();
        return new Department(departmentId, name, noOfTrucks);


    }
    public void updateDepartment(int departmentId, String departmentName, String sql ) throws SQLException {

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, departmentName);
        statement.setInt(2,departmentId);
        statement.executeUpdate();

    }

    public void deleteDepartment(int departmentId) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "delete from department where id="+departmentId;
        int rs = statement.executeUpdate(sql);
    }
}
