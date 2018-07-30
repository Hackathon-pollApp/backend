package poll.persistence;

import org.springframework.data.repository.CrudRepository;
import poll.domain.Entity;
import poll.domain.Event;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called eventRepository
// CRUD refers Create, Read, Update, Delete
interface HelperEventRepository extends CrudRepository<Event, Integer> {

    public Event findByName(String name);

    List<Event> findAllByEntity(Entity entityId);
}
