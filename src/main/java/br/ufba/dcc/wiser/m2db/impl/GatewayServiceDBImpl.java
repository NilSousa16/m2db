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

import br.ufba.dcc.wiser.m2db.service.GatewayServiceDB;
import br.ufba.dcc.wiser.m2model.model.Gateway;

// Model: https://github.com/apache/aries-jpa/blob/master/examples/tasklist-ds/src/main/java/org/apache/aries/jpa/example/tasklist/ds/impl/TaskServiceImpl.java

@Component
public class GatewayServiceDBImpl implements GatewayServiceDB {
	private JpaTemplate jpa;

	@Activate
	public void startOSGI() {
		System.out.println("Started DS in the M2DB - GatewayServiceDBImpl...");
	}

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
	public void update(Gateway gateway) {
		jpa.tx(new EmConsumer() {
            @Override
            public void accept(EntityManager em) {
            	em.merge(gateway);
            }
        });
	}

	@Override
	public void delete(String mac) {
		jpa.tx(new EmConsumer() {
			@Override
			public void accept(EntityManager em) {
				em.remove(find(mac));
			}
		});
	}
	
	@Override
	public Gateway find(String mac) {
        return jpa.txExpr(TransactionType.Required, new EmFunction<Gateway>() {
            @Override
            public Gateway apply(EntityManager em) {
                return em.find(Gateway.class, mac);
            }
        });
    }

	@Override
	public List<Gateway> list() {
		return jpa.txExpr(em -> em.createQuery("select g from Gateway g", Gateway.class).getResultList());
	}

}
