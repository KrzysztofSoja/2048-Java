package logikaGry;

public enum Kierunki 
{ 
	PRAWO, LEWO, GORA, DOL;
	
	public static String nazwa(int i){	// Warto by progrogamowanie refleksyjne zastosowac.
		if(i == 0) return "PRAWO";
		else if(i == 1) return "LEWO";
		else if(i == 2) return "GORA";
		else if(i == 3) return "DOL";
		else return "Bledna watosc!";
	}
	
	public boolean czyPion(){
		if(this == GORA || this == DOL) return true;
		return false;
	}
	
	public Kierunki dajPrzeciwny(){
		if(this == GORA) return DOL;
		else if(this == DOL) return GORA;
		else if(this == PRAWO) return LEWO;
		else return PRAWO;
	}
}