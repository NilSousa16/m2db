package br.ufba.dcc.wiser.m2db;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

public class Test {

	public static void main(String[] args) {
		

		NumberFormat formatter = new DecimalFormat("#0.00");		

		
//		System.out.println("Valor: " + formatter.format(new Random().nextDouble(1, 100)));
		
//		double valor = Double.parseDouble(formatter.format(new Random().nextDouble(1, 100)));
		
//		double valor = new Random().nextDouble(0, 1);
//		System.out.println("Valor: " + valor);
		
		double roundOff = Math.round(new Random().nextDouble(0, 1) * 100.0) / 100.0;
		
		System.out.println("Novo valor: " + roundOff);
	}

}
