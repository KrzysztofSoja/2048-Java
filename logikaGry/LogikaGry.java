package logikaGry;

/**
*	Klasa zawierająca całą logikę gry. 
*/

import java.util.Random;
import java.util.Scanner;

public class LogikaGry {
	Plansza plansza;
	WynikGry wynik;
	Random maszynaLosujaca;
	int celGry;
	
	public LogikaGry(Plansza plansza){
		this.plansza = plansza;
		maszynaLosujaca = new Random();
		wynik = new WynikGry();
		celGry = 2048;
		dodajPionek();						// Wywalic
	}
	
	public LogikaGry(int celGry, Plansza plansza){
		this.plansza = plansza;
		maszynaLosujaca = new Random();
		wynik = new WynikGry();
		this.celGry = celGry;
	}
	
	public boolean czyPrzegrales(){
		Plansza.Iterator i = plansza.new Iterator();
		while(!i.czyToKoniec()){
			if(i.dajWartosc() == 0) return false;
			i.nastepny();		
		}
		if(i.dajWartosc() == 0) return false;
		return true;
	}
	
	public boolean czyWygrales(){
		Plansza.Iterator i = plansza.new Iterator();
		while(!i.czyToKoniec()){
			if(i.dajWartosc() == celGry) return true;
			i.nastepny();
		}
		if(i.dajWartosc() == celGry) return true;
		return false;
	}
	
	public void dodajPionek(){
		int x, y;
		do{
			x = maszynaLosujaca.nextInt(plansza.dajRozmiar());
			y = maszynaLosujaca.nextInt(plansza.dajRozmiar());
		}while(plansza.dajWartosc(x, y) != 0);
		plansza.ustawWartosc(x, y, losujWartoscPionka());
	}
			
	private int losujWartoscPionka(){
		if(maszynaLosujaca.nextInt(3) == 0 ) return 4;
		else return 2;
	}
	
	private void przesunPionek(Plansza.Iterator i){
		if(i.nastepnyElement() != null && i.nastepnyElement().dajWartosc() == 0){
			i.nastepnyElement().ustawWartosc(i.dajWartosc());
			i.ustawWartosc(0);
			przesunPionek(i.nastepnyElement());
		}
	}
	
	public void przesunPionki(Kierunki kierunek){
		Plansza.Iterator i = plansza.new Iterator(kierunek.dajPrzeciwny());
		while(!i.czyToKoniec()){
			przesunPionek(plansza.new Iterator(i.dajIndeks(), i.dajIndeksSzeregu(), kierunek));
			i.nastepny();
		}
		przesunPionek(plansza.new Iterator(i.dajIndeks(), i.dajIndeksSzeregu(), kierunek));
	}
	
	private void polaczPionki(Plansza.Iterator i){
		if(i.nastepnyElement() != null && i.nastepnyElement().dajWartosc() == i.dajWartosc()){
			i.nastepnyElement().ustawWartosc( i.nastepnyElement().dajWartosc() + i.dajWartosc() );
			i.ustawWartosc(0);
		}
	}
	
	public void wykonajWszystkiePolaczeniaPionkow(Kierunki kierunek){
		Plansza.Iterator i = plansza.new Iterator(kierunek.dajPrzeciwny());
		while(!i.czyToKoniec()){
			polaczPionki(plansza.new Iterator(i.dajIndeks(), i.dajIndeksSzeregu(), kierunek));
			i.nastepny();
		}
		polaczPionki(plansza.new Iterator(i.dajIndeks(), i.dajIndeksSzeregu(), kierunek));
	}
	
	private boolean czyPionekMoznaRuszyc(Plansza.Iterator i){
		if(i.nastepnyElement() == null)
			return false;
		else if(i.nastepnyElement().dajWartosc() == i.dajWartosc() || i.nastepnyElement().dajWartosc() == 0)
			return true;
		else 
			return false;
	}
	
	private boolean czyRuchJestPoprawny(Kierunki kierunek){	
		Plansza.Iterator i = plansza.new Iterator(kierunek);
		while(!i.czyToKoniec()){
			if(i.dajWartosc() != 0 && czyPionekMoznaRuszyc(i)) return true;
			i.nastepny();
		}
		return false;
	}
	
	public WynikGry wykonajRuch(Kierunki kierunek){			//Jak juz sie ogarnie co ta metoda ma robic poprawic
		if(czyRuchJestPoprawny(kierunek)){
			
			przesunPionki(kierunek);
			wykonajWszystkiePolaczeniaPionkow(kierunek);
			przesunPionki(kierunek);
			
			if(czyPrzegrales()) 
				wynik.ustawPrzegrana();
			else if(czyWygrales())
				wynik.ustawWygrana();
			else
				dodajPionek();
		}
		return wynik;	
	}
}