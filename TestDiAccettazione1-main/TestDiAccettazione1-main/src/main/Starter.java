package main;

public class Starter {

	public static void main(String[] args) throws Throwable {
        System.out.println("Test in avvio...");
        Controllore c = new Controllore();
		if(c.controlliPrimaDellaConsegna()){
			System.out.println("Test passati con successo!");
		}else{
		System.out.println("Attenzione! Uno o piu' dei controlli prima della consegna "
					+ "non e' andato a buon fine,\nsistemare gli errori prima di poter avviare "
					+ "i test di accettazione. Buon lavoro!");
		}
		System.out.println("Chiusura programma.");
	}
}
