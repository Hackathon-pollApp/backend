package poll.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import poll.application.EventController;
import poll.application.dto.EventDTO;
import poll.application.dto.UserDTO;
import poll.domain.User;
import poll.utilities.InvalidParamException;
import poll.utilities.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class EventRestController {

    @Autowired
    private EventController controller;

    @PostMapping(value = "/events", produces = "application/json;charset=UTF-8")
    public String create(@RequestBody String formInputs) throws InvalidParamException, NotFoundException, ParseException {

        EventDTO newEvent = new Gson().fromJson(formInputs, EventDTO.class);

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(formInputs);
        //ArrayList user = (ArrayList) jsonObject.get("users");
        //UserDTO newUser = new Gson().fromJson(user, UserDTO.class);
        //System.out.println(user);

        //List<User> userList = new ArrayList<>();
        //for (User u : user) {
//            System.out.println(u.getEmail());
//        }
//        String options = (String) jsonObject.get("options");

        EventDTO event = controller.create(newEvent);

        return toJson(event);
    }

    private String toJson(Object object) {

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(object);
    }

    @GetMapping(value = "/events", produces = "application/json;charset=UTF-8")
    public String listEvents() throws NotFoundException {

        List<EventDTO> events = controller.listEvents();

        return toJson(events);
    }

    @GetMapping(value = "/events/{eventId}", produces = "application/json;charset=UTF-8")
    public String getEvent(@PathVariable int eventId) throws NotFoundException {

        EventDTO event = controller.getEventDTO(eventId);

        return toJson(event);
    }

    @PutMapping(value = "/events/{eventId}", produces = "application/json;charset=UTF-8")
    public String UpdateEvent(@PathVariable int eventId, @RequestBody String jEvent)
            throws NotFoundException, InvalidParamException {

        EventDTO eventToUpdate = new Gson().fromJson(jEvent, EventDTO.class);

        EventDTO event = controller.updateEvent(eventId, eventToUpdate);

        return toJson(event);
    }

    @GetMapping(value = "/entities/{entityId}/events", produces = "application/json;charset=UTF-8")
    public String getEvents(@PathVariable int entityId) throws NotFoundException, InvalidParamException {

        List<EventDTO> events = controller.getEventsByEntity(entityId);

        return toJson(events);
    }
}
