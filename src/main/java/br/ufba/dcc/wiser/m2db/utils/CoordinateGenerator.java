package br.ufba.dcc.wiser.m2db.utils;

import java.util.Random;

import br.ufba.dcc.wiser.m2model.model.utils.Coordinates;

public final class CoordinateGenerator {

	public static Coordinates generator(double centralLatitude, double centralLongitude, double radiusKm) {

		Random random = new Random();

		double EARTH_RADIUS = 6371;

		// Generate a random angle and a random distance within the radius
		double angle = random.nextDouble() * 2 * Math.PI;
		double distance = random.nextDouble() * radiusKm;

		// Convert the distance to latitude and longitude degrees
		double latOffset = (distance / EARTH_RADIUS) * (180 / Math.PI);
		double lonOffset = (distance / EARTH_RADIUS) * (180 / Math.PI) / Math.cos(Math.toRadians(centralLatitude));

		// Calculate the new latitude and longitude
		double newLatitude = centralLatitude + latOffset * Math.sin(angle);
		double newLongitude = centralLongitude + lonOffset * Math.cos(angle);

		return new Coordinates(newLatitude, newLongitude);
	}

}
