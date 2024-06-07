import java.util.LinkedList;

import utils.Procesador;
import utils.Tarea;
	/*
	Primero, ningun procesador podra ejecutar 2 tareas criticas. 
	Segundo, los procesadores no refrigerados no podran dedicar mas de X tiempo de ejecución a 
	las tareas asignadas. El tiempo X sera un parametro establecido por el usuario al momento de 
	solicitar la asignacion de las tareas a los procesadores.
	*/
public class backtraking {
    private int estados;
    private LinkedList<Procesador> procesadorList;
    private LinkedList<Tarea>solucionList; 
    private LinkedList<Tarea> tareaList;




	public backtraking(int estados, LinkedList<Procesador> procesadorList, LinkedList<Tarea> solucionList,
            LinkedList<Tarea> tareaList) {
        this.estados = estados;
        this.procesadorList = procesadorList;
        this.solucionList = solucionList;
        this.tareaList = tareaList;
    }




    /* 
	 * decide si agragr una tarea dada a un porcesador dado
	 * y retorna el procesador en caso de agragar la tarea
	 */
	private boolean addTareaAProcesador(Tarea tarea,Procesador proc, int x ){
		if((!proc.isUltima_tarea_critica()) || (!tarea.isEs_critica())){
			if(proc.isEsta_refrigerado()){
				return true;
			}else{
				if(proc.getCarga_total()+tarea.getTiempo_ejecucion() < x){
					return true;
				}
			}
		}
		return false;
	}

}
