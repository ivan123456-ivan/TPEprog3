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
	private Tarea tarea_E;



	public Backtraking(List<Procesador> procesadorList, List<Tarea> tareaList) {
        this.estados= 0;
        this.procesadorList= procesadorList;
        this.solucionList= new LinkedList<>();
		this.tareaList= new LinkedList<Tarea>(tareaList);
		for(int i= 0; i < this.tareaList.size(); i++) {
			this.solucionList.add(-1);
		}
		//this.maxCriticos= 0;
		this.minTiempo= 0;
		this.tarea_E= null;
    }

	public Integer getEstados() {
		return this.estados;
	}

	public Integer getMinTiempo() {
		return this.minTiempo;
	}

	public LinkedList<Integer> getSolutionList(int maxC, int maxT) { // lista de pos= tareas con valor= pos proc
		foundSolution(maxC, maxT);
		return new LinkedList<Integer>(this.solucionList);
	}

	public List<Procesador> doSolution(int maxC, int maxT) {
		if (foundSolution(maxC, maxT)) {
			for (int i= 0; i < tareaList.size();i++) {
				procesadorList.get(solucionList.get(i)).addTareas_cargadas(tareaList.get(i));
			}
		}
		return this.procesadorList;
		//return null
	}

	public boolean foundSolution(int maxC, int maxT) { 
		LinkedList<Integer> carga = new LinkedList<>(); // lista de procesadores con un contador en 0 para cada uno
		for(int i= 0; i < this.procesadorList.size(); i++) {
			carga.add(0);
		}
		LinkedList<Integer> critic = new LinkedList<>(); // lista de procesadores con un contador en 0 para cada uno
		for(int i= 0; i < this.procesadorList.size(); i++) {
			critic.add(0);
		}
		LinkedList<Integer> solutionTemp= new LinkedList<>();
		for(int i= 0; i < this.tareaList.size(); i++) {
			solutionTemp.add(-1);
		}
		//LinkedList tareasTemp= new LinkedList<Tarea>(this.tareaList);
		solution(solutionTemp, this.tareaList, critic, carga, maxC, maxT);

		if (this.estados != 0) {
			return true;
		} else {
			System.out.println("no se puede asignar la tarea: " + this.tarea_E);
			return false;
		}
	}

	private void solution(LinkedList<Integer> solutionTemp, LinkedList<Tarea> tareasTemp, LinkedList<Integer> criticXproc, LinkedList<Integer> tiempoXproc, int maxC, int maxT) {
		boolean found= false; // (；一_一)
		if (tareasTemp.size()!= 0) {
			for(int i= 0; i < this.procesadorList.size(); i++) {
				if (criticXproc.get(i) <= maxC && (this.procesadorList.get(i).isEsta_refrigerado() || (tiempoXproc.get(i) + tareasTemp.getLast().getTiempo_ejecucion()) <= maxT)) {
					found= true;
					//System.out.println(tareasTemp.size());
					solutionTemp.set((tareasTemp.size()-1), i);
					//solutionTemp.add(i);
					Tarea aux= tareasTemp.getLast();
					if (aux.isEs_critica()) {
						criticXproc.set(i, criticXproc.get(i) +1);
					}
					tareasTemp.removeLast();
					tiempoXproc.set(i, tiempoXproc.get(i) + aux.getTiempo_ejecucion());
					//System.out.println(tiempoXproc);
					solution(solutionTemp, tareasTemp, criticXproc, tiempoXproc, maxC, maxT);
					tareasTemp.add(aux);     //esta cosa iba antes
					solutionTemp.set(tareasTemp.size()-1, -1);
					//solutionTemp.add(i);
					//tareasTemp.add(aux);
					if (aux.isEs_critica()) {
						criticXproc.set(i, criticXproc.get(i) -1);
					}
					tiempoXproc.set(i, tiempoXproc.get(i) - aux.getTiempo_ejecucion());
					//System.out.println(tiempoXproc);
				}
			}
			if (!found) { // (；一_一)
				//System.out.println(tareasTemp.getLast());
				this.tarea_E= tareasTemp.getLast();
			}
		} else {
			int maxTiempo= 0;
			for(int i2= 0; i2 < tiempoXproc.size();i2++) { //recorrer solution temp y fijarse cuanto timepo acumula el procesador con mas carga,
				if (tiempoXproc.get(i2) > maxTiempo) {
					maxTiempo= tiempoXproc.get(i2);
					//System.out.println("tiempoXproc.get:   " + i2);
					//System.out.println(tiempoXproc.get(i2));
				}
			}
			this.estados++;
			if (maxTiempo != 0 && (minTiempo==0) || maxTiempo < minTiempo) {
				for (int i3= 0; i3< solutionTemp.size();i3++) {
					this.solucionList.set(i3, solutionTemp.get(i3));
					this.minTiempo= maxTiempo;
				}
			}
		}
	}

}
