package gr.codehub.universitydb.repository;

import gr.codehub.universitydb.model.Department;
import gr.codehub.universitydb.model.Student;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class CsvRepository {

    public static void writeToFile(String filename, Department department) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(filename);
        String line = department.getActorId() + "," + department.getFirstName() + "," + department.getLastName();
        pw.println(line);
        pw.close();
    }

    public static void writeToFileAllDb(String filename, List<Department> allDepartments) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(filename);
        for (Department allDepartment : allDepartments) {
            String line = "DepartmentId: "+allDepartment.getActorId() + ", " +"DepartmentName: " +allDepartment.getFirstName() + ", " +"Number of tracks: "+ allDepartment.getLastName();
            pw.println(line);
        }
        pw.close();
        System.out.println("Writing to file: "+ filename+ " was successfull!");
    }

    public static void writeToFileAllDb2(String filename, List<Student> allDepartments) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(filename);
        for (Student allDepartment : allDepartments) {
            String line = "StudentId: "+allDepartment.getStudentId() + ", " + "Student Name: "+ allDepartment.getStudentName() + ", " + "StudentDepId: "+ allDepartment.getStudentDepartment() + ", " + "Student Dep Name: "+allDepartment.getStudentDepartmentName();
            pw.println(line);
        }
        pw.close();
        System.out.println("Writing to file: "+ filename+ " was successfull!");
    }

}
