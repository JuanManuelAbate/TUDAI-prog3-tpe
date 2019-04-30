package estructuras;

import dominio.Aereopuerto;

public class GrafoDirigido {

	protected Vertice[] vertices;
	protected int libre;
	
	public GrafoDirigido(int size) {
		vertices = new Vertice[size];
		libre = 0;
	}
	
	public void addVertice(Object valor) {
		Vertice vertice = new Vertice(valor);
		vertices[libre] = vertice;
		libre = libre + 1;
	}
	
	public void addArco(Object origen, Object destino, Object valor) {
		Vertice aux = null;
		Vertice aux2 = null;
		Aereopuerto aDestino = null;
		Aereopuerto aOrigen = null;
		for (int i = 0; i < vertices.length; i++) {
			aDestino = (Aereopuerto) vertices[i].getValor();
			aOrigen = (Aereopuerto) vertices[i].getValor();
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
