package br.ufba.dcc.wiser.m2db.command;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import br.ufba.dcc.wiser.m2db.service.DeviceServiceDB;
import br.ufba.dcc.wiser.m2db.service.DeviceStatusServiceDB;
import br.ufba.dcc.wiser.m2db.service.GatewayServiceDB;
import br.ufba.dcc.wiser.m2db.service.GatewayStatusServiceDB;
import br.ufba.dcc.wiser.m2model.model.Device;
import br.ufba.dcc.wiser.m2model.model.DeviceStatus;
import br.ufba.dcc.wiser.m2model.model.Gateway;
import br.ufba.dcc.wiser.m2model.model.GatewayStatus;

@Component(service = DataGenerator.class, property = { "osgi.command.scope=fot-dev",
		"osgi.command.function=dataGenerator" })
public class DataGenerator {

	@Reference
	private GatewayServiceDB gatewayServiceDB;

	@Reference
	private DeviceServiceDB deviceServiceDB;

	@Reference
	private GatewayStatusServiceDB gatewayStatusServiceDB;

	@Reference
	private DeviceStatusServiceDB deviceStatusServiceDB;

	SimpleDateFormat form = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	public void dataGenerator(int totalGateway, int totalGatewayStatus, int totalDevice, int totalDeviceStatus) throws InterruptedException {

		Random random = new Random();
		Device device = new Device();
		String idDevice;

		List<String> deviceIdList = new ArrayList<String>();
		List<String> gatewayIdList = new ArrayList<String>();

		/*
		 * Configuration for data generation
		 */
		final int TOTAL_GATEWAY = totalGateway;
		final int TOTAL_GATEWAY_STATUS = totalGatewayStatus;
		final int TOTAL_DEVICE = totalDevice;
		final int TOTAL_DEVICE_STATUS = totalDeviceStatus;

		/**
		 * Gateways
		 */

		Gateway gateway = new Gateway();

		System.out.println(">>> Inserting data about the gateways");

		String smartSolutionList[] = { "smart traffic", "smart parking", "structural health", "water quality",
				"traffic congestion", "smart lighting", "air pollution", "forest fire detection" };

		for (int i = 0; i < TOTAL_GATEWAY; i++) {
			String mac = Integer.toString(random.nextInt(32)) + ":" + Integer.toString(random.nextInt(32)) + ":"
					+ Integer.toString(random.nextInt(32)) + ":" + Integer.toString(random.nextInt(32)) + ":"
					+ Integer.toString(random.nextInt(32)) + ":" + Integer.toString(random.nextInt(32));

			String ip = Integer.toString(random.nextInt(192)) + "." + Integer.toString(random.nextInt(192)) + "."
					+ Integer.toString(random.nextInt(192)) + "." + Integer.toString(random.nextInt(192));

			gatewayIdList.add(mac);

			gateway.setMac(mac);
			gateway.setIp(ip);
			gateway.setManufacturer("WiserUFBA");
			gateway.setHostName("GT" + random.nextInt(192));
			gateway.setStatus(true);
			gateway.setDate(Calendar.getInstance());
			gateway.setSolution(smartSolutionList[random.nextInt(8)]);
			gateway.setCoordinates("57.0030, 86.2199");
			
			try {
				gatewayServiceDB.add(gateway);
			} catch (Exception e) {
				System.out.println("Gateway insertion failed: " + gateway.getMac());
			}	
		}

		/**
		 * Devices
		 */

		System.out.println(">>> Inserting data about the devices");

		String typeDeviceList[] = { "temperature", "humidity", "fire", "luminosity", "ultrasonic", "gas", "movement",
				"atmospheric" };
		String categoryDeviceList[] = { "sensor", "actuador" };

		for (int i = 0; i < TOTAL_DEVICE; i++) {

			idDevice = Integer.toString(random.nextInt(10000));

			deviceIdList.add(idDevice);

			device.setId(idDevice);
			device.setLocation("122.9689, -44.7436");
			device.setDescription("DTM");
			device.setTypeDevice(typeDeviceList[random.nextInt(typeDeviceList.length)]);
			device.setCategory(categoryDeviceList[random.nextInt(categoryDeviceList.length)]);
			device.setStatus(true);
			device.setDate(Calendar.getInstance());

			gateway = new Gateway();
			gateway.setMac(gatewayIdList.get(random.nextInt(gatewayIdList.size())));
			device.setGateway(gateway);
			
			try {
				deviceServiceDB.add(device);
			} catch (Exception e) {
				System.out.println("Device insertion failed: " + device.getId());
			}			
		}

		/**
		 * Gateways Status
		 */

		System.out.println(">>> Inserting data about the gateways status");

		for (String idGatewayList : gatewayIdList) {
			for (int i = 0; i <= TOTAL_GATEWAY_STATUS; i++) {
				GatewayStatus gatewayStatus = new GatewayStatus(idGatewayList, random.nextInt(100), random.nextInt(100),
						random.nextInt(100), Calendar.getInstance());
				Thread.sleep(10);

				gatewayStatusServiceDB.add(gatewayStatus);
			}
		}

		/**
		 * Device Status
		 */

		System.out.println(">>> Inserting data about the device status");

		String situationList[] = { "operational", "testing", "unused" };

		for (String idDeviceList : deviceIdList) {
			DeviceStatus deviceStatus;

			for (int i = 0; i < TOTAL_DEVICE_STATUS; i++) {
				deviceStatus = new DeviceStatus(idDeviceList, situationList[random.nextInt(situationList.length)],
						Calendar.getInstance());
				Thread.sleep(20);
				deviceStatusServiceDB.add(deviceStatus);
			}

		}
		
		System.out.println(">>> Completed data entry");

	}

}