package mx.edu.utng.jqueryv1.juego1;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

import java.util.ArrayList;

import mx.edu.utng.jqueryv1.R;


public class Player {
	
	private ArrayList<Penguin> pinguinos = new ArrayList<Penguin>();
	private int NumJugador=0;
	private Context context;
	private View vista;
	Penguin penguinsel;
	Penguin penguinant;
	private Drawable drawablePenguins[] = new Drawable[2];
	int score = 0;
	private boolean enable;

	public Player(Context context, View vista, int numjugador) {
		this.context = context;
		this.NumJugador = numjugador;
		this.vista = vista;
		int codigoimagen = getImagenJugador();
		drawablePenguins[0]=context.getResources().getDrawable(codigoimagen);
		drawablePenguins[1]=context.getResources().getDrawable(R.drawable.fichaamarilla);
		CreaPinguinos();
		this.enable = true;
	}
	
	
	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}


	public boolean isEnable() {
		return enable;
	}


	public void setEnable(boolean enable) {
		this.enable = enable;
	}


	public Penguin getPenguinant() {
		return penguinant;
	}

	public void setPenguinant(Penguin penguinant) {
		this.penguinant = penguinant;
	}

	public Penguin getPenguinsel() {
		return penguinsel;
	}

	public void setPenguinsel(Penguin penguinsel) {
		this.penguinsel = penguinsel;
	}

	private void CreaPinguinos() {
		int tipotablero = getTipoTablero();
		for(int i = 0; i<tipotablero-2 ; i++){
			Penguin pinguino = new Penguin(vista, drawablePenguins[0]);
			pinguino.setJugador(NumJugador);
			pinguinos.add(pinguino);
		}
	}

	private int getImagenJugador() {
		if(NumJugador == 1)
			return R.drawable.fichaverde;
		else if(NumJugador == 2){
			return R.drawable.ficharoja;
		}
		else{
			return R.drawable.ficharoja;
		}
	}

	public int getTipoTablero(){
		SharedPreferences pref = context.getSharedPreferences(
				"es.sitvalencia.app.android.Penguinsonice_preferences", Context.MODE_PRIVATE);
		return Integer.parseInt(pref.getString("Tableros","6"));
	}
	
	public void onDraw(Canvas canvas){
		for (Penguin penguin : pinguinos) {
			penguin.dibujaGrafico(canvas);
		}
	}
	
	public void onSizeChange(int posx, int posy, int ancho){
		int tipotablero = getTipoTablero();
		for (Penguin penguin : pinguinos) {
			penguin.setAlto((int)(100));
			penguin.setAncho((int)(ancho-30)/(tipotablero));
			penguin.setPosX(posx);
			penguin.setPosY(posy);
			posx+=penguin.getAncho()+5;
		}
	}
	
	public void CalcularPenguinSelected(float x, float y){
		penguinsel = null;
		for(Penguin penguin: pinguinos){
			if(penguin.esselecionado(x,y)) penguinsel = penguin;
		}
	}

	public boolean marcarpinguino() {
		if(penguinsel != null){
			if(penguinant != null){
				  penguinant.setDrawable(drawablePenguins[0]);
			  }
			  penguinsel.setDrawable(drawablePenguins[1]);
			  penguinant=penguinsel;
			  return true;
		}
		return false;
	}

	public void moverpieza(Placas piezasel) {
		if(penguinant != null){
			penguinant.setAlto(piezasel.getAlto());
			penguinant.setAncho(piezasel.getAncho());
			penguinant.setPosX(piezasel.posX);
			penguinant.setPosY(piezasel.posY);
			penguinant.setPosXTablero(piezasel.getPosXtablero());
			penguinant.setPosYTablero(piezasel.getPosYtablero());
			penguinant.setDrawable(drawablePenguins[0]);
			penguinant=null;
			penguinsel=null;
		}
	}

	public void changeScore(int valor) {
		score=score + valor;
		
	}

	public ArrayList<Penguin> getPinguinos() {
		return pinguinos;
	}

	public void setPinguinos(ArrayList<Penguin> pinguinos) {
		this.pinguinos = pinguinos;
	}
	
	
	
}
