package estructuras;

public abstract class GrafoDirigido {

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
	
	public abstract void addArco(Object origen, Object destino, Object valor);
}
