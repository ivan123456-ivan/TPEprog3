
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import utils.Back;
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
	private LinkedList<Tarea> listaTareas;// se usa para el servicio 3 
	private LinkedList<Tarea> tareasCriticasListtrue;//se usa para servicio 2
	private LinkedList<Tarea> tareasCriticasListFalse;//se usa para servicio 2
	private Back back;
	private Greedy greedy;

	
	/*
     * Expresar la complejidad temporal del constructor.
	 * la complejidad del constructos sera O(n)
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
		this.cargarTareasHash(this.listaTareas);
		this.cargartareasLinkedtrueAndFalse(this.listaTareas);
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
    
    /*
     * Expresar la complejidad temporal del servicio 2.
	 * la complejidad en la del metodo es O(1) ya que al tener ya los elementos precargados en tablas
	 * se retornan directamenten sin hacer ningun tipo de busqueda
     */
	public List<Tarea> servicio2(boolean esCritica) {
		if(esCritica){
			return new LinkedList<Tarea>(this.tareasCriticasListtrue);
		}
		return new ArrayList<Tarea>(this.tareasCriticasListFalse);
	}

    /*
     * Expresar la complejidad temporal del servicio 3.
	 * la complejidad es O(n) n siendo la cantidad de elemtos que tiene las lista 
	 * ya que se debe buscar en la totalidad de los elementos para sacar una respuesta concreta
	 * y solida
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

	/* 
		ESTRATEGIA Backtraking
	 * Se recorre una lista de tareas temporal donde cada posicion corresponde a la misma posicion de la lista tareas, el valor que guarda esta lista temporal (solucionList) 
	es la posicion de la lista procesadorList, cada tarea de la lista temporal es asignada en todos los procesadores que se permita de forma recursiva, donde hay un for que intenta
	asignar la tarea actual a cada procesador y a su vez llamar al mismo metodo con la tarea siguiente, donde va a hacer lo mismo, de esta forma recorreria la asignacion de tareas como:
	primera tarea hasta la ultima al procesador 1, luego la ultima a procesador 2,3,etc. luego vuelve a la anteultima tarea, donde pasa al procesador 2, luego llama a la ultima tarea y
	prueva todos los procesadores denuevo etc 
	 */
	/*public List<Procesador> servicio_backtraking(int maxC, int maxT) {
		this.back= new Backtraking(this.procesadoresList, this.listaTareas);
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
	}*/

	public void servicio_backtraking(int tiempoMaximoNoRefrigerado) {
		this.back = new Back(tiempoMaximoNoRefrigerado);
		this.back.backtracking(listaTareas, procesadoresList);
		this.back.imprimirSolucion();
	}


	/* 
	 * ESTRATEGIA Greedy 
	 * por cada tarea se accede a un metodo que va a buscar entre todos los procesadores 
	 * cual es el que tiene menos cargar y luego accede a otro metodo que confirna que 
	 * sea posible ingresar la tarea en ese procesador,
	 * luego la ingresa y sigrue el mismo proceso para las demas tareas 
	 */
	public boolean getSolucionGreedy(int x){
		this.greedy = new Greedy(this.listaTareas, this.procesadoresList);
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
