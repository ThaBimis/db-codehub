package gr.codehub.actorsdb.service;

import gr.codehub.actorsdb.model.Actor;
import gr.codehub.actorsdb.repository.CsvRepository;

import java.io.FileNotFoundException;
import java.util.List;

public class FileService {
    private String filename;

    public FileService(String filename){
        this.filename = filename;
    }

    public void saveActor(Actor actor) throws FileNotFoundException {
        CsvRepository.writeToFile(filename, actor);
    }

    public void saveAllActors(List<Actor> allActors) throws FileNotFoundException {
        CsvRepository.writeToFileAllDb(filename, allActors);
    }
}
