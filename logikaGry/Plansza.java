package logikaGry;

/**
*	Klasa reprezentująca planszę na której toczy się gra. Umożliwia utawianie wartości komórki 
*	planszy na dowolną potęgę dwójki. Ponadto posiada Iterator umożliwiający iteracje po planszy
* 	w wybranym kierunku.
*	@see Iterator
*/

public class Plansza{
	private int plansza[][];
	private final int rozmiar = 4;
	
	public Plansza(){
		plansza = new int[rozmiar][rozmiar];
		zerujPlansze();
	}
	
	private void zerujPlansze(){
		for(int i = 0; i < rozmiar; i++){
			for (int j = 0; j < rozmiar; j++){
				plansza[i][j] = 0;
			}
		}
	}
	
	private boolean czyJestPotega2(int liczba){
		while(liczba > 1){
			if(liczba % 2 != 0) return false;
			liczba = liczba/2;
		}
		return true;
	}
	
	public void ustawWartosc(int x, int y, int wartosc) throws ArithmeticException{
		if(!czyJestPotega2(wartosc))
			throw new ArithmeticException("Wartosci w tablicy musza byc potega 2.");
		plansza[x][y] = wartosc;
	}
	
	public int dajWartosc(int x, int y){
		return plansza[x][y];
	}
	
	public int dajRozmiar(){
		return rozmiar;
	}
	
	/**
	*	Funkcja wykorzystywana tylko do testów. Wypisuje wartosi komórek planszy w konsoli.
	*/
	public void pokazWartosciTablicy(){
		for(int i = 0; i < rozmiar; i++){
			for(int j = 0; j < rozmiar; j++){
				System.out.print(plansza[i][j] + " ");
			}
			System.out.println("");
		}
	}
	
	
	/** Podstawowa klasa do nawigacji i maniulacji wieloma elementami tablicu */
	public class Iterator{
		private int iSzergu;
		private int i;
		private Kierunki kierunekIteracji;
		
		public Iterator(){
			iSzergu = 0;
			kierunekIteracji = Kierunki.GORA;
			naPoczatek();
		}

		public Iterator(Kierunki kierunek){
			iSzergu = 0;
			kierunekIteracji = kierunek;
			naPoczatek();
		}

		public Iterator(int szereg, Kierunki kierunek){
			iSzergu = szereg;
			kierunekIteracji = kierunek;
			naPoczatek();
		}
	
		public Iterator(int pozycja, int szereg, Kierunki kierunek) throws ArrayIndexOutOfBoundsException{
			if(pozycja < 0 || pozycja > rozmiar-1)
				throw new ArrayIndexOutOfBoundsException("Zla pozycja interatora.");
			
			iSzergu = szereg;
			kierunekIteracji = kierunek;
			i = pozycja;
		}
		
		public void naPoczatek(){
			if(kierunekIteracji == Kierunki.DOL || kierunekIteracji == Kierunki.PRAWO)
				i = 0;
			else 
				i = rozmiar-1;
		}
		
		public void naKoniec(){
			if(kierunekIteracji == Kierunki.DOL || kierunekIteracji == Kierunki.PRAWO)
				i = rozmiar-1;
			else 
				i = 0;
		}
		
		public boolean czyToKoniecSzeregu(){
			if(kierunekIteracji == Kierunki.DOL || kierunekIteracji == Kierunki.PRAWO){
				if(i == rozmiar-1)  return true;
				else return false;
			}
			else {
				if(i == 0) return true;
				else return false;
			}
		}
		
		public boolean czyToKoniec(){
			if(czyToKoniecSzeregu() && iSzergu == rozmiar-1)
				return true;
			else return false;
		}
		
		public void zwieksz() throws ArrayIndexOutOfBoundsException{
			if(czyToKoniecSzeregu()) 
				throw new ArrayIndexOutOfBoundsException("Brak kolejnej pozycji w tablicy." + i);
			switch(kierunekIteracji){
				case GORA:	i--; break;
				case DOL:	i++; break;
				case LEWO:	i--; break;
				case PRAWO: i++; break;
			}
		}
		
		public Iterator nastepnyElement(){
			if(i == rozmiar-1 && (kierunekIteracji == Kierunki.DOL || kierunekIteracji == Kierunki.PRAWO))
				return null;
			else if(i == 0 && (kierunekIteracji == Kierunki.GORA || kierunekIteracji == Kierunki.LEWO))
				return null;
			else{
				Iterator nastepny = new Iterator(i, iSzergu, kierunekIteracji);
				nastepny.zwieksz();
				return nastepny;
			}
		}
		
		public void nastepnySzereg() throws ArrayIndexOutOfBoundsException{
			if(iSzergu+1 > 3)
				throw new ArrayIndexOutOfBoundsException("Brak kolejnego szergu pozycji.");
			iSzergu++;
			naPoczatek();
		}
		
		public void nastepny() throws ArrayIndexOutOfBoundsException{
			if(czyToKoniecSzeregu()) nastepnySzereg();
			else zwieksz();
		}
		
		int dajIndeksSzeregu(){
			return iSzergu;
		}
		
		int dajIndeks(){
			return i;
		}
		
		public int dajWartosc(){
			if(kierunekIteracji.czyPion()){
				return plansza[i][iSzergu];
			}
			else{
				return plansza[iSzergu][i];
			}
		}
		
		public void ustawWartosc(int wartosc){
			if(kierunekIteracji.czyPion()){
				plansza[i][iSzergu] = wartosc;
			}
			else{
				plansza[iSzergu][i] = wartosc;
			}
		}
	}
}