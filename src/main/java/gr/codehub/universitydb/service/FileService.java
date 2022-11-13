package gr.codehub.universitydb.service;

import gr.codehub.universitydb.model.Department;
import gr.codehub.universitydb.model.Student;
import gr.codehub.universitydb.repository.CsvRepository;

import java.io.FileNotFoundException;
import java.util.List;

public class FileService {
    private String filename;

    public FileService(String filename){
        this.filename = filename;
    }

    public void saveActor(Department department) throws FileNotFoundException {
        CsvRepository.writeToFile(filename, department);
    }

    public void saveAllActors(List<Department> allDepartments) throws FileNotFoundException {
        CsvRepository.writeToFileAllDb(filename, allDepartments);
    }

    public void saveAllActors2(List<Student> allDepartments) throws FileNotFoundException {
        CsvRepository.writeToFileAllDb2(filename, allDepartments);
    }

}
