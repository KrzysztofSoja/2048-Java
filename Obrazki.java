import javafx.scene.image.*;
import java.io.File;

class Obrazki{
	ImageView tabObrazkow[];
	
	Obrazki(String format, String katalog){
		tabObrazkow = new ImageView[sprawdzIloscObrazkow(format, katalog)];
		for(int i = 0; i < tabObrazkow.length; i++){
			tabObrazkow[i] = new ImageView(new Image(katalog + Integer.toString((int)Math.pow(2, (double)i)) + format));
		}
	}
	
	private int sprawdzIloscObrazkow(String format, String katalog){
		int i = 1;		//Cicho zakładamy że obrazek 0 jest zawsze. To dlatego, że zawsze dla x należy do N 2^x != 0.
		while(new File(katalog + Integer.toString((int)Math.pow(2, (double)i)) + format).exists())
			i++;
		return i;
	}
	
	public ImageView dajObrazek(int liczbaNaPionku){
		return tabObrazkow[(int)Math.log(liczbaNaPionku)];
	}
	
	public void ustawRozmiar(int wysokosc, int szerokosc){
		for(int i = 0; i < tabObrazkow.length; i++){
			tabObrazkow[i].setFitHeight((double)wysokosc);
			tabObrazkow[i].setFitWidth((double)szerokosc);
		}
	}
}
