package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 * Classe che simula gli input da dare al gioco e raccoglie gli output restituiti da esso.
 * Utile per fare test. Implementa l'interfaccia IO.
 */

public class IOSimulator implements IO {
	
	private List<String> inputList;
	private Iterator<String> itInput;
	private List<String> outputList;
	
	/*
	 * Costruttore che crea un simulatore di IO
	 * con comando nullo.
	 */
	
	public IOSimulator() {
		this.inputList = new ArrayList<String>();
		//this.inputList.add("Giocatore Simulato");
		this.itInput = this.inputList.iterator();
		this.outputList = new ArrayList<String>();
	}
	
	/*
	 * Costruttore che crea un simulatore di IO che accetta come parametro una lista di
	 * input di tipo String
	 * @param listaInput Lista di input (List<String>)
	 */
	
	public IOSimulator(List<String> listaInput) {
		//this.inputList.add("Giocatore Simulato");
		this.inputList.addAll(listaInput);
		this.itInput = inputList.iterator();
		this.outputList = new ArrayList<String>();
	}
	
	public Iterator<String> getIterator(){
		return itInput;
	}
	
	public void setListaInput(List<String> listaInput) {
		this.inputList = listaInput;
//		System.out.println(this.inputList);
		this.itInput = inputList.iterator();
	}
	
	public void setListaInputSenzaNome(List<String> listaInput) {
		this.inputList = listaInput;
		this.itInput = inputList.iterator();
	}
	
	public List<String> getInputList(){
		return inputList;
	}
	
	public List<String> getOutputList(){
		return outputList;
	}
	
	/*
	 * Metodo polimorfo che aggiunge ad una lista l'output corrente generato dal gioco
	 */
	
	@Override
	public void mostraMessaggio(String msg) {
		this.outputList.add(msg);
	}
	
	/*
	 * Metodo che legge i comandi dalla lista di input e li fornisce al gioco.
	 * Se la lista di comandi � vuota o si � arrivati all fine di essa, viene
	 * messo in coda un ultimo comando "fine" in modo da fermare il gioco
	 */
	@Override
	public String leggiRiga() {
		if(itInput.hasNext()) 
			return itInput.next();
		return "fine";
		// Per motivi di precauzione l'ultimo input è sempre fine
	}
}