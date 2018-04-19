package logikaGry;

enum StanGry { PRZEGRANA, WYGRANA, TRWA; }

public class WynikGry{
	int wynik;
	StanGry stanGry;
	
	WynikGry(){
		wynik = 0;
		stanGry = StanGry.TRWA;
	}
	
	void ustawWygrana() { stanGry = StanGry.WYGRANA; }
	void ustawPrzegrana() { stanGry = StanGry.PRZEGRANA; }
	public int dajWynik() { return wynik; }
	
	void zwiekszWynik(int punkty) { 
		wynik += punkty; 
	}
	
	public boolean czyGraJestWygrana(){
		if(stanGry == StanGry.WYGRANA) return true;
		else return false;
	}
	
	public boolean czyGraCiagleTrwa(){
		if(stanGry == StanGry.TRWA) return true;
		else return false;
	}
}