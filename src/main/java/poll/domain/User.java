package poll.domain;

import javax.persistence.*;
import javax.persistence.Entity;

import poll.utilities.Encryptor;
import poll.utilities.InvalidParamException;

@Entity(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId", updatable = false, nullable = false)
    private Integer id;
    private Integer entityId;
    private String name;
    @Column(unique = true, length = 100)
    private String email;
    private String password;
    @Column(columnDefinition = "integer default 1")
    private Integer votes;
    @Column(columnDefinition = "tinyint(1) default 0")
    private boolean isActive;

    //User - Entity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entityId", referencedColumnName = "entityId", insertable = false, updatable = false)
    private poll.domain.Entity entity;

    public User() {
    }

    public User(Integer entityId, String name, String email, String password, Integer votes, boolean isActive) throws InvalidParamException {
        checkValidName(name);
        checkValidEmail(email);
        checkValidPassword(password);
        this.entityId = entityId;
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

    public Integer getEntityId() {
        return entityId;
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

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}
