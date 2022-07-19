package br.ufba.dcc.wiser.m2db.command;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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

	SimpleDateFormat form = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	public void add(String mac, String ip, String manufacturer, String hostName) {
		Gateway gateway = new Gateway(mac, ip, manufacturer, hostName, true, Calendar.getInstance());
		gatewayServiceDB.add(gateway);
	}

	public void update(String mac, String ip, String manufacturer, String hostName, boolean status) {
		Calendar date = Calendar.getInstance();

		if (gatewayServiceDB.find(mac) != null) {
			Gateway gateway = new Gateway(mac, ip, manufacturer, hostName, status, date);
			gatewayServiceDB.update(gateway);
			System.out.println("Update of id " + mac + " performed successfully.");
		} else {
			System.out.println("Record not found for update");
		}
	}

	public void delete(String mac) {
		if (gatewayServiceDB.find(mac) != null) {
			gatewayServiceDB.delete(mac);
			System.out.println("Delete of id " + mac + " performed successfully.");
		} else {
			System.out.println("Record not found for deletion");
		}
	}

	public void find(String mac) {
		Gateway gateway = gatewayServiceDB.find(mac);

		if (gateway != null) {
			System.out.println("--------- Information Found ---------");
			System.out.println("MAC\tIP\tMANUFACTURER\tHOSTNAME\tSTATUS\tUPDATE");
			System.out.println(gateway.getMac() + "\t" + gateway.getIp() + "\t" + gateway.getManufacturer() + "\t"
					+ gateway.getHostName() + "\t" + gateway.isStatus() + "\t"
					+ form.format(gateway.getDate().getTime()));
		} else {
			System.out.println("Register not found");
		}
	}

	public void list() {
		List<Gateway> listGateway = gatewayServiceDB.list();

		if (!listGateway.isEmpty()) {
			System.out.println("--------- List of Gateways ---------");
			System.out.println("MAC\tIP\tMANUFACTURER\tHOSTNAME\tSTATUS\tUPDATE");
			for (Gateway gateway : listGateway) {
				System.out.println(gateway.getMac() + "\t" + gateway.getIp() + "\t" + gateway.getManufacturer() + "\t"
						+ gateway.getHostName() + "\t" + gateway.isStatus() + "\t"
						+ form.format(gateway.getDate().getTime()));
			}
		} else {
			System.out.println("No information stored");
		}
	}
}