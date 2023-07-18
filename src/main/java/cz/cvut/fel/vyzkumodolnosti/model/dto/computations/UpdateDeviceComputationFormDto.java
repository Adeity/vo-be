package cz.cvut.fel.vyzkumodolnosti.model.dto.computations;

import cz.cvut.fel.vyzkumodolnosti.model.dto.PageableRequestDto;
import cz.cvut.fel.vyzkumodolnosti.model.dto.device.DeviceComputationFormDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDeviceComputationFormDto {

    DeviceComputationFormDto data;
    PageableRequestDto pageInfo;
}
