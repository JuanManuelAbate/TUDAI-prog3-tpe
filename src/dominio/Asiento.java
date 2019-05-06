package dominio;

public class Asiento {

	private int asientosTotales;
	private int asientosDisponibles;
	
	public Asiento(int asientosTotales) {
		this.asientosTotales = asientosTotales;
		this.asientosDisponibles = asientosTotales;
	}
	
	public int getAsientosTotales() {
		return this.asientosTotales;
	}
	
	public void reservarAsientos (int numeroDeAsientos) {
		asientosDisponibles = asientosDisponibles - numeroDeAsientos;
	}
	
	public int getAsientosDisponibles() {
		return this.asientosDisponibles;
	}
	
}
