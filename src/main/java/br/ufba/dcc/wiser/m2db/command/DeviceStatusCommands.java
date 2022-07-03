package br.ufba.dcc.wiser.m2db.command;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import br.ufba.dcc.wiser.m2db.service.DeviceServiceDB;
import br.ufba.dcc.wiser.m2db.service.DeviceStatusServiceDB;
import br.ufba.dcc.wiser.m2model.model.DeviceStatus;

@Component(service = DeviceStatusCommands.class, property = { "osgi.command.scope=fot-device-status",
		"osgi.command.function=add", "osgi.command.function=findById", "osgi.command.function=getListAll" })
public class DeviceStatusCommands {

	@Reference
	private DeviceServiceDB deviceServiceDB;

	@Reference
	private DeviceStatusServiceDB deviceStatusServiceDB;

	SimpleDateFormat form = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	public void add(String deviceId, String situation) {
		DeviceStatus deviceStatus = new DeviceStatus(deviceId, situation, Calendar.getInstance());
		deviceStatusServiceDB.add(deviceStatus);
	}

	public void findById(String DeviceId) {
		List<DeviceStatus> listDeviceStatusById = deviceStatusServiceDB.findById(DeviceId);

		if (!listDeviceStatusById.isEmpty()) {
			System.out.println("--------- List of Devices Status ---------");
			System.out.println("IDDEVICE\tSITUATION\tTIMESTAMP");
			for (DeviceStatus deviceStatus : listDeviceStatusById) {
				System.out.println(deviceStatus.getDevice().getId() + " - " + deviceStatus.getSituation() + " - "
						+ form.format(deviceStatus.getDate().getTime()));
			}
		} else {
			System.out.println("No information stored");
		}
	}

	public void getListAll() {
		List<DeviceStatus> listDeviceStatus = deviceStatusServiceDB.getListAll();

		if (!listDeviceStatus.isEmpty()) {
			System.out.println("--------- List of Devices Status ---------");
			System.out.println("IDDEVICE\tSITUATION\tTIMESTAMP");
			for (DeviceStatus deviceStatus : listDeviceStatus) {
				System.out.println(deviceStatus.getDevice().getId() + " - " + deviceStatus.getSituation() + ", "
						+ form.format(deviceStatus.getDate().getTime()));
			}
		} else {
			System.out.println("No information stored");
		}
	}

}
