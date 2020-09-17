package br.ufba.dcc.wiser.m2db.impl;

import java.util.List;

import org.apache.aries.jpa.template.JpaTemplate;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import br.ufba.dcc.wiser.m2db.service.GatewayStatusServiceDB;
import br.ufba.dcc.wiser.m2model.model.GatewayStatus;

//Model: https://github.com/apache/aries-jpa/blob/master/examples/tasklist-ds/src/main/java/org/apache/aries/jpa/example/tasklist/ds/impl/TaskServiceImpl.java

@Component
public class GatewayStatusServiceDBImpl implements GatewayStatusServiceDB {
	private JpaTemplate jpa;

	@Activate
	public void startOSGI() {
		System.out.println("Started DS in the M2DB - GatewayStatusServiceDBImpl...");
	}

	@Reference(target = "(osgi.unit.name=person)")
	public void setJpa(JpaTemplate jpa) {
		this.jpa = jpa;
	}

	@Override
	public void add(GatewayStatus gatewayStatus) {
		jpa.tx(em -> {
			em.persist(gatewayStatus);
			em.flush();
		});
	}

	@Override
	public List<GatewayStatus> findByMac(String mac) {
		return jpa.txExpr(
				em -> em.createQuery(
						"select g from GatewayStatus g where g.gateway.mac = " + mac, GatewayStatus.class).getResultList()
				);
	}

	@Override
	public List<GatewayStatus> getListAll() {
		return jpa.txExpr(em -> em.createQuery("select g from GatewayStatus g", GatewayStatus.class).getResultList());
	}

}
