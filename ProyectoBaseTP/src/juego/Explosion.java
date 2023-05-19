package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Explosion {
		private double x;
		private double y;
		Image img1;
		public Explosion(double x, double y) {
			this.x = x;
			this.y = y;
			img1 = Herramientas.cargarImagen("explosion.gif");//cargar imagen png
		}

		
		public void dibujar(Entorno e) {
			e.dibujarImagen(img1, this.x, this.y, 0);
		}
}

