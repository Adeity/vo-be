package cz.cvut.fel.vyzkumodolnosti.model.dto.personsapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Tohle je pro mapovani z vyzkumodolnosti.cz/api/persons endpointu
 */
@Data
@NoArgsConstructor
public class ParticipantDto {
    @JsonProperty("uniqueCode")
    private String researchNumber;
    @JsonProperty("email")
    private String email;
    @JsonProperty("locale")
    private String locale;
    @JsonProperty("researches")
    private List<ResearchDto> researches;
}
