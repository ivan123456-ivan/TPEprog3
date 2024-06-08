package utils;

import java.util.LinkedList;

public class Greedy {
    private LinkedList<Tarea> tareasList;
    private LinkedList<Procesador> procesadoresList;
    private int estados;

    public Greedy(LinkedList<Tarea> tareasList, LinkedList<Procesador> procesadoresList) {
        this.tareasList = tareasList;
        this.procesadoresList = procesadoresList;
        this.estados = 0;
    }

    public LinkedList<Tarea> getTareasList() {
        return tareasList;
    }
    public void setTareasList(LinkedList<Tarea> tareasList) {
        this.tareasList = tareasList;
    }
    public LinkedList<Procesador> getProcesadoresList() {
        return procesadoresList;
    }
    public void setProcesadoresList(LinkedList<Procesador> procesadoresList) {
        this.procesadoresList = procesadoresList;
    }
    public int getEstados() {
        return estados;
    }
    public void setEstados(int estados) {
        this.estados = estados;
    }

    public boolean getSolucion(int x){
        return this.greedy(x);
    }
    /* 
     * ESTRATEGIA
     * planteamos hacer una estrategia en la cual el algoritmo va aniadiendo tareas en 
     * el procesador con menos carga asi se intenta lograr el menor tiempo de ejecucion entre todos
     * los procesasdores
     */
    private boolean greedy(int x){
        int punteroTarea = 0;
        while (this.tareasList.size() < punteroTarea){
        this.procesadoresList.get(this.getProcesadorMejorOpcion(this.tareasList.get(punteroTarea),x)).addTareas_cargadas(this.tareasList.get(punteroTarea));
            punteroTarea ++;
        }
        return true;
    }
    
	/*
	Primero, ningun procesador podra ejecutar 2 tareas criticas. 
	Segundo, los procesadores no refrigerados no podran dedicar mas de X tiempo de ejecución a 
	las tareas asignadas. El tiempo X sera un parametro establecido por el usuario al momento de 
	solicitar la asignacion de las tareas a los procesadores.
	*/
	private boolean addTareaAProcesador(Tarea tarea,Procesador proc, int x ){
		if((!proc.isMaxTareasCriticas()) || (!tarea.isEs_critica())){
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

    private int getProcesadorMejorOpcion(Tarea t,int x){
        int menorTimepoEjecucucion= 0 ;
        int posMejorProcesador= -1;
        for (int i =0;i<procesadoresList.size(); i++) {
            if(menorTimepoEjecucucion > procesadoresList.get(i).getCarga_total()){
                if(addTareaAProcesador(t, procesadoresList.get(i),x)){
                    posMejorProcesador = i;
                    menorTimepoEjecucucion= this.procesadoresList.get(i).getCarga_total();
                }
            }  
        }
        return posMejorProcesador;
    }

}
