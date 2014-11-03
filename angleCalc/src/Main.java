import angleCalc.*;


public class Main {


	// lave en static main til at teste calc metoder.

	public static void main(String[] args) {
		values val = new values();
		Calc calc = new Calc(val.getRaekke_dist(), val.getMark_b(), val.getMark_l(), val.getTraktor_db(), val.getTraktor_pb());

		System.out.println("Antal rækker på marken: " + calc.total_raekke());
		
		
		
		
		for (double i = 1 ; i <= 90 ; i++){
			double tal = calc.nedkoering_pr(i);
//		//	System.out.println("Sin til gradtal " + i + ": " + Math.sin(i*((2*Math.PI)/360)));
			System.out.println("nedkøring ved " + i + " grader: " + tal + " cm");
			System.out.println("hældningstal ved " + i + " grader: ->" + -1* Math.tan(i*(2*Math.PI/360)) + "<-");
			
			calc.make_functions((int)i, 0);
			
			System.out.println("antal nedkørte rækker på første omgang med " + i + "grader: " + calc.antal_nedkoering_one((int)i, (int)calc.total_raekke(), 1, (int)calc.total_raekke(), 1, 0));
		}
		
		System.out.println("End");
		
		
	}
}
