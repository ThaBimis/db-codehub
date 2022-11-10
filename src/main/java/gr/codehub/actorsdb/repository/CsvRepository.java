package gr.codehub.actorsdb.repository;

import gr.codehub.actorsdb.model.Actor;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class CsvRepository {

    public static void writeToFile(String filename, Actor actor) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(filename);
        String line = actor.getActorId() + "," + actor.getFirstName() + "," +actor.getLastName();
        pw.println(line);
        pw.close();
    }

    public static void writeToFileAllDb(String filename, List<Actor> allActors) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(filename);
        for (Actor allActor : allActors) {
            String line = allActor.getActorId() + "," + allActor.getFirstName() + "," + allActor.getLastName();
            pw.println(line);
        }


        pw.close();
    }

}
