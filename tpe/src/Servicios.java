
import java.util.HashMap;
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
	private HashMap<String, Procesador> procesadores;
	private HashMap<String, Tarea> tareas;

	/*
     * Expresar la complejidad temporal del constructor.
     */
	public Servicios(String pathProcesadores, String pathTareas)
	{
		CSVReader reader = new CSVReader();
		reader.readProcessors(pathProcesadores);
		reader.readTasks(pathTareas);
		this.cargarProcesadores(reader.getProcesadores());
		this.cargarTareas(reader.getTareas());
	}

	private void cargarProcesadores(List<Procesador> auxList){
		for (Procesador procesador : auxList) {
			this.procesadores.put(procesador.getId(), procesador);
		}
	}
	private void cargarTareas(List<Tarea> auxList){
		for (Tarea tarea : auxList) {
			this.tareas.put(tarea.getId(), tarea);
		}
	}

	
	/*
     * Expresar la complejidad temporal del servicio 1.
	 * al ser la estructura que usamos una hashmap la  complejidad del metodo es O(1)
     */
	public Tarea servicio1(String ID) {	
		return this.tareas.get(ID);
	}
    
    /*
     * Expresar la complejidad temporal del servicio 2.
	 * la complejidad del metodo es O(n) siendo n la cantidad de elementos en la HashMap
     */
	public List<Tarea> servicio2(boolean esCritica) {
		LinkedList<Tarea> auxList = new LinkedList<Tarea>();
		for (HashMap.Entry<String, Tarea> tarea : this.tareas.entrySet()) {
			if(tarea.getValue().isEs_critica() == esCritica){
				auxList.add(tarea.getValue());
			}
		}
		return auxList;
	}

    /*
     * Expresar la complejidad temporal del servicio 3.
	 * la complejidad del metodo es O(n) siendo n la cantidad de elementos en la HaskMap
     */
	public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
		LinkedList<Tarea> auxList = new LinkedList<Tarea>();
		for (HashMap.Entry<String, Tarea> tarea : this.tareas.entrySet()) {
			if((tarea.getValue().getPrioridad() >=prioridadInferior) && (tarea.getValue().getPrioridad()>= prioridadSuperior)){
				auxList.add(tarea.getValue());
			}
		}
		return auxList;
	}

}
