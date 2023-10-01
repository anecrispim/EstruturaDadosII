package arvores;

public class TesteAVL {

	public static void main(String[] args) {
		ArvoreAVL arvore = new ArvoreAVL();
		
		System.out.println("Árvore com inserção SEM duplicidade: ");
		arvore.inserir(30);
		arvore.inserir(25);
		arvore.inserir(11);
		arvore.inserir(2);
		arvore.inserir(40);
		arvore.inserir(27);
		arvore.inserir(45);
		
		arvore.mostrarEmOrdem();
		
		arvore.excluir(30);
		System.out.println("");
		
		arvore.mostrarEmOrdem();
		
		System.out.println("Número de nodos primos: ");
		System.out.println(arvore.contaPrimoNodes());
		
		System.out.println("Árvore com inserção COM duplicidade: ");
		ArvoreAVL arvore2 = new ArvoreAVL();
		arvore2.inserir(30);
		arvore2.inserir(25);
		arvore2.inserir(11);
		arvore2.inserir(30);
		arvore2.inserir(45);
		arvore2.inserir(27);
		arvore2.inserir(45);
		
		arvore2.mostrarEmOrdem();
		
		arvore.mostrarNodosNoNivel(2);
		
		System.out.println("Níveis impares:");
		System.out.println(arvore.somarNosNiveisImpares());
	}

}
