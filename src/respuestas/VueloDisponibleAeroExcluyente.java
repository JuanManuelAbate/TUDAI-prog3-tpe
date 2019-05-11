package respuestas;

public class VueloDisponibleAeroExcluyente {
	String aerolinea;
	int escalas;
	double kms;
	
	public VueloDisponibleAeroExcluyente(String a,int e,double k) {
		aerolinea=a;
		escalas=e;
		kms=k;
	}

	public String getAerolinea() {
		return aerolinea;
	}

	public int getEscalas() {
		return escalas;
	}

	public double getKms() {
		return kms;
	}

}
