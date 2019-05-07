package tpe;

import java.util.Iterator;
import java.util.Scanner;

import dominio.*;
import respuestas.VueloDirecto;
import utils.CSVReader;

public class Main {
	
	public static void main(String[] args) {
		//inicializacion del sistema.
		SistemaAero sistemaAero = CSVReader.inicializarSistemaAero();
		
		//menu
		boolean ejecucion = true;
		Scanner scanner = new Scanner(System.in);
	    int opcion;
	    Iterator<Object> it;
		
		while(ejecucion) {
			System.out.println("*******************************MENU*******************************");
			System.out.println("Ingerese un numero de las siguientes opciones y presione enter:");
			System.out.println("1. Listar todos los aeropuertos.");
			System.out.println("2. Listar todas las reservas realizadas.");
			System.out.println("3. Servicio 1: Verificar vuelo directo.");
			System.out.println("4. Servicio 2: Obtener vuelos sin aerolinea.");
			System.out.println("5. Servicio 3: Vuelos disponibles.");
			System.out.println("6. Salir.");
			System.out.println("******************************************************************");
			opcion = scanner.nextInt();
			scanner.nextLine();

		    switch (opcion) {
		        case 1:
		            System.out.println();
		            System.out.println("Lista de aeropuertos:");
		            it = sistemaAero.getAeropuertos().iterator();
		    		while(it.hasNext()) {
		    			Aeropuerto aeropuerto = (Aeropuerto)it.next();
		    			System.out.println(aeropuerto.getNombre());
		    		}
		    		System.out.println();
		    		break;
		        case 2:
		        	System.out.println();
		            System.out.println("Lista de reservas:");
		        	it = sistemaAero.getReservas().iterator();
		    		while (it.hasNext()) {
		    			Reserva reserva = (Reserva) it.next();
		    			System.out.println("Origen:"+reserva.getOrigen()+" | Destino:"+reserva.getDestino()+" | Aerolinea:"+ reserva.getAerolinea()+" | Asientos:"+reserva.getAsientos());
		    		}
		    		System.out.println();
		            break;
		        case 3:
		    		System.out.println();
		    		System.out.println("Ingresar aeropuerto de origen");
		    		String aeropuertoOrigen = scanner.nextLine();
		    		System.out.println("Ingresar aeropuerto de destino");
		    		String aeropuertoDestino = scanner.nextLine();
		    		System.out.println("Ingresar aerolinea deseada");
		    		String aerolinea = scanner.nextLine();
		    		
		    		VueloDirecto vueloDirecto = sistemaAero.VerificarVueloDirecto(aeropuertoOrigen, aeropuertoDestino, aerolinea);
	    			System.out.println("Resultado:");
		    		if (vueloDirecto == null) {
		    			System.out.println("No existe vuelo directo para la combinacion ingresada");
		    		} else {
			    		System.out.println("Km requeridos:" + vueloDirecto.getKilometros() + " | Asientos disponibles:" + vueloDirecto.getCantidadAsientos());
		    		}
		    		System.out.println();
		    		break;
		        case 4:
		            System.out.println(opcion);
		            break;
		        case 5:
		        	System.out.println(opcion);
		            break;
		        default:
		        	ejecucion = false;
		    }
		}
		
		/*
		VueloDirecto v=sistemaAero.VerificarVueloDirecto("Comodoro Benitez", "El prat", "Delta");
		System.out.println(v.getCantidadAsientos());
		System.out.println(v.getKilometros());
		*/
	}
}
