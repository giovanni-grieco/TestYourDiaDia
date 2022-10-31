package main;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class Starter {
	public static void main(String[] args) throws Throwable {
		System.out.println("Test preliminari in avvio...");
		JUnitCore c = new JUnitCore();
		Result risultati=c.run(Controllore.class);
		if(risultati.getFailureCount()==0){
			System.out.println("Test preliminari passati con successo!");
			System.out.println("Test di accettazione in avvio...");
			io.cucumber.core.cli.Main.main(new String[]{"-g", "classpath:","-m","-p","pretty"});
		}else{
			System.out.println("ATTENZIONE! uno o piu' dei controlli prima della consegna "
					+ "non e' andato a buon fine,\nsistemare gli errori prima di poter avviare "
					+ "i test di accettazione. Buon lavoro!");
		}
		System.out.println("Chiusura programma.");
	} 
}