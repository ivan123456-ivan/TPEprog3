
import java.util.LinkedList;
import java.util.List;

import utils.CSVReader;
import utils.Procesador;
import utils.Tarea;

/**
 * NO modificar la interfaz de esta clase ni sus métodos públicos.
 * Sólo se podrá adaptar el nombre de la clase "Tarea" según sus decisiones
 * de implementación.
 */
public class Servicios {
	private LinkedList<Procesador> procesadores;
	private LinkedList<Tarea> tareas;

	/*
     * Expresar la complejidad temporal del constructor.
     */
	public Servicios(String pathProcesadores, String pathTareas)
	{
		CSVReader reader = new CSVReader();
		reader.readProcessors(pathProcesadores);
		reader.readTasks(pathTareas);
		this.procesadores= new LinkedList<>(reader.getProcesadores());
		this.tareas= new LinkedList<>(reader.getTareas());
	}
	
	/*
     * Expresar la complejidad temporal del servicio 1.
     */
	public Tarea servicio1(String ID) {	
		for (Tarea t : procesadores) {
			
		}
	}
    
    /*
     * Expresar la complejidad temporal del servicio 2.
     */
	public List<Tarea> servicio2(boolean esCritica) {

	}

    /*
     * Expresar la complejidad temporal del servicio 3.
     */
	public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {

	}

}
