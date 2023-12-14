package grafos;

public class TesteListaAdjacencia {

	public static void main(String[] args) {
		ListaAdjacencia grafo = new ListaAdjacencia(3, false);
		
		// Adicionando arestas ao grafo
		grafo.adicionaAresta(0, 1);
		grafo.adicionaAresta(0, 2);
		grafo.adicionaAresta(1, 2);
		
		// Mostrando a lista de adjacencias
		grafo.mostrarListaAdjacencias();
		
//		// Removendo arestas do grafo
//		grafo.removeAresta(0, 1);
//		
//		System.out.println();
//		grafo.mostrarListaAdjacencias();
//		
//		// Verifica ajacencia
//		System.out.println(grafo.verificaAjacencia(2, 0));
//		
//		// Ajacencias vertice
//		grafo.adjacenciasVertice(0);
		
		// testando 1a,b
		System.out.println();
		grafo.removeVertice(2);

		grafo.mostrarListaAdjacencias();
		
//		grafo.removeAresta(0, 1);
		
		System.out.println(grafo.verificaConexo());
		
		System.out.println();
		
		// testando 1c
		ListaAdjacencia grafo2 = new ListaAdjacencia(4, false);
		grafo2.adicionaAresta(0, 1);
		grafo2.adicionaAresta(0, 2);
		grafo2.adicionaAresta(1, 3);
		grafo2.adicionaAresta(2, 3);
		
		grafo2.mostrarListaAdjacencias();
		
		System.out.println(grafo2.verificaCompleto());
		
//		grafo2.adicionaAresta(1, 2);
//		grafo2.adicionaAresta(0, 3);
		
		grafo2.mostrarListaAdjacencias();
		
		System.out.println(grafo2.verificaCompleto());
		
		System.out.println();
		
		// testando 1d
        int distancia = grafo2.dijkstra(0, 1);

       System.out.println(distancia);
        
        System.out.println();
        
        // testando 1e
        System.out.println(grafo2.verificarEuleriano());
        
        //testando 1f
        System.out.println(grafo.verificarHamiltoniano());
	}

}
