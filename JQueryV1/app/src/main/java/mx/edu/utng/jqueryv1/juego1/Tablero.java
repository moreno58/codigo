package mx.edu.utng.jqueryv1.juego1;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

import java.util.ArrayList;

import mx.edu.utng.jqueryv1.R;


public class Tablero {
	
	public static final int VERTICALARRIBA=1;
	public static final int VERTICALABAJO=2;
	public static final int HORIZONTALIZQUIERDA=3;
	public static final int HORIZONTALDERECHA=4;
	public static final int DIAGONALARRIBADERECHA=5;
	public static final int DIAGONALARRIBAIZQUIERDA=6;
	public static final int DIAGONALABAJOIZDERECHA=7;
	public static final int DIAGONALABAJOIZQUIERDA=8;
	
	ArrayList<ArrayList<Placas>> tablero = new ArrayList<ArrayList<Placas>>();
	private Context context;
	private View vista;
	int PlayerOn = 1;
	Placas piezasel = null;

	public Tablero(Context context, View vista) {
		this.context = context;
		this.vista = vista;
		CreaTablero();
	}
	
	
	public ArrayList<ArrayList<Placas>> getTablero() {
		return tablero;
	}


	public void setTablero(ArrayList<ArrayList<Placas>> tablero) {
		this.tablero = tablero;
	}


	public void CreaTablero(){
		Drawable drawableficha;
		int tipotablero = getTipoTablero();
		int contx=0;
		int conty=0;
		for(int i = 0; i<tipotablero ; i++){
			ArrayList<Placas> placas = new ArrayList<Placas>();
			for(int j = 0 ; j<tipotablero ; j++){
				int rd = (int) (Math.random()*3+1);;
				if(rd==1){
					drawableficha=context.getResources().getDrawable(R.drawable.hielo1);
				}else if(rd==2){
					drawableficha=context.getResources().getDrawable(R.drawable.hielo2);
				}else{
					drawableficha=context.getResources().getDrawable(R.drawable.hielo3);
				}
				Placas placa = new Placas(this.vista, drawableficha);	
				placa.setValor(rd);
				placa.setPosXtablero(contx);
				placa.setPosYtablero(conty);
				placas.add(placa);
				contx++;
			}
			tablero.add(placas);
			conty++;
			contx=0;
		}
	}
		
	public int getTipoTablero(){
		SharedPreferences pref = context.getSharedPreferences(
					"es.sitvalencia.app.android.Penguinsonice_preferences", Context.MODE_PRIVATE);
		return Integer.parseInt(pref.getString("Tableros","6"));
	}

	public void onDraw(Canvas canvas) {
		for (ArrayList<Placas> placas : tablero) {
			for(Placas placa : placas){
				if(placa.isVisible())
					placa.dibujaGrafico(canvas);
			}
		}
		
	}

	public void onSizeChange(int posx, int posy, int alto, int ancho) {
		int tipotablero = getTipoTablero();
		for (ArrayList<Placas> fichas : tablero) {
			for(Placas ficha : fichas){
				ficha.setAlto((int)(alto-200-30)/tipotablero);
				ficha.setAncho((int)(ancho-30)/tipotablero);
				ficha.setPosX(posx);
				ficha.setPosY(posy);
				posx+=ficha.getAncho()+5;
			}
			posy+=((int)(alto-200-30)/tipotablero)+5;
			posx=0;
		}
	}
	
	public void CalcularPiezaSelecionada(float x, float y){
		for(ArrayList<Placas> placas: tablero){
			for(Placas placa : placas){
				if(placa.esselecionado(x,y)){
					piezasel = placa;
					break;
				}
			}
		}
	}
	
	public Placas getPlacaAnterior(int PosX, int PosY){
		for(ArrayList<Placas> placas: tablero){
			for(Placas placa : placas){
				if(placa.getPosXtablero() == PosX && placa.getPosYtablero() == PosY) return placa;
			}
		}
		return null;
	}
	public void activarJugador1() {
		piezasel=null;
		PlayerOn=1;
	}

	public Placas getPiezasel() {
		return piezasel;
	}

	public void setPiezasel(Placas piezasel) {
		this.piezasel = piezasel;
	}

	public void activarJugador2() {
		piezasel=null;
		PlayerOn=2;
	}

	public int getPlayerOn() {
		return PlayerOn;
	}

	public void setPlayerOn(int playerOn) {
		PlayerOn = playerOn;
	}
	
