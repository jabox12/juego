package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Meteorito {
	private double x;
	private double y;
	private double velocidad;
	private double angulo;
	private int ancho;
	private int alto;
	Image img1;
	
	public Meteorito(double x, double y, double velocidad, double angulo,int ancho, int alto) {
		this.x = x;
		this.y = y;
		this.velocidad = velocidad;
		this.angulo = angulo;
		this.ancho=ancho;
		this.alto=alto;
		img1 = Herramientas.cargarImagen("meteorito.png");//cargar imagen png
	}
	public void dibujar(Entorno e) {
		e.dibujarImagen(img1, this.x, this.y, 0);
	}
	public void mover() {
		y += velocidad * Math.sin(angulo);
		x += velocidad * Math.cos(angulo);
	}
	
	public boolean saleDePantalla(Entorno e) {
		return y > e.alto() - alto/2;
	}
	
	public boolean chocaConProyectil(Proyectil proyectil) {
		return x < proyectil.getX() + ancho / 2 && x > proyectil.getX() - ancho / 2 && y > proyectil.getY() - proyectil.getAlto()
				&& y < proyectil.getY() + proyectil.getAlto();
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getVelocidad() {
		return velocidad;
	}

	public double getAngulo() {
		return angulo;
	}

	public int getAlto() {
		return alto;
	}
	public int getAncho() {
		return ancho;
	}

}
