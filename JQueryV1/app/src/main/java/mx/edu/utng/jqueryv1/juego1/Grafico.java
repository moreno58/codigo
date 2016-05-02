/**
 * 
 */
package mx.edu.utng.jqueryv1.juego1;


import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;


public class Grafico {

	protected Drawable drawable; // Imagen que dibujaremos
	public double posX, posY; // Posicion
	protected int ancho; // Dimensiones de la imagen
	protected int alto;
	private int radioColision;// Para determinar colision

	// Donde dibujamos el grafico (usada en view.ivalidate)

	

	private View view;

	public Grafico(View view, Drawable drawable) {
		this.view = view;
		this.drawable = drawable;
		ancho = drawable.getIntrinsicWidth();
		alto = drawable.getIntrinsicHeight();
		radioColision = (alto + ancho) / 4;

	}

	public void dibujaGrafico(Canvas canvas) {

		canvas.save();
		drawable.setBounds((int) posX, (int) posY, (int) posX + ancho,
				(int) posY + alto);

		drawable.draw(canvas);
		canvas.restore();
	}
	
	public double distancia(Grafico g) {
		return Math.hypot(posX - g.posY, posY - g.posX);
	}

	public boolean verificaColision(Grafico g) {
		return (distancia(g) < (radioColision + g.radioColision));
	}

	public Drawable getDrawable() {
		return drawable;
	}

	public void setDrawable(Drawable drawable) {
		this.drawable = drawable;
	}

	public double getPosX() {
		return posX;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public double getPosY() {
		return posY;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}

	

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public int getRadioColision() {
		return radioColision;
	}

	public void setRadioColision(int radioColision) {
		this.radioColision = radioColision;
	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}
	
	public boolean esselecionado(float x, float y) {
		double distanciaxy = distancia(x,y);
		double distanciaexterna = distancia((float)posX,(float)posY+getAlto()/2);
		if(distanciaxy<distanciaexterna) return true;
		else return false;
	}
	
	public double distancia(float x, float y) {
		return Math.hypot(posX + getAncho()/2 - x, posY+getAlto()/2 - y);
	}

}
