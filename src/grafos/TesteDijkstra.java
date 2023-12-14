package grafos;

public class TesteDijkstra {

	public static void main(String[] args) {
		
		// 2a
		ListaAdjacencia grafoA = new ListaAdjacencia(5, true);
		
		grafoA.adicionaAresta(0, 1, 1);
		grafoA.adicionaAresta(0, 3, 3);
		grafoA.adicionaAresta(0, 4, 10);
		grafoA.adicionaAresta(1, 2, 5);
		grafoA.adicionaAresta(2, 4, 1);
		grafoA.adicionaAresta(3, 2, 2);
		grafoA.adicionaAresta(3, 4, 6);
		
		grafoA.mostrarListaAdjacencias();
		
		System.out.println();
		
		System.out.println("De 0 a 1: "+grafoA.dijkstra(0, 1));
		System.out.println("De 0 a 2: "+grafoA.dijkstra(0, 2));
		System.out.println("De 0 a 3: "+grafoA.dijkstra(0, 3));
		System.out.println("De 0 a 4: "+grafoA.dijkstra(0, 4));
		
		System.out.println();
		
		// 2b
		
		ListaAdjacencia grafoB = new ListaAdjacencia(6, true);
		
		grafoB.adicionaAresta(0, 1, 15);
		grafoB.adicionaAresta(0, 2, 9);
		grafoB.adicionaAresta(1, 3, 2);
		grafoB.adicionaAresta(2, 1, 4);
		grafoB.adicionaAresta(2, 3, 3);
		grafoB.adicionaAresta(2, 4, 16);
		grafoB.adicionaAresta(3, 4, 6);
		grafoB.adicionaAresta(3, 5, 21);
		grafoB.adicionaAresta(4, 5, 7);
		
		grafoB.mostrarListaAdjacencias();
		
		System.out.println();
		
		System.out.println("De 0 a 1: "+grafoB.dijkstra(0, 5));
		
		
	}

}
