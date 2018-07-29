package poll.application.dto;

import com.google.gson.annotations.Expose;
import poll.domain.Entity;
import poll.utilities.NotFoundException;

public class EntityDTO {
    @Expose
    private int id;
    @Expose
    private String name, email;
    private String password;

    public EntityDTO(Entity entity) throws NotFoundException {
        if (entity == null)
            throw new NotFoundException();

        this.name = entity.getName();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
        this.id = entity.getId();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        if (name == null) return "";
        return name;
    }

    public String getEmail() {
        if (email == null) return "";
        return email;
    }

    public String getPassword() {
        if (password == null) return "";
        return password;
    }
}
