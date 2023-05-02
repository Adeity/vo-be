package cz.cvut.fel.vyzkumodolnosti.model.dto.personsapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResearchDto {
    @JsonProperty("method")
    private MethodDto method;
}
