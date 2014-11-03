
public class values {
	double raekke_dist;
	double mark_b;
	double mark_l;
	double traktor_db;
	double traktor_pb;
	double vinkel;


	public double getVinkel() {
		return vinkel;
	}


	public void setVinkel(double vinkel) {
		this.vinkel = vinkel;
	}


	public double getRaekke_dist() {
		return raekke_dist;
	}


	public double getMark_b() {
		return mark_b;
	}


	public double getMark_l() {
		return mark_l;
	}


	public double getTraktor_db() {
		return traktor_db;
	}


	public double getTraktor_pb() {
		return traktor_pb;
	}

	/**
	 * Constructor values
	 * All values are in cm atm
	 * 
	 */
	public values(){
		raekke_dist = 30.0;
		mark_b = 20000.0;
		mark_l = 40000.0;
		traktor_db = 57.0;
		traktor_pb = 600.0;
		vinkel = 0.0;		//not used, I think.
	}
}
