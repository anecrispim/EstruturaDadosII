package arvores;

import java.util.ArrayList;
import java.util.List;

public class Nodo {
	List<Integer> chaves;
    List<Nodo> filho;
    boolean isFolha;

    Nodo(boolean isFolha) {
        this.chaves = new ArrayList<>();
        this.filho = new ArrayList<>();
        this.isFolha = isFolha;
    }
}
