package cz.cvut.fel.vyzkumodolnosti.repository.sleep;

import cz.cvut.fel.vyzkumodolnosti.repository.forms.DeviceEntityInfo;

public interface SleepSummaryInfo {
    Integer getId();

    String getUserAccessToken();

    String getSummaryId();

    String getCalendarDate();

    DeviceEntityInfo getDevice();
}
