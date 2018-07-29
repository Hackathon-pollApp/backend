package poll.application.dto;

import com.google.gson.annotations.Expose;

import poll.domain.User;
import poll.utilities.NotFoundException;

public class UserDTO {
    @Expose
    private int id;
    @Expose
    private String name, email;
    private String password;
    @Expose
    private int votes;
    @Expose
    private boolean isActive;

    public UserDTO(User user) throws NotFoundException {
        if (user == null)
            throw new NotFoundException();

        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.id = user.getId();
        this.votes = user.getVotes();
        this.isActive = user.getIsActive();
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

    public int getVotes() {
        if (votes == 0) return 1;
        return votes;
    }

    public boolean getIsActive() {
        if (!isActive) return false;
        return isActive;
    }

    @Override
    public String toString() {
        return name;
    }

}
