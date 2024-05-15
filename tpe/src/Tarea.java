
public class Tarea {
    private int tiempo_ejecucion;
    private String nombre, id;
    private boolean es_critica;

    public Tarea(int tiempo_ejecucion, String nombre, String id, boolean es_critica) {
        this.tiempo_ejecucion = tiempo_ejecucion;
        this.nombre = nombre;
        this.id = id;
        this.es_critica = es_critica;
    }

    public int getTiempo_ejecucion() {
        return tiempo_ejecucion;
    }

    public void setTiempo_ejecucion(int tiempo_ejecucion) {
        this.tiempo_ejecucion = tiempo_ejecucion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isEs_critica() {
        return es_critica;
    }

    public void setEs_critica(boolean es_critica) {
        this.es_critica = es_critica;
    }
}
