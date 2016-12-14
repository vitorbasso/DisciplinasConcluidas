#ifndef ARVORE_GENERICA_H_INCLUDED
#define ARVORE_GENERICA_H_INCLUDED


#include <stdio.h>
#include <stdlib.h>

typedef struct no no;

struct no {

    int info;
    no *esq;
    no *dir;

};

typedef struct no *Arv;

#endif // ARVORE_GENERICA_H_INCLUDED