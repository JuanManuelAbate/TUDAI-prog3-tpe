package estructuras;

public class Vertice {

	private Object valor;
	private MyLinkedList adyacentes;
	private boolean visitado;
	
	public Vertice(Object valor) {
		this.valor = valor;
		adyacentes = new MyLinkedList();
		visitado = false;
	}
	
	public Object getValor() {
		return valor;
	}
	
	public boolean getVisitado() {
		return visitado;
	}
	
	public void setVisitado(boolean estado) {
		this.visitado = estado;
	}
	
	public MyLinkedList getAdyacentes() {
		return adyacentes;
	}

	public void addAdyacente(Vertice destino, Object costo) {
		adyacentes.insertFront(new Arco(destino, costo));
	}
	
}