	public boolean esmovimientolegal(ArrayList<Penguin> pinguinos, Penguin penguin, Placas pieza) {
		//No puedo caer en una pieza no visible;
		if(!pieza.isVisible()){
			return false;
		}
		if(penguin.getPosXTablero() == -1 && penguin.getPosYTablero() == -1) return true;
		if(esmismaposicion(penguin,pieza)){
			return false;
		}
		int direccion = calculardirecciondelmovimiento(penguin, pieza);
		switch (direccion) {
		case VERTICALARRIBA:
			return esvalidoVerticalArriba(pinguinos,penguin,pieza);
		case VERTICALABAJO:
			return esvalidoVerticalAbajo(pinguinos,penguin,pieza);
		case HORIZONTALIZQUIERDA:
			return esvalidoHorizontalIzquierda(pinguinos,penguin,pieza);
		case HORIZONTALDERECHA:
			return esvalidohorizontalderecha(pinguinos,penguin,pieza);
		case DIAGONALABAJOIZDERECHA:
			return esvalidodiagonalabajoderecha(pinguinos,penguin,pieza);
		case DIAGONALABAJOIZQUIERDA:
			return esvalidodiagonalabajoizquierda(pinguinos,penguin,pieza);
		case DIAGONALARRIBADERECHA:
			return esvalidodiagonalarribaderecha(pinguinos,penguin,pieza);
		case DIAGONALARRIBAIZQUIERDA:
			return esvalidodiagonalarribaizquierda(pinguinos,penguin,pieza);
		}

		return false;
	}

	private boolean esvalidodiagonalarribaizquierda(
			ArrayList<Penguin> pinguinos, Penguin penguin, Placas pieza) {
		int posyPenguin = penguin.getPosYTablero();
		int posxPenguin = penguin.getPosXTablero();
		int posyPieza = pieza.getPosYtablero();
		if(!esvalidalapiezasel(penguin,pieza))return false;
		for (int y=posyPenguin-1,x=posxPenguin-1;y>=posyPieza; y--,x--){
			if(hayOtroPinguino(x,y,pinguinos) || nohaypieza(x,y) ) return false;
		}
		return true;
	}

	private boolean esvalidodiagonalarribaderecha(ArrayList<Penguin> pinguinos,
			Penguin penguin, Placas pieza) {
		int posyPenguin = penguin.getPosYTablero();
		int posxPenguin = penguin.getPosXTablero();
		int posyPieza = pieza.getPosYtablero();
		if(!esvalidalapiezasel(penguin,pieza))return false;
		for (int y=posyPenguin-1,x=posxPenguin+1;y>=posyPieza; y--,x++){
			if(hayOtroPinguino(x,y,pinguinos) || nohaypieza(x,y) ) return false;
		}
		
		return true;
	}

	private boolean esvalidalapiezasel(Penguin penguin, Placas pieza) {
		ArrayList<Position>positions =calcularposicionesposibles(penguin,pieza);
		Position piezaposition=new Position(pieza.getPosXtablero(), pieza.getPosYtablero());
		for (Position p : positions){
			if(p.equals(piezaposition)) return true;
		}
		return false;
	}


	private ArrayList<Position> calcularposicionesposibles(Penguin penguin, Placas pieza) {
		ArrayList<Position> position = new ArrayList<Position>();
		int posyPenguin = penguin.getPosYTablero();
		int posxPenguin = penguin.getPosXTablero();
		//posiciones hacia arriba derecha
		for (int y=posyPenguin-1,x=posxPenguin+1;y>=0 && x<=getTipoTablero(); y--,x++){
			position.add(new Position(x, y));
		}
		//posiciones hacia arriba izq
		for (int y=posyPenguin-1,x=posxPenguin-1;y>=0 && x>=0; y--,x--){
			position.add(new Position(x, y));
		}
		//posiciones abajo dere
		for (int y=posyPenguin+1,x=posxPenguin+1;y<=getTipoTablero() && x <= getTipoTablero(); y++,x++){
			position.add(new Position(x, y));
		}
		//posiciones hacia abajo izq
		for (int y=posyPenguin+1,x=posxPenguin-1;y<=getTipoTablero() && x>=0; y++,x--){
			position.add(new Position(x, y));
		}
		return position;
	}


	private boolean esvalidodiagonalabajoizquierda(
			ArrayList<Penguin> pinguinos, Penguin penguin, Placas pieza) {
		int posyPenguin = penguin.getPosYTablero();
		int posxPenguin = penguin.getPosXTablero();
		int posyPieza = pieza.getPosYtablero();
		if(!esvalidalapiezasel(penguin,pieza))return false;
		for (int y=posyPenguin+1,x=posxPenguin-1;y<=posyPieza; y++,x--){
			if(hayOtroPinguino(x,y,pinguinos) || nohaypieza(x,y) ) return false;
		}
		return true;
	}

