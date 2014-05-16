import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class Monte {

	public static void main(String[] args) {
		
		BigDecimal t = BigDecimal.ZERO, i = BigDecimal.ZERO, g = new BigDecimal("10000000"); // Number of points
		
		RadiusService radius = (x, y) -> {return Math.sqrt(x*x+y*y);};		
		
		int cpus = Runtime.getRuntime().availableProcessors();
		int maxThreads = cpus * 1;
		maxThreads = (maxThreads > 0 ? maxThreads : 1);
		
		boolean running = true;
		
		ExecutorService executorService =
				new ThreadPoolExecutor(
					maxThreads,
					maxThreads,
					1,
					TimeUnit.MINUTES, 
					new ArrayBlockingQueue<Runnable>(maxThreads, true),
					new ThreadPoolExecutor.CallerRunsPolicy());
		
		Runnable calc = new Runnable() {
			@Override
			public void run() {
				while (running) {
					
				}
			}
		};
		
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
