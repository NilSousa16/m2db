package br.ufba.dcc.wiser.m2db.command;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import br.ufba.dcc.wiser.m2db.service.GatewayServiceDB;
import br.ufba.dcc.wiser.m2db.service.GatewayStatusServiceDB;
import br.ufba.dcc.wiser.m2model.model.GatewayStatus;

@Component(service = GatewayStatusCommands.class, property = { "osgi.command.scope=fot-gateway-status",
		"osgi.command.function=add", "osgi.command.function=findByMac", "osgi.command.function=getListAll" })
public class GatewayStatusCommands {

	@Reference
	private GatewayServiceDB gatewayServiceDB;

	@Reference
	private GatewayStatusServiceDB gatewayStatusServiceDB;

	SimpleDateFormat form = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	public void add(String mac, double baterryLevel, long usedMemory, double usedProcessor) {
		GatewayStatus gatewayStatus = new GatewayStatus(mac, baterryLevel, usedMemory, usedProcessor,
				Calendar.getInstance());
		gatewayStatusServiceDB.add(gatewayStatus);
	}

	public void findByMac(String mac) {
		List<GatewayStatus> listGatewayStatusByMac = gatewayStatusServiceDB.findByMac(mac);

		if (!listGatewayStatusByMac.isEmpty()) {
			System.out.println("--------- List of Gateways Status ---------");
			System.out.println("Mac, BaterryLevel, UsedMemory, UsedProcessor, TimeStamp");
			for (GatewayStatus gatewayStatus : listGatewayStatusByMac) {
				System.out.println(gatewayStatus.getGateway().getMac() + ", " + gatewayStatus.getBaterryLevel() + ", " + gatewayStatus.getUsedMemory() + ", "
						+ gatewayStatus.getUsedProcessor() + ", " + form.format(gatewayStatus.getDate().getTime()));

			}
		} else {
			System.out.println("No information stored");
		}
	}

	public void getListAll() {
		List<GatewayStatus> listGatewayStatus = gatewayStatusServiceDB.getListAll();

		if (!listGatewayStatus.isEmpty()) {
			System.out.println("--------- List of Gateways Status ---------");
			System.out.println("Mac, BaterryLevel, UsedMemory, UsedProcessor, TimeStamp");
			for (GatewayStatus gatewayStatus : listGatewayStatus) {
				System.out.println(gatewayStatus.getGateway().getMac() + ", " + gatewayStatus.getBaterryLevel() + ", " + gatewayStatus.getUsedMemory() + ", "
						+ gatewayStatus.getUsedProcessor() + ", " + form.format(gatewayStatus.getDate().getTime()));

			}
		} else {
			System.out.println("No information stored");
		}
	}

}