	private boolean esvalidodiagonalabajoderecha(ArrayList<Penguin> pinguinos,
			Penguin penguin, Placas pieza) {
		int posyPenguin = penguin.getPosYTablero();
		int posxPenguin = penguin.getPosXTablero();
		int posyPieza = pieza.getPosYtablero();
		if(!esvalidalapiezasel(penguin,pieza))return false;
		for (int y=posyPenguin+1,x=posxPenguin+1;y<=posyPieza; y++,x++){
			if(hayOtroPinguino(x,y,pinguinos) || nohaypieza(x,y) ) return false;
		}
		return true;
	}

	private boolean esmismaposicion(Penguin penguin, Placas pieza) {
		int posxPenguin = penguin.getPosXTablero();
		int posyPenguin = penguin.getPosYTablero();
		int posxPieza = pieza.getPosXtablero();
		int posyPieza = pieza.getPosYtablero();
		if(posxPenguin == posxPieza && posyPieza == posyPenguin)return true;
		return false;
	}

	private boolean esvalidohorizontalderecha(ArrayList<Penguin> pinguinos, Penguin penguin, Placas pieza) {
		int posxPenguin = penguin.getPosXTablero();
		int posyPenguin = penguin.getPosYTablero();
		int posxPieza = pieza.getPosXtablero();
		for (int x=posxPenguin+1;x<=posxPieza; x++){
			if(hayOtroPinguino(x,posyPenguin,pinguinos) || nohaypieza(x,posyPenguin) ) return false;
		}
		return true;
	}

	private boolean nohaypieza(int x, int y) {
		Placas placa = getPlacaAnterior(x, y);
		if(placa != null && placa.isVisible()) return false;
		return true;
	}

	public boolean hayOtroPinguino(int x, int y,
			ArrayList<Penguin> pinguinos) {
		for(Penguin penguin:pinguinos){
			if(penguin.getPosXTablero()==x && penguin.getPosYTablero() == y)return true;
		}
		return false;
	}

	private boolean esvalidoHorizontalIzquierda(ArrayList<Penguin> pinguinos, Penguin penguin, Placas pieza) {
		int posxPenguin = penguin.getPosXTablero();
		int posyPenguin = penguin.getPosYTablero();
		int posxPieza = pieza.getPosXtablero();
		for (int x=posxPenguin-1;x>=posxPieza; x--){
			if(hayOtroPinguino(x,posyPenguin,pinguinos) || nohaypieza(x,posyPenguin) ) return false;
		}
		return true;
	}

	private boolean esvalidoVerticalAbajo(ArrayList<Penguin> pinguinos, Penguin penguin, Placas pieza) {
		int posyPenguin = penguin.getPosYTablero();
		int posxPenguin = penguin.getPosXTablero();
		int posyPieza = pieza.getPosYtablero();
		for (int y=posyPenguin+1;y<=posyPieza; y++){
			if(hayOtroPinguino(posxPenguin,y,pinguinos) || nohaypieza(posxPenguin,y) ) return false;
		}
		return true;
	}

	private boolean esvalidoVerticalArriba(ArrayList<Penguin> pinguinos, Penguin penguin, Placas pieza) {
		int posyPenguin = penguin.getPosYTablero();
		int posxPenguin = penguin.getPosXTablero();
		int posyPieza = pieza.getPosYtablero();
		for (int y=posyPenguin-1;y>=posyPieza; y--){
			if(hayOtroPinguino(posxPenguin,y,pinguinos) || nohaypieza(posxPenguin,y) ) return false;
		}
		return true;
	}

	private int calculardirecciondelmovimiento(Penguin penguin, Placas pieza) {
		int posxPenguin = penguin.getPosXTablero();
		int posyPenguin = penguin.getPosYTablero();
		int posxPieza = pieza.getPosXtablero();
		int posyPieza = pieza.getPosYtablero();
		
		//el movimiento es vertical? xpieza==xpenguin ypieza!=ypenguin
		if(posxPenguin == posxPieza && posyPenguin != posyPieza ){
			//el movimiento es arriba? ypieza<ypenguin
			if(posyPieza<posyPenguin) return VERTICALARRIBA;
			else return VERTICALABAJO;
		}
		//el movimiento es horizontal? xpieza!=xpenguin
		else if(posxPieza != posxPenguin && posyPenguin == posyPieza ){
			//el movimiento es a la derecha? xpenguin<xpieza
			if(posxPenguin< posxPieza) return HORIZONTALDERECHA;
			else return HORIZONTALIZQUIERDA;
		}
		//movimiento diagonal
		else if(posxPieza != posxPenguin && posyPenguin != posyPieza){
	          	//movimientodiagonalarriba? if posyPenguin > posyPieza
			if(posyPenguin>posyPieza){
				//derecha? 
				if(posxPenguin<posxPieza) return DIAGONALARRIBADERECHA;
				else return DIAGONALARRIBAIZQUIERDA;
			}
			//movimientodiagonalabajo
			else if(posyPenguin<posyPieza){
				if(posxPenguin<posxPieza) return DIAGONALABAJOIZDERECHA;
				else return DIAGONALABAJOIZQUIERDA;
			}
			
		}
		
		return 404;
	}

