import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class Monte {

	public static void main(String[] args) {
				
		RadiusService radius = (x, y) -> {return Math.sqrt(x*x+y*y);};		
		
		BigDecimal four = new BigDecimal("4");
		
		int cpus = Runtime.getRuntime().availableProcessors();
		int maxThreads = cpus * 1;
		maxThreads = (maxThreads > 0 ? maxThreads : 1);
		
		boolean running = true;
		
		InOther io = new InOther();
		
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
					double r = radius.getRadius(Math.random(), Math.random());
					if (r <= 1) {
						io.in++;
					}
					io.other++;
				}
			}
		};
		
		executorService.submit(calc);
		
		Runnable printer = new Runnable() {
			@Override
			public void run() {
				while (running) {
					System.out.print(four.multiply(new BigDecimal(io.in)).divide(new BigDecimal(io.other), 20, RoundingMode.HALF_UP) + "\r");
					try {
						java.lang.Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		executorService.submit(printer);
	}
	
	interface RadiusService {
		double getRadius(double a, double b);
	}
}

class InOther {
	
	public long in = 0, other = 0;
	
	public void setValues(long in, long other) {
		this.in = in;
		this.other = other;
	}
	
}
