#ifndef FILA_ARVORE_GENERICA_H_INCLUDED
#define FILA_ARVORE_GENERICA_H_INCLUDED


#include <stdio.h>
#include <stdlib.h>
#include "arvore_generica.h"

typedef struct NodeFila{
    Arv arvore;
    struct NodeFila *next;
}NodeFila;

typedef struct Fila{
    NodeFila *first;
    NodeFila *last;
    int qnt;
}Fila;

int criarF(Fila *fila);

int enfilar(Fila **fila, Arv arvore);

Arv desenfilar(Fila **fila);

int deletarF(Fila **fila);

#endif // FILA_ARVORE_GENERICA_H_INCLUDED