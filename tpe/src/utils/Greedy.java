package utils;

import java.util.LinkedList;

public class Greedy {
    private LinkedList<Tarea> tareasList;
    private LinkedList<Procesador> procesadoresList;
    private int estados;
    private int MaxtiempoConseguido;

    public Greedy(LinkedList<Tarea> tareasList, LinkedList<Procesador> procesadoresList) {
        this.tareasList = tareasList;
        this.procesadoresList = procesadoresList;
        this.estados = 0;
        this.MaxtiempoConseguido = -1;
    }

    public int getMaxtiempoConseguido() {
        return MaxtiempoConseguido;
    }

    private void setMaxtiempoConseguido() {
        for (Procesador procesador : procesadoresList) {
            if (procesador.getTiempoTotal() > this.MaxtiempoConseguido) {
                this.MaxtiempoConseguido = procesador.getTiempoTotal();
            }
        }
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

    public boolean getSolucion(int x) {
        return this.greedy(x);
    }

    /*
     * ESTRATEGIA
     * planteamos hacer una estrategia en la cual el algoritmo va añadiendo tareas
     * en
     * el procesador con menos carga asi se intenta lograr el menor tiempo de
     * ejecucion entre todos
     * los procesasdores
     */
    private boolean greedy(int x) {
        int punteroTarea = 0;
        while (this.tareasList.size() > punteroTarea) {
            int puenteroProcesador = this.getProcesadorMejorOpcion(this.tareasList.get(punteroTarea), x);
            if (puenteroProcesador != -1) {
                this.procesadoresList.get(puenteroProcesador).addTareas_cargadas(this.tareasList.get(punteroTarea));
            } else {
                this.estados = 0;
                this.MaxtiempoConseguido = -1;
                return false;
            }
            punteroTarea++;
        }
        this.estados = punteroTarea;
        this.setMaxtiempoConseguido();
        return true;
    }

    /*
     * el metodo ve en todos los porcesadores cual es la mejor opcion para agregar
     * la tarea
     * y al encontrarlo le pregunta a addTareaAProcesador si se puede agragar ahi
     * y retorna la posicion el procesador elejido
     */
    private int getProcesadorMejorOpcion(Tarea t, int x) {
        int posMejorProcesador = -1;
        int MenorCargaProcesador = 0;
        for (int i = 0; i < this.procesadoresList.size(); i++) {
            if (MenorCargaProcesador >= procesadoresList.get(i).getTiempoTotal()) {
                if (this.isTareaAProcesador(t, procesadoresList.get(i), x)) {
                    posMejorProcesador = i;
                    MenorCargaProcesador = this.procesadoresList.get(i).getTiempoTotal();

                }

            }

        }
        return posMejorProcesador;
    }

    /*
     * Primero, ningun procesador podra ejecutar 2 tareas criticas.
     * Segundo, los procesadores no refrigerados no podran dedicar mas de X tiempo
     * de ejecución a
     * las tareas asignadas. El tiempo X sera un parametro establecido por el
     * usuario al momento de
     * solicitar la asignacion de las tareas a los procesadores.
     */
    private boolean isTareaAProcesador(Tarea tarea, Procesador proc, int x) {

        if (tarea.isEs_critica() && proc.isMaxTareasCriticas()) {
            return false;
        } else {

            if (proc.isEsta_refrigerado()) {
                return true;
            } else {

                if ((proc.getTiempoTotal() + tarea.getTiempo_ejecucion()) > x) {
                    return false;
                } else {
                    return true;
                }

            }

        }

    }

}