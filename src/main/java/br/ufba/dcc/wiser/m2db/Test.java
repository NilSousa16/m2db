package br.ufba.dcc.wiser.m2db;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test {

	public static void main(String[] args) {
		

		NumberFormat formatter = new DecimalFormat("#0.00");		

		
//		System.out.println("Valor: " + formatter.format(new Random().nextDouble(1, 100)));
		
//		double valor = Double.parseDouble(formatter.format(new Random().nextDouble(1, 100)));
		
//		double valor = new Random().nextDouble(0, 1);
//		System.out.println("Valor: " + valor);
		
//		double roundOff = Math.round(new Random().nextDouble(0, 1) * 100.0) / 100.0;
//		
//		System.out.println("Novo valor: " + roundOff);
		
//		List<String> gatewayIdList = new ArrayList<String>();
//		
//		gatewayIdList.add("Nilson");
//		gatewayIdList.add("Samuel");
//		gatewayIdList.add("Djor");
//		
//		System.out.println("Tamanho: " + gatewayIdList.size());
//		
//		System.out.println("VAlor: " + gatewayIdList.get(2));
		
		String typeDeviceList[] = { "temperature", "humidity", "fire", "luminosity", "ultrasonic", "gas", "movement",
		"atmospheric" };
		
		System.out.println(">>> " + typeDeviceList.length);
		
	}

}
