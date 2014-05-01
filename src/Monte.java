import java.math.BigDecimal;
import java.math.RoundingMode;


public class Monte {

	public static void main(String[] args) {
		
		BigDecimal total = BigDecimal.ZERO, inside = BigDecimal.ZERO;
		
		BigDecimal goal = new BigDecimal("10000000"); // Number of points
		
		RadiusService radius = (x, y) -> {return Math.sqrt(x*x+y*y);};		
		
		while (total.compareTo(goal) < 0) {
			double r = radius.getRadius(Math.random(), Math.random());
			if (r <= 1) {
				inside = inside.add(BigDecimal.ONE);
			}
			total = total.add(BigDecimal.ONE);
		}
		System.out.println(inside.multiply(new BigDecimal("4")).divide(total, 50, RoundingMode.HALF_UP));
	}
	
	interface RadiusService {
		double getRadius(double a, double b);
	}

}
