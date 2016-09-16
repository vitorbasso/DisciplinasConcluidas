#ifndef STREAMLINED_H_INCLUDED
#define STREAMLINED_H_INCLUDED
#include <stdio.h>
#include <stdlib.h>
#include "labirinto.h"
#include "fila.h"
#include "pilha.h"

int montarLabirinto(int *niveis, int *saidas, int *chosenOne, int *inputManual);

int montarPlayerRoster(Fila *jogadores);

int jogar(Fila *jogadores, int niveis, int saidas, int chosenOne,int inputManual);

int anunciarVencedores(Fila *jogadores);

int liberarF(Fila *fila);
#endif // STREAMLINED_H_INCLUDED
