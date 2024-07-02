package utils;

import java.util.LinkedList;
import java.util.List;

public class Back {
    private int tiempoMaximoNoRefrigerado;
    private int tiempoMaximo;
    private List<Procesador> mejorSolucion;
    private int estadosGenerados;

    public Back(int tiempoMaximoNoRefrigerado) {
        this.tiempoMaximoNoRefrigerado = tiempoMaximoNoRefrigerado;
        this.tiempoMaximo = Integer.MAX_VALUE;
        this.estadosGenerados = 0;
    }

    public void backtracking(List<Tarea> tareas, List<Procesador> procesadores) {
        this.backtracking(tareas, procesadores, 0);
    }

    private void backtracking(List<Tarea> tareas, List<Procesador> procesadores, int tareaIndex) {
        estadosGenerados++;
        if (tareaIndex == tareas.size()) {
            int tiempoActual = obtenerTiempoMaximo(procesadores);
            if (tiempoActual < tiempoMaximo) {
                tiempoMaximo = tiempoActual;
                mejorSolucion = copiarSolucion(procesadores);
            }
        }else{
            Tarea tarea = tareas.get(tareaIndex);
            for (Procesador procesador : procesadores) {
                if (procesador.puedeAgregarTarea(tarea, tiempoMaximoNoRefrigerado)) {
                    procesador.addTareas_cargadas(tarea);
                    backtracking(tareas, procesadores, tareaIndex + 1);
                    procesador.removeTarea(tarea);
                }
            }
        }
    }

    private List<Procesador> copiarSolucion(List<Procesador> procesadores) {
        LinkedList<Procesador> copia = new LinkedList<Procesador>();
        for (Procesador p : procesadores) {
            Procesador nuevoProcesador = new Procesador(p.getCodigo(), p.getId(), p.isEsta_refrigerado(), p.getAnio_funcionamiento());
            nuevoProcesador.setTiempoTotal(p.getTiempoTotal());
            nuevoProcesador.setTareasCriticas(p.getTareasCriticas());
            nuevoProcesador.setTareas_cargadas(new LinkedList<>(p.getTareas_cargadas()));
            copia.add(nuevoProcesador);
        }
        return copia;
    }

    private int obtenerTiempoMaximo(List<Procesador> procesadores) {
        int maxTiempo = 0;
        for (Procesador procesador : procesadores) {
            maxTiempo += procesador.getTiempoTotal();
        }
        return maxTiempo;
    }

    public void imprimirSolucion() {
        System.out.println("Solución Backtracking:");
        if (mejorSolucion == null) {
            System.out.println("No existe solución posible.");
            return;
        }
        for (Procesador procesador : mejorSolucion) {
            System.out.println("Procesador " + procesador.getId() + ":");
            for (Tarea tarea : procesador.getTareas_cargadas()) {
                System.out.println("  Tarea " + tarea.getNombre());
            }
        }
        System.out.println("Tiempo máximo de ejecución: " + tiempoMaximo);
        System.out.println("Estados generados: " + estadosGenerados);
    }
}
