package dominio;

import java.util.HashMap;
import java.util.Map;

public class Etiqueta {

	double km;
	boolean cabotaje;
	Map<String, Integer> aerolineaAsientos;
	
	public Etiqueta() {
		aerolineaAsientos = new HashMap<>();
	}
	
	public double getKm() {
		return km;
	}
	
	public void setKm(double km) {
		this.km = km;
	}
	
	public boolean isCabotaje() {
		return cabotaje;
	}
	
	public void setCabotaje(boolean cabotaje) {
		this.cabotaje = cabotaje;
	}
	
	public void agregarAerolineaAsientos(String aerolinea, Integer asientos) {
		aerolineaAsientos.put(aerolinea, asientos);
	}
	
}
