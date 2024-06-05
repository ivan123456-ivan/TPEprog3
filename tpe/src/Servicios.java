
import java.util.ArrayList;
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
	private HashMap<String, Procesador> procesadoresHash;//
	private HashMap<String, Tarea> tareasHash;//se usa para servicio 1
	private List<Tarea> listaTareas;
	private LinkedList<Tarea> tareasCriticasListtrue;//se usa para servicio 2
	private LinkedList<Tarea> tareasCriticasListFalse;//se usa para servicio 2

	
	/*
     * Expresar la complejidad temporal del constructor.
     */
	public Servicios(String pathProcesadores, String pathTareas)
	{
		CSVReader reader = new CSVReader();
		reader.readProcessors(pathProcesadores);
		reader.readTasks(pathTareas);
		this.tareasHash = new HashMap<>();
		this.procesadoresHash = new HashMap<>();
		this.tareasCriticasListtrue = new LinkedList<>();
		this.tareasCriticasListFalse = new LinkedList<>();
		this.listaTareas = reader.getTareas();
		this.cargarProcesadoresHash(reader.getProcesadores());
		this.cargarTareasHash(reader.getTareas());
		this.cargartareasLinkedtrueAndFalse(reader.getTareas());
	}

	private void cargarProcesadoresHash(List<Procesador> auxList){
		for (Procesador procesador : auxList) {
			this.procesadoresHash.put(procesador.getId(), procesador);
		}
	}
	private void cargarTareasHash(List<Tarea> auxList){
		for (Tarea tarea : auxList) {
			this.tareasHash.put(tarea.getId(), tarea);
		}
	}
	private void cargartareasLinkedtrueAndFalse(List<Tarea> auxList){
		for (Tarea tarea : auxList) {
			if(tarea.isEs_critica()){
				this.tareasCriticasListtrue.add(tarea);
			}else{
				this.tareasCriticasListFalse.add(tarea);
			}
		}
	}

	/*
     * Expresar la complejidad temporal del servicio 1.
	 * al ser la estructura que usamos una hashmap la  complejidad del metodo es O(1)
     */
	public Tarea servicio1(String ID) {	
		return this.tareasHash.get(ID);
	}
    
    /*TENEMOS QUE CREAS UNA ESTRUCTURA PARA CADA SERVICIO PARA BUSCAR LA EFICIENCIA MAXIMA
     * Expresar la complejidad temporal del servicio 2.
	 * 
     */
	public List<Tarea> servicio2(boolean esCritica) {
		if(esCritica){
			return new LinkedList<Tarea>(this.tareasCriticasListtrue);
		}
		return new ArrayList<Tarea>(this.tareasCriticasListFalse);
	}

    /*TENEMOS QUE CREAS UNA ESTRUCTURA PARA CADA SERVICIO PARA BUSCAR LA EFICIENCIA MAXIMA
     * Expresar la complejidad temporal del servicio 3.
	 * 
     */
	public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
		LinkedList<Tarea> aux = new LinkedList<Tarea>();
		for (Tarea tarea : listaTareas) {
			if (tarea.getPrioridad() > prioridadInferior && tarea.getPrioridad() < prioridadSuperior) {
				aux.add(tarea);
			}
		}
		return aux;
	}
}
