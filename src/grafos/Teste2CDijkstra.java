package grafos;

public class Teste2CDijkstra {

	public static void main(String[] args) {
		ListaAdjacencia grafoC = new ListaAdjacencia(18, true);
		
		// Lisboa (0)
		grafoC.adicionaAresta(0, 1, 50);
		grafoC.adicionaAresta(0, 4, 150);
		grafoC.adicionaAresta(0, 8, 70);
		grafoC.adicionaAresta(0, 9, 130);
		//Setúbal (1)
		grafoC.adicionaAresta(1, 0, 50);
		grafoC.adicionaAresta(1, 2, 260);
		grafoC.adicionaAresta(1, 3, 135);
		//Faro (2)
		grafoC.adicionaAresta(2, 1, 260);
		grafoC.adicionaAresta(2, 3, 170);
		//Faro (3)
		grafoC.adicionaAresta(3, 1, 135);
		grafoC.adicionaAresta(3, 2, 170);
		grafoC.adicionaAresta(3, 4, 80);
		//Évora (4)
		grafoC.adicionaAresta(4, 0, 150);
		grafoC.adicionaAresta(4, 3, 80);
		grafoC.adicionaAresta(4, 5, 100);
		grafoC.adicionaAresta(4, 8, 120);
		//Portalegre (5)
		grafoC.adicionaAresta(5, 4, 100);
		grafoC.adicionaAresta(5, 6, 80);
		grafoC.adicionaAresta(5, 8, 150);
		//CasteloB (6)
		grafoC.adicionaAresta(6, 5, 80);
		grafoC.adicionaAresta(6, 8, 200);
		grafoC.adicionaAresta(6, 7, 100);
		grafoC.adicionaAresta(6, 10, 160);
		//Guarda (7)
		grafoC.adicionaAresta(7, 6, 100);
		grafoC.adicionaAresta(7, 10, 160);
		grafoC.adicionaAresta(7, 11, 80);
		grafoC.adicionaAresta(7, 17, 200);
		//Santarém (8)
		grafoC.adicionaAresta(8, 0, 70);
		grafoC.adicionaAresta(8, 4, 120);
		grafoC.adicionaAresta(8, 6, 200);
		grafoC.adicionaAresta(8, 5, 150);
		//Leiria (9)
		grafoC.adicionaAresta(9, 0, 130);
		grafoC.adicionaAresta(9, 10, 70);
		//Coimbra (10)
		grafoC.adicionaAresta(10, 9, 70);
		grafoC.adicionaAresta(10, 6, 160);
		grafoC.adicionaAresta(10, 7, 160);
		grafoC.adicionaAresta(10, 11, 80);
		grafoC.adicionaAresta(10, 12, 80);
		//Viseu (11)
		grafoC.adicionaAresta(11, 7, 80);
		grafoC.adicionaAresta(11, 10, 80);
		grafoC.adicionaAresta(11, 12, 100);
		grafoC.adicionaAresta(11, 16, 110);
		//Aveiro (12)
		grafoC.adicionaAresta(12, 10, 80);
		grafoC.adicionaAresta(12, 11, 100);
		grafoC.adicionaAresta(12, 13, 70);
		//Porto (13)
		grafoC.adicionaAresta(13, 12, 70);
		grafoC.adicionaAresta(13, 14, 80);
		grafoC.adicionaAresta(13, 15, 50);
		grafoC.adicionaAresta(13, 16, 120);
		//VianaC (14)
		grafoC.adicionaAresta(14, 13, 80);
		grafoC.adicionaAresta(14, 15, 50);
		//Braga (15)
		grafoC.adicionaAresta(15, 13, 50);
		grafoC.adicionaAresta(15, 14, 50);
		grafoC.adicionaAresta(15, 16, 100);
		//VilaReal (16)
		grafoC.adicionaAresta(16, 11, 110);
		grafoC.adicionaAresta(16, 13, 120);
		grafoC.adicionaAresta(16, 15, 100);
		grafoC.adicionaAresta(16, 17, 140);
		//Bragança (17)
		grafoC.adicionaAresta(17, 16, 140);
		grafoC.adicionaAresta(17, 7, 200);
		
		grafoC.mostrarListaAdjacencias();
		
		System.out.println();
		
		System.out.println("Distância de Lisboa(0) até Braga(15): "+grafoC.dijkstra(0, 15));
		
		
	}
}
