#include <stdio.h>
#include <stdlib.h>

#define MAX 10

struct registro{
    int chave;
    char nome[100];
}regitro;

typedef struct registro Registro;

typedef struct arquivo{
    Registro itens[MAX];
    int tamanho;
}arquivo;

typedef struct arquivo Arquivo;

Arquivo *iniciaArquivo();

int insereRegistro(Arquivo *arq, Registro Elem);

int removeRegistro(Arquivo *arq, int chave);

int pesquisa(Arquivo *arq, int chave);

int busca_seqOrd(Arquivo *arq, int chave);

int busca_bin(Arquivo *arq, int chave);

int main()
{

    Arquivo *arq = iniciaArquivo();
    Registro teste;
    teste.chave = 1;
    *teste.nome = "Vitor";
    Registro teste1;
    teste1.chave = 2;
    *teste1.nome = "Lorena";
    insereRegistro(arq, teste);
    insereRegistro(arq,teste1);
    removeRegistro(arq,2);

    return 0;
}

Arquivo *iniciaArquivo(){
    Arquivo *arq = (Arquivo*)malloc(sizeof(Arquivo));

    if(arq != NULL)
        arq->tamanho = 0;

    return arq;
}

int insereRegistro(Arquivo *arq, Registro Elem){
    if(arq->tamanho == MAX)
        return 0;

    arq->itens[arq->tamanho] = Elem;
    arq->tamanho++;
    return 1;
}

int removeRegistro(Arquivo *arq, int chave){
    if(arq->tamanho == 0)
        return 0;

    int x, posicao = busca_bin(arq,chave);
    if(posicao == -1)
        return 0;

    for(x = posicao; x<arq->tamanho; x++)
        arq->itens[x] = arq->itens[x+1];

    arq->tamanho--;
    return 1;
}

int pesquisa(Arquivo *arq, int chave){
    if(arq->tamanho == 0)
        return -1;

    int x = 0;
    while(x<arq->tamanho && arq->itens[x].chave != chave)
        x++;

    if(x == arq->tamanho)
        return -1;
    return x;
}

int busca_seqOrd(Arquivo *arq, int chave){
    if(arq->tamanho == 0 || arq->itens[0].chave>chave ||
       arq->itens[arq->tamanho-1].chave<chave)
        return -1;

    int x = 0;
    while(arq->itens[x].chave<chave)
        x++;
    if(arq->itens[x].chave>chave)
        return -1;
    return x;
}

int busca_bin(Arquivo *arq, int chave){
    if(arq->tamanho == 0)
        return -1;
    int meio, ini = 0, fim = arq->tamanho-1;
    while(ini <= fim){
        meio = (ini + fim)/2;
        if(arq->itens[meio].chave == chave)
            return meio;
        else if(arq->itens[meio].chave > chave)
            fim = meio-1;
        else
            ini = meio+1;
    }

    return -1;
}
