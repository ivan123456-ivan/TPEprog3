
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
	private List<Procesador> procesadoresList;//
	private HashMap<String, Tarea> tareasHash;//se usa para servicio 1
	private LinkedList<Tarea> listaTareas;// se usa para el servicio 3 y backtraking
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
		this.procesadoresList = new LinkedList<>();
		this.tareasCriticasListtrue = new LinkedList<>();
		this.tareasCriticasListFalse = new LinkedList<>();
		this.cargaraListaTarea(reader.getTareas());
		this.cargarProcesadoresList(reader.getProcesadores());
		this.cargarTareasHash(reader.getTareas());
		this.cargartareasLinkedtrueAndFalse(reader.getTareas());
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
	/*
	Primero, ningun procesador podra ejecutar 2 tareas criticas de forma consecutiva. 
	Segundo, los procesadores no refrigerados no podran dedicar mas de X tiempo de ejecución a 
	las tareas asignadas. El tiempo X sera un parametro establecido por el usuario al momento de 
	solicitar la asignacion de las tareas a los procesadores.
	*/
	/*
	 * ESTRATEGIA 
	 * deberiamos imaginar a cada procesador como un arbol
	 * que guarda tareas
	 * para cada rama va a ir una tarea, luego se le va a agregar otra tarea dentro de las tareas
	 * restantes, siempre tienendo en cuenta los parametros de ingreso preestablecidos
	 * (que no se agreguen dos criticas consecutivas,que a los no refrigerados no se les agrege 
	 * mas de x tiempo de procesamiento, )
	 */
	public List<Procesador> backtraking(int x){
		LinkedList <Procesador> porcAux = new LinkedList<>();
		porcAux.addAll(this.procesadoresList);//inicializo una lista procesadores para no trabajar con los originales
		LinkedList<Tarea> tareaAux= new LinkedList<>();
		tareaAux.addAll(this.listaTareas);//inicializo una lista de tareas para no trabajar con los originales
		
	}
	/* 
	 * decide si agragr una tarea dada a un porcesador dado
	 * y retorna el procesador en caso de agragar la tarea
	 */
	private Procesador addTareaAProcesador(Tarea tarea,Procesador proc, int x ){
		if((!proc.isUltima_tarea_critica()) || (!tarea.isEs_critica())){
			if(proc.isEsta_refrigerado()){
				proc.addTareas_cargadas(tarea);;
				return proc;
			}else{
				if(proc.getCarga_total()+tarea.getTiempo_ejecucion() < x){
					proc.addTareas_cargadas(tarea);
					return proc;
				}
			}
		}
		return null;
	}



}
