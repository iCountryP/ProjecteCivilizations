package m3;

public final class RandomUtils {

	private RandomUtils() {
		
	}
	
	public static boolean chancePercent(double percent) {
	    return Math.random() * 100 < percent;
	}
	
}
