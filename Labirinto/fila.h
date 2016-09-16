#ifndef FILA_H_INCLUDED
#define FILA_H_INCLUDED

#include <stdio.h>
#include <stdlib.h>
#include "pilha.h"


typedef struct Jogador{
    char nome[10];
    int prioridade;
    Pilha jogadas;
    struct Jogador *next;
}Jogador;

typedef struct Fila{
    Jogador *first;
    Jogador *last;
    int qnt;
}Fila;

int criarF(Fila *fila);

Jogador criarJ(int ndoJogador);

int enfilar(Fila **fila, Jogador jogador);

int girar(Fila **fila);

int desenfilar(Fila **fila);

void imprimir(Fila *fila);

int deletarF(Fila **fila);
#endif // FILA_H_INCLUDED
