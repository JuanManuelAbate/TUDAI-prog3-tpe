package dominio;

import estructuras.GrafoAeropuerto;

import java.util.Iterator;

import estructuras.MyLinkedList;
import respuestas.VueloDirecto;

public class SistemaAero {

	private GrafoAeropuerto conexiones;
	private MyLinkedList reservas;
	
	public SistemaAero(int numeroAereopuertos) {
		conexiones = new GrafoAeropuerto(numeroAereopuertos);
		reservas = new MyLinkedList();
	}
	
	public void agregarAeropuerto(Aeropuerto aeropuerto) {
		conexiones.addVertice(aeropuerto);
	}
	
	public void agregarConexion(String origen, String destino, Etiqueta etiqueta) {
		conexiones.addArco(origen, destino, etiqueta);
	}
	
	public void agregarReserva(Reserva reserva) {
		reservas.insertFront(reserva);
		conexiones.actualizarAsientos(reserva.getOrigen(), reserva.getDestino(), reserva.getAerolinea(), reserva.getAsientos());
	}
	
	//puntos solicitados
	
	/*listar todos los aereopuertos
	conexiones devolveria una lista de los aereopuertos que tiene y se devolveria aca aparece el tema de 
	mantener el grafo como object.
	*/
	public MyLinkedList getAeropuertos(){
		return conexiones.getElementos();
		
	}
	/*listar todas las reservas realizadas
	devolver reservas.
	 */
	public MyLinkedList getReservas() {
		return reservas;
	}
	//servicio 1
	public VueloDirecto VerificarVueloDirecto(String origen,String destino,String aerolinea) {
		VueloDirecto vuelo= new VueloDirecto();
		Etiqueta e= conexiones.verificarVueloDirecto(origen, destino);
		if(e==null){
			return null;
		}
		if(!e.contieneAerolinea(aerolinea)){
			return null;
		}
		int asientos= e.getAsientosDisponiblesAerolinea(aerolinea);
		
		vuelo.setCantidadAsientos(asientos);
		vuelo.setKilometros(e.getKm());
		return vuelo;
	}
	//servicio 2
	
	//servicio 3
	
}
