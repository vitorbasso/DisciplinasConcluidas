#include <stdio.h>
#include <stdlib.h>
#include "Arvore.h"

Arvore cria_arvore(int elem){

    Arvore arvore = (Arvore)malloc(sizeof(Node));

    if(arvore == NULL)
        return NULL;

    arvore->info = elem;
    arvore->filhos = NULL;
    arvore->irmaos = NULL;

    return arvore;

}

int insere(Arvore arvore, Arvore subArvore){

    if(arvore == NULL)
        return 0;

    subArvore->irmaos = arvore->filhos;
    arvore->filhos = subArvore;

    printf("Inserido %d na %d.\n", subArvore->info, arvore->info);

    return 1;

}

void exibe_arvore(Arvore arvore){

    if(arvore != NULL){
        printf("<");

        printf("%d", arvore->info);
        exibe_arvore(arvore->filhos);
        printf(">");
        exibe_arvore(arvore->irmaos);
    }

}

void libera_arvore(Arvore *arvore){

    if(*arvore != NULL){

        libera_arvore(&((*arvore)->filhos));
        libera_arvore(&((*arvore)->irmaos));

        free(*arvore);

    }

    *arvore = NULL;

}
