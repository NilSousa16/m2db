package br.ufba.dcc.wiser.m2db.service;

import java.util.List;

import br.ufba.dcc.wiser.m2model.model.Device;

public interface DeviceServiceDB {

	void add(Device device);

	void update(Device device);

	void delete(String id);

	Device find(String id);
	
	List<Device> listByGateway(String gatewayMac);

	List<Device> list();
	
}
