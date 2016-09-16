#include <stdio.h>
#include <stdlib.h>
#include "streamlined.h"
#include "fila.h"

int main()
{
    srand((unsigned int)time(NULL));
    int saidas, niveis, inputManual, chosenOne=0;
    Fila jogadores;
    criarF(&jogadores);

    printf("\tSimulacao do labirinto, grupo:\nVitor Martins Basso (11611BCC034)\nDiogo Mafra Magalhoes (11521BCC025)\n\n");
    printf("\n========================================================================================\n\n\n");


    if(!montarLabirinto(&niveis,&saidas,&chosenOne,&inputManual)){
        printf("Problema na montagem do labirinto.\n");
        return 0;
    }
    if(!montarPlayerRoster(&jogadores)){
        printf("Problema na montagem da lista de jogadores.\n");
        return 0;
    }
    printf("\n========================================================================================\n");
    printf("\nO labirinto possui %d niveis com %d saidas cada e a saida correta e a %d do nivel %d.\n\n", niveis,saidas,chosenOne,niveis);
    printf("\n========================================================================================\n");
    printf("\nA lista de jogadores, na ordem de jogada, e a seguinte:\n");
    imprimir(&jogadores);
    printf("\n========================================================================================\n");
    jogar(&jogadores,niveis,saidas,chosenOne,inputManual);
    printf("\n========================================================================================\n");
    anunciarVencedores(&jogadores);
    printf("\n========================================================================================\n");


    liberarF(&jogadores);

    return 1;
}
