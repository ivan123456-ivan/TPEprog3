
public class Main {

	public static void main(String args[]) {
		Servicios servicios = new Servicios("src\\datasets\\Procesadores.csv", "src\\datasets\\Tareas.csv");
		
		System.out.println(servicios.servicio1("T3"));

	}
}
