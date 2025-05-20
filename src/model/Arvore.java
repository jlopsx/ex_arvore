package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Arvore {

    private No raiz;
    private static List<Integer> ordem = new ArrayList<>();

    private No inserirNo(No atual, No novoNo)
    {
        if (atual == null)
            return novoNo;
        else if (novoNo.getValor() < atual.getValor())
            atual.setEsq(inserirNo(atual.getEsq(), novoNo));
        else
            atual.setDir(inserirNo(atual.getDir(), novoNo));
        return atual;
    }

    public void inserir(No novoNo)
    {
        raiz = inserirNo(raiz, novoNo);
        atribuirCamadasRec(raiz, 0);
    }

    public void exibir()
    {
        System.out.println("Itens na lista");

        exibirArvore(raiz);
    }

    private void exibirArvore(No atual)
    {
        if(atual!=null)
        {
            exibirArvore(atual.getEsq());
            System.out.println(atual.getValor());
            System.out.println(atual.getCamada());
            exibirArvore(atual.getDir());
        }
    }

    public void excluir(int item)
    {
        try {
            No atual = raiz;
            No pai = null, filho = null, temp = null;

            while(atual != null && atual.getValor() != item){
                pai = atual;
                if (item < atual.getValor())
                    atual = atual.getEsq();
                else
                    atual = atual.getDir();
            }

            if (atual == null)
                System.out.println("Elemento não localizado");
            if (pai == null) {
                if (atual.getDir() == null)
                    raiz = atual.getEsq();
                else if (atual.getEsq() == null)
                    raiz = atual.getDir();
                else{
                    for (temp=atual, filho=atual.getEsq();
                         filho.getDir() !=null;
                         temp=filho, filho=filho.getDir());

                    if (filho != atual.getEsq()){
                        temp.setDir(filho.getEsq());
                        filho.setEsq(raiz.getEsq());
                    }
                    filho.setDir(raiz.getDir());
                    raiz = filho;
                }
            }
            else if(atual.getDir() == null){
                if(pai.getEsq() == atual)
                    pai.setEsq(atual.getEsq());
                else
                    pai.setDir(atual.getEsq());
            }
            else if (atual.getEsq() == null) {
                if(pai.getEsq() == atual)
                    pai.setEsq(atual.getDir());
                else
                    pai.setDir(atual.getDir());
            }
            else{
                for (temp=atual, filho=atual.getEsq();
                     filho.getDir() !=null;
                     temp=filho, filho=filho.getDir());

                if(filho != atual.getEsq()){
                    temp.setDir(filho.getEsq());
                    filho.setEsq(atual.getEsq());
                }
                filho.setDir(atual.getDir());
                if (pai.getEsq() == atual)
                    pai.setEsq(filho);
                else
                    pai.setDir(filho);
            }
        }catch (NullPointerException erro) {
            // Elemento não encontrado
        }
    }



    public int conta(No atual)
    {
        if (atual == null)
        {
            return 0;
        }

        int contador = 1;

        contador += conta(atual.getEsq());
        contador += conta(atual.getDir());
        return contador;

    }
    public void contaArvore()
    {
        System.out.println(conta(raiz));
    }

    public void chamaRastreador(int num)
    {
        System.out.println(rastreador(raiz, num));
    }

    private Integer rastreador(No atual, int num)
    {
        if (atual == null)
        {
            return null;
        }
        System.out.println(atual.getValor());
        if (atual.getValor() == num)
        {
            return num;
        }
        if (rastreador(atual.getEsq(), num) != null)
        {
            return rastreador(atual.getEsq(), num);
        }

        return rastreador(atual.getDir(), num);

    }

    public void ordenaArvore()
    {
        ordem.clear();
        ordem.add(raiz.getValor());
        ordena(raiz);

        Collections.sort(ordem);
        for (int valores : ordem)
        {
            System.out.println(valores);
        }
    }

    private int ordena(No atual)
    {
        if (atual.getDir() != null)
        {
           ordem.add(ordena(atual.getDir()));
        }
        if (atual.getEsq() !=null)
        {
            ordem.add(ordena(atual.getEsq()));
        }

        return atual.getValor();


    }


    private void atribuirCamadasRec(No atual, int camada) {
        if (atual != null) {
            atual.setCamada(camada);
            atribuirCamadasRec(atual.getEsq(), camada + 1);
            atribuirCamadasRec(atual.getDir(), camada + 1);
        }
    }

}
