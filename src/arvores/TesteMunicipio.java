package arvores;

public class TesteMunicipio {

	public static void main(String[] args) {
		
		Municipio m1 = new Municipio("Rio do Sul", 100000, 80000);
		Municipio m2 = new Municipio("Laurentino", 8000, 9000);
		Municipio m3 = new Municipio("Lontras", 10000, 6000);
		Municipio m4 = new Municipio("Ibirama", 1000, 9000);
		
		ArvoreMunicipio municipio = new ArvoreMunicipio();
		
		municipio.inserir(m1);
		municipio.inserir(m2);
		municipio.inserir(m3);
		municipio.inserir(m4);
		
		System.out.println(municipio.contarMunicipios());
	}

}
