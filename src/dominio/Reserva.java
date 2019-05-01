package dominio;

public class Reserva {

	private String origen;
	private String destino;
	private String aerolinea;
	private int asientos;
	
	public Reserva(String origen, String destino, String aerolinea, int asientos) {
		super();
		this.origen = origen;
		this.destino = destino;
		this.aerolinea = aerolinea;
		this.asientos = asientos;
	}

	public String getOrigen() {
		return origen;
	}
	
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	
	public String getDestino() {
		return destino;
	}
	
	public void setDestino(String destino) {
		this.destino = destino;
	}
	
	public String getAerolinea() {
		return aerolinea;
	}
	
	public void setAerolinea(String aerolinea) {
		this.aerolinea = aerolinea;
	}
	
	public int getAsientos() {
		return asientos;
	}
	
	public void setAsientos(int asientos) {
		this.asientos = asientos;
	}
		
}
