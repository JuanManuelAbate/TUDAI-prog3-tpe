package estructuras;

public class Arco {

	private Object valor;
	private Vertice destino;
	
	public Arco(Vertice destino, Object valor) {
		this.destino = destino;
		this.valor = valor;
	}
	
	public Vertice getDestino() {
		return destino;
	}
	public Object getValor() {
		return valor;
	}
}
