package estructuras;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import dominio.Aeropuerto;
import dominio.Etiqueta;
import respuestas.AeropuertoKm;
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
	
	public List<AeropuertoKm> viajanteGreedy (String origen) {
		System.out.println();
		List<AeropuertoKm> resultado = new ArrayList<>();
		Vertice vActual = getVerticePorNombreAeropuerto(origen);
		vActual.setVisitado(true);
		Arco proximaCiudad = null;
		Etiqueta e = null;
		Aeropuerto a = null;
		AeropuertoKm akm = new AeropuertoKm(origen, 0);
		System.out.println("Añade el primer aeropuerto a la solucion con distancia 0");
		resultado.add(akm); 
		System.out.println("Solucion actual:");
		imprimirSolucion(resultado);
		
		while(hayCiudadesPorVisitar()) {
			proximaCiudad = ciudadMasCercanaNoVisitada(vActual.getAdyacentes());
			e = (Etiqueta) proximaCiudad.getValor();
			vActual = proximaCiudad.getDestino();
			vActual.setVisitado(true);
			a = (Aeropuerto) vActual.getValor();
			System.out.println("Añade el aeropuerto " + a.getNombre() + " a la solucion con distancia " + e.getKm());
			resultado.add(new AeropuertoKm(a.getNombre(), e.getKm())); 
			System.out.println("Solucion actual:");
			imprimirSolucion(resultado);
		}
		System.out.println("No quedan mas aeropuertos por visitar");
		proximaCiudad = ultimaCiudadAOrigen(origen, vActual.getAdyacentes());
		e = (Etiqueta) proximaCiudad.getValor();
		System.out.println("Añade el primer aeropuerto a la solucion con distancia " + e.getKm());
		resultado.add(new AeropuertoKm(origen, e.getKm())); 
		System.out.println("Solucion actual:");
		imprimirSolucion(resultado);

		for (int i = 0; i<vertices.length; i++) {
			vertices[i].setVisitado(false);
		}
		return resultado;
	}
	
	private boolean hayCiudadesPorVisitar() {
		System.out.println("Chequea que queden aeropuertos por visitar");
		for (int i = 0; i<vertices.length; i++) {
			if (!vertices[i].getVisitado()) {
				return true;
			}
		}
		return false;
	}
	
	private Arco ciudadMasCercanaNoVisitada(MyLinkedList adyacentes) {
		System.out.print("Busca el siguiente aeropuerto con menor distancia no visitado en este caso: ");
		Arco resultado = null;
		Arco aux = null;
		double cercana = Double.MAX_VALUE;
		Iterator<Object> it = adyacentes.iterator();
		while (it.hasNext()) {
			aux = (Arco) it.next();
			Etiqueta e = (Etiqueta) aux.getValor();
			if ((e.getKm() < cercana) && (!aux.getDestino().getVisitado())) {
				cercana = e.getKm();
				resultado = aux;
			}
		}
		Vertice v = resultado.getDestino();
		Aeropuerto a = (Aeropuerto) v.getValor();
		System.out.println(a.getNombre());
		return resultado;
	}
	
	private Arco ultimaCiudadAOrigen(String origen, MyLinkedList adyacentes) {
		Arco resultado = null;
		Arco aux = null;
		Iterator<Object> it = adyacentes.iterator();
		while (it.hasNext()) {
			aux = (Arco) it.next();
			Aeropuerto a = (Aeropuerto) aux.getDestino().getValor();
			if (a.getNombre().equals(origen)) {
				resultado = aux;
			}
		}
		return resultado;
	}
	
	private void imprimirSolucion(List<AeropuertoKm> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getNombre() + " -> " + list.get(i).getKm());
		}
		System.out.println("");
	}
	
	public List<AeropuertoKm> viajanteBacktracking(String origen){
		Vertice v = this.getVerticePorNombreAeropuerto(origen);
		List<AeropuertoKm> rparcial = new ArrayList<>();
		List<AeropuertoKm> rfinal = new ArrayList<>();
		rfinal.add(new AeropuertoKm(origen, 0));
		rfinal.add(new AeropuertoKm("maximo", Double.MAX_VALUE));
		viajanteBacktrackingPrivate(v,rparcial,rfinal, origen);
		return rfinal;
	}
	
	private void viajanteBacktrackingPrivate(Vertice v,List<AeropuertoKm> rparcial,List<AeropuertoKm> rfinal, String origen) {
		Aeropuerto aeropuerto = (Aeropuerto) v.getValor();
		System.out.println("Me paro en el vertice: " + aeropuerto.getNombre());
		v.setVisitado(true);
		if(!hayCiudadesPorVisitar()){
			System.out.println("*Se llego a estado final");
			Arco a = ultimaCiudadAOrigen(origen, v.getAdyacentes());
			Etiqueta e = (Etiqueta) a.getValor();
			AeropuertoKm akm = new AeropuertoKm(origen, e.getKm());
			rparcial.add(akm);
			System.out.println("Solucion parcial:");
			imprimirSolucion(rparcial);
			comparaResultado(rparcial,rfinal);
			System.out.println("Solucion final:");
			imprimirSolucion(rfinal);
			rparcial.remove(rparcial.size()-1);
		}
		else {
			System.out.println("Recorro los adyacentes no visitados de: " + aeropuerto.getNombre());
			Iterator<Object> it=v.getAdyacentes().iterator();
			while(it.hasNext()) {
				Arco a= (Arco)it.next();
				if(!a.getDestino().getVisitado()) {
					Vertice d=a.getDestino();
					Aeropuerto aer= (Aeropuerto)d.getValor();
					String nombre= aer.getNombre();
					Etiqueta etq= (Etiqueta)a.getValor();
					double km=etq.getKm();
					System.out.println("Agrego "+ nombre+"->"+km+ " a la respuesta parcial");
					AeropuertoKm aerokm=new AeropuertoKm(nombre,km);
					rparcial.add(aerokm);
					System.out.println("Solucion parcial:");
					imprimirSolucion(rparcial);
					viajanteBacktrackingPrivate(d,rparcial,rfinal,origen);
					rparcial.remove(rparcial.size()-1);
				}
			}
		}
		v.setVisitado(false);
	}
	private void comparaResultado(List<AeropuertoKm> rparcial,List<AeropuertoKm> rfinal) {
		double kmp= getKilometrosLista(rparcial);
		double kmf= getKilometrosLista(rfinal);
		
		if(kmp<kmf) {
			System.out.println("Se encontro una mejor solucion");
			AeropuertoKm akm = rfinal.get(0);
			rfinal.clear();
			rfinal.add(akm);
			for(int i=0;i<rparcial.size();i++) {
				rfinal.add(rparcial.get(i));
			}
		}
	}
	
	private double getKilometrosLista(List<AeropuertoKm> r) {
		Iterator<AeropuertoKm> it= r.iterator();
		double suma=0.0;
		while(it.hasNext()) {
			suma+=it.next().getKm();
		}
		return suma;
	}
}
