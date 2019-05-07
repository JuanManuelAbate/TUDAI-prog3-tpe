package respuestas;

import java.util.Map;

public class VueloDirectoAerolineas {

	private String aeropuertoOrigen;
	private String aeropuertoDestino;
	private double distancia;
	private Map<String, Integer> aerolineaPasajes;

	public VueloDirectoAerolineas(String aOrigen, String aDestino, double km, Map<String, Integer> aerolineaPasajes) {
		this.aeropuertoOrigen = aOrigen;
		this.aeropuertoDestino = aDestino;
		this.distancia = km;
		this.aerolineaPasajes = aerolineaPasajes;
	}

	public String getAeropuertoOrigen() {
		return aeropuertoOrigen;
	}

	public String getAeropuertoDestino() {
		return aeropuertoDestino;
	}

	public double getDistancia() {
		return distancia;
	}

	public Map<String, Integer> getAerolineaPasajes() {
		return aerolineaPasajes;
	}
	
}
