package main;

import static org.junit.Assert.assertTrue;

import org.junit.*;

public class Controllore {

	private boolean risultato;

	@Before
	public void setUp() throws Exception {
		this.risultato=false;
	}

	@Test
	public void esisteLabirinto() {
		String nomeAtteso = "it.uniroma3.diadia.ambienti.Labirinto";
		try {
			Class.forName(nomeAtteso);
			System.out.println("OK! Esiste Labirinto ed e' nel package corretto.");
			this.risultato=true;
		} catch (ClassNotFoundException e) {
			System.out.println("Attenzione! la classe Labirinto non esiste, e' stata chiamata diversamente o e' nel package sbagliato");
		}
		assertTrue(this.risultato);
	}

	@Test
	public void esisteStanza() {
		String nomeAtteso = "it.uniroma3.diadia.ambienti.Stanza";
		try {
			Class.forName(nomeAtteso);
			System.out.println("OK! Esiste Stanza ed e' nel package corretto.");
			this.risultato=true;
		} catch (ClassNotFoundException e) {
			System.out.println("Attenzione! la classe Stanza non esiste, e' stata chiamata diversamente o e' nel package sbagliato");
		}
		assertTrue(this.risultato);
	}

	@Test
	public void esisteAttrezzo() {
		String nomeAtteso = "it.uniroma3.diadia.attrezzi.Attrezzo";
		try {
			Class.forName(nomeAtteso);
			System.out.println("OK! Esiste Attrezzo ed e' nel package corretto.");
			this.risultato=true;
		} catch (ClassNotFoundException e) {
			System.out.println("Attenzione! la classe Attrezzo non esiste, e' stata chiamata diversamente o e' nel package sbagliato");
		}
		assertTrue(this.risultato);
	}

	@Test
	public void esisteGiocatore() {
		String nomeAtteso = "it.uniroma3.diadia.giocatore.Giocatore";
		try {
			Class.forName(nomeAtteso);
			System.out.println("OK! Esiste Giocatore ed e' nel package corretto.");
			this.risultato=true;
		} catch (ClassNotFoundException e) {
			System.out.println("Attenzione! la classe Giocatore non esiste, e' stata chiamata diversamente o e' nel package sbagliato");
		}
		assertTrue(this.risultato);
	}

	@Test
	public void esisteBorsa() {
		String nomeAtteso = "it.uniroma3.diadia.giocatore.Borsa";
		try {
			Class.forName(nomeAtteso);
			System.out.println("OK! Esiste Borsa ed e' nel package corretto.");
			this.risultato=true;
		} catch (ClassNotFoundException e) {
			System.out.println("Attenzione! la classe Borsa non esiste, e' stata chiamata diversamente o e' nel package sbagliato");
		}
		assertTrue(this.risultato);
	}

	@Test
	public void esisteComando() {
		String nomeAtteso = "it.uniroma3.diadia.Comando";
		try {
			Class.forName(nomeAtteso);
			System.out.println("OK! Esiste Comando ed e' nel package corretto.");
			this.risultato=true;
		} catch (ClassNotFoundException e) {
			System.out.println("Attenzione! la classe Comando non esiste, e' stata chiamata diversamente o e' nel package sbagliato");
		}
		assertTrue(this.risultato);
	}

	@Test
	public void esisteDiaDia() {
		String nomeAtteso = "it.uniroma3.diadia.DiaDia";
		try {
			Class.forName(nomeAtteso);
			System.out.println("OK! Esiste DiaDia ed e' nel package corretto.");
			this.risultato=true;
		} catch (ClassNotFoundException e) {
			System.out.println("Attenzione! la classe DiaDia non esiste, e' stata chiamata diversamente o e' nel package sbagliato");
		}
		assertTrue(this.risultato);
	}


	@Test
	public void esistePartita() {
		String nomeAtteso = "it.uniroma3.diadia.Partita";
		try {
			Class.forName(nomeAtteso);
			System.out.println("OK! Esiste Partita ed e' nel package corretto.");
			this.risultato=true;
		} catch (ClassNotFoundException e) {
			System.out.println("Attenzione! la classe Partita non esiste, e' stata chiamata diversamente o e' nel package sbagliato");
		}
		assertTrue(this.risultato);
	}
}
