package logikaGry;

import java.util.Scanner;

public class TesterLogiki{
	
	private static void wstawLosoweWartosciDoPlansz(LogikaGry g){
		for(int i = 0; i < 5; i++){
			g.dodajPionek();
		}
	}
	
	private static void zapelnijPlansze(Plansza p){
		Plansza.Iterator i = p.new Iterator();
		while(!i.czyToKoniec()){
			i.ustawWartosc(2);
			i.nastepny();
		}
		i.ustawWartosc(2);
	}
	
	private static void testWarunkuPorazki(){
		Plansza p = new Plansza();
		LogikaGry g = new LogikaGry(p);
		System.out.println("Test warunku porazki.");
		
		p.pokazWartosciTablicy();
		System.out.println("Warunek porazki: " + g.czyPrzegrales());
		
		wstawLosoweWartosciDoPlansz(g);
		p.pokazWartosciTablicy();
		System.out.println("Warunek porazki: " + g.czyPrzegrales());
		
		zapelnijPlansze(p);
		p.pokazWartosciTablicy();
		System.out.println("Warunek porazki: " + g.czyPrzegrales());
	}
	
	private static void testPrzesuwaniaPionkow(){
		Plansza p;
		LogikaGry g;
		System.out.println("Test przesuwania pionkow.");
		
		for(int k = 0; k < 4; k++){
			System.out.println("Test w kierunku " + Kierunki.nazwa(k));
			
			System.out.println("Przed przesunieciem:");
			p = new Plansza();
			g = new LogikaGry(p);
			wstawLosoweWartosciDoPlansz(g);
			p.pokazWartosciTablicy();
			
			System.out.println("Po przesunieciu:");
			g.przesunPionki(Kierunki.values()[k]);
			p.pokazWartosciTablicy();
			System.out.println("");
		}
		
	}
	
	private static void testLaczeniaPionkow(){
		Plansza p;
		LogikaGry g;
		System.out.println("Test laczania pionkow.");
		
		for(int k = 0; k < 4; k++){
			System.out.println("Test w kierunku " + Kierunki.nazwa(k));
			p = new Plansza();
			g = new LogikaGry(p);
			
			wstawLosoweWartosciDoPlansz(g);
			p.pokazWartosciTablicy();
			System.out.println("");
			for(int j = 0; j < 4; j++){
				
				g.przesunPionki(Kierunki.values()[k]);
				g.wykonajWszystkiePolaczeniaPionkow(Kierunki.values()[k]);
				g.przesunPionki(Kierunki.values()[k]);
				
				System.out.println("Pojedynczy ruch.");
				p.pokazWartosciTablicy();
				System.out.println("");
				g.dodajPionek();
				p.pokazWartosciTablicy();
				System.out.println("");
			}
		}
	}
	
	private static Kierunki wybierzKierunek(){
		Scanner odczyt = new Scanner(System.in);
		String pomocnicza = odczyt.nextLine();
		if(pomocnicza == "G" || pomocnicza =="g")
			return Kierunki.GORA;
		else if(pomocnicza == "D" || pomocnicza == "d")
			return Kierunki.DOL;
		else if(pomocnicza == "L" || pomocnicza == "l")
			return Kierunki.LEWO;
		else if(pomocnicza == "P" || pomocnicza == "p")
			return Kierunki.PRAWO;
		else
			return Kierunki.GORA;
	}
	
	static public void main(String args[]){
		testWarunkuPorazki();
		testPrzesuwaniaPionkow();
		testLaczeniaPionkow();
	}
}