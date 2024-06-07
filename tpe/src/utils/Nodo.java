package utils;

public class Nodo<T> {
    private T value; /*tarea  contenida en el nodo*/
    private Nodo<Tarea> tareaAgregada;/* esto es para agregar varios estados 
    solucion desde un nodo solo hasta los nodos que se deseen */
    
    public Nodo (T value){
        this.value = value;
        this.tareaAgregada = null;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void addNodoTarea(Tarea t){
        this.tareaAgregada = new Nodo<Tarea>(t);
    }
    
    public Nodo<Tarea> getNextNodoTarea(){
        return this.tareaAgregada;
    }

}
