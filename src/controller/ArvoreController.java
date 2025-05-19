package controller;

import model.Arvore;
import model.No;

public class ArvoreController {
    public void ControlaArvore() {
        Arvore arv = new Arvore();

        arv.inserir(new No(1));
        arv.inserir(new No(2));
        arv.inserir(new No(3));
        arv.excluir(1);
        arv.contaArvore();
    }
}
