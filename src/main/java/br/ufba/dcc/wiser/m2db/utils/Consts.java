package br.ufba.dcc.wiser.m2db.utils;

public final class Consts {

	// Prevent creation of instances of the class
	private Consts() {
		throw new UnsupportedOperationException("Utility class");
	}

	// List of smart solutions
	public static final String[] SMART_SOLUTION_LIST = { "smart traffic", "smart parking", "structural health",
			"water quality", "traffic congestion", "smart lighting", "air pollution", "forest fire detection" };

	// List of IoT Device Manufacturers
	public static final String[] MANUFACTURER_LIST = { "Bosch IoT Suite", "Cisco IoT", "Siemens Mindsphere",
			"Amazon AWS IoT", "Google Cloud IoT", "IBM Watson IoT", "Intel IoT", "Microsoft Azure IoT",
			"Schneider Electric EcoStruxure", "GE Digital Predix", "Huawei OceanConnect IoT", "Samsung Artik",
			"Honeywell Connected Enterprise", "PTC ThingWorx", "Sigfox", "Telenor Connexion", "Zebra Technologies",
			"NXP Semiconductors", "STMicroelectronics", "u-blox" };

	public static final String[] ACTUATOR_LIST = { "Smart traffic light", "Smart streetlight", "Solenoid valve",
			"Smart barrier", "Electric lock", "Automatic door", "Electronic siren", "Smart PTZ camera",
			"Smart message panel", "Drainage pump", "Smart ventilation actuator", "Smart gate barrier",
			"Vibration module for haptic feedback", "Smart sprinkler", "Gas solenoid valve", "Smart gate",
			"Smart robotic arm", "Smart blind", "Solenoid valve for water fountain" };

	public static final String[] SENSOR_LIST = { "Temperature sensor", "Humidity sensor", "Atmospheric pressure sensor",
			"Air quality sensor", "Gas sensor", "Light sensor", "Motion sensor", "Proximity sensor",
			"Ultrasonic sensor", "Infrared sensor", "Smoke sensor", "Flame sensor", "Vibration sensor",
			"Water level sensor", "Water flow sensor", "Soil moisture sensor", "pH sensor",
			"Electrical conductivity sensor", "Noise sensor", "Touch sensor", "Color sensor", "Presence sensor",
			"Acceleration sensor", "Gyroscope sensor", "Magnetism sensor", "GPS sensor", "RFID sensor", "NFC sensor",
			"Radar sensor", "Biometric sensor" };

