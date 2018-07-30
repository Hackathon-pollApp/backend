package poll.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import poll.application.dto.EventDTO;
import poll.domain.Entity;
import poll.domain.Event;
import poll.persistence.EntityRepository;
import poll.persistence.EventRepository;
import poll.utilities.InvalidParamException;
import poll.utilities.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EventController {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EntityController entityController;

    public EventDTO create(EventDTO eventDTO) throws InvalidParamException, NotFoundException {

        Event event = new Event(eventDTO.getEntityId(), eventDTO.getName(), eventDTO.getDescription(), eventDTO.getComment(), eventDTO.getImage(), eventDTO.getQuestion(), eventDTO.getIsActive());

        eventRepository.save(event);

        return new EventDTO(event);
    }

    public List<EventDTO> listEvents() throws NotFoundException {
        List<Event> eventList = eventRepository.getAllEvents();
        List<EventDTO> eventDTOList = new ArrayList<>();

        if (eventList.isEmpty())
            throw new NotFoundException();

        for (Event e : eventList) {
            eventDTOList.add(new EventDTO(e));
        }

        return eventDTOList;
    }

    public Event getEvent(int eventId) throws NotFoundException {
        Event event = eventRepository.getEventById(eventId);

        return event;
    }

    public EventDTO getEventDTO(int eventId) throws NotFoundException {

        Event event = eventRepository.getEventById(eventId);

        return new EventDTO(event);
    }

    public EventDTO updateEvent(int eventId, EventDTO eventToUpdate) throws NotFoundException, InvalidParamException {

        Event event = eventRepository.getEventById(eventId);

        if (!eventToUpdate.getName().equals(""))
            event.setName(eventToUpdate.getName());

        if (!eventToUpdate.getDescription().equals("")) {
            event.setDescription(eventToUpdate.getDescription());
        }
        //faltan atributos


        eventRepository.save(event);

        return new EventDTO(event);
    }

    public List<EventDTO> getEventsByEntity(int entityId) throws NotFoundException, InvalidParamException {

        Entity entity = entityController.getEntity(entityId);

        List<EventDTO> eventDTOList = new ArrayList<>();
        List<Event> eventList = eventRepository.getEventsByEntityId(entity);

        if (eventList.isEmpty())
            throw new NotFoundException();

        for (Event e : eventList) {
            eventDTOList.add(new EventDTO(e));
        }

        return eventDTOList;
    }
}
