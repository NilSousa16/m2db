package br.ufba.dcc.wiser.m2db.service;

import java.util.List;

import br.ufba.dcc.wiser.m2model.model.DeviceStatus;

public interface DeviceStatusServiceDB {
	
	void add(DeviceStatus deviceStatus);

	List<DeviceStatus> findById(String id);

	List<DeviceStatus> getListAll();

}
