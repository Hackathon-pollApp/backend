package poll.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import poll.application.dto.EntityDTO;
import poll.domain.Entity;
import poll.persistence.EntityRepository;
import poll.utilities.InvalidParamException;
import poll.utilities.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EntityController {

    @Autowired
    private EntityRepository entityRepository;

    public EntityDTO register(EntityDTO entityDTO) throws InvalidParamException, NotFoundException {

        Entity entity = new Entity(entityDTO.getName(), entityDTO.getEmail(), entityDTO.getPassword());

        entityRepository.save(entity);

        return new EntityDTO(entity);
    }

    public EntityDTO login(EntityDTO entityToLog) throws InvalidParamException, NotFoundException {

        Entity entity = entityRepository.getEntityByEmail(entityToLog.getEmail());

        entity.checkPasswordIsCorrect(entityToLog.getPassword());

        return new EntityDTO(entity);
    }

    public List<EntityDTO> listEntities() throws NotFoundException {
        List<Entity> entityList = entityRepository.getAllEntities();
        List<EntityDTO> entityDTOList = new ArrayList<>();

        if (entityList.isEmpty())
            throw new NotFoundException();

        for (Entity e : entityList) {
            entityDTOList.add(new EntityDTO(e));
        }

        return entityDTOList;
    }

    public Entity getEntity(int entityId) throws NotFoundException {
        Entity entity = entityRepository.getEntityById(entityId);

        return entity;
    }

    public EntityDTO getEntityDTO(int entityId) throws NotFoundException {

        Entity entity = entityRepository.getEntityById(entityId);

        return new EntityDTO(entity);
    }

    public EntityDTO updateEntity(int entityId, EntityDTO entityToUpdate) throws NotFoundException, InvalidParamException {

        Entity entity = entityRepository.getEntityById(entityId);

        if (!entityToUpdate.getEmail().equals(""))
            entity.setEmail(entityToUpdate.getEmail());

        if (!entityToUpdate.getName().equals(""))
            entity.setName(entityToUpdate.getName());

        //faltan atributos
        entityRepository.save(entity);

        return new EntityDTO(entity);
    }

}
