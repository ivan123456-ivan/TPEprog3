public class Main {

	public static void main(String args[]) {
		Servicios servicios = new Servicios(".\\tpe\\bin\\datasets\\Procesadores.csv", ".\\tpe\\bin\\datasets\\Tareas.csv");
		servicios.servicio_backtraking(80);
		//System.out.println(servicios.servicio1("T3"));

		//System.out.println(servicios.servicio2(true));
		
		//System.out.println(servicios.servicio3(30, 60));
		
		//System.out.println(servicios.getProcesadores());

		//System.out.println("///////////////////////////////////////////////////"); */
		//System.out.println(servicios.getProcesadores());  //servicio backtraking ya hace un return de procesadores igual
		/* System.out.println("ESTADOS:");
		System.out.println(servicios.getEstadosBacktraking());
		System.out.println("MIN TIEMPO:");
		System.out.println(servicios.getMinTiempoBacktraking()); */

		/* System.out.println("solucion entcontrada"+"="+servicios.getSolucionGreedy(101));
		System.out.println("////////////////////////////////////////////////////////");
		System.out.println(servicios.getProcesadores());
		System.out.println("///////////////////////////////////////////////////////////");
		System.out.println("estador generados"+"="+servicios.getEstadosGreedy());
		System.out.println("///////////////////////////////////////////////////");
		System.out.println("maximo tienpo generado"+"="+servicios.getMaxtiempoConseguido());
 */
	}
}
