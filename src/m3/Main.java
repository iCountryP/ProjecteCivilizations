package m3;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		new VentanaJuego();
	}

	
	public int getRandomIndex(ArrayList<Integer> array) {
		int sumaTotal = 0;
		
		for(int i = 0; i < array.size(); i++) {
			sumaTotal += (int) array.get(i);
		}
		System.out.println(sumaTotal);
		int numeroRandom = (int) ((Math.random()*sumaTotal) + 1);
		System.out.println(numeroRandom);
		int sumaTemporal = 0;
		for(int i = 0; i < array.size(); i++) {
			sumaTemporal += (int) array.get(i);
			if(sumaTemporal >= numeroRandom) {
				return i;
			}
		}
		return -1;
	}
	
}

