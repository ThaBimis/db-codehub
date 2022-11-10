package gr.codehub.actorsdb.service;

import gr.codehub.actorsdb.model.Actor;
import gr.codehub.actorsdb.repository.ActorRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ActorService {

    private ActorRepository repository = null;

    public ActorService(Connection connection){
        repository = new ActorRepository(connection);
    }

    public Actor findActorWithId(int actorId) throws SQLException {
        return repository.getActor(2);
    }

    public List<Actor> findAllActors() throws SQLException {
        return repository.getAllActors();
    }
}
