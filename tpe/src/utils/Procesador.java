package utils;

public class Procesador {
    private String codigo;
    private String id;
    private boolean esta_refrigerado;
    private int anio_funcionamiento;
    private int carga_total;//1
    private boolean ultima_tarea_critica;//2
    /*
     * agregamos las variables 1,2 para poder cargar tareas a los procesadores 
     * y saber si vamos a agregar dos tareas criticas seguidas
    */
    public Procesador(String codigo, String id, boolean esta_refrigerado, int anio_funcionamiento) {
        this.codigo = codigo;
        this.id = id;
        this.esta_refrigerado = esta_refrigerado;
        this.anio_funcionamiento = anio_funcionamiento;
        this.carga_total = 0;
        this.ultima_tarea_critica = false;
    }


    public int getCarga_total() {
        return this.carga_total;
    }

    public void setCarga_total(int cargar) {
        this.carga_total = this.carga_total + cargar;
    }

    public boolean isUltima_tarea_critica() {
        return ultima_tarea_critica;
    }

    public void setUltima_tarea_critica(boolean ultima_tarea_critica) {
        this.ultima_tarea_critica = ultima_tarea_critica;
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
