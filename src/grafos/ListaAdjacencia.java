package grafos;
import java.util.*;

class Aresta {
	int origem;
	int destino;
	int peso;
	
	public Aresta(int origem, int destino, int peso) {
		this.origem = origem;
		this.destino = destino;
		this.peso = peso;
	}
}

public class ListaAdjacencia {
	private int nVertices;
	private List<List<Aresta>> adjacencias;
	private boolean direcionado;
	private static final int INF = Integer.MAX_VALUE;
	
	
	public ListaAdjacencia(int nVertices, boolean direcionado) {
		this.nVertices = nVertices;
		this.direcionado = direcionado;
		this.adjacencias = new ArrayList<>(nVertices);
		for (int i=0; i < nVertices; i++) {
			adjacencias.add(new ArrayList<Aresta>());
		}
	}
	
	public void adicionaAresta(int u, int v, int peso) {
		Aresta aresta = new Aresta(u, v, peso);
		adjacencias.get(u).add(aresta);
		if (!direcionado) {
			Aresta arestaInvertida = new Aresta(v, u, peso);
			adjacencias.get(v).add(arestaInvertida);
		}
	}
	
	public void adicionaAresta(int u, int v) {
		adicionaAresta(u, v, 1);
	}
	
	public void mostrarListaAdjacencias() {
		for (int i=0; i<nVertices; i++) {
			System.out.print("Vértice "+i+": ");
			for (Aresta aresta : adjacencias.get(i)) {
				System.out.print("("+aresta.destino+", Peso: "+aresta.peso+")");
			}
			System.out.println();
		}
	}
	
	public void removeAresta(int u, int v) {
		List<Aresta> arestaU = adjacencias.get(u);
		for (Aresta aresta : arestaU) {
			if (aresta.destino == v) {
				arestaU.remove(aresta);
				break;
			}
		}
		if (!direcionado) {
			List<Aresta> arestaV = adjacencias.get(v);
			for (Aresta aresta : arestaV) {
				if (aresta.destino == u) {
					arestaV.remove(aresta);
					break;
				}
			}
		}
	}

	public boolean verificaAjacencia(int u, int v) {
		for (Aresta aresta : adjacencias.get(u)) {
			if (aresta.destino == v) {
				return true;
			}
		}
		return false;
	}
	
	public void adjacenciasVertice(int v) {
		System.out.print("Vértice "+v+": ");
		for (Aresta aresta : adjacencias.get(v)) {
			System.out.print("("+aresta.destino+", Peso: "+aresta.peso+")");
		}
		System.out.println();
	}
	
	//Trabalho 1a
	public void removeVertice(int vertice) {
	    
	    for (int i = 0; i < nVertices; i++) {
	        if (i != vertice) {
	            removeAresta(i, vertice); 
	            removeAresta(vertice, i);
	        }
	    }

	    // Remove o vértice da lista de adjacências
	    adjacencias.set(vertice, new ArrayList<>());

	    if (!direcionado) {
	        for (List<Aresta> lista : adjacencias) {
	            for (Aresta aresta : lista) {
	                if (aresta.destino > vertice) {
	                    aresta.destino--;
	                }
	            }
	        }
	    }
	    nVertices--;
	}
	
	// Trabalho 1b
	public boolean verificaConexo() {
	    if (nVertices == 0) {
	        return false;
	    }

	    boolean[] visitado = new boolean[nVertices]; 
	    Queue<Integer> fila = new LinkedList<>();
	    // Adiciona o primeiro vértice à fila e marca como visitado
	    fila.offer(0);
	    visitado[0] = true;

	    while (!fila.isEmpty()) {
	        int verticeAtual = fila.poll();

	        // Percorre todas as arestas do vértice atual
	        for (Aresta aresta : adjacencias.get(verticeAtual)) {
	            int verticeAdjacente = aresta.destino;

	            // Se o vértice adjacente não foi visitado, marca como visitado e adiciona à fila
	            if (!visitado[verticeAdjacente]) {
	                visitado[verticeAdjacente] = true;
	                fila.offer(verticeAdjacente);
	            }
	        }
	    }

	    // Verifica se todos os vértices foram visitados
	    for (boolean visit : visitado) {
	        if (!visit) {
	            return false;
	        }
	    }

	    return true;
	}
	
	// Trabalho 1c
	public boolean verificaCompleto() {
	    if (nVertices <= 1) {
	        return true; 
	    }

	    for (List<Aresta> lista : adjacencias) {
	        int tamanhoLista = lista.size();

	        if (tamanhoLista != nVertices - 1) {
	            return false;
	        }
	    }

	    return true;
	}
	
