package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;
public class naveJugador {
		
		private double x;
		private double y;
		private int ancho;
		private int alto;
		private double factorDesplazamiento;
		Image img1;
		
		public naveJugador(double x, double y, int ancho, int alto, double f) {
			this.x = x;
			this.y = y;
			this.ancho = ancho;
			this.alto = alto;
			this.factorDesplazamiento = f;
			img1 = Herramientas.cargarImagen("naveJugador.png");//cargar imagen png
		}
		
		public boolean chocasteConEnemigo(naveEnemiga enemigo) {
			return x < enemigo.getX() + ancho / 2 && x > enemigo.getX() - enemigo.getAncho() + ancho / 2
					&& y > enemigo.getY() - enemigo.getAlto() && y < enemigo.getY() + enemigo.getAlto();
		}
		public boolean chocasteConMeteorito(Meteorito enemigo) {
			return x < enemigo.getX() + ancho / 2 && x > enemigo.getX() - ancho / 2
					&& y > enemigo.getY() - enemigo.getAlto() && y < enemigo.getY() + enemigo.getAlto();
		}
		public boolean chocasteConVida(Vida vidaJug) {
			return x < vidaJug.getX() + ancho / 2 && x > vidaJug.getX() - ancho / 2
					&& y > vidaJug.getY() - vidaJug.getAlto() && y < vidaJug.getY() + vidaJug.getAlto();
		}
		
		public boolean chocasteProyectilEnemigo(ProyectilEnemigo proyectilEnemigo) {
			return x < proyectilEnemigo.getX() + ancho / 2 && x > proyectilEnemigo.getX() - ancho / 2 && y > proyectilEnemigo.getY() - proyectilEnemigo.getAlto()
					&& y < proyectilEnemigo.getY() + proyectilEnemigo.getAlto();
		}
		
		
		public Proyectil disparar() {
			return new Proyectil(x, y);
		}
		
		public void dibujar(Entorno e) {
			e.dibujarImagen(img1, this.x, this.y, 0);
		}
		

		
		public void moverIzquierda() {
			x -= factorDesplazamiento;
		}
		
		public void moverDerecha() {
			x += factorDesplazamiento;
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

		public double getFactorDesplazamiento() {
			return factorDesplazamiento;
		}
		
}
