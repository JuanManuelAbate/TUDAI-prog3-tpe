package dominio;

import estructuras.GrafoDirigido;
import estructuras.MyLinkedList;

public class Aereolinea {

	private GrafoDirigido conexiones;
	private MyLinkedList reservas;
	
	public Aereolinea(int numeroAereopuertos) {
		conexiones = new GrafoDirigido(numeroAereopuertos);
		reservas = new MyLinkedList();
	}
	
	public void agregarAereopuerto(Aereopuerto aereopuerto) {
		conexiones.addVertice(aereopuerto);
	}
	
	public void agregarConexion(String origen, String destino, Etiqueta etiqueta) {
		conexiones.addArco(origen, destino, etiqueta);
	}
	
	public void agregarReserva(Reserva reserva) {
		reservas.insertFront(reserva);
	}
	
	//puntos solicitados
	
	/*listar todos los aereopuertos
	conexiones devolveria una lista de los aereopuertos que tiene y se devolveria aca aparece el tema de 
	mantener el grafo como object.
	*/
	
	/*listar todas las reservas realizadas
	devolver reservas.
	 */
	
	//servicio 1
	
	//servicio 2
	
	//servicio 3
	
}
