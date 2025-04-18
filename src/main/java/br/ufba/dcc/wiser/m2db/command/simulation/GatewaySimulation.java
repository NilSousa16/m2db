package br.ufba.dcc.wiser.m2db.command.simulation;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import br.ufba.dcc.wiser.m2db.service.GatewayServiceDB;
import br.ufba.dcc.wiser.m2db.service.GatewayStatusServiceDB;
import br.ufba.dcc.wiser.m2db.utils.Consts;
import br.ufba.dcc.wiser.m2db.utils.CoordinateGenerator;
import br.ufba.dcc.wiser.m2model.model.Gateway;
import br.ufba.dcc.wiser.m2model.model.GatewayStatus;
import br.ufba.dcc.wiser.m2model.model.utils.Coordinates;

@Component(service = GatewaySimulation.class, property = { "osgi.command.scope=fot-gateway-simulation",
		"osgi.command.function=generateGateways", "osgi.command.function=generateGatewayStatus",
		"osgi.command.function=dev_generate_data" })
public class GatewaySimulation {

	@Reference
	private GatewayServiceDB gatewayServiceDB;

	@Reference
	private GatewayStatusServiceDB gatewayStatusServiceDB;

	Random random = new Random();

	// Ex - fot-gateway-simulation:generateGateways 15 -13.001254365313615
	// -38.507439494133 2
	public void generateGateways(int totalGateway, double centralLatitude, double centralLongitude, double radiusKm) {

		Gateway gateway;
		Coordinates coordinates;

		System.out.println(">>> Inserting data about the gateways...");

		for (int i = 0; i < totalGateway; i++) {
			String mac = Integer.toString(random.nextInt(32)) + ":" + Integer.toString(random.nextInt(32)) + ":"
					+ Integer.toString(random.nextInt(32)) + ":" + Integer.toString(random.nextInt(32)) + ":"
					+ Integer.toString(random.nextInt(32)) + ":" + Integer.toString(random.nextInt(32));

			String ip = Integer.toString(random.nextInt(192)) + "." + Integer.toString(random.nextInt(192)) + "."
					+ Integer.toString(random.nextInt(192)) + "." + Integer.toString(random.nextInt(192));

			gateway = new Gateway();

			gateway.setMac(mac);
			gateway.setIp(ip);
			gateway.setManufacturer(Consts.MANUFACTURER_LIST[random.nextInt(Consts.MANUFACTURER_LIST.length)]);
			gateway.setHostName("GT" + random.nextInt(192));
			gateway.setStatus(true);
			gateway.setDate(Calendar.getInstance());
			gateway.setSolution(Consts.SMART_SOLUTION_LIST[random.nextInt(Consts.SMART_SOLUTION_LIST.length)]);

			coordinates = CoordinateGenerator.generator(centralLatitude, centralLongitude, radiusKm);
			gateway.setCoordinates(coordinates);

			try {
				gatewayServiceDB.add(gateway);
			} catch (Exception e) {
				System.out.println("Gateway insertion failed: " + gateway.getMac());
				System.out.println("Message error: " + e.getMessage());
			}			
		}
		System.out.println(">>> Completed insertion of data about gateways...");
	}

	public void generateGatewaysBySolution(int totalGateway, String solution) {
	}

	// Ex - fot-gateway-simulation:generateGatewayStatus 15 27
	public void generateGatewayStatus(int totalGateway, int totalGatewayStatus) {

		Set<Gateway> gatewaySet = new HashSet<>();

		try {
			gatewaySet.addAll(gatewayServiceDB.list());
		} catch (Exception e) {
			System.out.println("Gateway list retrieval failed");
			System.out.println("Message error: " + e.getMessage());
		}

		// Capture the timestamp for time simulation
		Calendar calendarInstance = Calendar.getInstance();

		System.out.println(">>> Inserting data about the gateways status...");
		for (Gateway gateway : gatewaySet) {
			for (int i = 0; i <= totalGatewayStatus; i++) {
				// Simulates counting seconds
				calendarInstance.add(Calendar.SECOND, 1);

				GatewayStatus gatewayStatus = new GatewayStatus(gateway.getMac(), random.nextInt(100),
						random.nextInt(100), random.nextInt(100), calendarInstance);

				gatewayStatusServiceDB.add(gatewayStatus);				
			}
		}

		System.out.println(">>> Completed insertion of data about gateways...");
	}

	public void generateGatewayStatusBySolution(String solution, int totalGatewayStatus) {
	}

	public void generateGatewayStatusById(String macId, int totalGatewayStatus) {
	}

	public void dev_generate_data() {
		this.generateGateways(25, -13.001254365313615, -38.507439494133, 2);
		this.generateGatewayStatus(25, 24);
	}

}
