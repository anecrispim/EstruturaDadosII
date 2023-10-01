package arvores;

// classe Municipio
public class Municipio {
    private String nome;
    private double areaTotal;
    private int populacao;
    private double densidadeDemografica;

    public Municipio(String nome, double areaTotal, int populacao) {
        this.nome = nome;
        this.areaTotal = areaTotal;
        this.populacao = populacao;
        calcularDensidadeDemografica();
    }

    public String getNome() {
        return nome;
    }

    public double getAreaTotal() {
        return areaTotal;
    }

    public int getPopulacao() {
        return populacao;
    }
    
    
    public double getDensidadeDemografica() {
        return densidadeDemografica;
    }

    private void calcularDensidadeDemografica() {
        densidadeDemografica = populacao / areaTotal;
    }
}
