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

import poll.application.UserController;
import poll.application.dto.UserDTO;
import poll.utilities.InvalidParamException;
import poll.utilities.NotFoundException;

@CrossOrigin
@RestController
public class LoginRestController {
    @Autowired
    private UserController controller;

    @PostMapping(value = "/login", produces = "application/json;charset=UTF-8")
    public String login(@RequestBody String jUser) throws NotFoundException, InvalidParamException, ParseException {

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(jUser);
        String type = (String) jsonObject.get("type");

        UserDTO user = null;
        if (type.equals("user")) {
            UserDTO userToLog = new Gson().fromJson(jUser, UserDTO.class);

            user = controller.login(userToLog);
        }
        if (type.equals("entity")) {
            //UserDTO userToLog = new Gson().fromJson(jUser, UserDTO.class);

            //user = controller.login(userToLog);
        }

        return toJson(user);
    }

    private String toJson(Object object) {

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(object);
    }
}
