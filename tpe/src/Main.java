
public class Main {

	public static void main(String args[]) {
		Servicios servicios = new Servicios("tpe\\src\\datasets\\Procesadores.csv", "tpe\\src\\datasets\\Tareas.csv");
		
		//System.out.println(servicios.servicio1("T3"));
		//System.out.println(servicios.servicio2(true));
		System.out.println(servicios.servicio3(30, 60));
	}
}
