package poll.application.dto;

import com.google.gson.annotations.Expose;
import poll.domain.Option;
import poll.utilities.NotFoundException;

public class OptionDTO {

    @Expose
    private Integer id;
    @Expose
    private Integer eventId;
    @Expose
    private String optionText;
    @Expose
    private Integer quantity;

    public OptionDTO(Option option) throws NotFoundException {
        if (option == null)
            throw new NotFoundException();

        this.id = option.getId();
        this.eventId = option.getEventId();
        this.optionText = option.getOption();
        this.quantity = option.getQuantity();
    }

    public Integer getId() {
        return id;
    }

    public Integer getEventId() {
        return eventId;
    }

    public String getOptionText() {
        if (optionText == null) return "";
        return optionText;
    }

    public Integer getQuantity() {
        if (quantity == null) return 0;
        return quantity;
    }
}