	public static final String DEVICES_JSON = "[\n"
			+ "    { \"name\": \"Smart traffic light\", \"type\": \"Actuator\" },\n"
			+ "    { \"name\": \"Smart streetlight\", \"type\": \"Actuator\" },\n"
			+ "    { \"name\": \"Solenoid valve\", \"type\": \"Actuator\" },\n"
			+ "    { \"name\": \"Smart barrier\", \"type\": \"Actuator\" },\n"
			+ "    { \"name\": \"Electric lock\", \"type\": \"Actuator\" },\n"
			+ "    { \"name\": \"Automatic door\", \"type\": \"Actuator\" },\n"
			+ "    { \"name\": \"Electronic siren\", \"type\": \"Actuator\" },\n"
			+ "    { \"name\": \"Smart PTZ camera\", \"type\": \"Actuator\" },\n"
			+ "    { \"name\": \"Smart message panel\", \"type\": \"Actuator\" },\n"
			+ "    { \"name\": \"Drainage pump\", \"type\": \"Actuator\" },\n"
			+ "    { \"name\": \"Smart ventilation actuator\", \"type\": \"Actuator\" },\n"
			+ "    { \"name\": \"Smart gate barrier\", \"type\": \"Actuator\" },\n"
			+ "    { \"name\": \"Vibration module for haptic feedback\", \"type\": \"Actuator\" },\n"
			+ "    { \"name\": \"Smart sprinkler\", \"type\": \"Actuator\" },\n"
			+ "    { \"name\": \"Gas solenoid valve\", \"type\": \"Actuator\" },\n"
			+ "    { \"name\": \"Smart gate\", \"type\": \"Actuator\" },\n"
			+ "    { \"name\": \"Smart robotic arm\", \"type\": \"Actuator\" },\n"
			+ "    { \"name\": \"Smart blind\", \"type\": \"Actuator\" },\n"
			+ "    { \"name\": \"Solenoid valve for water fountain\", \"type\": \"Actuator\" },\n"
			+ "    { \"name\": \"Temperature sensor\", \"type\": \"Sensor\" },\n"
			+ "    { \"name\": \"Humidity sensor\", \"type\": \"Sensor\" },\n"
			+ "    { \"name\": \"Atmospheric pressure sensor\", \"type\": \"Sensor\" },\n"
			+ "    { \"name\": \"Air quality sensor\", \"type\": \"Sensor\" },\n"
			+ "    { \"name\": \"Gas sensor\", \"type\": \"Sensor\" },\n"
			+ "    { \"name\": \"Light sensor\", \"type\": \"Sensor\" },\n"
			+ "    { \"name\": \"Motion sensor\", \"type\": \"Sensor\" },\n"
			+ "    { \"name\": \"Proximity sensor\", \"type\": \"Sensor\" },\n"
			+ "    { \"name\": \"Ultrasonic sensor\", \"type\": \"Sensor\" },\n"
			+ "    { \"name\": \"Infrared sensor\", \"type\": \"Sensor\" },\n"
			+ "    { \"name\": \"Smoke sensor\", \"type\": \"Sensor\" },\n"
			+ "    { \"name\": \"Flame sensor\", \"type\": \"Sensor\" },\n"
			+ "    { \"name\": \"Vibration sensor\", \"type\": \"Sensor\" },\n"
			+ "    { \"name\": \"Water level sensor\", \"type\": \"Sensor\" },\n"
			+ "    { \"name\": \"Water flow sensor\", \"type\": \"Sensor\" },\n"
			+ "    { \"name\": \"Soil moisture sensor\", \"type\": \"Sensor\" },\n"
			+ "    { \"name\": \"pH sensor\", \"type\": \"Sensor\" },\n"
			+ "    { \"name\": \"Electrical conductivity sensor\", \"type\": \"Sensor\" },\n"
			+ "    { \"name\": \"Noise sensor\", \"type\": \"Sensor\" },\n"
			+ "    { \"name\": \"Touch sensor\", \"type\": \"Sensor\" },\n"
			+ "    { \"name\": \"Color sensor\", \"type\": \"Sensor\" },\n"
			+ "    { \"name\": \"Presence sensor\", \"type\": \"Sensor\" },\n"
			+ "    { \"name\": \"Acceleration sensor\", \"type\": \"Sensor\" },\n"
			+ "    { \"name\": \"Gyroscope sensor\", \"type\": \"Sensor\" },\n"
			+ "    { \"name\": \"Magnetism sensor\", \"type\": \"Sensor\" },\n"
			+ "    { \"name\": \"GPS sensor\", \"type\": \"Sensor\" },\n"
			+ "    { \"name\": \"RFID sensor\", \"type\": \"Sensor\" },\n"
			+ "    { \"name\": \"NFC sensor\", \"type\": \"Sensor\" },\n"
			+ "    { \"name\": \"Radar sensor\", \"type\": \"Sensor\" },\n"
			+ "    { \"name\": \"Biometric sensor\", \"type\": \"Sensor\" }\n" + "]";

	public static final String[] OPERATION_MODE_LIST = { 
			"operational", // Device is functioning normally
			"testing", // Device is in testing phase
			"unused", // Device is not in use
			"maintenance", // Device is under maintenance
			"standby", // Device is in standby mode (ready but inactive)
			"calibration", // Device is undergoing calibration
			"faulty" // Device is faulty or defective
	};

}
