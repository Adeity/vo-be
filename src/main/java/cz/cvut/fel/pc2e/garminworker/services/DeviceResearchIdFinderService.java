package cz.cvut.fel.pc2e.garminworker.services;

import cz.cvut.fel.pc2e.garminworker.repository.DeviceDao;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class DeviceResearchIdFinderService {
	private final DeviceDao dao;

	public DeviceResearchIdFinderService(DeviceDao dao) {
		this.dao = dao;
	}

	public Set<String> findAllResearchNumbers() {
		return dao.findAllResearchNumbers();
	}
}