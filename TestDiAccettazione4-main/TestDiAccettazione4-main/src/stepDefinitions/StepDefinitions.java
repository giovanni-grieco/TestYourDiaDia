package stepDefinitions;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import it.uniroma3.diadia.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

/**
 * Classe adibita alla definizione degli step definiti nel feature file "primeStampe.feature"
 * per l'API di BDD Cucumber. Questa classe raccoglie tutti gli step che si occupano di fare
 * i primi controlli a partire dal secondo homework sulle stampe.
 * @author Giovanni Pio Grieco & Lorenzo Battisti
 */

public class StepDefinitions {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	static final private String MESSAGGIO_VITTORIA ="Hai vinto!";
	static final private String MESSAGGIO_ARRIVEDERCI = "Grazie di aver giocato!";
	private String stringaDiBenvenuto;
	private IOSimulator ioSim;
	private List<String> listaInput;
	private Object labBuilderObj=null;
	private Class<?> labBuilderClass = null;
	Constructor<?> costruttoreLabBuilder = null;
	private Method addStanzaIniziale;
	private Method addStanzaVincente;
	private Method addStanza;
	private Method addStanzaMagica;
	private Method addStanzaBloccata;
	private Method addStanzaBuia;
	private Method addAdiacenza;
	private Method addAttrezzo;
	private Method addMago;
	private Method addStrega;
	private Method addCane;
	private Method getLabirinto;
	private Thread threadDiGioco;

	public StepDefinitions() {
		this.listaInput = new LinkedList<>();
		this.stringaDiBenvenuto = null;
		this.ioSim = new IOSimulator();
		this.threadDiGioco = new Thread(new RunnableDiaDia(ioSim), "Thread di Gioco");
		try {
			this.labBuilderClass=Class.forName("it.uniroma3.diadia.ambienti.Labirinto$LabirintoBuilder");
			this.costruttoreLabBuilder = labBuilderClass.getConstructor();
			this.labBuilderObj=costruttoreLabBuilder.newInstance();
			this.addStanzaIniziale = labBuilderClass.getMethod("addStanzaIniziale",String.class);
			this.addStanzaVincente = labBuilderClass.getMethod("addStanzaVincente",String.class);
			this.addStanza = labBuilderClass.getMethod("addStanza",String.class);
			this.addStanzaMagica = labBuilderClass.getMethod("addStanzaMagica", String.class,int.class);
			this.addStanzaBloccata = labBuilderClass.getMethod("addStanzaBloccata", String.class, String.class, String.class);
			this.addStanzaBuia = labBuilderClass.getMethod("addStanzaBuia", String.class, String.class);
			this.addAdiacenza = labBuilderClass.getMethod("addAdiacenza",String.class,String.class,String.class);
			this.addAttrezzo = labBuilderClass.getMethod("addAttrezzo",String.class,int.class);
			this.addMago = labBuilderClass.getMethod("addMago", String.class, String.class, String.class, int.class);
			this.addStrega = labBuilderClass.getMethod("addStrega", String.class, String.class);
			this.addCane = labBuilderClass.getMethod("addCane", String.class, String.class, String.class, int.class, String.class);
			this.getLabirinto = labBuilderClass.getMethod("getLabirinto");
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			System.out.println(corniciatore("ERRORE! Non e' possibile proseguire con i test\n"
					+ "Possibile causa:\n"
					+ "Labirinto builder non esiste come classe.\n"
					+ "La classe labirintoBuilder non ha un costruttore 'No Args'.\n"
					+ "La classe LabirintoBuilder non e' annidata all'interno di Labirinto.\n"
					+ "Almeno un metodo richiesto non esiste per la classe LabirintoBuilder.\n"
					+ "Almeno un metodo ha parametri diversi da quelli specificati.\n"));
			System.exit(1);
		}
	}

	private String corniciatore (String msg) {
		StringBuilder stringa  = new StringBuilder("\n================================================\n");
		stringa.append(msg);
		stringa.append("\n================================================\n");
		return stringa.toString();
	}

