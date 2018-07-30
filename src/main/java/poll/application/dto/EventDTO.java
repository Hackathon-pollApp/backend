package poll.application.dto;

import com.google.gson.annotations.Expose;
import poll.domain.Event;
import poll.utilities.NotFoundException;

public class EventDTO {
    @Expose
    private int id;
    @Expose
    private Integer entityId;
    @Expose
    private String name;
    @Expose
    private String description;
    @Expose
    private String comment;
    @Expose
    private String image;
    @Expose
    private String question;
    @Expose
    private boolean isActive;

    public EventDTO(Event event) throws NotFoundException {
        if (event == null)
            throw new NotFoundException();

        this.entityId = event.getEntityId();
        this.name = event.getName();
        this.description = event.getDescription();
        this.comment = event.getComment();
        this.image = event.getImage();
        this.question = event.getQuestion();
        this.isActive = event.getIsActive();
        this.id = event.getId();
    }

    public int getId() {
        return id;
    }

    public int getEntityId() {
        return entityId;
    }

    public String getName() {
        if (name == null) return "";
        return name;
    }

    public String getDescription() {
        if (description == null) return "";
        return description;
    }

    public String getImage() {
        if (image == null) return "";
        return image;
    }

    public String getQuestion() {
        if (question == null) return "";
        return question;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public String getComment() {
        return comment;
    }
}
