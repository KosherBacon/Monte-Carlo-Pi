import java.math.BigDecimal;
import java.math.RoundingMode;


public class Monte {

	public static void main(String[] args) {
		
		BigDecimal t = BigDecimal.ZERO, i = BigDecimal.ZERO, g = new BigDecimal("10000000"); // Number of points
		
		RadiusService radius = (x, y) -> {return Math.sqrt(x*x+y*y);};		
		
		while (t.compareTo(g) < 0) {
			double r = radius.getRadius(Math.random(), Math.random());
			if (r <= 1) {
				i = i.add(BigDecimal.ONE);
			}
			t = t.add(BigDecimal.ONE);
		}
		System.out.println(i.multiply(new BigDecimal("4")).divide(t, 50, RoundingMode.HALF_UP));
	}
	
	interface RadiusService {
		double getRadius(double a, double b);
	}

}
