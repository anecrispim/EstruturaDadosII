package arvores;

import java.util.LinkedList;
import java.util.Queue;

public class ArvoreAVL {
	// classe Nodo
	private class Nodo {
        private int dado;
        private int altd, alte;
        private Nodo dir, esq;
        private int count;

        public Nodo(int dado) {
            this.dado = dado;
            dir = esq = null;
            altd = alte = 0;
            this.count = 1;
        }
    }
	
	Nodo raiz;

	public ArvoreAVL() {
		raiz = null;
	}
	
	public void inserir(int dado) {
		raiz = inserirDado(raiz, dado);
	}
	
	private Nodo inserirDado(Nodo raiz, int dado) {
		if (raiz == null) {
			raiz = new Nodo(dado);
			return raiz;
		}
		
		if (dado < raiz.dado) {
			raiz.esq = inserirDado(raiz.esq, dado);
			if (raiz.esq.altd > raiz.esq.alte) {
				raiz.alte = raiz.esq.altd + 1;
			} else {
				raiz.alte = raiz.esq.alte + 1;
			}
			
			raiz = balanceamento(raiz);
		} else if (dado > raiz.dado) {
			raiz.dir = inserirDado(raiz.dir, dado);
			if (raiz.dir.altd > raiz.dir.alte) {
				raiz.altd = raiz.dir.altd + 1;
			} else {
				raiz.altd = raiz.dir.alte + 1;
			}
			raiz = balanceamento(raiz);
		}
		return raiz;
	}
	
	private Nodo balanceamento(Nodo raiz) {
		int fb = raiz.altd - raiz.alte; // fator balanceamento
		int fbSubArv; // fator balanceamento da sub árvore
		
		if (fb == 2) {
			fbSubArv = raiz.dir.altd - raiz.alte;
			if (fbSubArv >= 0) {
				raiz = rotacao_esquerda(raiz);
			} else {
				raiz.dir = rotacao_direita(raiz.dir);
				raiz = rotacao_esquerda(raiz);
			}
		} else if (fb == -2) {
			fbSubArv = raiz.esq.altd - raiz.esq.alte;
			if (fbSubArv <= 0) {
				raiz = rotacao_direita(raiz);
			} else {
				raiz.esq = rotacao_esquerda(raiz.esq);
				raiz = rotacao_direita(raiz);
			}
		}
		return raiz;
	}
	
	private Nodo rotacao_esquerda (Nodo raiz) {
		Nodo aux1, aux2;
		aux1 = raiz.dir;
		aux2 = raiz.esq;
		raiz.dir = aux2;
		aux1.esq = raiz;
		if (raiz.dir == null) {
			raiz.altd = 0;
		} else if (raiz.dir.alte > raiz.dir.altd) {
			raiz.altd = raiz.dir.alte + 1;
		} else {
			raiz.altd = raiz.dir.altd + 1;
		}
		
		if (aux1.esq.alte > aux1.esq.altd) {
			aux1.alte = aux1.esq.alte +1;
		} else {
			aux1.alte = aux1.esq.altd + 1;
		}
		return aux1;
	}
	
	private Nodo rotacao_direita(Nodo raiz) {
		Nodo aux1, aux2;
		aux1 = raiz.esq;
		aux2 = raiz.dir;
		raiz.esq = aux2;
		aux1.dir = raiz;
		if (raiz.esq == null) {
			raiz.alte = 0;
		} else if (raiz.esq.alte > raiz.esq.altd) {
			raiz.alte = raiz.esq.alte + 1;
		} else {
			raiz.alte = raiz.esq.altd + 1;
		}
		
		if (aux1.dir.alte > aux1.dir.altd) {
			aux1.altd = aux1.dir.alte + 1;
		} else {
			aux1.altd = aux1.dir.altd + 1;
		}
		return aux1;
	}
	
	public void mostrarEmOrdem() {
		mostrandoOrdenado(raiz);
	}
	
	private void mostrandoOrdenado(Nodo raiz) {
		if (raiz != null) {
			mostrandoOrdenado(raiz.esq);
			System.out.println(raiz.dado);
			mostrandoOrdenado(raiz.dir);
		}
	}
	
	// Exercício 2
	public void excluir(int dado) {
		raiz = excluirNodo(raiz, dado);
	}

	private Nodo excluirNodo(Nodo raiz, int dado) {
	    if (raiz == null) {
	        return raiz;
	    }

	    if (dado < raiz.dado) {
	        raiz.esq = excluirNodo(raiz.esq, dado);
	    } else if (dado > raiz.dado) {
	        raiz.dir = excluirNodo(raiz.dir, dado);
	    } else {
	        if (raiz.esq == null || raiz.dir == null) {
	            Nodo temp = (raiz.esq != null) ? raiz.esq : raiz.dir;

	            if (temp == null) {
	                temp = raiz;
	                raiz = null;
	            } else {
	                raiz = temp;
	            }
	        } else {
	            Nodo temp = menorNodo(raiz.dir);
	            raiz.dado = temp.dado;
	            raiz.dir = excluirNodo(raiz.dir, temp.dado);
	        }
	    }
	    if (raiz == null) {
	        return raiz;
	    }

	    if (raiz.esq == null) {
	        raiz.alte = 0;
	    } else {
	        raiz.alte = Math.max(raiz.esq.alte, raiz.esq.altd) + 1;
	    }
	    
	    if (raiz.dir == null) {
	        raiz.altd = 0;
	    } else {
	        raiz.altd = Math.max(raiz.dir.alte, raiz.dir.altd) + 1;
	    }

	    return balanceamento(raiz);
	}

