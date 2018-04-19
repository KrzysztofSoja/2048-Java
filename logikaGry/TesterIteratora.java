package logikaGry;

public class TesterIteratora{
	
	static public void  trywialnyTestIteratora(){
		Plansza p = new Plansza();
		Plansza.Iterator i = p.new Iterator();
		
		System.out.println("Trywialny test iteratora z klasy Plansza.");
		
		for(int j = 0; j < Math.pow(p.dajRozmiar(), 2); j++){
			System.out.println(j + " : " + i.dajIndeks() + "; ");
			if(i.czyToKoniecSzeregu()){
				System.out.println("Nastepuje przejscie do nastpnego szeregu.");
				try {
				i.nastepnySzereg(); } catch(ArrayIndexOutOfBoundsException e) {System.out.println("Brak kolejnego szeregu");}
			}
			else
				i.zwieksz();
		}
		System.out.println("");
	}
	
	static public void iteracjaPoCalejTablicy(){
		System.out.println("Probna iteracja po calej planszy w roznych kierunkach.");
		
		for(int k = 0; k < 4; k++){ //Iteracja dla czterech kierunkow przesukiwania.
			Plansza p = new Plansza();
			Plansza.Iterator i = p.new Iterator( Kierunki.values()[k] );
			
			int j = 1;
			while(!i.czyToKoniec()){
				i.ustawWartosc((int)Math.pow(2, j));
				i.nastepny();
				
				j++;
			}
			i.ustawWartosc((int)Math.pow(2, j));
			
			p.pokazWartosciTablicy();
			//System.out.println("j = " + j);
			System.out.println("");
		}
	}
	
	static public void iteracjaPojedynczegoSzeregu(){
		System.out.println("Test iteracji po pojedynczych szeregach.");
		
		for(int k = 0; k < 4; k++){
			Kierunki kierunek = Kierunki.values()[k];
			System.out.println("Test w kierunku "+ Kierunki.nazwa(k));
			
			for(int j = 0; j < 4; j++){
				System.out.println("Test szeregu nr " + j);
				
				Plansza p = new Plansza();
				Plansza.Iterator i = p.new Iterator(j, kierunek);
				
				int l = 1;
				while(!i.czyToKoniecSzeregu()){
					i.ustawWartosc((int)Math.pow(2, l));
					i.zwieksz();
					
					l++;
				}
				i.ustawWartosc((int)Math.pow(2, l));
				
				p.pokazWartosciTablicy();
				//System.out.println("j = " + j);
				System.out.println("");
			}
		}
	}
	
	static public void testFunkcjiNastepnyElement(){
		System.out.println("Test funkcji nastepnyElement()");
		for(int k = 0; k < 4; k++){
			Kierunki kierunek = Kierunki.values()[k];
			System.out.println("Test w kierunku "+ Kierunki.nazwa(k));
			
			for(int j = 0; j < 4; j++){
				System.out.println("Test szeregu nr " + j);
				
				Plansza p = new Plansza();
				Plansza.Iterator i = p.new Iterator(j, kierunek);
				
				int l = 1;
				while(!i.czyToKoniecSzeregu()){
					i.nastepnyElement().ustawWartosc((int)Math.pow(2, l));
					i.zwieksz();
					
					l++;
				}
				i.ustawWartosc((int)Math.pow(2, l));
				
				p.pokazWartosciTablicy();
				//System.out.println("j = " + j);
				System.out.println("");
			}
		}
	}
	
	
	
	public static void main(String agrs[]){
		trywialnyTestIteratora();
		System.out.println("");
		iteracjaPoCalejTablicy();
		System.out.println("");
		iteracjaPojedynczegoSzeregu();
		System.out.println("");
		testFunkcjiNastepnyElement();
	}
}