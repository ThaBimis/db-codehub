package gr.codehub.universitydb;

import gr.codehub.universitydb.model.Department;
import gr.codehub.universitydb.model.Student;
import gr.codehub.universitydb.service.DepartmentService;
import gr.codehub.universitydb.service.FileService;
import gr.codehub.universitydb.service.StudentService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class DatabaseMain {

    private static Connection connection = null;
    private static Properties dbProperties = null;

    public static void main(String[] args) throws SQLException {
        initiateDatabase();
        createTables(); //create table department and students
        runBusiness();
        System.out.println("Application Finished");

        //System.out.println(connection);
    }

    private static void createTables() throws SQLException {
        Statement statement = connection.createStatement();
        String sql =  dbProperties.getProperty("create.table.001");
        statement.executeUpdate(sql);
        sql = dbProperties.getProperty("create.table.002");
        statement.executeUpdate(sql);
    }

    private static void runBusiness() {

        Department departmentComp,departmentManag, departmentHisto;
        List<Department> allDepartments;
        List<Student> allStudents;

        String sqlCommand;
        try {

            sqlCommand = dbProperties.getProperty("insert.into.001");

            departmentComp = createDepartment(1, "Computer Science", "10", sqlCommand);
            departmentManag = createDepartment(2, "Management", "15", sqlCommand);
            departmentHisto = createDepartment(3, "History", "12", sqlCommand);

            System.out.println(departmentComp);
            System.out.println(departmentManag);
            System.out.println(departmentHisto);

            sqlCommand = dbProperties.getProperty("update.into.001");

            updateDepartment(2, "Business Management",sqlCommand);
            deleteDepartment(3);

            sqlCommand = dbProperties.getProperty("insert.into.002");

            createStudent(1, "George", 1, sqlCommand);
            createStudent(2, "Ann", 1,  sqlCommand);
            createStudent(3, "Marry", 2, sqlCommand);

            updateStudent(2, "Anna",dbProperties.getProperty("update.into.002"));

            Student student = findStudent(2);
            System.out.println(student);

            allDepartments = findAllDepartments();
            allStudents = findAllStudents();

            saveAllActorsToFile(allDepartments);
            saveAllActorsToFile2(allStudents);

        } catch (SQLException e) {
            System.out.println("Problem with business sql: " + e.getMessage());
        }  catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Student> findAllStudents() throws SQLException {
        StudentService ar = new StudentService(connection, "2");
        return ar.findAllStudents();
    }

    private static Student findStudent(int studentId) throws SQLException {
        StudentService ar = new StudentService(connection, Integer.toString(studentId));
        return ar.findStudentWithId(studentId); //trexw business
    }

    private static void updateStudent(int id, String name, String sql) throws SQLException {
        StudentService ar = new StudentService(connection,Integer.toString(id));
        ar.updateStudent( id, name, sql); //trexw business
    }

    private static Student createStudent(int id, String name, int department, String sql) throws SQLException {
       StudentService ar = new StudentService(connection, Integer.toString(department));
        return ar.createStudent(id, name, department, sql);
    }

    private static void deleteDepartment(int id) throws SQLException {
        DepartmentService ar = new DepartmentService(connection);
        ar.deleteDepartment(id); //trexw business
    }

    private static void updateDepartment(int id, String name, String sql) throws SQLException {
        DepartmentService ar = new DepartmentService(connection);
        ar.updateDepartment( id, name, sql); //trexw business
    }

    private static Department createDepartment(int id, String name, String numOfTrucks, String sql) throws SQLException {
        DepartmentService ar = new DepartmentService(connection);
        return ar.createDepartment(id, name, numOfTrucks, sql ); //trexw business
    }

    private static Department findDepartment(int departmentId) throws SQLException {
        DepartmentService ar = new DepartmentService(connection);
        return ar.findDepartmentWithId(departmentId); //trexw business
    }

    private static List<Department> findAllDepartments() throws SQLException {
        DepartmentService ar = new DepartmentService(connection);
        return ar.findAllDepartments();
    }

    private static void initiateDatabase() {
        try{

            readProperties();
            useMySqlDriver();
            connection = connectToDatabase(dbProperties.getProperty("connection.schema"));


        }catch(SQLException e){
            System.out.println("Problem with SQL" + e.getMessage());
        }catch (Exception e){
            System.out.println("System exception" + e.getMessage());
        }

    }



    private static void saveActorToFile(Department department) throws FileNotFoundException {
        FileService fs = new FileService("data\\actors.csv");
        fs.saveActor(department);
    }

    private static void saveAllActorsToFile(List<Department> allDepartments) throws FileNotFoundException {
        FileService fs = new FileService("data\\all_departments.csv");
        fs.saveAllActors(allDepartments);
    }

    private static void saveAllActorsToFile2(List<Student> allDepartments) throws FileNotFoundException {
        FileService fs = new FileService("data\\all_students.csv");
        fs.saveAllActors2(allDepartments);
    }


    private static void readProperties() throws IOException {
        InputStream inStream = DatabaseMain.class.getClassLoader().getResourceAsStream("mysql.properties");
        dbProperties = new Properties();
        dbProperties.load(inStream);

    }

    private static void useMySqlDriver() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
    }

    private static Connection connectToDatabase( String schema) throws SQLException {
        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/?user=root&password=P@ssw0rd");
        Statement s=connection.createStatement();
        int Result=s.executeUpdate("CREATE DATABASE IF NOT EXISTS university");

        String dbUrl = dbProperties.getProperty("connection.dbUrl");
        String userName = dbProperties.getProperty("connection.userName");
        String password = dbProperties.getProperty("connection.password");
       connection = DriverManager.getConnection(dbUrl+"/"+schema,userName, password);
        return connection;
    }




}
