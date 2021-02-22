package cybersoft.java10.service.impl;

import java.util.List;

import cybersoft.java10.model.*;
import cybersoft.java10.repository.IStatusRepository;
import cybersoft.java10.service.IStatusService;

public class StatusServiceImpl implements IStatusService {

	private IStatusRepository iStatusRepository;

	public void setStatusRepositoryImpl(IStatusRepository iStatusRepository) {
		this.iStatusRepository = iStatusRepository;
	}

	@Override
	public List<Status> getListStatus() {
		// TODO Auto-generated method stub
		return iStatusRepository.getListStatus();
	}

	@Override
	public int add(Status status) {
		// TODO Auto-generated method stub
		return iStatusRepository.add(status.getName());
	}

	@Override
	public int edit(Status status) {
		return iStatusRepository.edit(status.getId(), status.getName());
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return iStatusRepository.delete(id);
	}

	@Override
	public Status findByID(int id) {
		// TODO Auto-generated method stub
		return iStatusRepository.findByID(id);
	}

}
