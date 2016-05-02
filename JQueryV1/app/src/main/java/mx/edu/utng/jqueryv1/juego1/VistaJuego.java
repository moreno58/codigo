package mx.edu.utng.jqueryv1.juego1;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import mx.edu.utng.jqueryv1.R;

public class VistaJuego extends View {
	
	Context context;
	Tablero tablero = null;
	Player player1 = null;
	Player player2 = null;
	private TextView puntuacionesp1;
	private TextView puntuacionesp2;
	private Activity padre;
	
	public VistaJuego(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context=context;
		setBackgroundColor(Color.parseColor("#9AD6E6"));
		CreaTablero(context);
		CreaPlayer1(context);
		CreaPlayer2(context);
	}
	

	private void CreaTablero(Context context) {
		tablero = new Tablero(context, this);
	}

	private void CreaPlayer2(Context context) {
		player2 = new Player(context, this, 2);
	}

	private void CreaPlayer1(Context context) {
		player1=new Player(context, this, 1);
	}
	
	
	@Override
	protected synchronized void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		tablero.onDraw(canvas);
		player1.onDraw(canvas);
		player2.onDraw(canvas);
		int player=tablero.getPlayerOn();
		Paint pincelg = new Paint();
		pincelg.setColor(Color.RED);
		pincelg.setTextSize(30);
		Paint pincel = new Paint();
		pincel.setColor(Color.BLACK);
		pincel.setTextSize(30);
		if(player == 1){
			canvas.drawText(String.valueOf(player1.getScore()) + "Puntos", (int)tablero.getTablero().get(0).get(tablero.getTipoTablero()-1).getPosX()-60, 50, pincelg);
			canvas.drawText(String.valueOf(player2.getScore()) + "Puntos", (int)tablero.getTablero().get(tablero.getTipoTablero()-1).get(tablero.getTipoTablero()-1).getPosX()-60, (int)tablero.getTablero().get(tablero.getTipoTablero()-1).get(tablero.getTipoTablero()-1).getPosY() + 140, pincel);
		}else{
			canvas.drawText(String.valueOf(player1.getScore()) + "Puntos", (int)tablero.getTablero().get(0).get(tablero.getTipoTablero()-1).getPosX()-60, 50, pincel);
			canvas.drawText(String.valueOf(player2.getScore()) + "Puntos", (int)tablero.getTablero().get(tablero.getTipoTablero()-1).get(tablero.getTipoTablero()-1).getPosX()-60, (int)tablero.getTablero().get(tablero.getTipoTablero()-1).get(tablero.getTipoTablero()-1).getPosY()+140, pincelg);
		}
	}
	
	@Override
	protected void onSizeChanged(int ancho, int alto, int ancho_anter,
			int alto_anter) {
		
		super.onSizeChanged(ancho, alto, ancho_anter, alto_anter);
		tablero.onSizeChange(0,100, alto, ancho);
		player1.onSizeChange(0, 0, ancho);
		player2.onSizeChange(0, alto-100, ancho);
	}
	
	@Override
	public synchronized boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);
		float x = event.getX();
		float y = event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			break;
		case MotionEvent.ACTION_UP:
				calcularmunecoselecionado(x, y);
				boolean marcado = marcarpinguino();
				if(marcado == false){
						calcularpiezaselecionada(x, y);
						moverpieza();
					}
		}
		return true;
	}


	private void moverpieza() {
		if (tablero.getPiezasel() != null) {
			if (tablero.getPlayerOn() == 1 && player1.getPenguinant() != null) {
				if (tablero.esmovimientolegal(
						tablero.getPinguinos(player1, player2),
						player1.getPenguinant(), tablero.getPiezasel())) {
					if (player1.getPenguinant().getPosXTablero() != -1
							&& player1.getPenguinant().getPosYTablero() != -1) {
						Placas placa = tablero.getPlacaAnterior(player1
								.getPenguinant().getPosXTablero(), player1
								.getPenguinant().getPosYTablero());
						placa.setVisible(false);
						player1.changeScore(placa.getValor());
					}
					if (!tablero.hayOtroPinguino(tablero.getPiezasel()
							.getPosXtablero(), tablero.getPiezasel()
							.getPosYtablero(), tablero.getPinguinos(player1,
							player2))) {
						player1.moverpieza(tablero.getPiezasel());
						this.postInvalidate();
						tablero.calcularinvalidados(player1, player2);
						if (player2.isEnable())
							tablero.activarJugador2();
						else if (player1.isEnable()) {
							Toast.makeText(this.context,
									R.string.jugador2inavilitado,
									Toast.LENGTH_LONG).show();
						} else {
							SharedPreferences pref = context.getSharedPreferences(
									"es.sitvalencia.app.android.Penguinsonice_preferences", Context.MODE_PRIVATE);
						       Bundle bundle = new Bundle();
						       bundle.putInt("puntj1", player1.getScore());
						       bundle.putInt("puntj2", player2.getScore());

						       bundle.putString("nomj1", pref.getString("NombreJugador1", "Player1"));
						       bundle.putString("nomj2", pref.getString("Nombrejugador2", "Player2"));
						       Intent i=new Intent();
							   i.putExtras(bundle);
							   padre.setResult(Activity.RESULT_OK, i);
							   padre.finish();

						}
					} else {
						Toast.makeText(this.context,
								R.string.PosicionIncorrecta, Toast.LENGTH_LONG)
								.show();
					}
				} else {
					Toast.makeText(this.context, R.string.PosicionIncorrecta,
							Toast.LENGTH_LONG).show();
				}
			} else if (tablero.getPlayerOn() == 2
					&& player2.getPenguinant() != null) {
				if (tablero.esmovimientolegal(
						tablero.getPinguinos(player1, player2),
						player2.getPenguinant(), tablero.getPiezasel())) {
					if (player2.getPenguinant().getPosXTablero() != -1
							&& player2.getPenguinant().getPosYTablero() != -1) {
						Placas placa = tablero.getPlacaAnterior(player2
								.getPenguinant().getPosXTablero(), player2
								.getPenguinant().getPosYTablero());
						placa.setVisible(false);
						player2.changeScore(placa.getValor());
					}
					if (!tablero.hayOtroPinguino(tablero.getPiezasel()
							.getPosXtablero(), tablero.getPiezasel()
							.getPosYtablero(), tablero.getPinguinos(player1,
							player2))) {
						player2.moverpieza(tablero.getPiezasel());
						this.postInvalidate();
						tablero.calcularinvalidados(player1, player2);
						if (player1.isEnable())
							tablero.activarJugador1();
						else if (player2.isEnable()) {
							Toast.makeText(this.context,
									R.string.jugador1inavilitado,
									Toast.LENGTH_LONG).show();
						} else {
							SharedPreferences pref = context.getSharedPreferences(
									"es.sitvalencia.app.android.Penguinsonice_preferences", Context.MODE_PRIVATE);
						       Bundle bundle = new Bundle();
						       bundle.putInt("puntj1", player1.getScore());
						       bundle.putInt("puntj2", player2.getScore());

						       bundle.putString("nomj1", pref.getString("NombreJugador1", "Player1"));
						       bundle.putString("nomj2", pref.getString("Nombrejugador2", "Player2"));
						       Intent i=new Intent();
							   i.putExtras(bundle);
							   padre.setResult(Activity.RESULT_OK, i);
							   padre.finish();
						}
					} else {
						Toast.makeText(this.context,
								R.string.PosicionIncorrecta, Toast.LENGTH_LONG)
								.show();
					}
				} else {
					Toast.makeText(this.context, R.string.PosicionIncorrecta,
							Toast.LENGTH_LONG).show();
				}
			}
		}
	}

	private void calcularpiezaselecionada(float x, float y) {
		    tablero.CalcularPiezaSelecionada(x, y);
	}

	private boolean marcarpinguino() {
		boolean marcado = false;
		if(tablero.getPlayerOn() == 1){
			marcado = player1.marcarpinguino();
			this.postInvalidate();
		}else if(tablero.getPlayerOn() == 2){
			marcado = player2.marcarpinguino();
			this.postInvalidate();
		}
		return marcado;
	}

	private void calcularmunecoselecionado(float x, float y) {
		if(tablero.getPlayerOn() == 1){
			player1.CalcularPenguinSelected(x, y);
		}else if(tablero.getPlayerOn() == 2){
			player2.CalcularPenguinSelected(x, y);
		}
	}
	
	public void setPadre(Activity padre){
		this.padre=padre;
	}
	
	

	
}
