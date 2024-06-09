import java.util.LinkedList;

import utils.Procesador;

public class Main {

	public static void main(String args[]) {
		Servicios servicios = new Servicios(".\\tpe\\bin\\datasets\\Procesadores.csv", ".\\tpe\\bin\\datasets\\Tareas.csv");
		
		//System.out.println(servicios.servicio1("T3"));
		//System.out.println(servicios.servicio2(true));
		//System.out.println(servicios.servicio3(30, 60));
		//System.out.println(servicios.getProcesadores());
		System.out.println("///////////////////////////////////////////////////");
		//servicios.wtf();
		//LinkedList<Integer> list= new LinkedList<Integer>();
		//list.add(1);
		//list.removeLast();
		//System.out.println(list.get(0));
		System.out.println(servicios.servicio_backtraking(2,50));
		System.out.println("///////////////////////////////////////////////////");
		//System.out.println(servicios.getProcesadores());  //servicio backtraking ya hace un return de procesadores igual
		System.out.println("ESTADOS:");
		System.out.println(servicios.getEstadosBacktraking());
		System.out.println("MIN TIEMPO:");
		System.out.println(servicios.getMinTiempoBacktraking());

	}
}
