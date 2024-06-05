package utils;

import java.util.LinkedList;
import java.util.List;

public class ArbolBinarioOrdenado {
    private Nodo<Tarea> raiz;

    public ArbolBinarioOrdenado(){
        this.raiz= null;
    }
    public void put(int prioridad, Tarea tarea) {
        if(this.raiz ==null){
            this.raiz = new Nodo<Tarea>(tarea, prioridad);
        }else{
            this.put(prioridad, tarea, this.raiz);
        }
    }

    private void put(int prioridad, Tarea tarea, Nodo<Tarea> n){
        if(this.raiz.getKey() > prioridad){
            if(this.raiz.getMenor() != null){
                this.put(prioridad, tarea, this.raiz.getMenor());                
            }else{
                this.raiz.setMenor(new Nodo<Tarea>(tarea, prioridad));
            }
        }else{
            if(this.raiz.getMayor() != null){
                this.put(prioridad, tarea, this.raiz.getMayor());
            }else{
                this.raiz.setMayor(new Nodo<Tarea>(tarea,prioridad));
            }
        }
    }



    public List<Tarea> getelementosrangoprioridad(int prioridadInferior, int prioridadSuperior) {
        return(this.getelementosrangoprioridad(prioridadInferior, prioridadSuperior, raiz));
    }
    private List<Tarea> getelementosrangoprioridad(int prioridadInferior, int prioridadSuperior,Nodo<Tarea>n){
        List<Tarea> aux = new LinkedList<Tarea>();
        if(n==null){
            return null;
        }
        if((n.getKey() < prioridadSuperior) && (n.getKey()>prioridadInferior)){
            aux.add(n.getValue());
        }
        if(n.getKey()< prioridadSuperior){
            aux.addAll(this.getelementosrangoprioridad(prioridadInferior, prioridadSuperior,n.getMayor()));
        }else{
            aux.addAll(this.getelementosrangoprioridad(prioridadInferior, prioridadSuperior, n.getMenor()));        
        }
        return aux;
    }
}