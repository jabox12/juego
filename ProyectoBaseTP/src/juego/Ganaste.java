package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Ganaste {
	private double x;
	private double y;
	private int ancho;
	private int alto;
	Image img1;
	Image img2;
	Image img3;
	
	public Ganaste(double x, double y, int ancho, int alto) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		img1 = Herramientas.cargarImagen("ganaste.png");
		img2 = Herramientas.cargarImagen("ganasteNivel1.png");
		img3 = Herramientas.cargarImagen("ganasteNivel2.png");
	}
	
	public void dibujar(Entorno e) {
		e.dibujarImagen(img1, this.x, this.y, 0);
	}
	public void dibujarNivel1(Entorno e) {
		e.dibujarImagen(img2, this.x, this.y, 0);
	}
	public void dibujarNivel2(Entorno e) {
		e.dibujarImagen(img3, this.x, this.y, 0);
	}
}
