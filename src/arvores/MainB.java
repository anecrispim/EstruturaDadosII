package arvores;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MainB {

	public static void main(String[] args) {
		ArvoreB ab = new ArvoreB(10);
		ArvoreAVL avl = new ArvoreAVL();
		ArvoreBinaria abin = new ArvoreBinaria();
		
        int numElmentos = 100000001;
        Random random = new Random();
        Set<Integer> numerosAleatorios = new HashSet<>();
        
        while (numerosAleatorios.size() < numElmentos) { 
            int numeroAleatorio = random.nextInt(numElmentos);
            numerosAleatorios.add(numeroAleatorio);
            avl.inserirNaoRecursivo(numeroAleatorio);
        }
        
        int maiorNumero = encontrarMaiorNumero(numerosAleatorios); 
        
        /*for (int i = 1; i < numElmentos+1; i++) {
			abin.inserirNaoRecursivo(i);
		}*/
        
        long tempoInicial = System.currentTimeMillis();
		
        long tempoFinal = 0;
        System.out.println(avl.buscar(maiorNumero+1));
        //System.out.println(abin.buscaNodo(numElmentos+2));
		tempoFinal = System.currentTimeMillis();
		
		long tempoDeProcessamento = tempoFinal - tempoInicial;
		
		System.out.println("Tempo de processamento: " + tempoDeProcessamento + " milissegundos");
	}
	
	// Método para encontrar o maior número em um conjunto de inteiros
    private static int encontrarMaiorNumero(Set<Integer> numeros) {
        int maior = Integer.MIN_VALUE;
        for (int numero : numeros) {
            if (numero > maior) {
                maior = numero;
            }
        }
        return maior;
    }

}
