package br.ufba.dcc.wiser.m2db.command;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import br.ufba.dcc.wiser.m2db.service.GatewayServiceDB;
import br.ufba.dcc.wiser.m2model.model.Gateway;

@Component(service = GatewayCommands.class, property = { "osgi.command.scope=fot-gateway", "osgi.command.function=add",
		"osgi.command.function=update", "osgi.command.function=delete", "osgi.command.function=find",
		"osgi.command.function=list" })
public class GatewayCommands {

	@Reference
	private GatewayServiceDB gatewayServiceDB;

	public void add(String mac, String ip, String manufacturer, String hostName) {
		Gateway gateway = new Gateway(mac, ip, manufacturer, hostName, true);
		
		gatewayServiceDB.add(gateway);
		
		System.out.println("Add gateway \nMac: " + gateway.getMac() + "\nIp: " + gateway.getIp() + "\nManufacturer: " + gateway.getManufacturer() + "\nHostName: "
				+ gateway.getHostName() + "\nStatus: " + gateway.isStatus());
	}

	public void update(String mac, String ip, String manufacturer, String hostName, boolean status) {
		
		if(gatewayServiceDB.find(mac) != null) {
			Gateway gateway = new Gateway(mac, ip, manufacturer, hostName, status);
			gatewayServiceDB.update(gateway);
			System.out.println("Update of id " + mac + " performed successfully.");
		} else {
			System.out.println("Record not found for update");
		}
	}

	public void delete(String mac) {
		
		if(gatewayServiceDB.find(mac) != null) {
			gatewayServiceDB.delete(mac);
			System.out.println("Delete of id " + mac + " performed successfully.");
		} else {
			System.out.println("Record not found for deletion");
		}
	}

	public void find(String mac) {
		Gateway gateway = gatewayServiceDB.find(mac);
		
		if(gateway != null) {
			System.out.println("--------- Information found ---------");
			System.out.println("MAC, IP, MANUFACTURER, HOSTNAME, STATUS");
			System.out.println(gateway.getMac() + ", " + gateway.getIp() + ", " + gateway.getManufacturer() + ", "
					+ gateway.getHostName() + ", " + gateway.isStatus());
		} else {
			System.out.println("Register not found");
		}
	}

	public void list() {
		List<Gateway> listGateway = gatewayServiceDB.list();
		
		if(!listGateway.isEmpty()) {
			System.out.println("--------- List of Gateways ---------");
			System.out.println("MAC, IP, MANUFACTURER, HOSTNAME, STATUS");
			for (Gateway gateway : listGateway) {
				System.out.println(gateway.getMac() + ", " + gateway.getIp() + ", " + gateway.getManufacturer() + ", "
						+ gateway.getHostName() + ", " + gateway.isStatus());
			}
		} else {
			System.out.println("No information stored");
		}
	}
}