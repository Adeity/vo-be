package cz.cvut.fel.vyzkumodolnosti.model.dto.personsapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MethodDto {
    @JsonProperty("id")
    private int id;
    @JsonProperty("title")
    private String title;
}
