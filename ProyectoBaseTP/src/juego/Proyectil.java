package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Proyectil {
	private double x;
	private double y;
	private int alto;
	Image img1;
		   
	
		public Proyectil(double x, double y) {
			this.x = x;
			this.y = y;
			img1 = Herramientas.cargarImagen("proyectilJugador.png");//cargar imagen png
			this.alto = 30;
		}
		
	
	
	public void dibujar(Entorno e) {
		//e.dibujarCirculo(x, y, 2 * radio, Color.black);
		e.dibujarImagen(img1, this.x, this.y, 0);
	}
	
	public boolean teExcedisteDelEntorno(Entorno e) {
		return y < 0 || y > e.alto();
	}
	
	public void mover() {
		y-= 15;
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public double getAlto() {
		return alto;
	}

	
}
