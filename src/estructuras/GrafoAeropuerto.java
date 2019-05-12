package estructuras;

import java.util.Iterator;
import java.util.Map;

import dominio.Aeropuerto;
import dominio.Etiqueta;
import respuestas.VueloDirectoAerolineas;
import respuestas.VueloDisponibleAeroExcluyente;

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
		Etiqueta e = (Etiqueta) valor;
		aux2.addAdyacente(aux, e);
		aux.addAdyacente(aux2,new Etiqueta(e));
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

	public void actualizarAsientos(String origen, String destino, String aerolinea, int asientos) {
		Aeropuerto aOrigen = null;
		Aeropuerto aDestino = null;
		for(int i = 0; i < vertices.length; i++) {
			aOrigen = (Aeropuerto) vertices[i].getValor();
			if (aOrigen.getNombre().equals(origen)) {
				Iterator<Object> it = vertices[i].getAdyacentes().iterator();
				Arco a;
				while (it.hasNext()) {
					a = (Arco) it.next();
					aDestino = (Aeropuerto) a.getDestino().getValor();
					if (aDestino.getNombre().equals(destino)) {
						Etiqueta e = (Etiqueta) a.getValor();
						e.actualizarAsientos(aerolinea, asientos);
					}
				}
			}
		}
		
	}

	public MyLinkedList vuelosDirectosEntrePaises(String paisOrigen, String paisDestino) {
		MyLinkedList resultado = new MyLinkedList();
		Aeropuerto aOrigen = null;
		Aeropuerto aDestino = null;
		for(int i = 0; i < vertices.length; i++) {
			aOrigen = (Aeropuerto) vertices[i].getValor();
			if (aOrigen.getPais().equals(paisOrigen)) {
				Iterator<Object> it = vertices[i].getAdyacentes().iterator();
				while(it.hasNext()) {
					Arco a = (Arco) it.next();
					aDestino = (Aeropuerto) a.getDestino().getValor();
					if (aDestino.getPais().equals(paisDestino)) {
						Etiqueta e = (Etiqueta) a.getValor();
						Map<String, Integer> aerolineaPasajes = e.getAerolineasDisponibles();
						if (!aerolineaPasajes.isEmpty()) {
							resultado.insertFront(new VueloDirectoAerolineas(aOrigen.getNombre(), aDestino.getNombre(), e.getKm(), aerolineaPasajes));
						}
					}
				}
			}
		}
		return resultado;
	}
	public MyLinkedList vuelosDisponibles(String origen,String destino, String excluyente) {
		MyLinkedList vuelosdisp=new MyLinkedList();
		
		//get referencia al vertice de origen 
		Vertice v= this.getVerticePorNombreAeropuerto(origen);
		
		//llamo al metodo recursivo con el origen
		vuelosDisponiblesPrivate(v,excluyente,destino,0,0,vuelosdisp);
		
		
		return vuelosdisp;
	}
	
	private void vuelosDisponiblesPrivate(Vertice origen,String excluyente,String destino,double contadorkm,int contadorescalas,MyLinkedList vuelosdisp) {
		 origen.setVisitado(true);
		 Iterator<Object> it= origen.getAdyacentes().iterator();
		 while(it.hasNext()) {
			 Arco a=(Arco)it.next();
			 Vertice v= a.getDestino();
			 Etiqueta e= (Etiqueta)a.getValor();
			 String aerolinea= e.contieneAerolineaNoExcluyente(excluyente);
			 if (!v.getVisitado()&&aerolinea!=null){
				 Aeropuerto aero= (Aeropuerto)v.getValor();
				 if(aero.getNombre().equals(destino)) {
					 //generamos respuesta y la agregamos a la lista
					 VueloDisponibleAeroExcluyente vuelo=new VueloDisponibleAeroExcluyente(aerolinea,contadorescalas,contadorkm+e.getKm());
					 vuelosdisp.insertFront(vuelo);
				 }
				 else {
					 vuelosDisponiblesPrivate(v,excluyente,destino,contadorkm+e.getKm(),contadorescalas + 1,vuelosdisp);

				 }
			 }
		 }
		 origen.setVisitado(false);
	}
	
	private Vertice getVerticePorNombreAeropuerto(String nombre) {
		Vertice v=null;
		for(int i=0; i<vertices.length;i++) {
			Aeropuerto a= (Aeropuerto)vertices[i].getValor();
			if(a.getNombre().equals(nombre)) {
				v= vertices[i];
			}
		}
		return v;
	}
}
