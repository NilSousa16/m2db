package br.ufba.dcc.wiser.m2db.command;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import br.ufba.dcc.wiser.m2db.service.DeviceServiceDB;
import br.ufba.dcc.wiser.m2model.model.Device;

@Component(service = DeviceCommands.class, property = { 
		"osgi.command.scope=fot-device", "osgi.command.function=add",
		"osgi.command.function=update", "osgi.command.function=delete", 
		"osgi.command.function=find",
		"osgi.command.function=listDevices" 
	})
public class DeviceCommands {
	
	@Reference
	private DeviceServiceDB deviceServiceDB;
	
	SimpleDateFormat form = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	public void add(String id, String location, String description, String typeSensor, Boolean status, String macGateway) {
		Device device = new Device(location, description, typeSensor, status, Calendar.getInstance(), macGateway);
		device.setId(id);
		deviceServiceDB.add(device);	
	}

	public void update(String id, String location, Boolean status, String macGateway) {
		Calendar date = Calendar.getInstance();
		
		if (deviceServiceDB.find(id) != null) {
			Device device = new Device();
			
			// typeSensor and description not included because never change			
			device.setId(id);
			device.setLocation(location);
			device.setDate(date);
			device.setStatus(status);
			device.getGateway().setMac(macGateway);
			
			deviceServiceDB.update(device);
			
			System.out.println("Device update of id " + id + " performed successfully.");
		} else {
			System.out.println("Device record not found for update");
		}
	}

	public void delete(String id) {		
		if (deviceServiceDB.find(id) != null) {
			deviceServiceDB.delete(id);
			System.out.println("Delete of id " + id + " performed successfully.");
		} else {
			System.out.println("Record not found for deletion");
		}
	}

	public void find(String id) {
		Device device = deviceServiceDB.find(id);

		if (device != null) {
			System.out.println("--------- Information Found ---------");
			System.out.println(
					"IDDEVICE\tLOCATION\tDESCRIPTION\tTYPESENSOR\tSTATUS\tTIMESTAMP\tGATEWAY"
			);
			System.out.println(
					device.getId() + " - " + device.getLocation() + " - " + 
					device.getDescription() + " - " + device.getTypeSensor() + " - " + 
					device.getStatus() + " - " + form.format(device.getDate().getTime()) + " - " + 
					device.getGateway().getMac()
			);
			
		} else {
			System.out.println("Device register not found");
		}
	}

	public void listDevices() {
		List<Device> deviceGateway = deviceServiceDB.list();

		if (!deviceGateway.isEmpty()) {
			System.out.println("--------- List of Devices ---------");
			System.out.println(
					"IDDEVICE\tLOCATION\tDESCRIPTION\tTYPESENSOR\tSTATUS\tTIMESTAMP\tGATEWAY"
			);
			for (Device device : deviceGateway) {
				System.out.println(
						device.getId() + " - " + device.getLocation() + " - " + 
						device.getDescription() + " - " + device.getTypeSensor() + " - " + 
						device.getStatus() + " - " + form.format(device.getDate().getTime()) + " - " + 
						device.getGateway().getMac()
				);
			}
			
		} else {
			System.out.println("No information stored");
		}
	}

}
