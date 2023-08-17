package arvores;
import java.util.LinkedList;
import java.util.Queue;

public class ArvoreBinaria {
	private class Nodo {
		private int chave;
		private Nodo dir, esq;
		
		public Nodo(int item) {
			this.chave = item;
			dir = esq = null;
		}
	}
	
	Nodo raiz = null;
	
	public void inserir(int chave) {
		raiz = inserirDado(raiz, chave);
	}
	
	private Nodo inserirDado(Nodo raiz, int chave) {
		if (raiz == null) {
			raiz = new Nodo(chave);
			return raiz;
		}
		
		if (chave < raiz.chave) {
			raiz.esq = inserirDado(raiz.esq, chave);
		} else if (chave > raiz.chave) {
			raiz.dir = inserirDado(raiz.dir, chave);
		}
		
		return raiz;
	}
	
	public void mostrarEmOrdemCrescente() {
		mostrarCrescente(raiz);
	}
	
	private void mostrarCrescente(Nodo raiz) {
		if (raiz != null) {
			mostrarCrescente(raiz.esq);
			System.out.println(raiz.chave);
			mostrarCrescente(raiz.dir);
		}
	}
	
	public void mostrarEmOrdemDecrescente() {
		mostrarDecrescente(raiz);
	}
	
	private void mostrarDecrescente(Nodo raiz) {
		if (raiz != null) {
			mostrarDecrescente(raiz.dir);
			System.out.println(raiz.chave);
			mostrarDecrescente(raiz.esq);
		}
	}
	
	public void mostrarPorNivel() {
		if (raiz == null) {
			System.out.println("Árvore vazia!");
			return;
		}
		Queue<Nodo> fila = new LinkedList<>();
		fila.add(raiz);
		
		while (!fila.isEmpty()) {
			int tamanhoNivel = fila.size();
			for (int i = 0; i < tamanhoNivel; i++) {
				Nodo nodoAtual = fila.poll();
				if (nodoAtual != null) {
					System.out.print(nodoAtual.chave + " ");
					fila.add(nodoAtual.esq);
					fila.add(nodoAtual.dir);
				} else {
					System.out.print(" - ");
				}
			}
			System.out.println(); //nova linha para separar os níveis
		}
	}
	
	public void remover(int chave) {
		raiz = removerItem(raiz, chave);
	}
	
	private Nodo removerItem(Nodo raiz, int chave) {
		if (raiz == null) {
			//Nó não encontrado, não faz nada
			return null;
		}
		if (chave < raiz.chave) {
			//chave a ser removida está a esquerda
			raiz.esq = removerItem(raiz.esq, chave);
		} else if (chave > raiz.chave){
			//chave a ser removida está a direita
			raiz.dir = removerItem(raiz.dir, chave);
		} else {
			//encontramos o nó a ser removido
			if (raiz.esq == null) {
				//Caso em que o nó não possui filho a esquerda
				return raiz.dir;
			} else if (raiz.dir == null) {
				//Caso em que o nó não possui filho a direita
				return raiz.esq;
			} else {
				//Caso em que o nó possui ambos os filhos
				//Nó sucessor será o menor da subárvore da direita
				Nodo sucessor = encontrarSucessor(raiz.dir);
				//Substituimos o valor do nó a ser removido pelo valor do sucessor
				raiz.chave = sucessor.chave;
				raiz.dir = removerItem(raiz.dir,sucessor.chave);
			}
		}
		return raiz;
	}
	
	private Nodo encontrarSucessor(Nodo nodo) {
		while (nodo.esq != null) {
			nodo = nodo.esq;
		}
		return nodo;
	}
	
	public void mostrarMaior() {
		mostrarRaizMaisDireita(raiz);
	}
	
	private void mostrarRaizMaisDireita(Nodo raiz) {
		if (raiz != null) {
			if (raiz.dir == null) {
				System.out.println(raiz.chave);
			}
			mostrarRaizMaisDireita(raiz.dir);
			
		}
	}
	
	public void mostrarMenor() {
		mostrarRaizMaisEsquerda(raiz);
	}
	
