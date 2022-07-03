package br.ufba.dcc.wiser.m2db.impl;

import java.util.List;

import org.apache.aries.jpa.template.JpaTemplate;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Reference;

import br.ufba.dcc.wiser.m2db.service.DeviceStatusServiceDB;
import br.ufba.dcc.wiser.m2model.model.DeviceStatus;

public class DeviceStatusServiceDBImpl implements DeviceStatusServiceDB{

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
	public void add(DeviceStatus deviceStatus) {
		jpa.tx(em -> {
			em.persist(deviceStatus);
			em.flush();
		});
	}

	@Override
	public List<DeviceStatus> findById(String id) {
		return jpa.txExpr(
				em -> em.createQuery("select d from DeviceStatus d where d.device.id = :idDevice", DeviceStatus.class)
						.setParameter("idDevice", id).getResultList()
		);
	}

	@Override
	public List<DeviceStatus> getListAll() {
		return jpa.txExpr(em -> em.createQuery("select d from DeviceStatus d", DeviceStatus.class).getResultList());
	}

}
