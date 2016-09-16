#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include "labirinto.h"

int gerarSaidas(int posN, int posS, int saidas, int *range[2]){
    int primeira, ultima;
    ultima=posS*saidas;
    primeira=(posS*saidas-(saidas-1));
    range[0]=primeira;
    range[1]=ultima;
    return 1;
}

int saidaReal(int niveis, int saidas, int *chosenOne){
    srand((unsigned int)time(NULL));
    int totalSaidasUltimoNivel=pow(saidas,(niveis));
    *chosenOne=rand()%(totalSaidasUltimoNivel)+1;
    return 1;
}
