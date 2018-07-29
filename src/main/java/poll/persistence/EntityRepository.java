package poll.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import poll.domain.Entity;
import poll.utilities.InvalidParamException;
import poll.utilities.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EntityRepository {

    @Autowired
    private HelperEntityRepository repository;

    public void save(Entity entity) throws InvalidParamException {
        if (entity == null)
            throw new InvalidParamException();
        try {
            repository.save(entity);
        } catch (Exception e) {
            throw new InvalidParamException();
        }
    }

    public Entity getEntityByEmail(String email) throws InvalidParamException {
        Entity Entity = repository.findByEmail(email);
        if (Entity == null)
            throw new InvalidParamException();
        return Entity;
    }

    public List<Entity> getAllEntities() {
        List<Entity> result = new ArrayList<>();

        for (Entity u : repository.findAll()) {
            result.add(u);
        }

        return result;
    }

    public Entity getEntityById(int entityId) throws NotFoundException {

        try {
            return repository.findById(entityId).get();
        } catch (Exception exception) {
            throw new NotFoundException();
        }
    }
}
