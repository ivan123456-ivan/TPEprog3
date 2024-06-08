import utils.Procesador;

public class Main {

	public static void main(String args[]) {
		Servicios servicios = new Servicios(".\\tpe\\src\\datasets\\Procesadores.csv", ".\\tpe\\src\\datasets\\Tareas.csv");
		
		//System.out.println(servicios.servicio1("T3"));
		//System.out.println(servicios.servicio2(true));
		//System.out.println(servicios.servicio3(30, 60));
		System.out.println(servicios.getProcesadores());
		System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		System.out.println(servicios.servicio_backtraking(2,50));
		System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		System.out.println(servicios.getProcesadores());
	}
}
