package dominio;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Etiqueta {

	double km;
	boolean cabotaje;
	Map<String, Asiento> aerolineaAsientos;
	
	public Etiqueta() {
		aerolineaAsientos = new HashMap<>();
	}
	
	public Etiqueta(Etiqueta e) {
		this.km = e.km;
		this.cabotaje = e.cabotaje;
		aerolineaAsientos = new HashMap<>();
		Set<String> claves = e.aerolineaAsientos.keySet();
		for (String clave:claves) {
			this.aerolineaAsientos.put(clave, new Asiento(e.aerolineaAsientos.get(clave).getAsientosTotales()));
		}
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
		aerolineaAsientos.put(aerolinea, new Asiento(asientos));
	}
	public boolean contieneAerolinea(String aerolinea)	{
		return aerolineaAsientos.containsKey(aerolinea);
		
	}
	public int getAsientosDisponiblesAerolinea(String aerolinea) {
		return aerolineaAsientos.get(aerolinea).getAsientosDisponibles();
	}

	public void actualizarAsientos(String aerolinea, int asientos) {
		aerolineaAsientos.get(aerolinea).reservarAsientos(asientos);
	}

	public Map<String, Integer> getAerolineasDisponibles() {
		Map<String, Integer> aerolineasDisponibles = new HashMap<>();
		Set<String> claves = aerolineaAsientos.keySet();
		for (String clave : claves) {
			if (aerolineaAsientos.get(clave).getAsientosDisponibles() != 0) {
				aerolineasDisponibles.put(clave, aerolineaAsientos.get(clave).getAsientosDisponibles());
			}
		}
		return aerolineasDisponibles;
	}
	
}
