package poll.persistence;

import org.springframework.data.repository.CrudRepository;
import poll.domain.Entity;

// This will be AUTO IMPLEMENTED by Spring into a Bean called entityRepository
// CRUD refers Create, Read, Update, Delete
interface HelperEntityRepository extends CrudRepository<Entity, Integer> {

    public Entity findByEmail(String email);

    public Entity findByName(String name);

}
