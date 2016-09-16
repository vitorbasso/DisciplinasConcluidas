#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include "labirinto.h"
#include "fila.h"
#include "pilha.h"
#include "streamlined.h"

int montarLabirinto(int *niveis, int *saidas, int *chosenOne,int *inputManual){
    printf("Quantos niveis tera o labirinto?: ");
    scanf ("%d", niveis);
    printf("Quantas saidas terao cada nivel?: ");
    scanf ("%d", saidas);
    printf("O input sera manual ou automatico?(1 para manual e 0 para automatico): ");
    scanf("%d", inputManual);
    if(!saidaReal(*niveis,*saidas,chosenOne))return 0;
    return 1;
}

int montarPlayerRoster(Fila *jogadores){
    int qnt, j;
    printf("Quantos jogadores irao jogar?: ");
    scanf("%d", &qnt);
    Jogador ordem[qnt];
    Jogador temp;
    int i;
    for(i=1;i<=qnt;i++){
        if(i==1){
            ordem[0]=criarJ(i);
        }
        else{
            temp=criarJ(i);
            j=i-1;
            while(j>0 && temp.prioridade>=ordem[j-1].prioridade){
                ordem[j]=ordem[j-1];
                j--;
            }
            ordem[j]=temp;
        }
    }
    for(i=0;i<qnt;i++){
        enfilar(&jogadores,ordem[i]);
    }
}

int jogar(Fila *jogadores,int niveis,int saidas, int chosenOne,int inputManual){
    int max=pow(saidas,niveis);
    int jaFoi[niveis][max];
    int range[2];
    int s, p, k, posN, posS, escolha, continua=0, ganhou=0, terminoJogo=0;;
    Pilha *jogadas;
    Jogador *aux;
    if(inputManual)printf("\n\nAgora e a vez do jogador %s.\n\n", jogadores->first->next->nome);
    while(terminoJogo<jogadores->qnt){

        for(k=0;k<niveis;k++){
            for(s=0;s<pow(saidas,k+1);s++){
                jaFoi[k][s]=0;
            }
        }
        jogadas=&jogadores->first->next->jogadas;

        while(1){
            int close=0;
            int cont=0;
            posN=jogadas->first->posN;
            posS=jogadas->first->posS;
            gerarSaidas(posN,posS,saidas,range);
            for(k=0;k<niveis;k++){
                cont=0;
                continua=0;
                for(s=range[0];s<=range[1];s++){
                    if(jaFoi[posN][s-1]==0){
                        continua=1;
                        break;
                    }
                    else{
                        cont++;
                    }
                    if(cont==saidas){
                        if(inputManual)printf("Voce ja foi em todos os caminhos desse ramo. Voltando para o ramo anterior.\n");
                        while(posN<=jogadas->first->posN){
                            desempilhar(&jogadas);
                        }
                        posN=jogadas->first->posN;
                        posS=jogadas->first->posS;
                        gerarSaidas(posN,posS,saidas,range);
                    }
                }
            }
            if(inputManual){
                printf("Voce esta no nivel %d. Voce tem as saidas que estao entre %d e %d desse nivel para tomar. Qual deseja?: ",posN+1,range[0],range[1]);
                scanf("%d",&escolha);
                while(escolha<range[0] || escolha>range[1]){
                    printf("Voce nao tem essa saida como opcao. Escolha entre %d e %d",range[0],range[1]);
                    scanf("%d",&escolha);
                }
            }
            else{
                escolha=rand()%saidas+range[0];
            }
            //for(k=0;k<niveis;k++){
                for(s=range[0];s<=range[1];s++){
                    //if(posN==k){
                        if(escolha==s){
                            if(jaFoi[posN][s-1]==1){
                                if(inputManual)printf("Voce ja foi por esse caminho.\n");
                            }
                            else{
                                if(posN==niveis-1){
                                    empilhar(&jogadas, (posN), posS);
                                    if(inputManual)printf("Esta nao e a saida correta.\n");
                                    if(escolha==chosenOne)ganhou=1;
                                }
                                else{
                                    empilhar(&jogadas,(posN+1), escolha);
                                }
                                jaFoi[posN][s-1]=1;
                            }
                            //close=1;
                            break;
                        }
                    //}
                }
                //if(close==1)break;
            //}
            if(ganhou==1)break;

        }
        aux=jogadores->first->next;
        printf("\nJogador %s terminou o labirinto com %d movimentos.\n\n",aux->nome,jogadas->nJogadas);
        terminoJogo++;
        girar(&jogadores);
        if(terminoJogo<jogadores->qnt)if(inputManual)printf("\n========================================================================================\n");
        if(terminoJogo<jogadores->qnt)if(inputManual)printf("\n\nAgora e a vez do jogador %s.\n\n", jogadores->first->next->nome);
        ganhou=0;
    }
    return 1;
}

int anunciarVencedores(Fila *jogadores){
    Jogador *aux;
    aux=jogadores->first->next;
    Jogador ordem[jogadores->qnt];
    if(aux==NULL)return 0;
    int i, j;
    for (i=1;i<=jogadores->qnt;i++){
        if(i==1){
            ordem[0]=*aux;
        }
        else{
            j=i-1;
            while(j>0 && aux->jogadas.nJogadas<=ordem[j-1].jogadas.nJogadas){
                ordem[j]=ordem[j-1];
                j--;
            }
            ordem[j]=*aux;
        }
        aux=aux->next;
    }
    printf("\nO vencedor foi %s, com %d jogadas.\n", ordem[0].nome, ordem[0].jogadas.nJogadas);
    printf("\nOs seguintes foram, na ordem: \n");
    for(i=1;i<jogadores->qnt;i++){
        printf("\n%d - %s, com %d jogadas.\n", i+1, ordem[i].nome, ordem[i].jogadas.nJogadas);
    }

    return 1;
}

int liberarF(Fila *fila){
    if(!deletarF(&fila))return 0;
    return 1;
}
