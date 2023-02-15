package cz.cvut.fel.vyzkumodolnosti.services;

import cz.cvut.fel.vyzkumodolnosti.services.sleeps.DeviceUserIdConnectorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class DeviceServiceFacade {
	private final DeviceUserIdConnectorService deviceUserIdConnectorService;
	private final DeviceResearchIdFinderService deviceResearchIdFinderService;

    @Autowired
    public DeviceServiceFacade(DeviceUserIdConnectorService deviceUserIdConnectorService, DeviceResearchIdFinderService deviceResearchIdFinderService) {
		this.deviceUserIdConnectorService = deviceUserIdConnectorService;
		this.deviceResearchIdFinderService = deviceResearchIdFinderService;
	}

    public void connectExistingDevicesToUserIds() {
		this.deviceUserIdConnectorService.connectExistingDevicesToUserIds();
    }

	public Set<String> findAllResearchNumbers() {
		return deviceResearchIdFinderService.findAllResearchNumbers();
	}

	public List<String> findAllActiveResearchNumbers() {
		return deviceResearchIdFinderService.findAllActiveResearchNumbers();
	}
}