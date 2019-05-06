package estructuras;

import java.util.Iterator;

import dominio.Aeropuerto;
import dominio.Etiqueta;

public class GrafoAeropuerto extends Grafo {
	
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
		aux.addAdyacente(aux2,valor);
	}

	public Etiqueta verificarVueloDirecto(String origen, String destino){
		Aeropuerto a;
		Arco b;
		Etiqueta e=null;
		for(int i=0;i<vertices.length;i++) {
			a=(Aeropuerto)vertices[i].getValor();
			if(a.getNombre().equals(origen)) {
				Iterator<Object> it = vertices[i].getAdyacentes().iterator(); 
				while(it.hasNext()){
					b=(Arco)it.next();
					Aeropuerto c = (Aeropuerto) b.getDestino().getValor();
					if(c.getNombre().equals(destino)) {
					 e=(Etiqueta)b.getValor();
					}
				}
			}
		}
		return e;
	}
}
