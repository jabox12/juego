package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Menu {
	private double x;
	private double y;
	private int ancho;
	private int alto;
	Image img1;
	
	public Menu(double x, double y, int ancho, int alto) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		img1 = Herramientas.cargarImagen("menu.png");//cargar imagen png
	}
	
	public void dibujar(Entorno e) {
		e.dibujarImagen(img1, this.x, this.y, 0);

	}
}