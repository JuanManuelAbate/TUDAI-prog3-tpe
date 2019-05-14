package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import dominio.Aeropuerto;
import dominio.Reserva;
import estructuras.MyLinkedList;
import respuestas.VueloDirecto;
import respuestas.VueloDirectoAerolineas;
import respuestas.VueloDisponibleAeroExcluyente;

public class CSVWritter {

	public static void generarArchivoListaAeropuertos(MyLinkedList listaAeropuertos) {
		BufferedWriter bw = null;
		try {
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
			File file = new File(formatter.format(date) + " - Listar todos los aeropuertos.csv");
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);

			Iterator<Object> it = listaAeropuertos.iterator();
			while(it.hasNext()) {
				Aeropuerto aeropuerto = (Aeropuerto) it.next();
				bw.write(aeropuerto.getNombre());
				bw.newLine();
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
			} catch (Exception ex) {
				System.out.println("Error cerrando el BufferedWriter" + ex);
			}
		}
	}

	public static void generarArchivoListaReservas(MyLinkedList listaReservas) {
		BufferedWriter bw = null;
		try {
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
			File file = new File(formatter.format(date) + " - Listar todas las reservas realizadas.csv");
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);

			Iterator<Object> it = listaReservas.iterator();
			while(it.hasNext()) {
				Reserva reserva = (Reserva) it.next();
				bw.write("Origen:"+reserva.getOrigen()+" | Destino:"+reserva.getDestino()+" | Aerolinea:"+ reserva.getAerolinea()+" | Asientos:"+reserva.getAsientos());
				bw.newLine();
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
			} catch (Exception ex) {
				System.out.println("Error cerrando el BufferedWriter" + ex);
			}
		}
	}

	public static void generarArchivoVueloDirecto(VueloDirecto vueloDirecto) {
		BufferedWriter bw = null;
		try {
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
			File file = new File(formatter.format(date) + " - Servicio 1: Verificar vuelo directo.csv");
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);

			bw.write("Km requeridos:" + vueloDirecto.getKilometros() + " | Asientos disponibles:" + vueloDirecto.getCantidadAsientos());
			bw.newLine();

		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
			} catch (Exception ex) {
				System.out.println("Error cerrando el BufferedWriter" + ex);
			}
		}
	}

	public static void generarArchivoVuelosDirectosEntrePaises(MyLinkedList vueloDirectoAerolineasLista) {
		BufferedWriter bw = null;
		try {
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
			File file = new File(formatter.format(date) + " - Servicio 3: Vuelos disponibles.csv");
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);

			Iterator<Object> it = vueloDirectoAerolineasLista.iterator();
			VueloDirectoAerolineas vueloDirectoAerolineas;
			while(it.hasNext()) {
				vueloDirectoAerolineas = (VueloDirectoAerolineas) it.next();
				bw.write("Aeropuerto Origen:" + vueloDirectoAerolineas.getAeropuertoOrigen() +
						" | Aeropuerto Destino:" + vueloDirectoAerolineas.getAeropuertoDestino() +
						" | Km requeridos:" + vueloDirectoAerolineas.getDistancia());
				bw.newLine();
				bw.write("Aerolineas disponibles: ");
				bw.newLine();
				Map<String, Integer> aerolineaPasajes = vueloDirectoAerolineas.getAerolineaPasajes();
				Set<String> claves = aerolineaPasajes.keySet();
				for (String clave : claves) {
					bw.write("*" + clave + " - " + aerolineaPasajes.get(clave) + " pasajes disponibles");
					bw.newLine();
				}
				bw.newLine();
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
			} catch (Exception ex) {
				System.out.println("Error cerrando el BufferedWriter" + ex);
			}
		}
	}

	public static void generarARchivoVuelosEntreAeropuertosSinAerolinea(MyLinkedList vuelosDisponibles) {
		BufferedWriter bw = null;
		try {
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
			File file = new File(formatter.format(date) + " - Servicio 2: Obtener vuelos sin aerolinea.csv");
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);

			Iterator<Object> it = vuelosDisponibles.iterator();
			VueloDisponibleAeroExcluyente vueloDisponibleAeroExcluyente;
			while(it.hasNext()) {
				vueloDisponibleAeroExcluyente = (VueloDisponibleAeroExcluyente) it.next();
				bw.write("Aerolinea: "+vueloDisponibleAeroExcluyente.getAerolinea()+" | Cantidad de escalas: "+vueloDisponibleAeroExcluyente.getEscalas()+" | Cantidad Kilometros: "+vueloDisponibleAeroExcluyente.getKms());
				bw.newLine();
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
			} catch (Exception ex) {
				System.out.println("Error cerrando el BufferedWriter" + ex);
			}
		}	
	}
	
	
}
