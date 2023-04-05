package cz.cvut.fel.vyzkumodolnosti.model.dto.computations;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.cvut.fel.vyzkumodolnosti.model.domain.computations.ChronotypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class SingleGlobalValueDto {

    @JsonProperty("id")
    @Min(0) @Max(4)
    private Integer id;
    @JsonProperty("awake_from")
    @Pattern(regexp = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")
    private String awakeFrom;
    @JsonProperty("awake_to")
    @Pattern(regexp = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")
    private String awakeTo;
    @JsonProperty("sleep_from")
    @Pattern(regexp = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")
    private String sleepFrom;
    @JsonProperty("sleep_to")
    @Pattern(regexp = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")
    private String sleepTo;
    @JsonProperty("title")
    private String title;

    @Override
    public String toString() {
        return "SingleGlobalValueDto{" +
                "id=" + id +
                ", awakeFrom='" + awakeFrom + '\'' +
                ", awakeTo='" + awakeTo + '\'' +
                ", sleepFrom='" + sleepFrom + '\'' +
                ", sleepTo='" + sleepTo + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
