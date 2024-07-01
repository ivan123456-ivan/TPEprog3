package utils;

import java.util.LinkedList;

public class Procesador {
    private String codigo;
    private String id;
    private boolean esta_refrigerado;
    private int anio_funcionamiento;
    /* cada nodo agregado a la list seria un estado solucion posible dentro del problema */
    private LinkedList<Tarea> tareas_cargadas;//1
    private int tiempoTotal;
    private int tareasCriticas;
    /*
     * agregamos las variables 1,2,3 para poder cargar tareas a los procesadores 
     * llevar la cuenta de la carga con la variable 2
     * y saber si vamos a agregar dos tareas criticas seguidas
    */
    public Procesador(String codigo, String id, boolean esta_refrigerado, int anio_funcionamiento) {
        this.codigo = codigo;
        this.id = id;
        this.esta_refrigerado = esta_refrigerado;
        this.anio_funcionamiento = anio_funcionamiento;
        this.tareas_cargadas= new LinkedList<>();
        this.tiempoTotal = 0;
        this.tareasCriticas = 0;
    }

    public LinkedList<Tarea> getTareas_cargadas() {
        return new LinkedList<>(tareas_cargadas);
    }
    /*
     * al cargar una tarea lo que hacemos es recalcular todos los valores correspondientes 
     * para asi no tener que recalcularlos cada vez que agragmos una tarea
    */
    public void addTareas_cargadas(Tarea tarea) {
        this.tareas_cargadas.add(tarea);
        this.tiempoTotal += tarea.getTiempo_ejecucion();
        if (tarea.isEs_critica()) {
            this.tareasCriticas++;
        }
        
    }
    
    public boolean puedeAgregarTarea(Tarea tarea, int tiempoMaximoNoRefrigerado) {
        if ((tarea.isEs_critica()) && (tareasCriticas >= 2)){
            return false;
        } 
        if (!this.esta_refrigerado) {
            if((tiempoTotal + tarea.getTiempo_ejecucion()) > tiempoMaximoNoRefrigerado){
            return false;  
            }
        }
        return true;
    }

    public void removeTarea(Tarea t) {
        this.tareas_cargadas.remove(t);
        this.tiempoTotal -= t.getTiempo_ejecucion();
        if (t.isEs_critica()) {
            this.tareasCriticas--;
        }
    }

    public int getTiempoTotal() {
        return this.tiempoTotal;
    }

    public void setTiempoTotal(int cargar) {
        this.tiempoTotal = this.tiempoTotal + cargar;
    }

    /*public boolean isMaxTareasCriticas() {
        return MaxTareasCriticas;
    }

    public void setMaxTareasCriticas() {
        int cont = 0;
        for (Tarea tarea : tareas_cargadas) {
            if(cont == this.MaxCriticas){
                this.MaxTareasCriticas = true;
            }else{
                this.MaxTareasCriticas = false;
            }
            if(tarea.isEs_critica()){
                cont ++;
            }
        }
    }*/


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isEsta_refrigerado() {
        return esta_refrigerado;
    }

    public void setEsta_refrigerado(boolean esta_refrigerado) {
        this.esta_refrigerado = esta_refrigerado;
    }

    public int getAnio_funcionamiento() {
        return anio_funcionamiento;
    }

    public void setAnio_funcionamiento(int anio_funcionamiento) {
        this.anio_funcionamiento = anio_funcionamiento;
    }

    @Override
    public String toString() {
        return "Procesador [id=" + id + ", tareas_cargadas=" + tareas_cargadas + "]";
    }

    public void setTareas_cargadas(LinkedList<Tarea> tareas_cargadas) {
        this.tareas_cargadas = tareas_cargadas;
    }

    public int getTareasCriticas() {
        return tareasCriticas;
    }

    public void setTareasCriticas(int tareasCriticas) {
        this.tareasCriticas = tareasCriticas;
    }

    /* @Override
    public String toString() {
        return "Procesador [codigo=" + codigo + ", id=" + id + ", tareas_cargadas=" + tareas_cargadas + ", carga_total="
                + carga_total + "]";
    } */
}
