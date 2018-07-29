package poll.domain;

import poll.utilities.InvalidParamException;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity(name = "Event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eventId", updatable = false, nullable = false)
    private Integer id;
    private Integer entityId;
    private String name;
    private String description;
    private String image;
    private String question;
    @Column(columnDefinition = "tinyint(1) default 0")
    private boolean isActive;

    public Event() {
    }

    public Event(Integer EntityId, String name, String description, String image, String question, boolean isActive) throws InvalidParamException {
        checkValidStringField(name);
        checkValidStringField(description);
        checkValidStringField(image);
        checkValidStringField(question);
        this.entityId = EntityId;
        this.name = name;
        this.description = description;
        this.image = image;
        this.question = question;
        this.isActive = isActive;
    }

    private void checkValidStringField(String string) throws InvalidParamException {
        if (string.equals(""))
            throw new InvalidParamException();
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
        checkValidStringField(name);
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws InvalidParamException {
        checkValidStringField(description);
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) throws InvalidParamException {
        checkValidStringField(image);
        this.image = image;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) throws InvalidParamException {
        checkValidStringField(question);
        this.question = question;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}
