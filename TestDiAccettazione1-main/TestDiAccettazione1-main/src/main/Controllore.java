package main;


public class Controllore {

	public boolean esisteLabirinto() {
		String nomeAtteso = "it.uniroma3.diadia.ambienti.Labirinto";
		try {
			@SuppressWarnings("unused")
			Class<?> classeLab= Class.forName(nomeAtteso);
			System.out.println("OK! Esiste Labirinto ed e' nel package corretto.");
			return true;
		} catch (ClassNotFoundException e) {
			System.out.println("Attenzione! la classe Labirinto non esiste, e' stata chiamata diversamente o e' nel package sbagliato");
			return false;
		}
	}

	public boolean esisteStanza() {
		String nomeAtteso = "it.uniroma3.diadia.ambienti.Stanza";
		try {
			@SuppressWarnings("unused")
			Class<?> classeStanza= Class.forName(nomeAtteso);
			System.out.println("OK! Esiste Stanza ed e' nel package corretto.");
			return true;
		} catch (ClassNotFoundException e) {
			System.out.println("Attenzione! la classe Stanza non esiste, e' stata chiamata diversamente o e' nel package sbagliato");
			return false;
		}
	}

	public boolean esisteAttrezzo() {
		String nomeAtteso = "it.uniroma3.diadia.attrezzi.Attrezzo";
		try {
			@SuppressWarnings("unused")
			Class<?> classeAttrezzo= Class.forName(nomeAtteso);
			System.out.println("OK! Esiste Attrezzo ed e' nel package corretto.");
			return true;
		} catch (ClassNotFoundException e) {
			System.out.println("Attenzione! la classe Attrezzo non esiste, e' stata chiamata diversamente o e' nel package sbagliato");
			return false;
		}
	}

	public boolean esisteGiocatore() {
		String nomeAtteso = "it.uniroma3.diadia.giocatore.Giocatore";
		try {
			@SuppressWarnings("unused")
			Class<?> classeGiocatore= Class.forName(nomeAtteso);
			System.out.println("OK! Esiste Giocatore ed e' nel package corretto.");
			return true;
		} catch (ClassNotFoundException e) {
			System.out.println("Attenzione! la classe Giocatore non esiste, e' stata chiamata diversamente o e' nel package sbagliato");
			return false;
		}
	}

	public boolean esisteBorsa() {
		String nomeAtteso = "it.uniroma3.diadia.giocatore.Borsa";
		try {
			@SuppressWarnings("unused")
			Class<?> classeBorsa= Class.forName(nomeAtteso);
			System.out.println("OK! Esiste Borsa ed e' nel package corretto.");
			return true;
		} catch (ClassNotFoundException e) {
			System.out.println("Attenzione! la classe Borsa non esiste, e' stata chiamata diversamente o e' nel package sbagliato");
			return false;
		}
	}

	public boolean esisteComando() {
		String nomeAtteso = "it.uniroma3.diadia.Comando";
		try {
			@SuppressWarnings("unused")
			Class<?> classeComando= Class.forName(nomeAtteso);
			System.out.println("OK! Esiste Comando ed e' nel package corretto.");
			return true;
		} catch (ClassNotFoundException e) {
			System.out.println("Attenzione! la classe Comando non esiste, e' stata chiamata diversamente o e' nel package sbagliato");
			return false;
		}
	}

	public boolean esisteDiaDia() {
		String nomeAtteso = "it.uniroma3.diadia.DiaDia";
		try {
			@SuppressWarnings("unused")
			Class<?> classeDiadia= Class.forName(nomeAtteso);
			System.out.println("OK! Esiste DiaDia ed e' nel package corretto.");
			return true;
		} catch (ClassNotFoundException e) {
			System.out.println("Attenzione! la classe DiaDia non esiste, e' stata chiamata diversamente o e' nel package sbagliato");
			return false;
		}
	}


	public boolean esistePartita() {
		String nomeAtteso = "it.uniroma3.diadia.Partita";
		try {
			@SuppressWarnings("unused")
			Class<?> classePartita= Class.forName(nomeAtteso);
			System.out.println("OK! Esiste Partita ed e' nel package corretto.");
			return true;
		} catch (ClassNotFoundException e) {
			System.out.println("Attenzione! la classe Partita non esiste, e' stata chiamata diversamente o e' nel package sbagliato");
			return false;
		}
	}

	public boolean controlliPrimaDellaConsegna() {
		return this.esisteDiaDia()&&this.esistePartita()&&this.esisteLabirinto()&&
				this.esisteStanza()&&this.esisteGiocatore()&&this.esisteComando()&&
				this.esisteBorsa()&&this.esisteAttrezzo();
	}
}
