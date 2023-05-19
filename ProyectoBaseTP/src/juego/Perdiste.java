package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Perdiste {
	private double x;
	private double y;
	private int ancho;
	private int alto;
	Image img1;
	
	public Perdiste(double x, double y, int ancho, int alto) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		img1 = Herramientas.cargarImagen("perdiste.png");//cargar imagen png
	}
	
	public void dibujar(Entorno e) {
		e.dibujarImagen(img1, this.x, this.y, 0);

	}
}