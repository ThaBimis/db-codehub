package gr.codehub.actorsdb.service;

import gr.codehub.actorsdb.model.Department;
import gr.codehub.actorsdb.repository.CsvRepository;

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

}
