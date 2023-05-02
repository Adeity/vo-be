package cz.cvut.fel.vyzkumodolnosti.model.dto.personsapi;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ParticipantListDto {
    List<ParticipantDto> participants;
}
