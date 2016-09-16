#include <stdio.h>
#include <stdlib.h>
#include "pilha.h"

int criarP(Pilha *pilha){
    pilha->first=malloc(sizeof(NodeP));
    if(pilha->first==NULL)return 0;
    pilha->first->posN=0;
    pilha->first->posS=1;
    //pilha->first->escolha=1;
    pilha->first->next=NULL;
    pilha->last=pilha->first;
    pilha->nJogadas=0;
    pilha->qnt=1;
    return 1;
}


int empilhar(Pilha **pilha, int posN, int posS){
    NodeP *aux=malloc(sizeof(NodeP));
    if(aux==NULL)return 0;
    aux->posN=posN;
    aux->posS=posS;
    //aux->escolha=escolha;
    aux->next=(*pilha)->first;
    (*pilha)->first=aux;
    (*pilha)->nJogadas++;
    (*pilha)->qnt++;
    return 1;
}

int desempilhar(Pilha **pilha){
    NodeP *aux;
    aux=(*pilha)->first;
    if(aux==NULL)return 0;
    (*pilha)->first=aux->next;
    (*pilha)->qnt--;
    free(aux);
    return 1;
}
