package arvores;

public class TesteArvoreB {

	public static void main(String[] args) {
		ArvoreB ab = new ArvoreB(2);
		ab.inserir(1);
		ab.inserir(3);
        ab.inserir(7);
        ab.inserir(10);
        ab.inserir(11);
        ab.inserir(13);
        ab.inserir(14);
        ab.inserir(15);
        ab.inserir(18);
        ab.inserir(16);
        ab.inserir(19);
        ab.inserir(24);
        ab.inserir(25);
        ab.inserir(26);
        ab.inserir(21);
        ab.inserir(4);
        ab.inserir(5);
        ab.inserir(20);
        ab.inserir(22);
        ab.inserir(2);
        ab.inserir(17);
        ab.inserir(12);
        ab.remover(13);

        System.out.println("Busca (15): " + ab.busca(15)); // Saída: true
        System.out.println("Busca (13): " + ab.busca(13)); // Saída: false
        
	}
}
