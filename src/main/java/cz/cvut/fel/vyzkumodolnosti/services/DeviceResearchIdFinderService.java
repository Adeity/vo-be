package cz.cvut.fel.vyzkumodolnosti.services;

import cz.cvut.fel.vyzkumodolnosti.repository.forms.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class DeviceResearchIdFinderService {
	private final DeviceRepository dao;

	public DeviceResearchIdFinderService(DeviceRepository dao) {
		this.dao = dao;
	}

	public Set<String> findAllResearchNumbers() {
		return dao.findAllResearchNumbers();
	}

	public List<String> findAllActiveResearchNumbers() {
		return dao.findAllActiveResearchNumbers();
	}
}