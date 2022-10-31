package main;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

public class Controllore {
	
	private boolean risultato;

	@Before
	public void setUp() throws Exception {
		this.risultato=false;
	}
	
	@Test
	public void controlloMetodiBorsa() {
		String nomeAtteso = "it.uniroma3.diadia.giocatore.Borsa";

		try {
			Class<?> classeDaControllare = Class.forName(nomeAtteso);
			Method[] metodiDaControllare = classeDaControllare.getDeclaredMethods();
			Boolean trovatoGetContenutoOrdinatoPerPeso= false;
			Boolean trovatoGetContenutoOrdinatoPerNome= false;
			Boolean trovatoGetContenutoRaggruppatoPerPeso= false;
			Boolean trovatoSortedSetOrdinatoPerPeso = false;
			for(Method method : metodiDaControllare) {
				if(method.getName().equals("getContenutoOrdinatoPerPeso")) {
					trovatoGetContenutoOrdinatoPerPeso= true;
				}
				if(method.getName().equals("getContenutoOrdinatoPerNome")) {
					trovatoGetContenutoOrdinatoPerNome= true;
				}
				if(method.getName().equals("getContenutoRaggruppatoPerPeso")) { 
					trovatoGetContenutoRaggruppatoPerPeso= true;
				}
				if(method.getName().equals("getSortedSetOrdinatoPerPeso")) {
					trovatoSortedSetOrdinatoPerPeso= true;
				}
			}
			if(trovatoGetContenutoOrdinatoPerPeso) {
				System.out.println("Ok! Esiste getContenutoOrdinatoPerPeso");
			}
			else { 
				System.out.println("Attenzione! Non esiste  il metodo getContenutoOrdinatoPerPeso");
			}
			if(trovatoGetContenutoOrdinatoPerNome) {
				System.out.println("Ok! Esiste getContenutoOrdinatoPerNome");
			}
			else { 
				System.out.println("Attenzione! Non esiste  il metodo getContenutoOrdinatoPerNome");
			}
			if(trovatoGetContenutoRaggruppatoPerPeso) {
				System.out.println("Ok! Esiste getContenutoRaggruppatoPerPeso");
			}
			else { 
				System.out.println("Attenzione! Non esiste  il metodo getContenutoRaggruppatoPerPeso");
			}
			if(trovatoSortedSetOrdinatoPerPeso) {
				System.out.println("Ok! Esiste getSortedSetOrdinatoPerPeso");
			}
			else { 
				System.out.println("Attenzione! Non esiste  il metodo getSortedSetOrdinatoPerPeso");
			}
			risultato= trovatoGetContenutoOrdinatoPerNome&&trovatoGetContenutoOrdinatoPerPeso&&trovatoGetContenutoRaggruppatoPerPeso&&trovatoSortedSetOrdinatoPerPeso;
		} catch (ClassNotFoundException e) {
			System.out.println("Attenzione! la classe Borsa non esiste, e' stata chiamata diversamente o e' nel package sbagliato.\n");
		}
		assertTrue(risultato);
	}
	
	@Test
	public void esisteLabirintoBuilder() {
		String nomeAtteso = "it.uniroma3.diadia.ambienti.Labirinto$LabirintoBuilder";
		try {
			@SuppressWarnings("unused")
			Class<?> classeDaControllare = Class.forName(nomeAtteso);
			System.out.println("OK! Esiste la classe LabirintoBuilder");
			risultato = true;
		} catch (ClassNotFoundException e) {
			System.out.println("Attenzione, la classe LabirintoBuilder non e' implementata, ha un nome diverso o e' nel paclage sbagliato!");
		}	
		assertTrue(risultato);
	}


	@Test
	public void testDiUnitaLabirintoBuilder() {
		Class<?> labBuilderClass = null;
		try {
			labBuilderClass=Class.forName("it.uniroma3.diadia.ambienti.Labirinto$LabirintoBuilder");
			labBuilderClass.getConstructor();
			//Utilizziamo questa mappa per facilitare l'accesso ai metodi nei test
			labBuilderClass.getMethod("addStanzaIniziale",String.class);
			labBuilderClass.getMethod("addStanzaVincente",String.class);
			labBuilderClass.getMethod("addStanza",String.class);
			labBuilderClass.getMethod("addStanzaMagica", String.class,int.class);
			labBuilderClass.getMethod("addStanzaBloccata", String.class, String.class, String.class);
			labBuilderClass.getMethod("addStanzaBuia", String.class, String.class);
			labBuilderClass.getMethod("addAdiacenza",String.class,String.class,String.class);
			labBuilderClass.getMethod("addAttrezzo",String.class,int.class);
			labBuilderClass.getMethod("getLabirinto");
			this.risultato=true;
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalArgumentException e) {
			e.printStackTrace();
			System.out.println("Test di unità per LabirintoBuilder non superati con successo.");
			System.out.println("Assicurarsi dell'esistenza della classe LabirintoBuilder e che i suoi metodi siano chiamati appropriatamente.");
		}
		assertTrue(risultato);
	}
	
	@Test
	public void esistenzaInterfaceIo(){
		String nomeAtteso = "it.uniroma3.diadia.IO";
		try {
			Class<?> classeIo= Class.forName(nomeAtteso);
			if(classeIo.isInterface()) {
				System.out.println("OK! Esiste l'interface IO.");
				classeIo.getMethod("leggiRiga");
				classeIo.getMethod("mostraMessaggio", String.class);
				risultato=true;
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Attenzione! L'interface IO non esiste, o è stata chiamata diversamente oppure è nel package sbagliato. Assicurarsi che IO sia una interface e non una classe.");
		} catch (NoSuchMethodException e) {
			System.out.println("Attenzione! Non esiste il metodo leggiRiga() e/o mostraMessaggio(String msg) nell'interface.");
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		assertTrue(risultato);
	}
	
	@Test
	public void esistenzaBuilderDiadia() {
		Class<?> classeDiaDia;
		try {
			classeDiaDia = Class.forName("it.uniroma3.diadia.DiaDia");
			for(Constructor<?> constructor : classeDiaDia.getDeclaredConstructors()) {
				if(constructor.getParameterCount()==1 && constructor.getParameterTypes()[0]== Class.forName("it.uniroma3.diadia.IO")) {
					this.risultato=true;
				}
			}
			if(this.risultato)
				System.out.println("OK! Esiste il costruttore di diadia richiesto.");
			else
				System.out.println("Attenzione! Non esiste il costruttore di DiaDia al quale si puo' passare l'IO.");
		} catch (ClassNotFoundException e) {
			System.out.println("Attenzione! Non e' stata individuata nessuna classe DiaDia. Assicurarsi di aver esportato\nil proprio DiaDia in formato jar e di averlo posizionato nella cartella esatta.");
		}
		assertTrue(this.risultato);
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
	
	
	//Da modificare per controllare l'esistenza di tutti i comandi??
//	@Test
//	public void esisteComando() {
//		String nomeAtteso = "it.uniroma3.diadia.comandi.AbstractComando";
//		try {
//			Class.forName(nomeAtteso);
//			System.out.println("OK! Esiste Comando ed e' nel package corretto.");
//			this.risultato=true;
//		} catch (ClassNotFoundException e) {
//			System.out.println("Attenzione! la classe Comando non esiste, e' stata chiamata diversamente o e' nel package sbagliato");
//		}
//		assertTrue(this.risultato);
//	}

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

