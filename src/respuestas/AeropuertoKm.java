package respuestas;

public class AeropuertoKm {

	private String nombre;
	private double km;
	
	public AeropuertoKm() {
		
	}
	
	public AeropuertoKm(String nombre, double km) {
		super();
		this.nombre = nombre;
		this.km = km;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public double getKm() {
		return km;
	}
	
	public void setKm(double km) {
		this.km = km;
	}
	
}
