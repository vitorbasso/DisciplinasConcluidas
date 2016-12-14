#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include "arvore_binaria.h"
#include "Fila.h"



Arvore cria_vazia(){
    return NULL;
}

Arvore cria_arvore(int elemento, Arvore esquerdo, Arvore direito){

    Node *raiz = malloc(sizeof(Node));

    if(raiz == NULL)
        return NULL;

    raiz->info = elemento;
    raiz->sae = esquerdo;
    raiz->sad = direito;

    return raiz;

}


int arvore_vazia(Arvore arvore){

    if(arvore == NULL)
        return 1;
    return 0;

}


void libera_arvore(Arvore* arvore){

    if(!arvore_vazia(*arvore)){
        libera_arvore(&((*arvore)->sae));
        libera_arvore(&((*arvore)->sad));
        free((*arvore));
    }

    (*arvore) = NULL;

}

int existe(Arvore arvore, int elemento){

    if(arvore_vazia(arvore))
        return 0;

    if(arvore->info == elemento)
        return 1;

    if(1 == existe(arvore->sae, elemento))
        return 1;
    if(1 == existe(arvore->sad, elemento))
        return 0;

    return 0;

}

int remove_folha(Arvore *arvore, int elemento){

    if(arvore_vazia(*arvore))
        return 0;

    if((*arvore)->info == elemento){
        if(arvore_vazia((*arvore)->sae) && arvore_vazia((*arvore)->sad)){
            free(*arvore);
            *arvore = NULL;
            return 1;
        }else
            return 0;
    }else{
        if(1 == remove_folha(&((*arvore)->sae), elemento))
            return 1;
        if(1 == remove_folha(&((*arvore)->sad), elemento))
            return 1;

        return 0;
    }

}


void preorder(Arvore arvore){

    if(arvore_vazia(arvore))
        printf("<>");
    else{

        printf("<");

        printf("%d", arvore->info);

        preorder(arvore->sae);
        preorder(arvore->sad);

        printf(">");

    }

}

void postorder(Arvore arvore){

    if(arvore_vazia(arvore))
        printf("<>");
    else{

        printf("<");

        postorder(arvore->sae);
        postorder(arvore->sad);

        printf("%d", arvore->info);

        printf(">");

    }

}

void inorder(Arvore arvore){

    if(arvore_vazia(arvore))
        printf("<>");
    else{

        printf("<");

        postorder(arvore->sae);

        printf("%d", arvore->info);

        postorder(arvore->sad);

        printf(">");

    }

}

void percorre_nivel(Arvore arvore){

    if (arvore == NULL)
        return;

    Fila *fila = malloc(sizeof(Fila));
    criarF(fila);

    enfilar(&fila, arvore);

    printf("\n");

    while(fila->qnt != 0){

        Arvore next = fila->first->next->arvore;

        if(arvore_vazia(next))
            printf("<>");
        else
            printf("<%d>", (next)->info);

        if(!arvore_vazia((next)->sae))
            enfilar(&fila, (next)->sae);
        if(!arvore_vazia((next)->sad))
            enfilar(&fila, (next)->sad);

        desenfilar(&fila);

    }

}

int gera_notacao(Arvore A) {
    //Gera notacao textual da Arvore A e retorna na String S
    //1 bem sucedido , 0 contrario
    if(arvore_vazia(A))
        printf("<>");
    else{

        printf("<");

        printf("%d", A->info);

        gera_notacao(A->sae);
        gera_notacao(A->sad);

        printf(">");

    }


    return 1;
}

int folha(Arvore arvore){

    if(arvore_vazia(arvore))
        return 0;

    if(arvore_vazia(arvore->sae) && arvore_vazia(arvore->sad))
        return 1;

    return 0;

}

int nro_folha (Arvore A) {
    if (arvore_vazia(A))
        return 0;

    if (folha(A))
        return 1;

    return nro_folha(A->sae) + nro_folha(A->sad);
}

int nro_derivacao (Arvore A) {
    //Retorna o numero de nos de derivacao de A
    return 0;
}

int um_filho (Arvore A) {
    //Retorna o numero de nos que possuem apenas um filho em A
    if (arvore_vazia(A))
        return 0;

    if (arvore_vazia(A->sae) && !arvore_vazia(A->sad))
        return 1 + um_filho(A->sad);

    if (arvore_vazia(A->sad) && !arvore_vazia(A->sae))
        return 1 + um_filho(A->sae);

    return um_filho(A->sae) + um_filho(A->sad);
}

int altura_arv(Arvore arvore){

    if (arvore_vazia(arvore))
        return 0;

    if(folha(arvore))
        return 0;

    if (altura_arv(arvore->sae) < altura_arv(arvore->sad))
        return altura_arv(arvore->sad) + 1;

    return altura_arv(arvore->sae) + 1;
}

int cheia (Arvore A) {
    //Retorna 1 se arvore cheia, 0 caso contrario

    int h = altura_arv(A);
    int folhas = nro_folha(A);

    return (folhas == pow(2.0, (double)h));
}

int nivel_no (Arvore A, int elem, int p) {
    //Retorna o nivel (profundidade) do no com conteudo igual a elem
    //Retornar -1 caso o elemento nao exista

    if (arvore_vazia(A))
        return -1;

    if (A->info == elem)
        return p;

    int ret = nivel_no(A->sae, elem, p + 1);
    if (ret != -1)
        return ret;

    ret = nivel_no(A->sad, elem, p + 1);

    return ret;
}

int iguais (Arvore A1, Arvore A2) {
    //Retorna 1 caso a Arvore A1 seja igual a Arvore A2

    if (arvore_vazia(A1) && arvore_vazia(A2))
        return 1;

    if (arvore_vazia(A1) || arvore_vazia(A2))
        return 0;

    if (A1->info != A2->info)
        return 0;

    return iguais(A1->sae, A2->sae) && iguais(A1->sad, A2->sad);
}

int altura_no (Arvore A, int elem) {
    //Retorna a altura do elemento elem, -1 caso nao exista na arvore

    if (arvore_vazia(A))
        return -1;

    if (A->info == elem)
        return altura_arv(A);

    int ret = altura_no(A->sae, elem);

    if (ret != -1)
        return ret;

    ret = altura_no(A->sad, elem);

    return ret;
}


