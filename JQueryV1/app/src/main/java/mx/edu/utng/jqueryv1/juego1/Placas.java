package mx.edu.utng.jqueryv1.juego1;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

public class Placas extends Grafico{

	int valor;
	int posXtablero;
	int posYtablero;
	boolean visible;
	
	public Placas(View view, Drawable drawable) {
		super(view, drawable);
		setVisible(true);
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


	public int getPosXtablero() {
		return posXtablero;
	}

	public void setPosXtablero(int posXtablero) {
		this.posXtablero = posXtablero;
	}

	public int getPosYtablero() {
		return posYtablero;
	}

	public void setPosYtablero(int posYtablero) {
		this.posYtablero = posYtablero;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public boolean esselecionado(float x, float y) {
		double distanciaxy = distancia(x,y);
		double distanciaexterna = distancia((float)posX,(float)posY+getAlto()/2);
		if(distanciaxy<distanciaexterna) return true;
		else return false;
	}
	
	@Override
	public double distancia(float x, float y) {
		return Math.hypot(posX + getAncho()/2 - x, posY+getAlto()/2 - y);
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	

}
