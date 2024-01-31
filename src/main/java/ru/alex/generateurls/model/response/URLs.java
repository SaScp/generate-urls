package ru.alex.generateurls.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Builder
@Document
public class URLs {

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String id;

    @NotNull(message = "your url is null")
    @JsonProperty("your_url")
    private String yourUrl;

    @NotNull
    @JsonProperty("new_url")
    private String newUrl;

    @NotNull
    private Date timestamp;
}
