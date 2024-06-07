import java.util.ArrayList;
import java.util.LinkedList;

import utils.Procesador;
import utils.Tarea;
	/*
	Primero, ningun procesador podra ejecutar 2 tareas criticas. 
	Segundo, los procesadores no refrigerados no podran dedicar mas de X tiempo de ejecución a 
	las tareas asignadas. El tiempo X sera un parametro establecido por el usuario al momento de 
	solicitar la asignacion de las tareas a los procesadores.
	*/
public class Backtraking {
    private int MaxCriticos;
    private int estados;
    private LinkedList<Procesador> procesadorList;
    private LinkedList<Tarea>solucionList; 
    private LinkedList<Tarea> tareaList;




	public backtraking(int MaxCriticos,LinkedList<Procesador> procesadorList, LinkedList<Tarea> solucionList,
            LinkedList<Tarea> tareaList) {
        this.estados=0;
        this.MaxCriticos = MaxCriticos;
        this.procesadorList = procesadorList;
        this.solucionList = solucionList;
        this.tareaList = tareaList;
    }



    public boolean encontrarSolucion(int x){
        return this.backtraking(x,0);
    }

    private boolean backtraking(int x,int pos){
        if(this.tareaList.size()== pos){
            return true;
        }
        for(Procesador p : this.procesadorList ){
            this.backtraking(x, pos)
        }
    }   

    /* 
	 * decide si agragr una tarea dada a un porcesador dado
	 * y retorna el procesador en caso de agragar la tarea
	 */
	private boolean addTareaAProcesador(Tarea tarea,Procesador proc, int x ){

	}

}
