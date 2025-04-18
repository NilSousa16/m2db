package br.ufba.dcc.wiser.m2db.command.simulation;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.ufba.dcc.wiser.m2db.service.DeviceServiceDB;
import br.ufba.dcc.wiser.m2db.service.DeviceStatusServiceDB;
import br.ufba.dcc.wiser.m2db.service.GatewayServiceDB;
import br.ufba.dcc.wiser.m2db.utils.Consts;
import br.ufba.dcc.wiser.m2db.utils.CoordinateGenerator;
import br.ufba.dcc.wiser.m2model.model.Device;
import br.ufba.dcc.wiser.m2model.model.DeviceStatus;
import br.ufba.dcc.wiser.m2model.model.Gateway;
import br.ufba.dcc.wiser.m2model.model.utils.Coordinates;

@Component(service = DeviceSimulation.class, property = { "osgi.command.scope=fot-device-simulation",
		"osgi.command.function=generateDevices", "osgi.command.function=dev_generate_data" })
public class DeviceSimulation {

	@Reference
	private GatewayServiceDB gatewayServiceDB;

	@Reference
	private DeviceServiceDB deviceServiceDB;

	@Reference
	private DeviceStatusServiceDB deviceStatusServiceDB;

	Random random = new Random();

	public class DeviceJson {
		private String name;
		private String type;

		public DeviceJson(String name, String type) {
			this.name = name;
			this.type = type;
		}

		public String getName() {
			return name;
		}

		public String getType() {
			return type;
		}

		@Override
		public String toString() {
			return "Device{name='" + name + "', type='" + type + "'}";
		}
	}

	// Ex - fot-device-simulation:generateDevices 568 2
	public void generateDevices(int totalDevice, double radiusKm) {

		Device device = new Device();		

		Set<Gateway> gatewaySet = new HashSet<>();
		gatewaySet.addAll(gatewayServiceDB.list());

		// List of the devices
		Gson gson = new Gson();
		Type listType = new TypeToken<List<DeviceJson>>() {
		}.getType();
		List<DeviceJson> devicesJson = gson.fromJson(Consts.DEVICES_JSON, listType);

		// Capture the timestamp for time simulation
		Calendar calendarInstance = Calendar.getInstance();

		Coordinates coordinates;

		System.out.println(">>> Inserting data about the devices...");

		// Generate devices
		for (int i = 0; i < totalDevice; i++) {
			// Selecting gateway for the device
			Gateway gateway = gatewaySet.stream().skip(random.nextInt(gatewaySet.size())).findFirst().orElse(null);

			// Simulates counting seconds
			calendarInstance.add(Calendar.SECOND, 1);

			device.setId(UUID.randomUUID().toString());

			coordinates = CoordinateGenerator.generator(gateway.getCoordinates().getLatitude(),
					gateway.getCoordinates().getLongitude(), radiusKm);

			device.setCoordinates(coordinates);
			device.setDescription("DTM");

			int deviceSelection = random.nextInt(devicesJson.size());
			device.setTypeDevice(devicesJson.get(deviceSelection).getName());
			device.setCategory(devicesJson.get(deviceSelection).getType());

			device.setStatus(true);
			device.setDate(calendarInstance);

			device.setGateway(gateway);

			try {
				deviceServiceDB.add(device);
			} catch (Exception e) {
				System.out.println("Device insertion failed: " + device.getId());
				System.out.println("Message error: " + e.getMessage());
			}
		}
		System.out.println(">>> Completed insertion of data about devices...");
	}

	public void generateDeviceStatus(int totalDevice, int totalDeviceStatus) {
		Set<Device> deviceSet = new HashSet<>();
		deviceSet.addAll(deviceServiceDB.list());

		// Capture the timestamp for time simulation
		Calendar calendarInstance = Calendar.getInstance();

		DeviceStatus deviceStatus;

		System.out.println(">>> Inserting data about the device status...");

		for (Device device : deviceSet) {

			for (int i = 0; i < totalDeviceStatus; i++) {
				// Simulates counting seconds
				calendarInstance.add(Calendar.SECOND, 1);

				deviceStatus = new DeviceStatus(device.getId(),
						Consts.OPERATION_MODE_LIST[random.nextInt(Consts.OPERATION_MODE_LIST.length)],
						calendarInstance);
				try {
					deviceStatusServiceDB.add(deviceStatus);
				} catch (Exception e) {
					System.out.println("Device status insertion failed: " + device.getId());
					System.out.println("Message error: " + e.getMessage());
				}
			}
		}

		System.out.println(">>> Completed data entry");
	}

	public void dev_generate_data() {
		this.generateDevices(568, 2);
		this.generateDeviceStatus(568, 32);
	}

}
