package br.ufba.dcc.wiser.m2db.command;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import br.ufba.dcc.wiser.m2db.model.Gateway;
import br.ufba.dcc.wiser.m2db.service.GatewayService;

@Component(service = GatewayCommands.class, property = { "osgi.command.scope=fot-gateway", "osgi.command.function=add",
		"osgi.command.function=update", "osgi.command.function=delete", "osgi.command.function=find",
		"osgi.command.function=list" })
public class GatewayCommands {

	@Reference
	private GatewayService gatewayService;

	public void add(String mac, String ip, String manufacturer, String hostName) {

		System.out.println("Mac: " + mac + " Ip: " + ip 
				+ " Manufacturer: " + manufacturer + " HostName: " + hostName);

		Gateway gateway = new Gateway(mac, ip, manufacturer, hostName);

		gatewayService.add(gateway);
	}

	public void update(String mac) {
		gatewayService.update(mac);
	}

	public void delete(String mac) {
		gatewayService.delete(mac);
	}

	public void find(String mac) {
		gatewayService.find(mac);
	}

	public void list() {
		List<Gateway> listGateway = gatewayService.list();
		for (Gateway gateway : listGateway) {
			System.out.println(gateway.getMac() + ", " + gateway.getIp() + ", " + gateway.getManufacturer() + ", "
					+ gateway.getHostName());
		}
	}
}