package gr.codehub.actorsdb.repository;

import gr.codehub.actorsdb.model.Department;
import gr.codehub.actorsdb.model.Student;

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
            String line = allDepartment.getActorId() + "," + allDepartment.getFirstName() + "," + allDepartment.getLastName();
            pw.println(line);
        }


        pw.close();
    }

    public static void writeToFileAllDb2(String filename, List<Student> allDepartments) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(filename);
        for (Student allDepartment : allDepartments) {
            String line = allDepartment.getStudentId() + "," + allDepartment.getStudentName() + "," + allDepartment.getStudentDepartment();
            pw.println(line);
        }


        pw.close();
    }

}
