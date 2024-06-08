package utils;
import java.util.LinkedList;
import java.util.List;

import utils.Procesador;
import utils.Tarea;
	/*
	Primero, ningun procesador podra ejecutar 2 tareas criticas. 
	Segundo, los procesadores no refrigerados no podran dedicar mas de X tiempo de ejecución a 
	las tareas asignadas. El tiempo X sera un parametro establecido por el usuario al momento de 
	solicitar la asignacion de las tareas a los procesadores.
	*/
public class Backtraking {
    private int estados;
	//private int maxCriticos;
	private int minTiempo;
    private List<Procesador> procesadorList;
    private LinkedList<Integer>solucionList; //pos lista = pos tarea, valor = pos procesador
    private LinkedList<Tarea> tareaList;




	public Backtraking(List<Procesador> procesadorList, List<Tarea> tareaList) {
        this.estados= 0;
        this.procesadorList= procesadorList;
        this.solucionList= new LinkedList<>();
		for(int i= 0; i < this.procesadorList.size(); i++) {
			this.solucionList.add(-1);
		}
        this.tareaList= new LinkedList<Tarea>(tareaList);
		//this.maxCriticos= 0;
		this.minTiempo= 0;
    }

	public LinkedList<Integer> getSolutionList() {
		if (this.estados==0) {
			System.out.println("llamar a otra funcion");
		}
		return new LinkedList<Integer>(this.solucionList);
	}

	public boolean doSolution() {
		if (this.estados==0) {
			System.out.println("llamar a otra funcion");
			return false;
		}
		for (int i= 0; i < tareaList.size();i++) {
			procesadorList.get(solucionList.get(i)).addTareas_cargadas(tareaList.get(i));
		}
		return true;
	}

	public boolean foundSolution(int maxC, int maxT) {  //void o boolean?
		LinkedList<Integer> procs0 = new LinkedList<>(); // lista de procesadores con un contador en 0 para cada uno
		for(int i= 0; i < this.procesadorList.size(); i++) {
			procs0.add(0);
		}
		LinkedList<Integer> solutionTemp= new LinkedList<>();
		for(int i= 0; i < this.procesadorList.size(); i++) {
			solutionTemp.add(-1);
		}
		//LinkedList tareasTemp= new LinkedList<Tarea>(this.tareaList);
		solution(solutionTemp, this.tareaList, procs0, procs0, maxC, maxT);
		if (this.estados != 0) {
			return true;
		} else {
			return false;
		}
	}

	private void solution(LinkedList<Integer> solutionTemp, LinkedList<Tarea> tareasTemp, LinkedList<Integer> criticXproc, LinkedList<Integer> tiempoXproc, int maxC, int maxT) {
		if (tareasTemp.size()!= 1) {
		//if (tareasTemp != null) {
			for(int i= 0; i < this.procesadorList.size(); i++) {
				if (criticXproc.get(i)!= maxC && (this.procesadorList.get(i).isEsta_refrigerado() || tiempoXproc.get(i)<= maxT)) {
					System.out.println(tareasTemp.size());
					solutionTemp.set((tareasTemp.size()-1), i);
					//solutionTemp.add(i);
					Tarea aux= tareasTemp.getLast();
					if (aux.isEs_critica()) {
						criticXproc.set(i, criticXproc.get(i) +1);
					}
					tareasTemp.removeLast();
					tiempoXproc.set(i, tiempoXproc.get(i) + aux.getTiempo_ejecucion());
					solution(solutionTemp, tareasTemp, criticXproc, tiempoXproc, maxC, maxT);
	
					solutionTemp.set(tareasTemp.size()-1, -1);
					//solutionTemp.add(i);
					tareasTemp.add(aux);
					if (aux.isEs_critica()) {
						criticXproc.set(i, criticXproc.get(i) -1);
					}
					tiempoXproc.set(i, tiempoXproc.get(i) - aux.getTiempo_ejecucion());
					
				}
			}
		} else {
			int maxTiempo= 0;
			for(int i2= 0; i2 < tiempoXproc.size();i2++) { //recorrer solution temp y fijarse cuanto timepo acumula el procesador con mas carga,
				if (tiempoXproc.get(i2) > maxTiempo) {
					maxTiempo= tiempoXproc.get(i2);
				}
			}
			if (maxTiempo != 0 && maxTiempo < minTiempo) {
				this.solucionList= solutionTemp;
			}
		}
	}

}
