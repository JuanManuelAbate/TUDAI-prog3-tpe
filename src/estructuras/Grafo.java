package estructuras;

public abstract class Grafo {

	protected Vertice[] vertices;
	protected int libre;
	
	public Grafo(int size) {
		vertices = new Vertice[size];
		libre = 0;
	}
	
	public void addVertice(Object valor) {
		Vertice vertice = new Vertice(valor);
		vertices[libre] = vertice;
		libre = libre + 1;
	}
	
	public abstract void addArco(Object origen, Object destino, Object valor);
	
	public MyLinkedList getElementos() {
		MyLinkedList listaElementos=new MyLinkedList();
		
		for(int i=0; i<vertices.length;i++) {
			listaElementos.insertFront(vertices[i].getValor());
		}
		
		return listaElementos;
	}
}
