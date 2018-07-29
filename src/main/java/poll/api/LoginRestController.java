package poll.api;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import poll.application.EntityController;
import poll.application.UserController;
import poll.application.dto.EntityDTO;
import poll.application.dto.UserDTO;
import poll.utilities.InvalidParamException;
import poll.utilities.NotFoundException;

@CrossOrigin
@RestController
public class LoginRestController {
    @Autowired
    private UserController userController;
    @Autowired
    private EntityController entityController;

    @PostMapping(value = "/login", produces = "application/json;charset=UTF-8")
    public String login(@RequestBody String formInputs) throws NotFoundException, InvalidParamException, ParseException {

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(formInputs);
        String type = (String) jsonObject.get("type");

        UserDTO user = null;
        EntityDTO entity = null;

        if (type.equals("user")) {
            UserDTO userToLog = new Gson().fromJson(formInputs, UserDTO.class);

            user = userController.login(userToLog);
        }else if (type.equals("entity")) {
            EntityDTO entityToLog = new Gson().fromJson(formInputs, EntityDTO.class);

            entity = entityController.login(entityToLog);
        }

        return (user != null) ? toJson(user) : (entity != null) ? toJson(entity) : "";
    }

    private String toJson(Object object) {

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(object);
    }
}
