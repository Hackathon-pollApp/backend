package poll.domain;

import poll.utilities.Encryptor;
import poll.utilities.InvalidParamException;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@javax.persistence.Entity(name = "Entity")
public class Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entityId", updatable = false, nullable = false)
    private Integer id;
    private String name;
    @Column(unique = true, length = 100)
    private String email;
    private String password;

    public Entity() {
    }

    public Entity(String name, String email, String password) throws InvalidParamException {
        checkValidName(name);
        checkValidEmail(email);
        checkValidPassword(password);
        this.name = name;
        this.email = email;
        this.password = Encryptor.encryptPassword(password);
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

}
