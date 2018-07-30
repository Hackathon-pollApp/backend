package poll.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import poll.domain.Entity;
import poll.domain.Event;
import poll.utilities.InvalidParamException;
import poll.utilities.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EventRepository {

    @Autowired
    private HelperEventRepository repository;

    public void save(Event event) throws InvalidParamException {
        if (event == null)
            throw new InvalidParamException();
        try {
            repository.save(event);
        } catch (Exception e) {
            throw new InvalidParamException();
        }
    }

    public List<Event> getEventsByEntityId(Entity entityId) throws InvalidParamException {
        return repository.findAllByEntity(entityId);
    }

    public List<Event> getAllEvents() {
        List<Event> result = new ArrayList<>();

        for (Event e : repository.findAll()) {
            result.add(e);
        }

        return result;
    }

    public Event getEventById(int eventId) throws NotFoundException {

        try {
            return repository.findById(eventId).get();
        } catch (Exception exception) {
            throw new NotFoundException();
        }
    }
}
