package poll.domain;

import poll.utilities.InvalidParamException;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity(name = "OptionQuestion")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "optionId", updatable = false, nullable = false)
    private Integer id;
    private Integer eventId;
    private String optionText;
    private Integer quantity;

    //Option - Event
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eventId", referencedColumnName = "eventId", insertable = false, updatable = false)
    private Event event;

    public Option() {
    }

    public Option(Integer eventId, String option, Integer quantity) throws InvalidParamException {
        checkValidStringField(option);
        this.eventId = eventId;
        this.optionText = option;
        this.quantity = quantity;
    }

    private void checkValidStringField(String string) throws InvalidParamException {
        if (string.equals(""))
            throw new InvalidParamException();
    }

    public Integer getId() {
        return id;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getOption() {
        return optionText;
    }

    public void setOption(String option) throws InvalidParamException {
        checkValidStringField(option);
        this.optionText = option;
    }

    public Integer getQuantity() {

        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
