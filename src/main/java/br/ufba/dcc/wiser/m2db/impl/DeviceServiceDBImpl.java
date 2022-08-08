package br.ufba.dcc.wiser.m2db.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.aries.jpa.template.EmConsumer;
import org.apache.aries.jpa.template.EmFunction;
import org.apache.aries.jpa.template.JpaTemplate;
import org.apache.aries.jpa.template.TransactionType;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import br.ufba.dcc.wiser.m2db.service.DeviceServiceDB;
import br.ufba.dcc.wiser.m2model.model.Device;

@Component
public class DeviceServiceDBImpl implements DeviceServiceDB {

	private JpaTemplate jpa;

	@Activate
	public void startOSGI() {
		System.out.println("Started DS in the M2DB - DeviceServiceDBImpl...");
	}

	@Reference(target = "(osgi.unit.name=person)")
	public void setJpa(JpaTemplate jpa) {
		this.jpa = jpa;
	}

	@Override
	public void add(Device device) {
		jpa.tx(em -> {
			em.persist(device);
			em.flush();
		});
	}

	@Override
	public void update(Device device) {
		jpa.tx(new EmConsumer() {
			@Override
			public void accept(EntityManager em) {
				em.merge(device);
			}
		});
	}

	@Override
	public void delete(String id) {
		jpa.tx(new EmConsumer() {
			@Override
			public void accept(EntityManager em) {
				em.remove(find(id));
			}
		});
	}

	@Override
	public Device find(String id) {
		return jpa.txExpr(TransactionType.Required, new EmFunction<Device>() {
			@Override
			public Device apply(EntityManager em) {
				return em.find(Device.class, id);
			}
		});
	}

	@Override
	public List<Device> listByGateway(String gatewayMac) {
		return jpa.txExpr(
				em -> em.createQuery("select d from Device d where d.gateway.mac = :gatewayMac", Device.class)
						.setParameter("gatewayMac", gatewayMac).getResultList()
		);
	}
	
	@Override
	public List<Device> list() {
		return jpa.txExpr(em -> em.createQuery("select d from Device d", Device.class).getResultList());
	}
	
}
