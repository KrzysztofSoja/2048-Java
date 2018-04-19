import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.GridPane;

import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.scene.image.*;

import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;

import logikaGry.*;

public class Wyswietlacz extends Application {
	Kierunki kierunekPrzesuwania;
	boolean nacisnietyKlawisz;
	
	ImageView widokPlanszy[][];
	LogikaGry gra;
	Plansza plansza;
	
	private void ustawObiektNasluchujacy(Scene scena, GridPane panel){
		scena.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent event){
				if(nacisnietyKlawisz == false){
					ustawKierunekZKlawisza(event);
					gra.wykonajRuch(kierunekPrzesuwania);
					aktualizujObrazPlanszy(panel);
					nacisnietyKlawisz = true;
				}
			}
		});
		
		scena.setOnKeyReleased(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent event){
				nacisnietyKlawisz = false;
			}
		});
	}
	
	private void ustawKierunekZKlawisza(KeyEvent event){
		switch(event.getCode()){
			case UP:    kierunekPrzesuwania = Kierunki.GORA;  break;
			case DOWN:  kierunekPrzesuwania = Kierunki.DOL;   break;
			case LEFT:  kierunekPrzesuwania = Kierunki.LEWO;  break;
			case RIGHT: kierunekPrzesuwania = Kierunki.PRAWO; break;
			case W:     kierunekPrzesuwania = Kierunki.GORA;  break;
			case S:     kierunekPrzesuwania = Kierunki.DOL;   break;
			case A:     kierunekPrzesuwania = Kierunki.LEWO;  break;
			case D:     kierunekPrzesuwania = Kierunki.PRAWO; break;	
		}
	}
	
	private void aktualizujObrazPlanszy(GridPane panel){
		panel.getChildren().clear();
		for(int i = 0; i < widokPlanszy.length; i++){
			for(int j = 0; j < widokPlanszy[i].length; j++){	//To niżej powoduje lagi!!!
				widokPlanszy[i][j] = new ImageView(new Image("testoweObrazki/" + String.valueOf(plansza.dajWartosc(i,j)) + ".png")); 
				panel.add(widokPlanszy[i][j], j, i);
			}
		}
	}
	
	@Override
	public void init(){
		plansza = new Plansza();
		gra = new LogikaGry(plansza);
		widokPlanszy = new ImageView[4][4];
	}
	
	@Override
	public void start(Stage okno){
		GridPane panelGry = new GridPane();
		Scene scenaGry = new Scene(panelGry);
		
		aktualizujObrazPlanszy(panelGry);
		ustawObiektNasluchujacy(scenaGry, panelGry);		
		
		okno.setTitle("2048");
		okno.setScene(scenaGry);
		okno.show();
	}
	
	public static void main(String[] args) { launch(args); }
}