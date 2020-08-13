package br.ufba.dcc.wiser.m2db.impl;

import java.util.List;

import org.apache.aries.jpa.template.JpaTemplate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import br.ufba.dcc.wiser.m2db.model.Gateway;
import br.ufba.dcc.wiser.m2db.service.GatewayService;

@Component
public class GatewayServiceImpl implements GatewayService {
	private JpaTemplate jpa;
	
	@Reference(target = "(osgi.unit.name=person)")
	public void setJpa(JpaTemplate jpa) {
		this.jpa = jpa;
	}

	@Override
	public void add(Gateway gateway) {
		jpa.tx(em -> {
			em.persist(gateway);
			em.flush();
		});		
	}

	@Override
	public void update(String mac) {
		System.out.println("In development - update");
	}

	@Override
	public void delete(String mac) {
		System.out.println("In development - delete");
	}
	
	@Override
	public Gateway find(String mac) {
		System.out.println("In development - find");
		return null;
	}

	@Override
	public List<Gateway> list() {
		return jpa.txExpr(em -> em.createQuery("select g from Gateway g", Gateway.class).getResultList());
	}
	
}
