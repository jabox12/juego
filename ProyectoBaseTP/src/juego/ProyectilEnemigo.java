package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class ProyectilEnemigo {
	private double x;
	private double y;
	private Image img;
	private int alto;
	public ProyectilEnemigo(double x, double y) {
		this.x = x;
		this.y = y;
		this.img = Herramientas.cargarImagen("proyectilEnemigo.png");
		this.alto = 30;
	}

	public void dibujar(Entorno e) {
		e.dibujarImagen(img, x, y, 0, 1.0);
	}
	
	public void dibujarBoss(Entorno e) {
		e.dibujarImagen(img, x, y, 0, 2.0);
	}

	public void mover() {
		y += 6;
	}
	public void moverBoss() {
		y += 12;
	}

	public boolean teExcedisteDelEntorno(Entorno e) {
		return y < 0 || y > e.alto();
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public int getAlto() {
		return alto;
	}

}
