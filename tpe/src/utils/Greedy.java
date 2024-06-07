package utils;

import java.util.LinkedList;

	/*
	Primero, ningun procesador podra ejecutar 2 tareas criticas. 
	Segundo, los procesadores no refrigerados no podran dedicar mas de X tiempo de ejecución a 
	las tareas asignadas. El tiempo X sera un parametro establecido por el usuario al momento de 
	solicitar la asignacion de las tareas a los procesadores.
	*/
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

    public boolean getSolucion(){
        return this.greedy();
    }

    private boolean greedy(){
        int mejorTiempo = 0;
        
    }
}
