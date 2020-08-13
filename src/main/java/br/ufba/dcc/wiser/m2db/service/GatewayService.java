package br.ufba.dcc.wiser.m2db.service;

import java.util.List;

import br.ufba.dcc.wiser.m2db.model.Gateway;

public interface GatewayService {
	void add(Gateway gateway);
    void update(String mac);
    void delete(String mac);
    Gateway find(String mac);
    List<Gateway> list();
}
