package poll.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import poll.application.EntityController;
import poll.application.dto.EntityDTO;
import poll.utilities.InvalidParamException;
import poll.utilities.NotFoundException;

import java.util.List;

@CrossOrigin
@RestController
public class EntityRestController {

    @Autowired
    private EntityController controller;

    @PostMapping(value = "/entities", produces = "application/json;charset=UTF-8")
    public String register(@RequestBody String jEntity) throws InvalidParamException, NotFoundException {

        EntityDTO newEntity = new Gson().fromJson(jEntity, EntityDTO.class);

        EntityDTO entity = controller.register(newEntity);

        return toJson(entity);
    }

    @GetMapping(value = "/entities", produces = "application/json;charset=UTF-8")
    public String listEntities() throws NotFoundException {

        List<EntityDTO> entities = controller.listEntities();

        return toJson(entities);
    }

    @GetMapping(value = "/entities/{entityId}", produces = "application/json;charset=UTF-8")
    public String getUser(@PathVariable int entityId) throws NotFoundException {

        EntityDTO entity = controller.getEntityDTO(entityId);

        return toJson(entity);
    }

    @PutMapping(value = "/entities/{entityId}", produces = "application/json;charset=UTF-8")
    public String UpdateUser(@PathVariable int entityId, @RequestBody String jEntity)
            throws NotFoundException, InvalidParamException {

        EntityDTO entityToUpdate = new Gson().fromJson(jEntity, EntityDTO.class);

        EntityDTO entity = controller.updateEntity(entityId, entityToUpdate);

        return toJson(entity);
    }

    private String toJson(Object object) {

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(object);
    }
}
