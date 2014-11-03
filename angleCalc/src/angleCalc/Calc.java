package angleCalc;

public class Calc {
	//marken og traktorens deminsioner
	double raekke_dist = -1;
	double mark_b = -1;
	double mark_l = -1;
	double traktor_db = -1;
	double traktor_pb = -1;
	double paakoert = -1;
	double all_raekke = -1;

	//traktorens relative højre- og venstreside funktioner
	double f_traktor_l_a = -1;
	double f_traktor_l_b = -1;
	double f_traktor_r_a = -1;
	double f_traktor_r_b = -1;

	/**
	 * Calc Constructor
	 * @param raekke_dist - Laengden mellem hver raekke
	 * @param mark_b - Breden paa marken og den som gaar vinkelret paa raekkerne
	 * @param mark_l - Længden paa marken og den som gaar parralelt med raekkerne
	 * @param traktor_db - Den benyttet traktors daek brede
	 * @param traktor_pb - Den benyttet traktors paahaengs brede
	 */
	public Calc(double raekke_dist, double mark_b, double mark_l, double traktor_db, double traktor_pb){
		this.raekke_dist = raekke_dist;
		this.mark_b = mark_b;
		this.mark_l = mark_l;
		this.traktor_db = traktor_db;
		this.traktor_pb = traktor_pb;

		this.paakoert = 0;

		this.f_traktor_l_a = 0;
		this.f_traktor_l_b = 0;
		this.f_traktor_r_a = 0;
		this.f_traktor_r_b = 0;

		this.all_raekke = total_raekke();
	}

	/**
	 * error_check
	 * @return - false hvis en værdi er mindre end 0, ellers true
	 */
	private boolean error_check(){
		if(raekke_dist < 0 || mark_b < 0 || mark_l < 0 || traktor_db < 0 || traktor_pb < 0){
			return false;
		}
		else
			return true;
	}

	/**
	 * Reakke_numb
	 * @return - returnere antallet af raekke udfra markens brede og raekke mellemrum rundet ned.
	 */
	public double total_raekke(){
		if(error_check()){
			double raekke_numb; 
			raekke_numb = (this.mark_b / this.raekke_dist) + 1;
			raekke_numb = raekke_numb - 0.5;
			raekke_numb = Math.round(raekke_numb);


			return raekke_numb;
		}
		else
			return -1;
	}

	/**
	 * nedkoering_pr
	 * @param paakoering_vinkel - 
	 * @return - laengden af en paakoering
	 */
	public double nedkoering_pr(double paakoering_vinkel) {
		double nedkoering_pr = -1;
		if(error_check()){
			nedkoering_pr = ((this.traktor_db) / Math.sin(paakoering_vinkel*((2*Math.PI)/360)));
			return nedkoering_pr;
		}
		else
			return nedkoering_pr;
	}

