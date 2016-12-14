#ifndef FILA_H_INCLUDED
#define FILA_H_INCLUDED


#include <stdio.h>
#include <stdlib.h>
#include "arvore_binaria.h"

typedef struct NodeFila{
    Arvore arvore;
    struct NodeFila *next;
}NodeFila;

typedef struct Fila{
    NodeFila *first;
    NodeFila *last;
    int qnt;
}Fila;

int criarF(Fila *fila);

int enfilar(Fila **fila, Arvore arvore);

Arvore desenfilar(Fila **fila);

int deletarF(Fila **fila);

#endif // FILA_H_INCLUDED
