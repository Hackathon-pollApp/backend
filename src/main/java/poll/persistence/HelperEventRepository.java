package poll.persistence;

import org.springframework.data.repository.CrudRepository;
import poll.domain.Event;

// This will be AUTO IMPLEMENTED by Spring into a Bean called eventRepository
// CRUD refers Create, Read, Update, Delete
interface HelperEventRepository extends CrudRepository<Event, Integer> {

    public Event findByEntityId(int entityId);

    public Event findByName(String name);

}
