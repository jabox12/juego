package juego;
import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class naveEnemiga {
	private double x;
	private double y;
	private int ancho;
	private int alto;
	private double velocidad;
	private boolean direccion;
	Image img1;
	
	public naveEnemiga(double x, double y,int ancho, int alto, int velocidad) {
		this.x=x;
		this.y=y;
		this.alto=alto;
		this.ancho=ancho;
		this.velocidad = velocidad;
		img1 = Herramientas.cargarImagen("naveEnemiga.png");
	}
	
	public void dibujar(Entorno e) {
		e.dibujarImagen(img1, this.x, this.y, 0, 1.2);	

	}
	public void dibujarBoss(Entorno e) {
		e.dibujarImagen(img1, this.x, this.y, 0, 4);	
	}
	
	
	public void mover() {
		if(direccion) {
			 x += velocidad*2;
			 y += velocidad/2;
		} else {
			x -= velocidad*2;
			y += velocidad/2;
		}
	}
	public void moverBoss() {
		if(direccion) {
			 x += velocidad;
		} else {
			x -= velocidad;
		}
	}
	
	public void cambiarDireccion() {
		if (direccion) {
			direccion = false;
		} else {
			direccion = true;
		}
	}
	public ProyectilEnemigo disparar() {
		return new ProyectilEnemigo(x, y);
	}
	public boolean chocaEntorno(Entorno e) {
		return x < 0 + ancho / 2 || x > e.ancho() - ancho / 2;
	}
	
	public boolean chocaConProyectil(Proyectil proyectil) {
		return x < proyectil.getX() + ancho / 2 && x > proyectil.getX() - ancho / 2 && y > proyectil.getY() - proyectil.getAlto()
				&& y < proyectil.getY() + proyectil.getAlto();
	}
	
	public boolean saleDePantalla(Entorno e) {
		return y > e.alto() - alto/2;
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public int getAncho() {
		return ancho;
	}

	public int getAlto() {
		return alto;
	}
}
