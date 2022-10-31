package it.uniroma3.diadia;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class RunnableDiaDia implements Runnable{

	private Object diadiaObj = null;
	private Class<?> classeDiaDia = null;
	private Class<?> classeIO = null;
	private Method metodoGioca = null;
	private Class<?> classeLabirinto = null;

	public RunnableDiaDia (IO io) {
		try {
			this.classeDiaDia = Class.forName("it.uniroma3.diadia.DiaDia");
			this.classeIO = Class.forName("it.uniroma3.diadia.IO");
			metodoGioca=classeDiaDia.getMethod("gioca");
			Constructor<?>[] listaCostruttori = classeDiaDia.getConstructors();
			for(Constructor<?> c:listaCostruttori) {
				if(c.getParameterCount()==1 && c.getParameterTypes()[0]==classeIO) {
					this.diadiaObj=c.newInstance(io);
				}
			}
			if(diadiaObj==null) {
				throw new InstantiationException();
			}
			
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			System.out.println("Qualcosa e' andato storto. Assicurarsi di avere un costruttore per la Classe DiaDia\nche riceva come parametro un oggetto di tipo IO.");
			System.out.println("Il programma ora si interrompera'.");
			System.exit(1);
		}
	}

	public RunnableDiaDia (IO io,Object labirinto) {
		try {
			this.classeDiaDia = Class.forName("it.uniroma3.diadia.DiaDia");
			this.classeIO = Class.forName("it.uniroma3.diadia.IO");
			this.classeLabirinto = Class.forName("it.uniroma3.diadia.ambienti.Labirinto");
			metodoGioca=classeDiaDia.getMethod("gioca");
			Constructor<?>[] listaCostruttori = classeDiaDia.getConstructors();
			for(Constructor<?> c:listaCostruttori) {
				if(c.getParameterCount()==2 && c.getParameterTypes()[0]==classeIO && c.getParameterTypes()[1]==classeLabirinto) {
					this.diadiaObj=c.newInstance(io,labirinto);
				}
			}
			if(diadiaObj==null) {
				throw new InstantiationException();
			}
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			System.out.println("Qualcosa e' andato storto. Assicurarsi di avere un costruttore di DiaDia che accetti come parametri\nun oggetto di tipo IO e uno di tipo Labirinto");
			System.out.println("Il programma ora si interrompera'.");
			System.exit(1);
		}
	}

	@Override
	public void run() {
		try {
			metodoGioca.invoke(diadiaObj);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			System.out.println("Qualcosa e' andato storto.\nIl metodo gioca non esiste o prende parametri fuori dalle specifiche.");
			System.out.println("Il programma ora si interrompera'.");
			System.exit(1);
		}
	}
}