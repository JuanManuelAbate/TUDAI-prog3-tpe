package estructuras;

import dominio.Aeropuerto;

public class GrafoAeropuerto extends GrafoDirigido {
	
	public GrafoAeropuerto(int size) {
		super(size);
	}

	@Override
	public void addArco(Object origen, Object destino, Object valor) {
		Vertice aux = null;
		Vertice aux2 = null;
		Aeropuerto aDestino = null;
		Aeropuerto aOrigen = null;
		for (int i = 0; i < vertices.length; i++) {
			aDestino = (Aeropuerto) vertices[i].getValor();
			aOrigen = (Aeropuerto) vertices[i].getValor();
			if (aDestino.getNombre().equals((String) destino)) {
				aux = vertices[i];
			}
			if (aOrigen.getNombre().equals((String) origen)) {
				aux2 = vertices[i];
			}
		}
		aux2.addAdyacente(aux, valor);
	}

}