	/**
	 * antal_nedkoering_one
	 * @param vinkel - vinklen der bliver kørt med (måske overflødig)
	 * @param minHigh - mindste rækkenummer
	 * @param maxHigh - højeste rækkenummer
	 * @param status - hvilke grænse værdier der er fundet
	 * @return - antallet af rækker nedkørt på denne ene omgang
	 */
	public double antal_nedkoering_one(int vinkel, int minHigh, int minLow, int maxHigh, int maxLow, int status){
		/*
		 * status = 0 => ingen værdier fundet
		 * status = 1 => mindste værdi fundet
		 * status = 2 => mindste og højeste værdi fundet
		 */
		
		if(status == 2){
			return (maxHigh - minLow + 1);
		}
		
		if(status == 1){
			int m = (maxHigh+maxLow)/2;
			if(check_if_end(m, 1)) return antal_nedkoering_one(vinkel, minHigh, minLow, m, maxLow, 2);
			if(check_if_end(maxHigh, 1) || (collision(maxHigh) == 0 && maxHigh == all_raekke)) return antal_nedkoering_one(vinkel, minHigh, minLow, maxHigh, maxLow, 2);
			if(check_if_end(maxLow, 1)) return antal_nedkoering_one(vinkel, minHigh, minLow, maxLow, maxLow, 2);
			
			if(collision(m) == 0) return antal_nedkoering_one(vinkel, minHigh, minLow, maxHigh, m, status);
			return antal_nedkoering_one(vinkel, minHigh, minLow, m, maxLow, status);
		}
		
		if(status == 0){
			int m = (minHigh+minLow)/2;
			if(check_if_end(m, 0) == true)	return antal_nedkoering_one(vinkel, minHigh, m, maxHigh, maxLow, 1);
			if(check_if_end(minHigh, 0) == true) return antal_nedkoering_one(vinkel, minHigh, minHigh, maxHigh, maxLow, 1);
			if(check_if_end(minLow, 0) == true) return antal_nedkoering_one(vinkel, minHigh, minLow, maxHigh, maxLow, 1);
			
			if(collision(m) == 0) return antal_nedkoering_one(vinkel, m, minLow, maxHigh, maxLow, status);
			return antal_nedkoering_one(vinkel, minHigh, m, maxHigh, maxLow, status);
			
		}
		
		
		
		/*
		 * noget rekursive kald, skal finde antallet af alle nedkoeringer for en bestemt vinkel
		 * evt. lav en metode der udregner antal nedkoeringer på en strækning kørt som den her metoder benytter
		 * brug metoden collision(int raekke_numb) til at finde udaf om der er kollision blandt traktorens bane og en raekke.
		 * der skal nok kræves nogle flere konstanter til kaldet af antal_nedkoeringer
		*/

		return -1;
	}
	/**
	 * Check if an end
	 * @param raekke_numb - den række som skal undersøges
	 * @param status - undersøges om 0 = laveste end eller 1 = højeste end
	 * @return true hvis den er en end som status spørger om
	 */
	public Boolean check_if_end(int raekke_numb, int status){
		if(status == 1 && collision(raekke_numb + 1) == -1 && collision(raekke_numb) == 0){
				return true;
		}
		if(status == 0 && collision(raekke_numb-1) == -1 && collision(raekke_numb) == 0){
				return true;
		}
		return false;
	}

	public void make_functions(int vinkel, int brede_tilbagelagt) {
		make_function_a(vinkel);
		make_function_b(vinkel, brede_tilbagelagt);

		//skal lave alle funktionerne til rækkerne og til markens yderlige kanter
	}
	
	public void make_function_a(int vinkel){
		this.f_traktor_l_a = -1* Math.tan(vinkel*(2*Math.PI/360)); // lader til at være den rigtige hældning
		this.f_traktor_r_a = -1* Math.tan(vinkel*(2*Math.PI/360));

	};
	
	public void make_function_b(int vinkel, int brede_tilbagelagt){
		//brede_tilbagelagt skal indsættes i nedenstående diffineringer af b

		this.f_traktor_l_b = brede_tilbagelagt; //+ noget vinkel værk, og lige finde ud af hvorhenne de skal difineres
		this.f_traktor_r_b = brede_tilbagelagt + (traktor_pb/Math.sin(vinkel*(2*Math.PI/360))); // same ^
	}

	/**
	 * Check collision
	 * @param raekke_numb - Det række nummer som skal tjekkes imod de oprettet traktors funktioner
	 * @return - 0 hvis der er collision, -1 hvis der ingen collision er.
	 */
	public int collision(int raekke_numb){
		double x;
		double y;

		y = (f_traktor_l_b - raekke_b(raekke_numb)) / (f_traktor_l_a) ;
//		y = f_traktor_l_a*(x) + raekke_b(raekke_numb);
		x = raekke_b(raekke_numb);
		
		if (raekke_numb <= 0){
			return -1;
		}
		
		if(x <= mark_b && x >= 0 && y <= mark_l && y >= 0){
			return 0;
		}
		else{
			y = (f_traktor_r_b - raekke_b(raekke_numb)) / (f_traktor_r_a) ;
//			y = f_traktor_r_a*(x) + raekke_b(raekke_numb);
			x = raekke_b(raekke_numb);
			
		}
		if(x <= mark_b && x >= 0 && y <= mark_l && y >= 0){
			return 0;
		}
		else return -1;
	}

	public double raekke_b(int raekke_numb){	
		return ((raekke_numb-1)*raekke_dist);
	};
}