	public ArrayList<Penguin> getPinguinos(Player player1, Player player2) {
		ArrayList<Penguin> pinguinosenjuego=new ArrayList<Penguin>();
		for(Penguin penguinplayer1:player1.getPinguinos()){
			pinguinosenjuego.add(penguinplayer1);
		}
		for(Penguin penguinplayer2:player2.getPinguinos()){
			pinguinosenjuego.add(penguinplayer2);
		}
		return pinguinosenjuego;
	}

	public void calcularinvalidados(Player player1, Player player2) {
		//calculoinvalidadoplayer1
		ArrayList<Penguin> penguins = getPinguinos(player1, player2);
		ArrayList<Penguin> penguinsplayer1 = player1.getPinguinos();
		boolean enableplayer1 = false;
		for(Penguin penguinp1:penguinsplayer1){
			if(esposiblemovimiento(penguinp1, penguinsplayer1,penguins)){
				enableplayer1=true;
				break;
			}
		}
		if(!enableplayer1) player1.setEnable(false);
		//calculoinvalidadoplayer1
		ArrayList<Penguin> penguinsplayer2 = player2.getPinguinos();
		boolean enableplayer2 = false;
		for(Penguin penguinp2:penguinsplayer2){
			if(esposiblemovimiento(penguinp2,penguinsplayer2,penguins)){
				enableplayer2=true;
				break;
			}
		}
		if(!enableplayer2) player2.setEnable(false);
		
		
		
	}

	private boolean esposiblemovimiento(Penguin penguin, ArrayList<Penguin> penguinsplayer, ArrayList<Penguin> penguins) {
		// TODO Auto-generated method stub
		//movimientoarriba
		int posxpenguin = penguin.getPosXTablero();
		int posypenguin = penguin.getPosYTablero();
		if(posypenguin ==-1 && posxpenguin == -1) return true;
		//movimientoarriba
		if(posypenguin-1>=0 && !nohaypieza(posxpenguin, posypenguin-1) && 
				!hayOtroPinguino(posxpenguin, posypenguin-1, penguins)) return true;
		
		//movimientoabajo
		if(posypenguin+1<this.getTipoTablero() && !nohaypieza(posxpenguin, posypenguin+1) && 
				!hayOtroPinguino(posxpenguin, posypenguin+1, penguins)) return true;
		
		//movimientoderecha
		if(posxpenguin+1<this.getTipoTablero() && !nohaypieza(posxpenguin+1, posypenguin) && 
				!hayOtroPinguino(posxpenguin+1, posypenguin, penguins)) return true;
		
		//movimientoizquierda
		if(posxpenguin-1>=0 && !nohaypieza(posxpenguin-1, posypenguin) && 
				!hayOtroPinguino(posxpenguin-1, posypenguin, penguins)) return true;
		
		//movimientodiagonalarribaderecha
		if(posxpenguin+1<this.getTipoTablero() && posypenguin-1>=0 && !nohaypieza(posxpenguin+1, posypenguin-1) && 
				!hayOtroPinguino(posxpenguin+1, posypenguin-1, penguins)) return true;
		
		//movimientodiagonalarribaizquierda
		if(posxpenguin-1>=0 && posypenguin-1>=0 && !nohaypieza(posxpenguin-1, posypenguin-1) && 
				!hayOtroPinguino(posxpenguin-1, posypenguin-1, penguins)) return true;
		
		//movimientodiagonalabajoderecha
		if(posxpenguin+1<this.getTipoTablero() && posypenguin+1<this.getTipoTablero() && !nohaypieza(posxpenguin+1, posypenguin+1) && 
				!hayOtroPinguino(posxpenguin+1, posypenguin+1, penguins)) return true;
		
		//movimientodiagonalabajoizquierda
		if(posxpenguin-1>=0 && posypenguin+1<this.getTipoTablero() && !nohaypieza(posxpenguin-1, posypenguin+1) && 
				!hayOtroPinguino(posxpenguin-1, posypenguin+1, penguins)) return true;

		return false;
	}

	/**
     * Created by Barron on 29/04/2016.
     */
	static class Position {
        int x;
        int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        boolean equals(Position p){
            if(this.x==p.x && this.y==p.y)return true;
            else return false;
        }

    }
}

