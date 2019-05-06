package tpe;

import java.util.Iterator;

import dominio.*;
import respuestas.VueloDirecto;
import utils.CSVReader;

public class Main {
	
	public static void main(String[] args) {
		//inicializacion 
		SistemaAero sistemaAero = CSVReader.inicializarSistemaAero();
		
		//menu
		
		Iterator<Object> it = sistemaAero.getAeropuertos().iterator();

		while(it.hasNext()) {
			Aeropuerto a=(Aeropuerto)it.next();
			System.out.println(a.getNombre());
		}
		
		it=sistemaAero.getReservas().iterator();
		while (it.hasNext()) {
			Reserva a= (Reserva)it.next();
			System.out.println("Origen: "+a.getOrigen()+" Destino: "+a.getDestino()+" Aerolinea: "+ a.getAerolinea()+" Asientos: "+a.getAsientos());
		}
		
		VueloDirecto v=sistemaAero.VerificarVueloDirecto("Comodoro Benitez", "El prat", "Delta");
		System.out.println(v.getCantidadAsientos());
		System.out.println(v.getKilometros());
		
	}
}
