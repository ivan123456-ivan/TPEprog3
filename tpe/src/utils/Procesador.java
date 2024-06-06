package utils;

import java.util.LinkedList;

public class Procesador {
    private String codigo;
    private String id;
    private boolean esta_refrigerado;
    private int anio_funcionamiento;
    private LinkedList<Tarea> tareas_realizar;
    private int cargaTarea;
    private boolean ultimaCritica;

    public Procesador(String codigo, String id, boolean esta_refrigerado, int anio_funcionamiento) {
        this.codigo = codigo;
        this.id = id;
        this.esta_refrigerado = esta_refrigerado;
        this.anio_funcionamiento = anio_funcionamiento;
        this.tareas_realizar= new LinkedList<>();
    }
    
    public void addTarea(Tarea t){
        this.tareas_realizar.add(t);
    }
    public LinkedList<Tarea> getTarea(){
        return new LinkedList<Tarea>(this.tareas_realizar);
    }

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
}
