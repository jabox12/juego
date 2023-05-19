package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Fondo {
	private double x;
	private double y;
	private int ancho;
	private int alto;
	Image img1;
	
	public Fondo(double x, double y, int ancho, int alto) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		img1 = Herramientas.cargarImagen("fondo.gif");//cargar imagen png
	}
	
	public void dibujar(Entorno e) {
		e.dibujarRectangulo(x, y, ancho, alto, 0,Color.BLACK);
		e.dibujarImagen(img1, this.x, this.y, 0);
	}
}