	private Nodo menorNodo(Nodo nodo) {
	    Nodo atual = nodo;
	    while (atual.esq != null) {
	        atual = atual.esq;
	    }
	    return atual;
	}
	
	// Exercicio 3
	public boolean isAVL() {
        return isAVL(raiz);
    }

    private boolean isAVL(Nodo raiz) {
        if (raiz == null) {
            return true;
        }

        int fb = calcularFatorBalanceamento(raiz);

        if (Math.abs(fb) > 1) {
            return false;
        }
        return isAVL(raiz.esq) && isAVL(raiz.dir);
    }

    private int calcularFatorBalanceamento(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }

        int alturaEsq = calcularAltura(nodo.esq);
        int alturaDir = calcularAltura(nodo.dir);
        return alturaDir - alturaEsq;
    }

    private int calcularAltura(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }

        int alturaEsq = calcularAltura(nodo.esq);
        int alturaDir = calcularAltura(nodo.dir);
        return Math.max(alturaEsq, alturaDir) + 1;
    }
    
    // Exercicio 4
    public int contaPrimoNodes() {
        return contaPrimoNodes(raiz);
    }

    private int contaPrimoNodes(Nodo raiz) {
        if (raiz == null) {
            return 0;
        }

        if (isPrimo(raiz.dado)) {
            return 1 + contaPrimoNodes(raiz.esq) + contaPrimoNodes(raiz.dir);
        } else {
            return contaPrimoNodes(raiz.esq) + contaPrimoNodes(raiz.dir);
        }
    }

    private boolean isPrimo(int num) {
        if (num <= 1) {
            return false;
        }
        if (num <= 3) {
            return true;
        }
        if (num % 2 == 0 || num % 3 == 0) {
            return false;
        }
        for (int i = 5; i * i <= num; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
    
    // Exercicio 5
    public void inserirDuplicado(int dado) {
        raiz = inserirDadoDuplicado(raiz, dado);
    }

    private Nodo inserirDadoDuplicado(Nodo raiz, int dado) {
        if (raiz == null) {
            raiz = new Nodo(dado);
            return raiz;
        }

        if (dado < raiz.dado) {
            raiz.esq = inserirDadoDuplicado(raiz.esq, dado);
        } else if (dado > raiz.dado) {
            raiz.dir = inserirDadoDuplicado(raiz.dir, dado);
        } else {
            raiz.count++;
        }

        atualizaAlturas(raiz);
        raiz = balanceamento(raiz);

        return raiz;
    }

    private void atualizaAlturas(Nodo raiz) {
        if (raiz == null) {
            return;
        }
        raiz.alte = (raiz.esq != null) ? Math.max(raiz.esq.alte, raiz.esq.altd) + 1 : 0;
        raiz.altd = (raiz.dir != null) ? Math.max(raiz.dir.alte, raiz.dir.altd) + 1 : 0;
    }
    
    // Exercicio 6
    public void mostrarNodosNoNivel(int nivel) {
        mostrarNodosNoNivel(raiz, nivel);
    }

    private void mostrarNodosNoNivel(Nodo raiz, int nivelDesejado) {
        if (raiz == null) {
            return;
        }

        Queue<Nodo> queue = new LinkedList<>();
        queue.offer(raiz);
        int nivelAtual = 0;

        while (!queue.isEmpty() && nivelAtual <= nivelDesejado) {
            int nodosNoNivelAtual = queue.size();
            
            if (nivelAtual == nivelDesejado) {
                System.out.print("Nodos do nível " + nivelDesejado + ": ");
                for (int i = 0; i < nodosNoNivelAtual; i++) {
                    Nodo node = queue.poll();
                    if (node != null) {
                        System.out.print("(" + node.dado + ", qtd: " + node.count + ") ");
                    }
                }
                System.out.println();
            }
            
            for (int i = 0; i < nodosNoNivelAtual; i++) {
                Nodo node = queue.poll();
                
                if (node != null) {
                    if (node.esq != null) {
                        queue.offer(node.esq);
                    }
                    if (node.dir != null) {
                        queue.offer(node.dir);
                    }
                }
            }
            
            nivelAtual++;
        }
    }
    
    // Exercicio 7
    public int somarNosNiveisImpares() {
        return somarNosNiveisImpares(raiz);
    }

    private int somarNosNiveisImpares(Nodo raiz) {
        if (raiz == null) {
            return 0;
        }

        Queue<Nodo> queue = new LinkedList<>();
        queue.offer(raiz);
        int nivelAtual = 1; 
        int soma = 0;

        while (!queue.isEmpty()) {
            int nodosNoNivelAtual = queue.size();
            
            if (nivelAtual % 2 == 1) {
                for (int i = 0; i < nodosNoNivelAtual; i++) {
                    Nodo node = queue.poll();
                    soma += node.dado;
                }
            } else {
                for (int i = 0; i < nodosNoNivelAtual; i++) {
                    queue.poll();
                }
            }
            
            for (int i = 0; i < nodosNoNivelAtual; i++) {
                Nodo node = queue.poll();
                
                if (node != null) {
                    if (node.esq != null) {
                        queue.offer(node.esq);
                    }
                    if (node.dir != null) {
                        queue.offer(node.dir);
                    }
                }
            }
            
            nivelAtual++;
        }

        return soma;
    }


}
