package gr.codehub.actorsdb.repository;

import gr.codehub.actorsdb.model.Actor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ActorRepository {

    private Connection connection = null;

    public ActorRepository(Connection connection){
        this.connection = connection;
    }

    public Actor getActor(int actorId) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "select * from actor where actor_id=" + actorId;
        ResultSet rs = statement.executeQuery(sql);
        Actor actor = null;
        while(rs.next()){

            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            System.out.println( actorId + " = " + firstName + " "+lastName);
            actor = new Actor(actorId, firstName, lastName);
        }
        rs.close();
        return actor;
    }

    public List<Actor> getAllActors() throws SQLException {
        List<Actor> allActors = new ArrayList<>();
        Statement statement = connection.createStatement();
        String sql = "select * from actor";
        ResultSet rs = statement.executeQuery(sql);
        Actor actor = null;
        while(rs.next()){
            String actorId = rs.getString("actor_id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            System.out.println( actorId + " = " + firstName + " "+lastName);
            actor = new Actor(Integer.parseInt(actorId), firstName, lastName);
            allActors.add(actor);
        }
        rs.close();
        return allActors;
    }
}
