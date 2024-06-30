package utils;

import java.util.LinkedList;

public class Greedy {
    private LinkedList<Tarea> tareasList;
    private LinkedList<Procesador> procesadoresList;
    private int candidatos;
    private int MaxtiempoConseguido;

    public Greedy(LinkedList<Tarea> tareasList, LinkedList<Procesador> procesadoresList) {
        this.tareasList = tareasList;
        this.procesadoresList = procesadoresList;
        this.candidatos = 0;
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

    public int getcandidatos() {
        return candidatos;
    }

    public void setcandidatos(int estados) {
        this.candidatos = estados;
    }

    public void getSolucion(int x) {
        this.greedy(x);
    }

    private void greedy(int x){
        for (Tarea t : this.tareasList){
            if(this.mejorprocesador(t, x)!= -1){
                procesadoresList.get(this.mejorprocesador(t, x)).addTareas_cargadas(t);
            }else{
                System.out.println("no se pudo agregar esta tarea = " + t.getId());
            }
            candidatos ++;
        }
        this.setMaxtiempoConseguido();
    }

    private int mejorprocesador(Tarea t , int x){
        Procesador p = null;
        for(Procesador paux : this.procesadoresList){
            if(paux.puedeAgregarTarea(t, x)){
                if(p == null){
                    p = paux;
                }else{
                    if(p.getTiempoTotal() > paux.getTiempoTotal()){
                        p=paux;
                    }
                }
            }
        }
        if(p== null){
            return -1;
        }else{
            return procesadoresList.indexOf(p);
        }
    }

    public void imprimirSolucion() {
        System.out.println("Solución greedy:");
        for (Procesador procesador : procesadoresList) {
            System.out.println("Procesador " + procesador.getId() + ":");
            for (Tarea tarea : procesador.getTareas_cargadas()) {
                System.out.println("  Tarea " + tarea.getNombre());
            }
        }
        System.out.println("Tiempo máximo de ejecucion: " + this.MaxtiempoConseguido);
        System.out.println("candidatos considerados: " + this.candidatos);
    }
}