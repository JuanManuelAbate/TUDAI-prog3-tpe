package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import dominio.Aeropuerto;
import dominio.Etiqueta;
import dominio.Reserva;
import dominio.SistemaAero;

public class CSVReader {

    public static SistemaAero inicializarSistemaAero() {
        String archivoAeropuertos = "Aeropuertos.csv";
        String archivoReservas = "Reservas.csv";
        String archivoRutas = "Rutas.csv";
        String line = "";
        String cvsSplitBy = ";";
        SistemaAero sistemaAero = null;

        try (BufferedReader br = new BufferedReader(new FileReader(archivoAeropuertos))) {
        	
        	sistemaAero = new SistemaAero((int) Files.lines(Paths.get(archivoAeropuertos)).count());
            while ((line = br.readLine()) != null) {

                String[] items = line.split(cvsSplitBy);
                sistemaAero.agregarAeropuerto(new Aeropuerto(items[0], items[1], items[2]));
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try (BufferedReader br = new BufferedReader(new FileReader(archivoReservas))) {
        	
            while ((line = br.readLine()) != null) {

                String[] items = line.split(cvsSplitBy);
                sistemaAero.agregarReserva(new Reserva(items[0], items[1], items[2], Integer.parseInt(items[3])));
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try (BufferedReader br = new BufferedReader(new FileReader(archivoRutas))) {
        	
            while ((line = br.readLine()) != null) {

                String[] items = line.split(cvsSplitBy);
                Etiqueta etiqueta = new Etiqueta();
                if ("0".equals(items[3])) {
                	etiqueta.setCabotaje(false);
                } else {
                	etiqueta.setCabotaje(true);
                }
                etiqueta.setKm(Double.parseDouble(items[2]));
                String aerolineasAsientos = items[4].substring(1, items[4].length() - 1);
                String[] aerolineasAsientosItems = aerolineasAsientos.split(",");
                for (int i = 0; i < aerolineasAsientosItems.length; i++) {
                	String[] aerolineaAsientos = aerolineasAsientosItems[i].split("-");
                	etiqueta.agregarAerolineaAsientos(aerolineaAsientos[0], Integer.parseInt(aerolineaAsientos[1]));
                }
                sistemaAero.agregarConexion(items[0], items[1], etiqueta);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return sistemaAero;
    }
    
}