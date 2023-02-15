package cz.cvut.fel.vyzkumodolnosti.repository.forms;

import java.time.LocalDate;

public interface DeviceEntityInfo {
    int getId();

    String getResearchNumber();

    Boolean getAllowed();

    LocalDate getDeregistrationTime();
}
