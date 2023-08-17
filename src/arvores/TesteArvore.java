package arvores;

public class TesteArvore {

	public static void main(String[] args) {
		ArvoreBinaria arvore = new ArvoreBinaria();
		
		arvore.inserir(30);
		arvore.inserir(25);
		arvore.inserir(20);
		arvore.inserir(22);
		arvore.inserir(40);
		arvore.inserir(27);
		arvore.inserir(45);
		arvore.inserir(48);
		arvore.inserir(28);
		arvore.inserirSemRecursao(5);
		arvore.inserirSemRecursao(3);
		arvore.inserirSemRecursao(6);
		
		//arvore.mostrarEmOrdemCrescente();
		
		System.out.println("Árvore completa:");
		arvore.mostrarPorNivel();
		
		/*arvore.remover(22);
		arvore.remover(40);
		
		System.out.println("Após remoção");
		
		arvore.mostrarEmOrdemDecrescente();
		*/
		System.out.println("Maior elemento:");
		arvore.mostrarMaior();
		System.out.println("Menor elemento:");
		arvore.mostrarMenor();
		System.out.println("Folhas da árvore:");
		arvore.mostrarFolhas();
		System.out.println("Ancestrais do nodo:");
		arvore.mostrarAncestrais(20);
		System.out.println("Descendentes do nodo:");
		arvore.mostrarDescendentes(25);
		System.out.println("Subarvore a direita do nodo:");
		arvore.mostrarSubArvoreDireita(25);
		System.out.println("Subarvore a esquerda do nodo:");
		arvore.mostrarSubArvoreEsquerda(25);
		System.out.println("Lista encadeada da árvore: ");
		System.out.println(arvore.criarListaEncadeada());
		System.out.println("Elementos pares da árvore: ");
		arvore.mostrarPares();
		System.out.println("Nível nodo: ");
		arvore.nivelNodo(30);
		System.out.println("Altura árvore: ");
		arvore.altura();
		System.out.println("Tamanho árvore: ");
		arvore.tamanho();
	}

}
