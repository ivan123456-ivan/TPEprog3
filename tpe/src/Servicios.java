
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import utils.Backtraking;
import utils.CSVReader;
import utils.Greedy;
import utils.Procesador;
import utils.Tarea;

/**
 * NO modificar la interfaz de esta clase ni sus métodos públicos.
 * Sólo se podrá adaptar el nombre de la clase "Tarea" según sus decisiones
 * de implementación.
 */
public class Servicios {
	private LinkedList<Procesador> procesadoresList;//
	private HashMap<String, Tarea> tareasHash;//se usa para servicio 1
	private LinkedList<Tarea> listaTareas;// se usa para el servicio 3 y backtraking
	private LinkedList<Tarea> tareasCriticasListtrue;//se usa para servicio 2
	private LinkedList<Tarea> tareasCriticasListFalse;//se usa para servicio 2
	private Backtraking back;//= new Backtraking(this.procesadoresList, this.listaTareas);
	private Greedy greedy;

	
	/*
     * Expresar la complejidad temporal del constructor.
     */
	public Servicios(String pathProcesadores, String pathTareas)
	{
		CSVReader reader = new CSVReader();
		reader.readProcessors(pathProcesadores);
		reader.readTasks(pathTareas);
		this.tareasHash = new HashMap<>();
		this.procesadoresList = new LinkedList<>();
		this.tareasCriticasListtrue = new LinkedList<>();
		this.tareasCriticasListFalse = new LinkedList<>();
		this.listaTareas= new LinkedList<>();
		this.cargaraListaTarea(reader.getTareas());
		this.cargarProcesadoresList(reader.getProcesadores());
		this.cargarTareasHash(reader.getTareas());
		this.cargartareasLinkedtrueAndFalse(reader.getTareas());
		this.greedy = new Greedy(this.listaTareas, this.procesadoresList);
	}
	private void cargaraListaTarea(List<Tarea> aux){
		for (Tarea tarea : aux) {
			this.listaTareas.add(tarea);
		}
	}

	private void cargarProcesadoresList(List<Procesador> auxList){
		this.procesadoresList.addAll(auxList);
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

	public List<Procesador> getProcesadores() {
		return new LinkedList<Procesador>(this.procesadoresList);
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

	public List<Procesador> servicio_backtraking(int maxC, int maxT) {
		this.back= new Backtraking(this.procesadoresList, this.listaTareas);
		//back.foundSolution(maxC, maxT);
		back.doSolution(maxC, maxT);
		return new LinkedList<>(this.procesadoresList);
	}

	public Integer getEstadosBacktraking() {
		if (back!= null) {
			return back.getEstados();
		}
		return 0;
	}

	public Integer getMinTiempoBacktraking(){
		if (back!= null) {
			return back.getMinTiempo();
		}
		return 0;
	}

	public boolean getSolucionGreedy(int x){
		return this.greedy.getSolucion(x);

	}

	public int getEstadosGreedy(){
		return this.greedy.getEstados();
	}

	public int getMaxtiempoConseguido(){
		return this.greedy.getMaxtiempoConseguido();
	}
	
	public void getSolucionList(){
		LinkedList<Procesador> aux = this.greedy.getProcesadoresList();
		for (Procesador procesador : aux) {
			System.out.println(procesador);
		}
	}
}