	@Given("il gioco viene avviato")
	public void il_gioco_viene_avviato() {
		ioSim.setListaInput(listaInput);
		threadDiGioco.start();
		try {
			threadDiGioco.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Given("^avviato$")
	public void avviato() {
		System.out.println("Funziona");
	}

	@Then("viene mostrato il messaggio di benvenuto")
	public void viene_mostrato_il_messaggio_di_benvenuto() {
		try {
			stringaDiBenvenuto = ioSim.getOutputList().get(0);
			for(String stringa:ioSim.getOutputList()) {
				if(stringa.contains(MESSAGGIO_BENVENUTO)) {
					//esiste la stringa di benvenuto all'interno dell stampe di gioco
					assertTrue(true);
					return;
				}
			}
			System.out.println(
					corniciatore("ERRORE! non e' stato rilevato il messaggio di benvenuto\n"
							+"Possibili cause:\n"
							+"-Il messaggio di benvenuto e' stato cancellato/modificato rispetto a quello inizialmente distribuito\n"
							+"-Il messaggio di benvenuto non e' il primo messaggio stampato"));
			assertTrue(false);
		}catch (IndexOutOfBoundsException e) {
			System.out.println(corniciatore("\nERRORE! sembra che il programma non stampi alcun messaggio\n"
					+"Assicurarsi di aver rimpiazzato tutti i System.out.println con la funzione IO.mostraMessaggio(String msg)\n"
					+"Assicurarsi che sia presente il metodo sopracitato quando ci si aspetta una stampa"));
			//Nessuna stampa viene mai eseguita (ne di benvenuto ne di nessun altro tipo)
			assertTrue(false);
		}
		assertNotNull(stringaDiBenvenuto);
		//Come posso verificare che effetivamente nello STDOUT appaia la stringa?
	}

	@Then("hai vinto la partita")
	public void hai_vinto_la_partita() {
		//Dovrebbe contenere la stringa di benvenuto e quella di arrivederci
		if(ioSim.getOutputList().get(ioSim.getOutputList().size()-1).contains(MESSAGGIO_VITTORIA)) {
			//esiste la stringa di benvenuto all'interno dell stampe di gioco
			assertTrue(true);
			return;
		}
		System.out.println(corniciatore("ERRORE! La partita non va a buon fine."));
		assertTrue(false);
	}

	@Then("il gioco stampa un messaggio di arrivederci")
	public void il_gioco_stampa_un_messaggio_di_arrivederci() {
		//Dovrebbe contenere la stringa di benvenuto e quella di arrivederci
		if(ioSim.getOutputList().get(ioSim.getOutputList().size()-1).contains(MESSAGGIO_ARRIVEDERCI)) {
			//esiste la stringa di benvenuto all'interno dell stampe di gioco
			assertTrue(true);
			return;
		}
		System.out.println(corniciatore("ERRORE! Non e' stato rilevato il messaggio di arrivederci.\nLe possibili cause possono essere:\n"
				+ "-Non viene stampato un messaggio di arrivederci\n"
				+ "-Il messaggio viene stampato ma esso non e' l'ultimo\n"
				+ "-Potrebbe essere stato modificato il messaggio di arrivederci\n"
				+ "-Assicurarsi che dopo aver digitato il comando fine venga stampato un messaggio di arrivederci contenente la stringa di default."));
		assertTrue(false);
	}

	@Then("il gioco si chiude")
	public void il_gioco_si_chiude() {
		if(!threadDiGioco.isAlive()) {
			assertTrue(true);
		}else {
			System.out.println(corniciatore("ERRORE! Il programma non si chiude quando aspettato\n"
					+"possibili cause:\n"
					+ "il programma non si chiude quando viene utilizzato il comando 'fine'\n"
					+ "Il programma non si chiude quando il giocatore muore\n"
					+ "Il programma non si chiude quando il giocatore vince"));
			assertFalse(true);
		}
	}

	@Given("inserisco il comando {string} in coda")
	public void inserisco_il_comando_in_coda(String string) {
		if(!string.equals("fine"))
			listaInput.add(string);
		//se viene digitato fine non c'è bisogno di aggiungerlo
	}

	@Then("verifico lo spostamento da {string} a {string} verso {string}")
	public void verifico_lo_spostamento_da_a(String origine, String destinazione, String direzione) {
		//Verifico che nelle stampe del comando guarda sia presenta la stanza d'origine
		//Iterator<String> itOutput = ioSim.getOutputList().iterator();
		boolean trovataOrigine = false;
		boolean trovataDestinazione = false;
		for(String stampa:ioSim.getOutputList()) {
			if(stampa.contains(origine)) {
				trovataOrigine=true;
			}else {
				if(stampa.contains(destinazione)) {
					trovataDestinazione=true;
				}
			}
		}
		if(!trovataOrigine) {
			System.out.println(corniciatore("ERRORE! La stanza "+origine+" non e' stata trovata.\n"
					+ "Possibili cause:\n"
					+ "La stanza "+origine+" non esiste oppure il suo nome e' stato modificato\n"
					+ "Il comando guarda non stampa il nome della stanza\n"
					+ "La stanza "+origine+" non e' la stanza di partenza del labirinto"));
		}
		if(!trovataDestinazione) {
			System.out.println(corniciatore("ERRORE! La stanza "+destinazione+" non e' stata trovata.\n"
					+ "Possibili cause:\n"
					+ "Il comando 'vai' non funziona correttamente\n"
					+ "La stanza "+destinazione+" non esiste oppure il suo nome e' stato modificato\n"
					+ "Il comando guarda non stampa il nome della stanza\n"
					+ "La stanza "+destinazione+" non e' a "+direzione+" di "+origine));
		}
		assertTrue(trovataOrigine);
		assertTrue(trovataDestinazione);
	}

	@Then("controllo che {string} viene preso e posato")
	public void controllo_che_l_oggetto_viene_preso_e_posato(String nomeAttrezzo) {
		int i=0;
		boolean condizione=false;
		while(i<ioSim.getOutputList().size()&&!condizione) {
			String stringa=ioSim.getOutputList().get(i);
			if(stringa.contains(nomeAttrezzo)) {
				condizione=true;
			}
			i++;
		}

		if(!condizione) {
			System.out.println(corniciatore("ERRORE! L'attrezzo "+nomeAttrezzo+" non e' stato rilevato\n"
					+ "Possibili cause:\n"
					+ "-Il comando guarda non stampa il nome dell'attrezzo\n"
					+ "-L'attrezzo non e' presente nella stanza"));
		}

		assertTrue(condizione); //trovato l'attrezzo nella stanza
		condizione=false;
		while(i<ioSim.getOutputList().size()&&!condizione) {
			String stringa=ioSim.getOutputList().get(i);
			if(stringa.contains("nessun attrezzo")) {
				condizione=true;
			}
			i++;
		}
		boolean comandoPrendiFunzionante = condizione;
		if(!condizione) {
			System.out.println(corniciatore("ERRORE! Non e' stato possibile verificare il funzionamento del comando prendi\n"
					+ "Possibili cause:\n"
					+ "-La stanza contiene altri attrezzi oltre a "+nomeAttrezzo+"\n"
					+ "-Il comando guarda non stampa 'nessun attrezzo' quando la stanza e' vuota di attrezzi\n"
					+ "-Il comando prendi non funziona correttamente"));
		}
		assertTrue(condizione); //Nessun attrezzo nella stanza perchè è stato preso

		condizione=false;
		while(i<ioSim.getOutputList().size()&&!condizione) {
			String stringa=ioSim.getOutputList().get(i);
			if(stringa.contains(nomeAttrezzo)) {
				condizione=true;
			}
			i++;
		}
		if(comandoPrendiFunzionante) {
			if(!condizione) {
				System.out.println(corniciatore("ERRORE! L'attrezzo "+nomeAttrezzo+" non e' stato rilevato una volta posato\n"
						+ "Possibili cause:\n"
						+ "-Il comando guarda non stampa il nome dell'attrezzo\n"
						+ "-Il comando posa non funziona correttamente"));
			}
		}
		assertTrue(condizione); //L'attrezzo è di nuovo presente nella stanza
	}

	@Then("viene mostrato il messaggio di morte")
	public void viene_mostrato_il_messaggio_di_morte() {
		boolean trovataParolaChiave = false;
		for(String stringa:ioSim.getOutputList()) {
			if(stringa.contains("morto")) {
				//esiste la stringa di morte all'interno dell stampe di gioco
				trovataParolaChiave=true;
			}
		}
		if(!trovataParolaChiave) {
			System.out.println(corniciatore("ERRORE! Non e' stato rilevato il messaggio di morte\n"
					+ "Possibili cause:\n"
					+ "-Non viene effettuata la stampa annunciante la morte\n"
					+ "-La stampa di morte e' stata modificata\n"
					+ "-Il personaggio non muore"));
		}
		assertTrue(trovataParolaChiave);
	}

	@Given("carico un bilocale composto da {string} collegata a {string} alla {string}")
	public void carico_un_bilocale_composto_da_collegata_a_alla(String stanzaOrigine, String direzione, String stanzaDestinazione){

		try {
			addStanzaIniziale.invoke(labBuilderObj,stanzaOrigine);
			addStanzaVincente.invoke(labBuilderObj,stanzaDestinazione);
			addAdiacenza.invoke(labBuilderObj, stanzaOrigine, stanzaDestinazione, direzione);
			this.threadDiGioco = new Thread(new RunnableDiaDia(ioSim,getLabirinto.invoke(labBuilderObj)));
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			System.out.println(corniciatore("ERRORE! Non e' possibile costruire un labirinto\n"
					+ "Possibile causa:\n"
					+ "Labirinto builder non esiste come classe."));
		}
	}

	@Given("carico un labirinto completo per testare una partita HW4")
	public void carico_un_labirinto_completo_per_testare_una_partita_hw4() {
		try {
			addStanzaIniziale.invoke(labBuilderObj,"stanza iniziale");
			addAttrezzo.invoke(labBuilderObj, "torcia", 2);
			addStanza.invoke(labBuilderObj, "stanza con mago");
			addMago.invoke(labBuilderObj, "mago", "ciao", "ottocsib", 1);
			addAdiacenza.invoke( labBuilderObj,"stanza iniziale", "stanza con mago", "est");
			addStanzaMagica.invoke(labBuilderObj, "stanza magica", 2);
			addAdiacenza.invoke( labBuilderObj, "stanza con mago","stanza magica", "est");
			addStanza.invoke(labBuilderObj, "stanza con cane");
			addCane.invoke(labBuilderObj,"cane","bau","chiave", 2,"biscotto");
			addAdiacenza.invoke( labBuilderObj,"stanza magica","stanza con cane", "est");
			addStanzaBuia.invoke(labBuilderObj, "stanza buia", "torcia");
			addAdiacenza.invoke( labBuilderObj,"stanza con cane", "stanza buia", "est");
			addStanza.invoke(labBuilderObj, "stanza con strega");
			addStrega.invoke(labBuilderObj, "strega", "salve");
			addAdiacenza.invoke( labBuilderObj,"stanza buia","stanza con strega", "est");
			addStanzaBloccata.invoke(labBuilderObj, "stanza bloccata", "est", "chiave");
			addAdiacenza.invoke( labBuilderObj,"stanza con strega", "stanza bloccata", "est");
			addStanzaVincente.invoke(labBuilderObj,"stanza vincente");
			addAdiacenza.invoke( labBuilderObj,"stanza bloccata", "stanza vincente", "est");
			this.threadDiGioco = new Thread(new RunnableDiaDia(ioSim,getLabirinto.invoke(labBuilderObj)));
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException e) {
			e.printStackTrace();
			System.out.println(corniciatore("ERRORE! Non e' possibile costruire un labirinto\n"
					+ "Possibile causa:\n"
					+ "Labirinto builder non esiste come classe."));
		}
	}

	@Given("un monolocale con personaggio {string} e con un attrezzo {string} di peso {string}")
	public void un_monolocale_con_personaggio_e_con_un_attrezzo_di_peso(String tipoPersonaggio, String nomeAttrezzo, String pesoAttrezzo) {
		try {
			addStanzaIniziale.invoke(labBuilderObj,"stanza con mago");
			if(tipoPersonaggio.equals("Mago"))
				addMago.invoke(labBuilderObj, "mago","ciao","attrezzoDaRegalare",1);
			addAttrezzo.invoke(labBuilderObj, nomeAttrezzo, Integer.parseInt(pesoAttrezzo));

			this.threadDiGioco = new Thread(new RunnableDiaDia(ioSim,getLabirinto.invoke(labBuilderObj)));
		} catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			System.out.println(corniciatore("ERRORE! Non e' possibile costruire un labirinto\n"
					+ "Possibile causa:\n"
					+ "Labirinto builder non esiste come classe.\n"
					+ "La classe labirinto non ha un costruttore 'No Args'."));
		}
	}

	@Given("un monolocale con personaggio {string} che posi nella stanza {string} di peso {string} dopo averci interagito")
	public void un_monolocale_con_personaggio_che_posi_nella_stanza_di_peso_dopo_averci_interagito(String tipoPersonaggio, String nomeAttrezzo, String pesoAttrezzo) {
		try {
			addStanzaIniziale.invoke(labBuilderObj,"stanza con mago");
			if(tipoPersonaggio.equals("Mago"))
				addMago.invoke(labBuilderObj, "mago","ciao",nomeAttrezzo, Integer.parseInt(pesoAttrezzo));

			this.threadDiGioco = new Thread(new RunnableDiaDia(ioSim,getLabirinto.invoke(labBuilderObj)));
		} catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			System.out.println(corniciatore("ERRORE! Non e' possibile costruire un labirinto\n"
					+ "Possibile causa:\n"
					+ "Labirinto builder non esiste come classe.\n"
					+ "La classe labirinto non ha un costruttore 'No Args'."));
		}
	}