	private void mostrarRaizMaisEsquerda(Nodo raiz) {
		if (raiz != null) {
			if (raiz.esq == null) {
				System.out.println(raiz.chave);
			}
			mostrarRaizMaisEsquerda(raiz.esq);
			
		}
	}
	
	public void mostrarFolhas() {
		if (raiz == null) {
			System.out.println("Árvore vazia!");
			return;
		}
		Queue<Nodo> fila = new LinkedList<>();
		fila.add(raiz);
		
		while (!fila.isEmpty()) {
			int tamanhoNivel = fila.size();
			for (int i = 0; i < tamanhoNivel; i++) {
				Nodo nodoAtual = fila.poll();
				if (nodoAtual != null) {
					if (nodoAtual.dir == null && nodoAtual.esq == null) {
						System.out.println(nodoAtual.chave);
					}
					fila.add(nodoAtual.esq);
					fila.add(nodoAtual.dir);
				}
			}
		}
	}
	
	// Exercicio 1 d)
	public Nodo buscaNodo(int chave) {
		if (raiz == null) {
			return null;
		}
		Queue<Nodo> fila = new LinkedList<>();
		fila.add(raiz);
		
		while (!fila.isEmpty()) {
			int tamanhoNivel = fila.size();
			for (int i = 0; i < tamanhoNivel; i++) {
				Nodo nodoAtual = fila.poll();
				if (nodoAtual != null) {
					if (nodoAtual.chave == chave) {
						return nodoAtual;
					}
					fila.add(nodoAtual.esq);
					fila.add(nodoAtual.dir);
				}
			}
		}
		return null;
	}
	
	public void mostrarAncestrais(int chave) {
		ancestrais(raiz, chave);
	}
	
	private void ancestrais(Nodo raiz, int chave) {
		if (raiz == null) {
			System.out.println("Árvore vazia!");
			return;
		}
		if (buscaNodo(chave) == null) {
			System.out.println("Nodo não existente na árvore!");
			return;
		}
		if (chave < raiz.chave) {
			System.out.println(raiz.chave);
			ancestrais(raiz.esq, chave);
		} else if (chave > raiz.chave){
			System.out.println(raiz.chave);
			ancestrais(raiz.dir, chave);
		} 
	}
	
	// Exercicio 1 e)
	public void mostrarDescendentes(int chave) {
		Nodo nodoExistente = buscaNodo(chave); // metódo buscaNodo foi criado no exercicio 1 d) acima
		if (nodoExistente == null) {
			System.out.println("Nodo não existente na árvore!");
			return;
		}
		descendentes(nodoExistente, chave);
	}

	private void descendentes(Nodo raiz, int chave) {
		if (raiz != null) {
			mostrarDecrescente(raiz.dir);
			mostrarDecrescente(raiz.esq);
			if (chave != raiz.chave) {
				System.out.println(raiz.chave);
			}
		}
	}
	
	// Exercicio 1 f)
	public void mostrarSubArvoreDireita(int chave) {
		Nodo nodoExistente = buscaNodo(chave); // metódo buscaNodo foi criado no exercicio 1 d) acima
		if (nodoExistente == null) {
			System.out.println("Nodo não existente na árvore!");
			return;
		}
			
		subArvoreDireta(nodoExistente.dir);
	}
		
	private void subArvoreDireta(Nodo raiz) {
		if (raiz != null) {
			mostrarCrescente(raiz);
		}
	}
	
	// Exercicio 1 g)
	public void mostrarSubArvoreEsquerda(int chave) {
		Nodo nodoExistente = buscaNodo(chave); // metódo buscaNodo foi criado no exercicio 1 d) acima
		if (nodoExistente == null) {
			System.out.println("Nodo não existente na árvore!");
			return;
		}
			
		subArvoreEsquerda(nodoExistente.esq);
	}
		
	private void subArvoreEsquerda(Nodo raiz) {
		if (raiz != null) {
			mostrarCrescente(raiz);
		}
	}
	
	// Exercicio 1 h)
	public LinkedList<Integer> criarListaEncadeada() {
		if (raiz == null) {
			System.out.println("Árvore vazia!");
			return null;
		}
		Queue<Nodo> fila = new LinkedList<>();
		LinkedList<Integer> lista = new LinkedList<>();
		fila.add(raiz);
		
		while (!fila.isEmpty()) {
			int tamanhoNivel = fila.size();
			for (int i = 0; i < tamanhoNivel; i++) {
				Nodo nodoAtual = fila.poll();
				if (nodoAtual != null) {
					lista.add(nodoAtual.chave);
					fila.add(nodoAtual.esq);
					fila.add(nodoAtual.dir);
				}
			}
		}
		return lista;
	}
	
