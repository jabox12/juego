package juego;


import java.awt.Color;
import java.io.File;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	
	// Variables y métodos propios de cada grupo
	private Fondo fondo;
	private naveJugador jugador;
	private naveEnemiga [] enemigo;
	private naveEnemiga boss;
	private Proyectil proyectil;
	private ProyectilEnemigo [] proyectilEnemigo;
	private ProyectilEnemigo proyectilBoss;
	private Meteorito  meteorito;
	private Vida vidaJug;
	private Explosion explotar;
	private agarrasteVida agarrasteVida;
	private Perdiste perdiste;
	private Ganaste ganaste;
	private Menu menu;
	private int contadorDeTicks;
	private int contadorDeTicks2;
	private int contadorDeTicks3;
	private int contExplosionEnemigos;
	private int contExplosionMeteoritos;
	private int contExplosionJugador;
	private int contAuraJugador;
	private int vidas;
	private int vidasBoss;
	private int puntaje;
	private int musicaP;
	private boolean explotoEnemigos=false;
	private boolean explotoMeteoritos=false;
	private boolean explotoJugador=false;
	private boolean agarrasteVidaAura=false;
	private double auraXJugador;
	private double auraYJugador;
	private double explotoXEnemigos;
	private double explotoYEnemigos;
	private double explotoXMeteoritos;
	private double explotoYMeteoritos;
	private double explotoXJugador;
	private double explotoYJugador;
	private Clip clip = null;

	
	Juego()
	{
	
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Attack on Titan, Final Season - Grupo 9 Gonzalez Legal, Gutierrez- v1", 800, 600);
		
		// Inicializar lo que haga falta para el juego
	
		fondo = new Fondo(this.entorno.ancho()/2, this.entorno.alto()/2,800,600);
		menu = new Menu(this.entorno.ancho()/2, this.entorno.alto()/2,800,600);

		// Inicia el juego!
		this.entorno.iniciar();
	}
	//Musica
	void Musica(String musica) {
		if(musicaP==0) {
			//musicaP= 1;
			try {
				File musicaPrincipalFile = new File(musica);
				AudioInputStream musicaPrincipal = AudioSystem.getAudioInputStream(musicaPrincipalFile);
				clip = AudioSystem.getClip();
				clip.loop(Clip.LOOP_CONTINUOUSLY);
				clip.open(musicaPrincipal);
				clip.start();
			}
			catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
	
	//PANTALLAS
	void Reiniciar(){
		clip.setFramePosition(0);
		Musica("src/sonidos/musicaPrincipal.wav");
		clip.start();
		vidas=3;
		puntaje=0;
		contadorDeTicks = 500;
		contadorDeTicks2 = 250;
		contadorDeTicks3 = 500;
		jugador = new naveJugador(this.entorno.ancho()/2, this.entorno.alto() - 50, 60, 60, 10);
		perdiste = new Perdiste(this.entorno.ancho()/2, this.entorno.alto()/1.75,800,600);
		ganaste = new Ganaste(this.entorno.ancho()/2, this.entorno.alto()/1.65,800,600);
		enemigo = new naveEnemiga[5];
		proyectilEnemigo = new ProyectilEnemigo[5];
	}
	
	void nivelUno() {
		clip.stop();
		clip.setFramePosition(0);
		Musica("src/sonidos/musicaPrincipal.wav");
		clip.start();
		menu=null;
		contadorDeTicks = 500;
		contadorDeTicks2 = 200;
		contadorDeTicks3 = 500;
		vidas = 3;
		fondo = new Fondo(this.entorno.ancho()/2, this.entorno.alto()/2,800,600);
		jugador = new naveJugador(this.entorno.ancho()/2, this.entorno.alto() - 50, 60, 60, 10);
		perdiste = new Perdiste(this.entorno.ancho()/2, this.entorno.alto()/1.75,800,600);
		ganaste = new Ganaste(this.entorno.ancho()/2, this.entorno.alto()/1.65,800,600);
		enemigo = new naveEnemiga[5];
		proyectilEnemigo = new ProyectilEnemigo[5];
	}
	
	void nivelDos() {
		clip.setFramePosition(0);
		clip.start();
		jugador=null;
		puntaje=1001;
		contadorDeTicks = 500;
		contadorDeTicks2 = 250;
		contadorDeTicks3 = 500;
		jugador = new naveJugador(this.entorno.ancho()/2, this.entorno.alto() - 50, 60, 60, 10);
		perdiste = new Perdiste(this.entorno.ancho()/2, this.entorno.alto()/1.75,800,600);
		ganaste = new Ganaste(this.entorno.ancho()/2, this.entorno.alto()/1.65,800,600);
		enemigo = new naveEnemiga[6];
		proyectilEnemigo = new ProyectilEnemigo[6];
	}
	
	void nivelTres() {
		clip.stop();
		clip.setFramePosition(0);
		Musica("src/sonidos/musicaBoss.wav");
		clip.start();
		jugador=null;
		contadorDeTicks = 500;
		contadorDeTicks2 = 200;
		contadorDeTicks3 = 500;
		vidasBoss=10;
		puntaje=2002;
		fondo = new Fondo(this.entorno.ancho()/2, this.entorno.alto()/2,800,600);
		jugador = new naveJugador(this.entorno.ancho()/2, this.entorno.alto() - 50, 60, 60, 10);
		boss = new naveEnemiga(this.entorno.ancho()/2, 100, 170, 155,3);
		perdiste = new Perdiste(this.entorno.ancho()/2, this.entorno.alto()/1.75,800,600);
		ganaste = new Ganaste(this.entorno.ancho()/2, this.entorno.alto()/1.75,800,600);
	}
	
	//Metodos TICK
	void menuActivo() {
		if(menu!=null) {
			if(musicaP== 0) {
				Musica("src/sonidos/musicaMenu.wav");
				musicaP = 1;
			}
			fondo.dibujar(entorno);
			menu.dibujar(entorno);
			if(this.entorno.sePresiono('j')) {
				musicaP = 0;
				nivelUno();
			}	
		}
	}
	
	void jugadorMov() {
		if (this.jugador.getX() - this.jugador.getAncho() / 2 > 0 && (this.entorno.estaPresionada(this.entorno.TECLA_IZQUIERDA) || this.entorno.estaPresionada('a')) ) {
			jugador.moverIzquierda();
		}
		if (this.jugador.getX() + this.jugador.getAncho() / 2 < this.entorno.ancho() && (this.entorno.estaPresionada(this.entorno.TECLA_DERECHA) ||this.entorno.estaPresionada('d')) ) {
			jugador.moverDerecha();
		}
	}

	void explosionEnemigos() {
		if (explotoEnemigos==true) {
			explotar = new Explosion(explotoXEnemigos,explotoYEnemigos);
			explotar.dibujar(entorno);
		}
		if(contExplosionEnemigos>=100) {
			explotoEnemigos=false;
			contExplosionEnemigos=0;
		}
	}
	
	void explosionMeteoritos() {
		if (explotoMeteoritos==true) {
			explotar = new Explosion(explotoXMeteoritos,explotoYMeteoritos);
			explotar.dibujar(entorno);
		}
		if(contExplosionMeteoritos>=100) {
			explotoMeteoritos=false;
			contExplosionMeteoritos=0;
		}
	}
	
	void explosionJugador() {
		if (explotoJugador==true) {
			explotar = new Explosion(explotoXJugador,explotoYJugador);
			explotar.dibujar(entorno);
		}
		if(contExplosionJugador>=100) {
			explotoJugador=false;
			contExplosionJugador=0;
		}
	}
	
	void auraJugador() {
		if (agarrasteVidaAura==true) {
			agarrasteVida = new agarrasteVida(auraXJugador,auraYJugador);
			agarrasteVida.dibujar(entorno);
		}
		if(contAuraJugador>=150) {
			agarrasteVidaAura=false;
			contAuraJugador=0;
		}
	}
	
	void navesEnemigas() {
			for (int e = 0; e < enemigo.length; e++) {
				if (enemigo[e] != null) {
					enemigo[e].dibujar(entorno);
					enemigo[e].mover();
					if (enemigo[e].chocaEntorno(entorno)) {
						enemigo[e].cambiarDireccion();
					}
					if (jugador.chocasteConEnemigo(enemigo[e])) {
						explotoXJugador=enemigo[e].getX();
						explotoYJugador=enemigo[e].getY();
						explotoJugador=true;
						Herramientas.cargarSonido("sonidos/explosion.wav").start();
						vidas -= 1;
						enemigo[e] = null;
					}
					if (proyectil != null && enemigo[e] != null && enemigo[e].chocaConProyectil(proyectil)) {
						explotoXEnemigos=enemigo[e].getX();
						explotoYEnemigos=enemigo[e].getY();
						explotoEnemigos=true;
						Herramientas.cargarSonido("sonidos/explosion.wav").start();
						puntaje += 200;
						contadorDeTicks = 250;
						contExplosionEnemigos=0;
						proyectil = null;
						enemigo[e] = null;
					}
					if (proyectilEnemigo[e] == null && enemigo[e] != null && enemigo[e].getY()<300 && vidas >=1 && puntaje != 1000 && puntaje != 2001) {
						proyectilEnemigo[e] = enemigo[e].disparar();
						Herramientas.cargarSonido("sonidos/disparoEnemigo.wav").start();
					}
					if (proyectilEnemigo[e] != null && proyectilEnemigo[e].teExcedisteDelEntorno(entorno)) {
						proyectilEnemigo[e] = null;

					}
					if (enemigo[e] != null && enemigo[e].saleDePantalla(entorno)) {
						enemigo[e] = null;
					}
					if(enemigo[e]!= null && vidas <= 0) {
						proyectilEnemigo[e] = null;
						enemigo[e] = null;
					}
					if(enemigo[e]!= null && (puntaje == 1000 || puntaje == 2001)) {
						proyectilEnemigo[e] = null;
						enemigo[e] = null;
					}
				}
			}
	}

	void naveBoss() {
		if (boss != null) {
			boss.dibujarBoss(entorno);
			boss.moverBoss();
			if (boss.chocaEntorno(entorno)) {
				boss.cambiarDireccion();
			}
			if (proyectil != null && boss != null && boss.chocaConProyectil(proyectil)) {
				explotoXEnemigos=proyectil.getX();
				explotoYEnemigos=proyectil.getY();
				explotoEnemigos=true;
				Herramientas.cargarSonido("sonidos/explosion.wav").start();
				contadorDeTicks = 250;
				contExplosionEnemigos=0;
				proyectil = null;
				vidasBoss-=1;
			}
			if (proyectilBoss == null && boss != null && boss.getY()<300 && vidas > 0 && puntaje != 3000) {
				proyectilBoss = boss.disparar();
				Herramientas.cargarSonido("sonidos/disparoEnemigo.wav").start();
			}
			if (proyectilBoss != null && proyectilBoss.teExcedisteDelEntorno(entorno)) {
				proyectilBoss = null;

			}
			if(boss!= null && (vidas <= 0 || vidasBoss<=0)) {
				boss = null;
				proyectilBoss = null;
				puntaje=3000;
			}
		}
	}
	
	void meteoritosColisiones() {
		if (meteorito != null) {
			meteorito.dibujar(entorno);
			meteorito.mover();
			if (jugador.chocasteConMeteorito(meteorito)) {
				explotoXJugador=meteorito.getX();
				explotoYJugador=meteorito.getY();
				explotoJugador=true;
				Herramientas.cargarSonido("sonidos/explosion.wav").start();
				vidas -= 1;
				meteorito = null;
				contadorDeTicks2 = 0;
				contExplosionJugador= 0;
			}
			if (meteorito != null && meteorito.saleDePantalla(entorno)) {
				meteorito = null;
				contadorDeTicks2 = 0;
			}
			if (proyectil != null && meteorito != null && meteorito.chocaConProyectil(proyectil)) {
				explotoXMeteoritos=meteorito.getX();
				explotoYMeteoritos=meteorito.getY();
				explotoMeteoritos=true;
				Herramientas.cargarSonido("sonidos/meteorito.wav").start();
				proyectil = null;
				contExplosionMeteoritos=0;

			}
			if(meteorito != null && vidas <= 0) {
				meteorito = null;
			}
			if(meteorito != null &&  (puntaje == 1000 || puntaje == 2001 || puntaje == 3000)) {
				meteorito = null;
			}
		}
	}
	
	void vidasColisiones() {
		if (vidaJug != null) {
			vidaJug.dibujar(entorno);
			vidaJug.mover();
			if (jugador.chocasteConVida(vidaJug)) {
				auraXJugador=vidaJug.getX();
				auraYJugador=vidaJug.getY();
				agarrasteVidaAura=true;
				vidas += 1;
				vidaJug = null;
				contadorDeTicks2 = 0;
				contAuraJugador=0;
				Herramientas.cargarSonido("sonidos/agarrasteVida.wav").start();
			}
			if (vidaJug != null && vidaJug.saleDePantalla(entorno)) {
				vidaJug = null;
				contadorDeTicks2 = 0;
			}
			if(vidaJug != null && vidas <= 0) {
				vidaJug = null;
			}
			if(vidaJug != null &&  (puntaje == 1000 || puntaje == 2001 || puntaje == 3000)) {
				vidaJug = null;
			}
		}
	}
	
	void aparicionEnemigos() {
		if (contadorDeTicks >= 800) {
			int nulo = 0;
			int random = (int)(Math.random()*(750-50+1)+50);
			for (int i = 0; i < enemigo.length; i++) {
				if (enemigo[i] == null && nulo == 0) {
					enemigo[i] = new naveEnemiga(random, 10, 60,40, 2);
					nulo += 1;
				}
			}
			contadorDeTicks = 0;
		}
	}
	
	void aparicionMeteoritos() {
		if (contadorDeTicks2 >= 500) {
			int nulo2 = 0;
			int random2 = (int)(Math.random()*4+1);
			if (meteorito == null && nulo2 == 0 && random2==4) {
				meteorito = new Meteorito(0,0, 4,7, 40,30);
				nulo2 += 1;
			}else if(meteorito == null && nulo2 == 0 && random2==2) {
				meteorito = new Meteorito(800,0, 4,15, 40,30);
				nulo2 += 1;
			}else if(meteorito == null && nulo2 == 0 && random2==3) {
				meteorito = new Meteorito(400,0, 4,-4.5, 40,30);
				nulo2 += 1;
			}else if (meteorito == null && nulo2 == 0 && random2==4) {
				meteorito = new Meteorito(400,0, 4,7.7, 40,30);
				nulo2 += 1;
			}

			contadorDeTicks2 = 0;
		}
	}
	
	void aparicionVidas() {

		if (contadorDeTicks3 >= 2500) {
			int nulo3 = 0;
			int random3 = (int)(Math.random()*4+1);
			if (vidaJug == null && nulo3 == 0 && random3==4) {
				vidaJug = new Vida(0,0, 4,7, 40,30);
				nulo3 += 1;
			}else if(vidaJug == null && nulo3 == 0 && random3==2) {
				vidaJug = new Vida(800,0, 4,15, 40,30);
				nulo3 += 1;
			}else if(vidaJug == null && nulo3 == 0 && random3==3) {
				vidaJug = new Vida(400,0, 4,-4.5, 40,30);
				nulo3 += 1;
			}else if (vidaJug == null && nulo3 == 0 && random3==4) {
				vidaJug = new Vida(400,0, 4,7.7, 40,30);
				nulo3 += 1;
			}
			contadorDeTicks3 = 0;
		}
	}
	
	void proyectilAliado() {
		if (proyectil != null) {
			proyectil.dibujar(entorno);
			proyectil.mover();
			if (proyectil.teExcedisteDelEntorno(entorno)) {
				proyectil = null;
				Herramientas.cargarSonido("sonidos/disparoJugador.wav").start();
			}
		}
		if (entorno.sePresiono(entorno.TECLA_ESPACIO) && proyectil == null && vidas != 0 && puntaje !=1000 && puntaje != 2001 && puntaje != 3000) {
			proyectil = jugador.disparar();
			Herramientas.cargarSonido("sonidos/disparoJugador.wav").start();
		}
	}
	
	void proyectilEnemigo() {
		for (int i = 0; i < proyectilEnemigo.length; i++) {
			ProyectilEnemigo l = proyectilEnemigo[i];
			if (l != null) {
				l.dibujar(entorno);
				l.mover();
				// Si el proyectil choca con el jugador lo ponemos en null y restamos una vida
				if (jugador.chocasteProyectilEnemigo(l)) {
					explotoXJugador=proyectilEnemigo[i].getX();
					explotoYJugador=proyectilEnemigo[i].getY();
					explotoJugador=true;
					Herramientas.cargarSonido("sonidos/explosion.wav").start();
					proyectilEnemigo[i] = null;
					contExplosionJugador = 0;
					vidas -= 1;
				}
			}
		}
	}
	
	void proyectilBoss() {
		if (proyectilBoss != null) {
			proyectilBoss.dibujarBoss(entorno);
			proyectilBoss.moverBoss();
			// Si el proyectil choca con el jugador lo ponemos en null y restamos una vida
			if (jugador.chocasteProyectilEnemigo(proyectilBoss)) {
				explotoXJugador=jugador.getX();
				explotoYJugador=jugador.getY();
				explotoJugador=true;
				Herramientas.cargarSonido("sonidos/explosion.wav").start();
				proyectilBoss = null;
				contExplosionJugador = 0;
				vidas = 0;
			}
		}
	}
	
	void contadoresDeTicks(){
		if (puntaje < 1001) {
			contadorDeTicks += 9;
			contadorDeTicks2 += 9;
			contadorDeTicks3 += 3;

		}else if (puntaje > 1000 && puntaje < 2001) {
			contadorDeTicks += 13;
			contadorDeTicks2 += 13;
			contadorDeTicks3 += 3;
		}else if (puntaje > 2000) {
			contadorDeTicks += 20;
			contadorDeTicks2 += 20;
			contadorDeTicks3 += 3;
		}
		contExplosionEnemigos += 3;
		contExplosionMeteoritos += 3;
		contExplosionJugador += 3;
		contAuraJugador += 3;
	}
	
	void interfaz() {
		entorno.cambiarFont("Arial BLACK", 20, Color.white);
		if(puntaje<2001) {
			entorno.escribirTexto("Vidas: " + vidas + " Puntos: " + puntaje, 280, 22);
		}else {
			entorno.escribirTexto("Vidas: " + vidas + "  Vidas Boss: " + vidasBoss, 280, 22);
		}
	}
	
	void pasarNivel() {
		if(puntaje == 1000) {
			clip.stop();
			if(musicaP == 0) {
				Herramientas.cargarSonido("sonidos/pasaDeNivel.wav").start();
				musicaP = 1;
			}
			fondo.dibujar(entorno);
			ganaste.dibujarNivel1(entorno);
			explotoJugador=false;
			explotoEnemigos=false;
			explotoMeteoritos=false;
			if(this.entorno.sePresiono('y')) {
				musicaP= 0;
				nivelDos();
			}
		}
		if(puntaje == 2001) {
			clip.stop();
			if(musicaP == 0) {
				Herramientas.cargarSonido("sonidos/pasaDeNivel.wav").start();
				musicaP = 1;
			}
			fondo.dibujar(entorno);
			ganaste.dibujarNivel2(entorno);
			explotoJugador=false;
			explotoEnemigos=false;
			explotoMeteoritos=false;
			if(this.entorno.sePresiono('y')) {
				musicaP = 0;
				nivelTres();
			}
		}
		if(puntaje >= 3000) {
			clip.stop();
			if(musicaP== 0) {
				Herramientas.cargarSonido("sonidos/ganaste.wav").start();
				musicaP = 1;
			}
			fondo.dibujar(entorno);
			ganaste.dibujar(entorno);
			explotoJugador=false;
			explotoEnemigos=false;
			explotoMeteoritos=false;
			if(this.entorno.sePresiono('y')) {
				musicaP = 0;
				Reiniciar();
			}
		}
	}
	
	void moriste() {
		if (vidas<=0) {
			fondo.dibujar(entorno);
			perdiste.dibujar(entorno);
			explotoJugador=false;
			explotoEnemigos=false;
			explotoMeteoritos=false;
			clip.stop();
			if(musicaP == 0) {
				Herramientas.cargarSonido("sonidos/perdiste.wav").start();
				musicaP = 1;
			}
			if (this.entorno.sePresiono('y')) {
				musicaP = 0;
				Reiniciar();
			}
		}
	}
	
	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	
	
	public void tick()
	{	
		//Menu activo
		menuActivo();
		
		//Menu inactivo
		if (menu==null) {
			fondo.dibujar(entorno);
			if(puntaje!=1000 && puntaje!=2001 && puntaje!=3000) {
				jugador.dibujar(entorno);
			}
			//Movimineto del jugador
			jugadorMov();
		
			//nave enemiga colisiones y disparos
			if (puntaje<2001) {
				navesEnemigas();
			}
			
			//BOSSS colisiones y disparos
			if(puntaje>=2001) {
				naveBoss();

			}
		
			//Meteoritos colisiones
			meteoritosColisiones();
			
			//Vidas colisiones
			vidasColisiones();

		
			//contador de ticks para que re aparezcan los enemigos
			if (puntaje < 2001) {
				aparicionEnemigos();
			}
			
			// contador para que aparezcan los meteoritos
			aparicionMeteoritos();
			
			// contador para que aparezcan las vidas
			aparicionVidas();

		
			//proyectil aliado
			proyectilAliado();
		
		
			// proyectil enemigo
			if(puntaje<=2001) {
				proyectilEnemigo();
			}
			//Poryectil BOSS
			if(puntaje >= 2001) {
				proyectilBoss();
			}
			
			//Explosiones y Aura
			explosionEnemigos();
			explosionJugador();
			explosionMeteoritos();
			auraJugador();
			
			//Contadores de ticks segun los niveles
			contadoresDeTicks();
		
			//Contador de vidas y puntos
			interfaz();
			
			//pantalla pasar de nivel
			pasarNivel();
			
			//Pantalla de Perdiste
			moriste();
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