	// Trabalho 1d
	public int dijkstra(int origem, int destino) {
	    int[] distancias = new int[nVertices+1];
	    boolean[] visitado = new boolean[nVertices];
	    Arrays.fill(distancias, INF);
	    distancias[origem] = 0;

	    PriorityQueue<Integer> filaPrioridade = new PriorityQueue<>(Comparator.comparingInt(o -> distancias[o]));
	    filaPrioridade.add(origem);

	    while (!filaPrioridade.isEmpty()) {
	        int u = filaPrioridade.poll();
	        visitado[u] = true;

	        for (Aresta aresta : adjacencias.get(u)) {
	            int v = aresta.destino;
	            int peso = aresta.peso;

	            if (!visitado[v] && distancias[u] != INF && distancias[u] + peso < distancias[v]) {
	                distancias[v] = distancias[u] + peso;
	                
	                filaPrioridade.add(v);
	            }
	        }
	    }

	    return distancias[destino];
	}
	
	// Trabalho 1e
	public String verificarEuleriano() {
        if (nVertices == 0) {
            return "Grafo vazio";
        }

        int countOddDegree = 0;
        boolean conexo = verificaConexo();

        for (int i = 0; i < nVertices; i++) {
            int grau = adjacencias.get(i).size();
            if (grau % 2 != 0) {
                countOddDegree++;
            }
        }

        if (conexo) {
            if (countOddDegree == 0) {
                return "Euleriano"; // Grafo é Euleriano (todos os vértices têm grau par)
            } else if (countOddDegree == 2) {
                return "Semieuleriano"; // Grafo é semieuleriano (exatamente dois vértices têm grau ímpar)
            } else {
                return "Não Euleriano"; // Grafo não é Euleriano nem semieuleriano (mais de dois vértices com grau ímpar)
            }
        } else {
            return "Não Euleriano"; // Grafo não é conexo
        }
    }
	
	// Trabalho 1f
	public String verificarHamiltoniano() {
        if (nVertices == 0) {
            return "Grafo vazio";
        }

        int[][] matrizAdj = gerarMatrizAdjacencia();
        int[] caminho = new int[nVertices];
        Arrays.fill(caminho, -1);

        caminho[0] = 0;
        if (verificarCicloHamiltoniano(1, caminho, matrizAdj)) {
            return "Hamiltoniano"; // Se encontrar um ciclo Hamiltoniano, o grafo é Hamiltoniano
        }

        if (verificarCaminhoSemiHamiltoniano(caminho, matrizAdj)) {
            return "Semi-Hamiltoniano"; // Se encontrar um caminho semi-Hamiltoniano, o grafo é semi-Hamiltoniano
        }

        return "Não Hamiltoniano"; // Se não for Hamiltoniano nem semi-Hamiltoniano
    }

    private int[][] gerarMatrizAdjacencia() {
        int[][] matriz = new int[nVertices][nVertices];

        for (int i = 0; i < nVertices; i++) {
            for (int j = 0; j < nVertices; j++) {
                matriz[i][j] = 0;
            }
        }

        for (int i = 0; i < nVertices; i++) {
            for (Aresta aresta : adjacencias.get(i)) {
                matriz[i][aresta.destino] = 1;
                matriz[aresta.destino][i] = 1;
            }
        }

        return matriz;
    }

    private boolean verificarCicloHamiltoniano(int posicao, int[] caminho, int[][] matrizAdj) {
        if (posicao == nVertices) {
            return matrizAdj[caminho[posicao - 1]][caminho[0]] == 1;
        }

        for (int v = 1; v < nVertices; v++) {
            if (verificaValido(v, posicao, caminho, matrizAdj)) {
                caminho[posicao] = v;
                if (verificarCicloHamiltoniano(posicao + 1, caminho, matrizAdj)) {
                    return true;
                }
                caminho[posicao] = -1;
            }
        }

        return false;
    }

    private boolean verificaValido(int v, int posicao, int[] caminho, int[][] matrizAdj) {
        if (matrizAdj[caminho[posicao - 1]][v] == 0) {
            return false;
        }

        for (int i = 0; i < posicao; i++) {
            if (caminho[i] == v) {
                return false;
            }
        }

        return true;
    }

    private boolean verificarCaminhoSemiHamiltoniano(int[] caminho, int[][] matrizAdj) {
        int contador = 0;
        for (int i = 1; i < nVertices; i++) {
            if (matrizAdj[caminho[i - 1]][caminho[i]] == 0) {
                return false;
            }
            contador++;
        }

        return contador == nVertices - 1;
    }
}
