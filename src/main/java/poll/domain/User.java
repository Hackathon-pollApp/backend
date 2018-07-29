package poll.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import poll.utilities.Encryptor;
import poll.utilities.InvalidParamException;

@Entity(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "userId", updatable = false, nullable = false)
    private Integer id;
    private String name;
    private String email;
    private String password;
    @Column(columnDefinition = "integer default 1")
    private Integer votes;
    @Column(columnDefinition = "tinyint(1) default 0")
    private boolean isActive;

    public User() {
    }

    public User(String name, String email, String password, Integer votes, boolean isActive) throws InvalidParamException {
        checkValidName(name);
        checkValidEmail(email);
        checkValidPassword(password);
        this.name = name;
        this.email = email;
        this.password = Encryptor.encryptPassword(password);
        this.votes = votes;
        this.isActive = isActive;
    }

    private void checkValidName(String name) throws InvalidParamException {
        if (name.equals(""))
            throw new InvalidParamException();
    }

    private void checkValidEmail(String email) throws InvalidParamException {
        if (email.equals("") || !email.contains(".com") || !email.contains("@"))
            throw new InvalidParamException();
    }

    public void checkValidPassword(String password) throws InvalidParamException {
        if (password.length() < 7)
            throw new InvalidParamException();
    }

    public void checkPasswordIsCorrect(String password) throws InvalidParamException {
        Encryptor.checkIfPasswordMatches(password, this.password);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws InvalidParamException {
        checkValidName(name);
        this.name = name;
    }

    public void setEmail(String email) throws InvalidParamException {
        checkValidEmail(email);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
