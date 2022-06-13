package br.ufba.dcc.wiser.m2db.service;

import java.util.List;

import br.ufba.dcc.wiser.m2model.model.GatewayStatus;

public interface GatewayStatusServiceDB {

	void add(GatewayStatus gatewayStatus);

	List<GatewayStatus> findByMac(String mac);

	List<GatewayStatus> getListAll();

}
