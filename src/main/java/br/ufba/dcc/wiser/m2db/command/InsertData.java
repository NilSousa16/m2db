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

@Component(service = InsertData.class, property = { "osgi.command.scope=fot-dev", "osgi.command.function=insertData" })
public class InsertData {

	@Reference
	private GatewayServiceDB gatewayServiceDB;

	@Reference
	private DeviceServiceDB deviceServiceDB;

	@Reference
	private GatewayStatusServiceDB gatewayStatusServiceDB;

	@Reference
	private DeviceStatusServiceDB deviceStatusServiceDB;

	SimpleDateFormat form = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	public void insertData() throws InterruptedException {

		Gateway gateway = new Gateway();

		Random random = new Random();
		Device device = new Device();
		String idDevice;

		List<String> deviceIdList = new ArrayList<String>();

		/**
		 * Gateways
		 */

		System.out.println(">>> Inserting data about the gateways");

		/* Gateway 01 */
		gateway.setMac("12.12.12.12.12.12");
		gateway.setIp("192.168.12.12");
		gateway.setManufacturer("WiserCity");
		gateway.setHostName("GT01");
		gateway.setStatus(true);
		gateway.setDate(Calendar.getInstance());
		gateway.setSolution("smart traffic");
		gateway.setCoordinates("57.0030, 86.2199");
		gatewayServiceDB.add(gateway);
		/* Gateway 02 */
		gateway.setMac("13.13.13.13.13.13");
		gateway.setIp("192.168.22.222");
		gateway.setManufacturer("WiserCity");
		gateway.setHostName("GT02");
		gateway.setStatus(true);
		gateway.setDate(Calendar.getInstance());
		gateway.setSolution("smart light");
		gateway.setCoordinates("-69.6749, 0.7987");
		gatewayServiceDB.add(gateway);
		/* Gateway 03 */
		gateway.setMac("14.14.14.14.14.14");
		gateway.setIp("192.168.10.210");
		gateway.setManufacturer("WiserCity");
		gateway.setHostName("GT03");
		gateway.setStatus(true);
		gateway.setDate(Calendar.getInstance());
		gateway.setSolution("smart light");
		gateway.setCoordinates("-3.2955, 28.1461");
		gatewayServiceDB.add(gateway);
		/* Gateway 04 */
		gateway.setMac("15.15.15.15.15.15");
		gateway.setIp("192.168.20.220");
		gateway.setManufacturer("WiserCity");
		gateway.setHostName("GT04");
		gateway.setStatus(true);
		gateway.setDate(Calendar.getInstance());
		gateway.setSolution("smart light");
		gateway.setCoordinates("-117.3494, 36.9742");
		gatewayServiceDB.add(gateway);
		/* Gateway 05 */
		gateway.setMac("16.16.16.16.16.16");
		gateway.setIp("192.168.30.230");
		gateway.setManufacturer("WiserCity");
		gateway.setHostName("GT05");
		gateway.setStatus(true);
		gateway.setDate(Calendar.getInstance());
		gateway.setSolution("smart traffic");
		gateway.setCoordinates("75.1715, -0.6166");
		gatewayServiceDB.add(gateway);

		/**
		 * Devices
		 */

		System.out.println(">>> Inserting data about the devices");

		/* Gateway - 12.12.12.12.12.12 */
		idDevice = Integer.toString(random.nextInt(10000));
		deviceIdList.add(idDevice);
		device.setId(idDevice);
		device.setLocation("122.9689, -44.7436");
		device.setDescription("DTM");
		device.setTypeDevice("temperature");
		device.setCategory("sensor");
		device.setStatus(true);
		device.setDate(Calendar.getInstance());

		gateway = new Gateway();
		gateway.setMac("12.12.12.12.12.12");
		device.setGateway(gateway);
		deviceServiceDB.add(device);

		idDevice = Integer.toString(random.nextInt(10000));
		deviceIdList.add(idDevice);
		device.setId(idDevice);
		device.setLocation("-122.9089, -65.4445");
		device.setDescription("DTM");
		device.setTypeDevice("temperature");
		device.setCategory("sensor");
		device.setStatus(true);
		device.setDate(Calendar.getInstance());

		gateway = new Gateway();
		gateway.setMac("12.12.12.12.12.12");
		device.setGateway(gateway);
		deviceServiceDB.add(device);

		idDevice = Integer.toString(random.nextInt(10000));
		deviceIdList.add(idDevice);
		device.setId(idDevice);
		device.setLocation("-122.9089, -65.4445");
		device.setDescription("DTM");
		device.setTypeDevice("eletric lock");
		device.setCategory("actuador");
		device.setStatus(true);
		device.setDate(Calendar.getInstance());

		gateway = new Gateway();
		gateway.setMac("12.12.12.12.12.12");
		device.setGateway(gateway);
		deviceServiceDB.add(device);

		/* Gateway - 13.13.13.13.13.13 */
		/* Device - 01 */
		idDevice = Integer.toString(random.nextInt(10000));
		deviceIdList.add(idDevice);
		device.setId(idDevice);
		device.setLocation("39.6684, -74.2444");
		device.setDescription("SMS");
		device.setTypeDevice("fire");
		device.setCategory("sensor");
		device.setStatus(true);
		device.setDate(Calendar.getInstance());
		gateway = new Gateway();
		gateway.setMac("13.13.13.13.13.13");
		device.setGateway(gateway);
		deviceServiceDB.add(device);

		/* Device - 02 */
		idDevice = Integer.toString(random.nextInt(10000));
		deviceIdList.add(idDevice);
		device.setId(idDevice);
		device.setLocation("39.6684, -74.2444");
		device.setDescription("SMS");
		device.setTypeDevice("temperature");
		device.setCategory("sensor");
		device.setStatus(true);
		device.setDate(Calendar.getInstance());
		gateway = new Gateway();
		gateway.setMac("13.13.13.13.13.13");
		device.setGateway(gateway);
		deviceServiceDB.add(device);

		/* Device - 03 */
		idDevice = Integer.toString(random.nextInt(10000));
		deviceIdList.add(idDevice);
		device.setId(idDevice);
		device.setLocation("147.4131, 65.5867");
		device.setDescription("SMS");
		device.setTypeDevice("humidity");
		device.setCategory("sensor");
		device.setStatus(true);
		device.setDate(Calendar.getInstance());
		gateway = new Gateway();
		gateway.setMac("13.13.13.13.13.13");
		device.setGateway(gateway);
		deviceServiceDB.add(device);

		/* Gateway - 14.14.14.14.14.14 */
		/* Device - 01 */
		idDevice = Integer.toString(random.nextInt(10000));
		deviceIdList.add(idDevice);
		device.setId(idDevice);
		device.setLocation("89.6331, -46.3719");
		device.setDescription("HMS");
		device.setTypeDevice("light switch");
		device.setCategory("actuador");
		device.setStatus(true);
		device.setDate(Calendar.getInstance());
		gateway = new Gateway();
		gateway.setMac("14.14.14.14.14.14");
		device.setGateway(gateway);
		deviceServiceDB.add(device);

		/* Device - 02 */
		idDevice = Integer.toString(random.nextInt(10000));
		deviceIdList.add(idDevice);
		device.setId(idDevice);
		device.setLocation("116.4168, -50.0219");
		device.setDescription("HMS");
		device.setTypeDevice("light switch");
		device.setCategory("actuador");
		device.setStatus(true);
		device.setDate(Calendar.getInstance());
		gateway = new Gateway();
		gateway.setMac("14.14.14.14.14.14");
		device.setGateway(gateway);
		deviceServiceDB.add(device);

		/* Device - 03 */
		idDevice = Integer.toString(random.nextInt(10000));
		deviceIdList.add(idDevice);
		device.setId(idDevice);
		device.setLocation("73.8520, -74.7956");
		device.setDescription("HMS");
		device.setTypeDevice("luminosity");
		device.setCategory("sensor");
		device.setStatus(true);
		device.setDate(Calendar.getInstance());
		gateway = new Gateway();
		gateway.setMac("14.14.14.14.14.14");
		device.setGateway(gateway);
		deviceServiceDB.add(device);

		/* Gateway - 15.15.15.15.15.15 */
		/* Device - 01 */
		idDevice = Integer.toString(random.nextInt(10000));
		deviceIdList.add(idDevice);
		device.setId(idDevice);
		device.setLocation("-53.1663, -86.4732");
		device.setDescription("GTA");
		device.setTypeDevice("temperatute");
		device.setCategory("sensor");
		device.setStatus(true);
		device.setDate(Calendar.getInstance());
		gateway = new Gateway();
		gateway.setMac("15.15.15.15.15.15");
		device.setGateway(gateway);
		deviceServiceDB.add(device);

		/* Device - 02 */
		idDevice = Integer.toString(random.nextInt(10000));
		deviceIdList.add(idDevice);
		device.setId(idDevice);
		device.setLocation("-53.1663, -86.4732");
		device.setDescription("GTA");
		device.setTypeDevice("luminosity");
		device.setCategory("sensor");
		device.setStatus(true);
		device.setDate(Calendar.getInstance());
		gateway = new Gateway();
		gateway.setMac("15.15.15.15.15.15");
		device.setGateway(gateway);
		deviceServiceDB.add(device);

		/* Device - 03 */
		idDevice = Integer.toString(random.nextInt(10000));
		deviceIdList.add(idDevice);
		device.setId(idDevice);
		device.setLocation("-93.3353, -14.6778");
		device.setDescription("GTA");
		device.setTypeDevice("humidity");
		device.setCategory("sensor");
		device.setStatus(true);
		device.setDate(Calendar.getInstance());
		gateway = new Gateway();
		gateway.setMac("15.15.15.15.15.15");
		device.setGateway(gateway);
		deviceServiceDB.add(device);

		/* Gateway - 16.16.16.16.16.16 */
		/* Device - 01 */
		idDevice = Integer.toString(random.nextInt(10000));
		deviceIdList.add(idDevice);
		device.setId(idDevice);
		device.setLocation("-93.3353, -14.6778");
		device.setDescription("ASX");
		device.setTypeDevice("temperature");
		device.setCategory("sensor");
		device.setStatus(true);
		device.setDate(Calendar.getInstance());
		gateway = new Gateway();
		gateway.setMac("16.16.16.16.16.16");
		device.setGateway(gateway);
		deviceServiceDB.add(device);

		/* Device - 02 */
		idDevice = Integer.toString(random.nextInt(10000));
		deviceIdList.add(idDevice);
		device.setId(idDevice);
		device.setLocation("-89.4459, -38.5067");
		device.setDescription("ASX");
		device.setTypeDevice("door handle");
		device.setCategory("actuator");
		device.setStatus(true);
		device.setDate(Calendar.getInstance());
		gateway = new Gateway();
		gateway.setMac("16.16.16.16.16.16");
		device.setGateway(gateway);
		deviceServiceDB.add(device);

		/* Device - 03 */
		idDevice = Integer.toString(random.nextInt(10000));
		deviceIdList.add(idDevice);
		device.setId(idDevice);
		device.setLocation("-89.4459, -38.5067");
		device.setDescription("ASX");
		device.setTypeDevice("light switch");
		device.setCategory("actuator");
		device.setStatus(true);
		device.setDate(Calendar.getInstance());
		gateway = new Gateway();
		gateway.setMac("16.16.16.16.16.16");
		device.setGateway(gateway);
		deviceServiceDB.add(device);

		/**
		 * Gateways Status
		 */

		System.out.println(">>> Inserting data about the gateways status");

		for (int i = 0; i <= 100; i++) {
			GatewayStatus gatewayStatus = new GatewayStatus("12.12.12.12.12.12", random.nextInt(100),
					random.nextInt(100), random.nextInt(100), Calendar.getInstance());
			Thread.sleep(20);

			gatewayStatusServiceDB.add(gatewayStatus);
		}

		for (int i = 0; i <= 100; i++) {
			GatewayStatus gatewayStatus = new GatewayStatus("13.13.13.13.13.13", random.nextInt(100),
					random.nextInt(100), random.nextInt(100), Calendar.getInstance());
			Thread.sleep(20);

			gatewayStatusServiceDB.add(gatewayStatus);
		}

		for (int i = 0; i <= 100; i++) {
			GatewayStatus gatewayStatus = new GatewayStatus("14.14.14.14.14.14", random.nextInt(100),
					random.nextInt(100), random.nextInt(100), Calendar.getInstance());
			Thread.sleep(20);

			gatewayStatusServiceDB.add(gatewayStatus);
		}

		for (int i = 0; i <= 100; i++) {
			GatewayStatus gatewayStatus = new GatewayStatus("15.15.15.15.15.15", random.nextInt(100),
					random.nextInt(100), random.nextInt(100), Calendar.getInstance());
			Thread.sleep(20);

			gatewayStatusServiceDB.add(gatewayStatus);
		}

		for (int i = 0; i <= 100; i++) {
			GatewayStatus gatewayStatus = new GatewayStatus("16.16.16.16.16.16", random.nextInt(100),
					random.nextInt(100), random.nextInt(100), Calendar.getInstance());
			Thread.sleep(20);

			gatewayStatusServiceDB.add(gatewayStatus);
		}

		/**
		 * Device Status
		 */

		System.out.println(">>> Inserting data about the device status");

		String situationList[] = { "operational", "testing", "unused" };

		for (String idDeviceList : deviceIdList) {
			DeviceStatus deviceStatus;
			System.out.println(">>>>" + idDeviceList);
			for (int i = 0; i < 50; i++) {
				deviceStatus = new DeviceStatus(idDeviceList, situationList[random.nextInt(3)],
						Calendar.getInstance());
				Thread.sleep(20);
				deviceStatusServiceDB.add(deviceStatus);
			}

		}

	}

}