package gr.codehub.actorsdb;

import gr.codehub.actorsdb.model.Actor;
import gr.codehub.actorsdb.repository.ActorRepository;
import gr.codehub.actorsdb.service.ActorService;
import gr.codehub.actorsdb.service.FileService;

import javax.swing.plaf.nimbus.State;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Properties;

public class DatabaseTester {

    private static Connection connection = null;
    private static Properties dbProperties = null;

    public static void main(String[] args) throws SQLException {
        initiateDatabase();
        runBusiness();
        System.out.println("Application Finished");

        //System.out.println(connection);
    }

    private static void runBusiness() {
        Actor actor = null;
        List<Actor> allActors;

        try {
            actor = findActor(1);
            allActors = findAllActors();
            System.out.println(actor);
            System.out.println(allActors);
            saveActorToFile(actor);
            saveAllActorsToFile(allActors);
        } catch (SQLException e) {
            System.out.println("Problem with business sql: " + e.getMessage());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static Actor findActor(int actorId) throws SQLException {
        ActorService ar = new ActorService(connection);
        return ar.findActorWithId(actorId); //trexw business
    }

    private static List<Actor> findAllActors() throws SQLException {
        ActorService ar = new ActorService(connection);
        return ar.findAllActors();
    }

    private static void initiateDatabase() {
        try{
            readProperties();
            useMySqlDriver();
            connectToDatabase(dbProperties.getProperty("connection.schema"));


        }catch(SQLException e){
            System.out.println("Problem with SQL" + e.getMessage());
        }catch (Exception e){
            System.out.println("System exception" + e.getMessage());
        }

    }



    private static void saveActorToFile(Actor actor) throws FileNotFoundException {
        FileService fs = new FileService("data\\actors.csv");
        fs.saveActor(actor);
    }

    private static void saveAllActorsToFile(List<Actor> allActors) throws FileNotFoundException {
        FileService fs = new FileService("data\\all_actors.csv");
        fs.saveAllActors(allActors);
    }
    private static void readProperties() throws IOException {
        InputStream inStream =DatabaseTester.class.getClassLoader().getResourceAsStream("mysql.properties");
        dbProperties = new Properties();
        dbProperties.load(inStream);

    }

    private static void useMySqlDriver() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
    }

    private static Connection connectToDatabase( String schema) throws SQLException {
        String dbUrl = dbProperties.getProperty("connection.dbUrl");
        String userName = dbProperties.getProperty("connection.userName");
        String password = dbProperties.getProperty("connection.password");
        connection = DriverManager.getConnection(dbUrl+"/"+schema,userName, password);
        return connection;

    }


}
