package br.ufba.dcc.wiser.m2db.service;

import java.util.List;

import br.ufba.dcc.wiser.m2model.model.Gateway;

public interface GatewayServiceDB {
	void add(Gateway gateway);

	void update(Gateway gateway);

	void delete(String mac);

	Gateway find(String mac);

	List<Gateway> list();
}
