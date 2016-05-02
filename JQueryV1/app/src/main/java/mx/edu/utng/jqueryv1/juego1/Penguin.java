package mx.edu.utng.jqueryv1.juego1;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

public class Penguin extends Grafico {
	
	
	int jugador;
	int posXTablero;
	int posYTablero;
	
	public Penguin(View view, Drawable drawable) {
		super(view, drawable);
		setPosXTablero(-1);
		setPosYTablero(-1);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void dibujaGrafico(Canvas canvas) {
		canvas.save();
		drawable.setBounds((int) posX, (int) posY, (int) posX + ancho,
				(int) posY + alto);

		drawable.draw(canvas);
		canvas.restore();
	}

	public int getPosXTablero() {
		return posXTablero;
	}

	public void setPosXTablero(int posXTablero) {
		this.posXTablero = posXTablero;
	}

	public int getPosYTablero() {
		return posYTablero;
	}

	public void setPosYTablero(int posYTablero) {
		this.posYTablero = posYTablero;
	}

	public int getJugador() {
		return jugador;
	}

	public void setJugador(int jugador) {
		this.jugador = jugador;
	}

	
	

}
