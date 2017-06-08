#include <stdio.h>
#include <stdlib.h>
#include "Arvore.h"
#include "Fila.h"


Arvore cria_vazia(){
    return NULL;
}

Arvore cria_arvore(int elemento, Arvore esquerdo, Arvore direito){

    Arvore raiz = (Arvore)malloc(sizeof(Node));

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


void exibe_arvore_preorder(Arvore arvore){

    if(arvore_vazia(arvore))
        printf("<>");
    else{

        printf("<");

        printf("%d", arvore->info);

        exibe_arvore_preorder(arvore->sae);
        exibe_arvore_preorder(arvore->sad);

        printf(">");

    }

}

void exibe_arvore_postorder(Arvore arvore){

    if(arvore_vazia(arvore))
        printf("<>");
    else{

        printf("<");

        exibe_arvore_postorder(arvore->sae);
        exibe_arvore_postorder(arvore->sad);

        printf("%d", arvore->info);

        printf(">");

    }

}

void exibe_arvore_inorder(Arvore arvore){

    if(arvore_vazia(arvore))
        printf("<>");
    else{

        printf("<");

        exibe_arvore_postorder(arvore->sae);

        printf("%d", arvore->info);

        exibe_arvore_postorder(arvore->sad);

        printf(">");

    }

}

void percorre_arvore(Arvore arvore){

    Fila *fila = malloc(sizeof(Fila));
    criarF(fila);

    enfilar(&fila, arvore);

    printf("\n");

    while(fila->qnt != 0){

        Arvore *next = fila->first->next;

        if(arvore_vazia(*next))
            printf("<>");
        else
            printf("<%d>", (*next)->info);

        if(!arvore_vazia((*next)->sae))
            enfilar(&fila, (*next)->sae);
        if(!arvore_vazia((*next)->sad))
            enfilar(&fila, (*next)->sad);

        desenfilar(&fila);

    }

}

int folha(Arvore arvore){

    if(arvore_vazia(arvore))
        return 0;

    if(arvore_vazia(arvore->sae) && arvore_vazia(arvore->sad))
        return 1;

    return 0;

}

int grau_arvore(Arvore arvore){

    if(folha(arvore))
        return 0;
    if(grau_arvore(arvore->sae) > grau_arvore(arvore->sad))
        return grau_arvore(arvore->sae) + 1;
    else
        return grau_arvore(arvore->sad) + 1;

}