	// Exercicio 1 i)
	public void mostrarPares() {
		if (raiz == null) {
			System.out.println("Árvore vazia!");
			return;
		}
		Queue<Nodo> fila = new LinkedList<>();
		fila.add(raiz);
		
		while (!fila.isEmpty()) {
			int tamanhoNivel = fila.size();
			for (int i = 0; i < tamanhoNivel; i++) {
				Nodo nodoAtual = fila.poll();
				if (nodoAtual != null) {
					if (nodoAtual.chave % 2 == 0) {
						System.out.println(nodoAtual.chave);
					}
					fila.add(nodoAtual.esq);
					fila.add(nodoAtual.dir);
				}
			}
		}
	}
	
	// Exercicio 1 j)
	public void nivelNodo(int chave) {
		if (raiz == null) {
			System.out.println("Árvore vazia!");
			return;
		}
		Queue<Nodo> fila = new LinkedList<>();
		fila.add(raiz);
		
		int nivel = 1;
		while (!fila.isEmpty()) {
			int tamanhoNivel = fila.size();
			for (int i = 0; i < tamanhoNivel; i++) {
				Nodo nodoAtual = fila.poll();
				if (nodoAtual != null) {
					if (nodoAtual.chave == chave) {
						System.out.println(nivel);
					}
					fila.add(nodoAtual.esq);
					fila.add(nodoAtual.dir);
				}
			}
			nivel++;
		}
	}
	
	// Exercicio 1 k)
	public void altura() {
		if (raiz == null) {
			System.out.println("Árvore vazia!");
			return;
		}
		Queue<Nodo> fila = new LinkedList<>();
		fila.add(raiz);
		
		int nivel = 1;
		int ultimoNivel = nivel;
		while (!fila.isEmpty()) {
			int tamanhoNivel = fila.size();
			for (int i = 0; i < tamanhoNivel; i++) {
				Nodo nodoAtual = fila.poll();
				if (nodoAtual != null) {
					if (nodoAtual.esq == null && nodoAtual.dir == null) {
						ultimoNivel = nivel;
					}
					fila.add(nodoAtual.esq);
					fila.add(nodoAtual.dir);
				}
			}
			nivel++;
		}
		System.out.println(ultimoNivel);
	}
	
	// Exercicio 1 l)
	public void tamanho() {
		if (raiz == null) {
			System.out.println("Árvore vazia!");
			return;
		}
		Queue<Nodo> fila = new LinkedList<>();
		fila.add(raiz);
		
		int tamanho = 0;
		while (!fila.isEmpty()) {
			int tamanhoNivel = fila.size();
			for (int i = 0; i < tamanhoNivel; i++) {
				Nodo nodoAtual = fila.poll();
				if (nodoAtual != null) {
					fila.add(nodoAtual.esq);
					fila.add(nodoAtual.dir);
					tamanho++;
				}
				
			}
		}
		System.out.println(tamanho);
	}
	
	// Exercicio 1 m)
	public void inserirSemRecursao(int chave) {
		Nodo novoNodo = new Nodo(chave);
		
		 if (raiz == null) {
		 	raiz = novoNodo;
		 	return;
		 }
		
		 Nodo nodoAtual = raiz;
		 Nodo pai;
		
		 while (true) {
		 	pai = nodoAtual;
		
		 	if (chave < nodoAtual.chave) {
		 		nodoAtual = nodoAtual.esq;
		 		if (nodoAtual == null) {
					 pai.esq = novoNodo;
		 			 return;
		 		}
		 	} else if (chave > nodoAtual.chave) {
		 		nodoAtual = nodoAtual.dir;
		 		if (nodoAtual == null) {
		 			pai.dir = novoNodo;
		 			return;
		 		}
		 	} else {
		 		// Caso em que a chave já existe na árvore
		 		return;
		 	}
		 }
	}

}