	@Given("un trilocale con personaggio Strega con stanze adiacenti con numeri diversi di attrezzi")
	public void un_trilocale_con_personaggio_Strega_con_stanze_adiacenti_con_numeri_diversi_di_attrezzi() {
		try {
			addStanzaIniziale.invoke(labBuilderObj,"stanza con strega");
			addStrega.invoke(labBuilderObj, "strega","ciao");
			addStanza.invoke(labBuilderObj,"zero attrezzi");
			addStanza.invoke(labBuilderObj, "un attrezzo");
			addAttrezzo.invoke(labBuilderObj, "attrezzo",1);
			addAdiacenza.invoke(labBuilderObj, "stanza con strega","zero attrezzi","nord");
			addAdiacenza.invoke(labBuilderObj, "stanza con strega","un attrezzo","est");

			this.threadDiGioco = new Thread(new RunnableDiaDia(ioSim,getLabirinto.invoke(labBuilderObj)));
		} catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			System.out.println(corniciatore("ERRORE! Non e' possibile costruire un labirinto\n"
					+ "Possibile causa:\n"
					+ "Labirinto builder non esiste come classe.\n"
					+ "La classe labirinto non ha un costruttore 'No Args'."));
		}
	}

	@Given("un monolocale con personaggio {string}")
	public void un_monolocale_con_personaggio(String tipoPersonaggio) {
		try {
			if(tipoPersonaggio.equals("Cane")) {
				addStanzaIniziale.invoke(labBuilderObj,"Stanza con cane");
				addCane.invoke(labBuilderObj,"cane","bau","chiave", 2,"biscotto");
			}
			this.threadDiGioco = new Thread(new RunnableDiaDia(ioSim,getLabirinto.invoke(labBuilderObj)));
		} catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			System.out.println(corniciatore("ERRORE! Non e' possibile costruire un labirinto\n"
					+ "Possibile causa:\n"
					+ "Labirinto builder non esiste come classe.\n"
					+ "La classe labirinto non ha un costruttore 'No Args'."));
		}
	}

	@Then("verifico che il personaggio {string} posi nella stanza {string} di peso {string}")
	public void verifico_che_il_personaggio_posi_nella_stanza_di_peso(String tipoPersonaggio, String nomeAttrezzo, String pesoAttrezzo) {
		boolean trovataSpadaRegalato=false;
		List<String> outputList = this.ioSim.getOutputList();
		int i=0;
		while(!trovataSpadaRegalato&&i<outputList.size()) {
			trovataSpadaRegalato=outputList.get(i).contains(nomeAttrezzo)&&outputList.get(i).contains(pesoAttrezzo);
			i++;
		}
		assertTrue(trovataSpadaRegalato);
	}

	@Given("inserisco il comando {string} in coda per {string} volte")
	public void inserisco_il_comando_in_coda_per_volte(String comando, String volte) {
		int volteint = Integer.parseInt(volte);
		for(int i=0;i<volteint;i++) {
			this.listaInput.add(comando);
		}
	}

	@Given("un monolocale con personaggio {string} a cui piace il {string} e in cambio da una {string} di peso {string}")
	public void un_monolocale_con_personaggio_a_cui_piace_il_e_in_cambio_da_una_di_peso(String tipoPersonaggio, String attrezzoPiaciuto, String nomeAttrezzoRegalato, String pesoAttrezzoRegalato) {
		try {
			addStanzaIniziale.invoke(labBuilderObj,"Stanza con cane");
			addAttrezzo.invoke(labBuilderObj, "biscotto",1);
			addCane.invoke(labBuilderObj,"cane","bau",nomeAttrezzoRegalato, Integer.parseInt(pesoAttrezzoRegalato),"biscotto");

			this.threadDiGioco = new Thread(new RunnableDiaDia(ioSim,getLabirinto.invoke(labBuilderObj)));
		} catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			System.out.println(corniciatore("ERRORE! Non e' possibile costruire un labirinto\n"
					+ "Possibile causa:\n"
					+ "Labirinto builder non esiste come classe.\n"
					+ "La classe labirinto non ha un costruttore 'No Args'."));
		}
	}
}