package arvores;

// classe ArvoreMunicipio
public class ArvoreMunicipio {
    private class Nodo {
        private Municipio municipio;
        private Nodo esq, dir;

        public Nodo(Municipio municipio) {
            this.municipio = municipio;
            esq = dir = null;
        }
    }

    private Nodo raiz;

    public ArvoreMunicipio() {
        raiz = null;
    }

    public void inserir(Municipio municipio) {
        raiz = inserirNodo(raiz, municipio);
    }

    private Nodo inserirNodo(Nodo raiz, Municipio municipio) {
        if (raiz == null) {
            return new Nodo(municipio);
        }

        int comparacao = municipio.getNome().compareTo(raiz.municipio.getNome());

        if (comparacao < 0) {
            raiz.esq = inserirNodo(raiz.esq, municipio);
        } else if (comparacao > 0) {
            raiz.dir = inserirNodo(raiz.dir, municipio);
        }

        return raiz;
    }
    
    // 8 letra a)
    public int contarMunicipios() {
        return contarMunicipios(raiz);
    }

    private int contarMunicipios(Nodo raiz) {
        if (raiz == null) {
            return 0;
        }

        int contaEsquerda = contarMunicipios(raiz.esq);
        int contaDireita = contarMunicipios(raiz.dir);

        return contaEsquerda + contaDireita + 1;
    }
    
    // 8 letra b)
    public void mostrarMunicipiosComMaisDeXHabitantes(int limitePopulacao) {
        mostrarMunicipiosComMaisDeXHabitantes(raiz, limitePopulacao);
    }

    private void mostrarMunicipiosComMaisDeXHabitantes(Nodo raiz, int limitePopulacao) {
        if (raiz == null) {
            return;
        }

        mostrarMunicipiosComMaisDeXHabitantes(raiz.esq, limitePopulacao);

        if (raiz.municipio.getPopulacao() > limitePopulacao) {
            System.out.println(raiz.municipio.getNome());
        }

        mostrarMunicipiosComMaisDeXHabitantes(raiz.dir, limitePopulacao);
    }
    
    // 8 letra c)
    public void mostrarDensidadeDemografica() {
        mostrarDensidadeDemografica(raiz);
    }

    private void mostrarDensidadeDemografica(Nodo raiz) {
        if (raiz == null) {
            return;
        }
        mostrarDensidadeDemografica(raiz.esq);

        System.out.println("Município: " + raiz.municipio.getNome() + ", Densidade Demográfica: " + raiz.municipio.getDensidadeDemografica());

        mostrarDensidadeDemografica(raiz.dir);
    }
    
    // 8 letra d)
    public double calcularPorcentagemAreaCidadesEmRelacaoAoPais(double areaTotalNacional) {
        double somaAreaCidades = calcularSomaAreaCidades(raiz);
        
        double porcentagem = (somaAreaCidades / areaTotalNacional) * 100.0;

        return porcentagem;
    }

    private double calcularSomaAreaCidades(Nodo raiz) {
        if (raiz == null) {
            return 0;
        }

        double somaEsquerda = calcularSomaAreaCidades(raiz.esq);
        double somaDireita = calcularSomaAreaCidades(raiz.dir);

        return somaEsquerda + somaDireita + raiz.municipio.getAreaTotal();
    }
    
    // 8 letra e)
    public String encontrarCidadeComMaiorPopulacao() {
        if (raiz == null) {
            return "Nenhuma cidade cadastrada";
        }

        Nodo nodoMaiorPopulacao = encontrarCidadeComMaiorPopulacao(raiz);
        return nodoMaiorPopulacao.municipio.getNome();
    }

    private Nodo encontrarCidadeComMaiorPopulacao(Nodo raiz) {
        if (raiz == null) {
            return null;
        }

        Nodo maiorPopulacaoEsquerda = encontrarCidadeComMaiorPopulacao(raiz.esq);
        Nodo maiorPopulacaoDireita = encontrarCidadeComMaiorPopulacao(raiz.dir);

        Nodo maiorAtual = raiz;
        if (maiorPopulacaoEsquerda != null && maiorPopulacaoEsquerda.municipio.getPopulacao() > maiorAtual.municipio.getPopulacao()) {
            maiorAtual = maiorPopulacaoEsquerda;
        }
        if (maiorPopulacaoDireita != null && maiorPopulacaoDireita.municipio.getPopulacao() > maiorAtual.municipio.getPopulacao()) {
            maiorAtual = maiorPopulacaoDireita;
        }

        return maiorAtual;
    }

}
