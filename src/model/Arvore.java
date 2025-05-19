package model;

public class Arvore {

    private No raiz;

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
        rastreador(raiz, num);
    }

    public int rastreador(No atual, int num)
    {
        if (atual.getValor() == num)
        {
            return num;
        }
        return 0;
    }

}
