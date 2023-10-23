package arvores;

public class ArvoreB {
	private Nodo nodo;
    private int ordem;

    public ArvoreB(int ordem) {
        this.nodo = new Nodo(true);
        this.ordem = ordem;
    }

    public boolean busca(int chave) {
        return buscaChave(nodo, chave);
    }

    private boolean buscaChave(Nodo nodo, int chave) {
        int i = 0;
        while (i < nodo.chaves.size() && chave > nodo.chaves.get(i)) {
            i++;
        }
        if (i < nodo.chaves.size() && chave == nodo.chaves.get(i)) {
            return true;
        }
        if (nodo.isFolha) {
            return false;
        } else {
            return buscaChave(nodo.filho.get(i), chave);
        }
    }

    
    public void inserir(int chave) {
        Nodo aux = nodo;
        if (aux.chaves.size() == (2 * ordem) - 1) {
            Nodo novoNodo = new Nodo(false);
            novoNodo.filho.add(nodo);
            splitFilho(novoNodo, 0);
            nodo = novoNodo;
            aux = novoNodo;
        }
        inserirChave(aux, chave);
    }

    private void inserirChave(Nodo nodo, int chave) {
        while (true) {
            int i = nodo.chaves.size() - 1;
            if (nodo.isFolha) {
                while (i >= 0 && chave < nodo.chaves.get(i)) {
                    i--;
                }
                nodo.chaves.add(i + 1, chave);
                break;
            } else {
                while (i >= 0 && chave < nodo.chaves.get(i)) {
                    i--;
                }
                i++;
                if (nodo.filho.get(i).chaves.size() == (2 * ordem) - 1) {
                	splitFilho(nodo, i);
                    if (chave > nodo.chaves.get(i)) {
                        i++;
                    }
                }
                nodo = nodo.filho.get(i);
            }
        }
    }

    private void splitFilho(Nodo nodo, int i) {
        Nodo y = nodo.filho.get(i);
        Nodo z = new Nodo(y.isFolha);
        nodo.filho.add(i + 1, z);
        nodo.chaves.add(i, y.chaves.get(ordem - 1));
        z.chaves.addAll(y.chaves.subList(ordem, 2 * ordem - 1));
        y.chaves.subList(ordem - 1, 2 * ordem - 1).clear();
        if (!y.isFolha) {
            z.filho.addAll(y.filho.subList(ordem, 2 * ordem));
            y.filho.subList(ordem, 2 * ordem).clear();
        }
    }

    public void remover(int chave) {
        removerChave(nodo, chave);
    }

    private void removerChave(Nodo nodo, int chave) {
    	int idNodo = encontraChaveIndex(nodo, chave);
        if (idNodo < nodo.chaves.size() && chave == nodo.chaves.get(idNodo)) {
            if (nodo.isFolha) {
                // Se a chave está em um nó folha, remove a chave.
                nodo.chaves.remove(idNodo);
            } else {
                // Se a chave está em um nó interno, substitui a chave pela chave sucessora e remove a chave sucessora.
                Nodo successor = encontraMin(nodo.filho.get(idNodo + 1));
                int successorChave = successor.chaves.get(0);
                removerChave(nodo.filho.get(idNodo + 1), successorChave);
                nodo.chaves.set(idNodo, successorChave);
            }
        } else {
            /* Se a chave não está presente no nó corrente e o nó não é folha, então procura
        	recursivamente no filho apropriado.*/
            if (nodo.isFolha) {
                // Se a chave não está presente em um nó folha, então a chave não está na árvore.
                System.out.println("Chave " + chave + " não encontrada na árvore.");
                return;
            }
            boolean ultimoFilho = (idNodo == nodo.chaves.size());
            Nodo filho = nodo.filho.get(idNodo);
            if (filho.chaves.size() < ordem) {
                preencher(nodo, idNodo);
            }
            if (ultimoFilho && idNodo > nodo.chaves.size()) {
            	removerChave(nodo.filho.get(idNodo - 1), chave);
            } else {
            	removerChave(nodo.filho.get(idNodo), chave);
            }
        }
    }
    
    private int encontraChaveIndex(Nodo nodo, int chave) {
        int idNodo = 0;
        while (idNodo < nodo.chaves.size() && chave > nodo.chaves.get(idNodo)) {
            idNodo++;
        }
        return idNodo;
    }

    private Nodo encontraMin(Nodo nodo) {
        while (!nodo.isFolha) {
            nodo = nodo.filho.get(0);
        }
        return nodo;
    }

    private void preencher(Nodo nodo, int idNodo) {
        if (idNodo != 0 && nodo.filho.get(idNodo - 1).chaves.size() >= ordem) {
            pegarAnterior(nodo, idNodo);
        } else if (idNodo != nodo.chaves.size() && nodo.filho.get(idNodo + 1).chaves.size() >= ordem) {
            pegarProximo(nodo, idNodo);
        } else {
            if (idNodo != nodo.chaves.size()) {
                merge(nodo, idNodo);
            } else {
                merge(nodo, idNodo - 1);
            }
        }
    }

    private void pegarAnterior(Nodo nodo, int idNodo) {
        Nodo filho = nodo.filho.get(idNodo);
        Nodo irmao = nodo.filho.get(idNodo - 1);
        // Mover a chave do pai para o nó filho.
        filho.chaves.add(0, nodo.chaves.get(idNodo - 1));
        // Mover a última chave do irmão para o pai.
        nodo.chaves.set(idNodo - 1, irmao.chaves.remove(irmao.chaves.size() - 1));
        if (!irmao.isFolha) {
            filho.filho.add(0, irmao.filho.remove(irmao.filho.size() - 1));
        }
    }

    private void pegarProximo(Nodo nodo, int idNodo) {
        Nodo filho = nodo.filho.get(idNodo);
        Nodo irmao = nodo.filho.get(idNodo + 1);
        // Mover a chave do pai para o nó filho.
        filho.chaves.add(nodo.chaves.get(idNodo));
        // Mover a primeira chave do irmão para o pai.
        nodo.chaves.set(idNodo, irmao.chaves.remove(0));
        if (!irmao.isFolha) {
            filho.filho.add(irmao.filho.remove(0));
        }
    }

    private void merge(Nodo nodo, int idNodo) {
        Nodo filho = nodo.filho.get(idNodo);
        Nodo irmao = nodo.filho.get(idNodo + 1);
        // Adicionar a chave do pai ao nó filho.
        filho.chaves.add(nodo.chaves.remove(idNodo));
        // Adicionar todas as chaves e filhos do irmão ao nó filho.
        filho.chaves.addAll(irmao.chaves);
        filho.filho.addAll(irmao.filho);
        // Remover o irmão.
        nodo.filho.remove(idNodo + 1);
    }
    
    public void mostrarElementos() {
        mostrarElementos(nodo);
    }

    private void mostrarElementos(Nodo nodo) {
    	if (nodo != null) {
            int i;
            for (i = 0; i < nodo.chaves.size(); i++) {
                // Recursivamente visitar o i-ésimo filho do nó.
                mostrarElementos(nodo.filho.get(i));

                // Exibir a chave i-ésima do nó.
                System.out.print(nodo.chaves.get(i) + " ");
            }

            // Recursivamente visitar o último filho do nó.
            if (!nodo.isFolha) {
                mostrarElementos(nodo.filho.get(i));
            }
        }
    }

    public void mostrar() {
        mostrarElementos(nodo);
        System.out.println();
    }
}
