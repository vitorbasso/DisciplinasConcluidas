#include <stdio.h>
#include <stdlib.h>
#include "fila.h"

int criarF(Fila *fila){
    fila->first=malloc(sizeof(Jogador));
    if(fila->first==NULL)return 0;
    fila->first->next=NULL;
    fila->last=fila->first->next;
    fila->qnt=0;
    return 1;
}

Jogador criarJ(int ndoJogador){
    Jogador temp;
    printf("Qual o nome do jogador numero %d?: ", ndoJogador);
    char nome[10];
    scanf ("%s",temp.nome);
    temp.prioridade=rand()%200+1;
    if(!criarP(&temp.jogadas))printf("Nao foi possivel criar pilha jogadas do jogador.\n");
    temp.next=NULL;
    return temp;
}

int enfilar(Fila **fila, Jogador jogador){
    Jogador *aux=malloc(sizeof(Jogador));
    *aux=jogador;
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

int girar(Fila **fila){
    if((*fila)->first==NULL)return 0;
    (*fila)->last=(*fila)->last->next;
    (*fila)->first->next=(*fila)->first->next->next;
    return 1;
}

int desenfilar(Fila **fila){
    if((*fila)->first==NULL)return 0;
    if((*fila)->qnt<=0) return 0;
    Jogador *aux;
    aux=(*fila)->first->next;
    (*fila)->first->next=(*fila)->first->next->next;
    (*fila)->last->next=(*fila)->last->next->next;
    free(aux);
    (*fila)->qnt--;
    return 1;
}

void imprimir(Fila *fila){
    int i;
    Jogador *aux;
    aux=fila->first->next;
    while(aux!=fila->last){
        printf("Nome: %s. Prioridade: %d\n", aux->nome, aux->prioridade);
        aux=aux->next;
    }
    printf("Nome: %s. Prioridade: %d\n", aux->nome, aux->prioridade);
}

int deletarF(Fila **fila){
    Jogador *aux;
    aux=(*fila)->first->next;
    Jogador *aux2;
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
