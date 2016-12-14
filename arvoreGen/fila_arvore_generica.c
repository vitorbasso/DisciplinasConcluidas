#include <stdio.h>
#include <stdlib.h>
#include "fila_arvore_generica.h"

int criarF(Fila *fila){
    fila->first = malloc(sizeof(NodeFila));

    if(fila->first==NULL)
        return 0;

    fila->first->next = NULL;

    fila->last = fila->first->next;

    fila->qnt = 0;

    return 1;
}

int enfilar(Fila **fila, Arv arvore){
    NodeFila *aux=malloc(sizeof(NodeFila));
    (*aux).arvore=arvore;
    (*aux).next = NULL;
    if(aux==NULL || (*fila)->first==NULL)return 0;
    if((*fila)->qnt==0){
        (*fila)->first->next=aux;
    }
    else{
        (*fila)->last->next=aux;
    }
    (*fila)->last=aux;
    aux->next=(*fila)->first->next;
    (*fila)->qnt++;
    return 1;
}

Arv desenfilar(Fila **fila){
    Arv arvore = (Arv)malloc(sizeof(Arv));
    if((*fila)->first==NULL)return 0;
    if((*fila)->qnt<=0) return 0;
    NodeFila *aux;
    aux=(*fila)->first->next;
    (*fila)->first->next=(*fila)->first->next->next;
    (*fila)->last->next=(*fila)->last->next->next;
    arvore = aux->arvore;
    free(aux);
    (*fila)->qnt--;
    return arvore;
}

int deletarF(Fila **fila){
    NodeFila *aux;
    aux=(*fila)->first->next;
    NodeFila *aux2;
    aux2=aux;
    if(aux==NULL || aux2==NULL)return 0;
    while(aux!=(*fila)->last){
        aux2=aux;
        aux=aux->next;
        (*fila)->first->next=aux;
        (*fila)->last->next=(*fila)->first;
        free(aux2);
    }
    free(aux);
    (*fila)->first->next=NULL;
    (*fila)->last=NULL;

    return 1;
}
