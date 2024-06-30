public class Main {

	public static void main(String args[]) {
		Servicios servicios = new Servicios(".\\tpe\\bin\\datasets\\Procesadores.csv", ".\\tpe\\bin\\datasets\\Tareas.csv");
		//servicios.servicio_backtraking(80);
		//System.out.println(servicios.servicio1("T3"));

		//System.out.println(servicios.servicio2(true));
		
		//System.out.println(servicios.servicio3(30, 60));
		
		//System.out.println(servicios.getProcesadores());

		servicios.getSolucionGreedy(0);
	}
}
