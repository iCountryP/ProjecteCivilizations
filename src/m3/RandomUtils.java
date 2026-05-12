package m3;

public final class RandomUtils {

	private RandomUtils() {
		
	}
	
	public static int chooseRandomInt(int minimo, int maximo) {
		return (int) (Math.random() * (maximo - minimo + 1)) + minimo;
	}
	
	public static boolean chancePercent(double percent) {
	    return Math.random() * 100 < percent;
	}
	
	public static int weightedChoice(int[] probabilityArray) {
        int sumaTotal = 0;
        
        for (int i = 0; i < probabilityArray.length; i++) {
            sumaTotal += (int) probabilityArray[i];
        }
        System.out.println(sumaTotal);
        int numeroRandom = (int) ((Math.random()*sumaTotal) + 1);
        System.out.println(numeroRandom);
        int sumaTemporal = 0;
        for (int i = 0; i < probabilityArray.length; i++) {
            sumaTemporal += (int) probabilityArray[i];
            if(sumaTemporal >= numeroRandom) {
                return i;
            }
        }
        return -1;
	}
	
}
