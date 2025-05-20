package controller;

import model.Arvore;
import model.No;

public class ArvoreController {
    public void ControlaArvore() {
        Arvore arv = new Arvore();

        arv.inserir(new No(4));
        arv.inserir(new No(3));
        arv.inserir(new No(2));
        arv.inserir(new No(6));
        arv.inserir(new No(1));
        arv.inserir(new No(5));
        arv.inserir(new No(9));
        arv.inserir(new No(5));
        arv.inserir(new No(2));
        arv.exibir();
        arv.excluir(2);
        arv.chamaRastreador(2);
        arv.ordenaArvore();
    }
}
