/*
************************************************************************

        Universidade Federal de Uberlândia (UFU)
        Faculdade de Ciência da Computação (FACOM)


        Trabalho de AED2 - Grafos e Dijkstra

        Autores:

                 Vitor Martins Basso ------------ 11611BCC034

                 Weuler Borges Santos Filho ----- 11611BCC055


        Data: 14 de Setembro de 2016

************************************************************************
*/

#include "Menu.h"
#include "Rnp.h"
#include "Grafo.h"

void menuRNP(const char *file_rnp, const char *file_arestas){

    Grafo grafo = gerarRnp(file_rnp, file_arestas);

    int *visited, *parent, option, v1, v2, end = 1;
    float tamanhoArquivo;
    Vertice vertice;

    visited = (int*)malloc(grafo.qntVertices * sizeof(int));
    parent = (int*)malloc(grafo.qntVertices * sizeof(int));


    while(end){
        printf("\t###################\n\n");
        printf("\n");
        printf("\t\t\t\tRede Nacional de Ensino e Pesquisa (RNP) - IPE\n");
        printf("\n\n");
        printf("\t 1 - Imprimir a rede RNP IPE atual\n");
        printf("\t 2 - Imprimir quantidade de Vertices presente na RNP IPE\n");
        printf("\t 3 - Imprimir o grau de um dado Vertice da rede\n");
        printf("\t 4 - Menor caminho de um Vertice para o outro\n");
        printf("\t 5 - Alcance de um dado vertice\n");
        printf("\t 6 - Ate cinco caminhos de um Vertice para o outro\n");
        printf("\t 0 - Sair do programa\n");
        printf("\n");
        printf("\tPor favor digite a opcao desejada: ");
        scanf("%d", &option);
        printf("\n\n");
        switch(option){
        case 1:
            imprimeRnp(&grafo);
            break;
        case 2:
            printf("\t O grafo atual tem %d Vertices\n\n", grafo.qntVertices);
            break;
        case 3:
            printf("\t Deseja saber o grau de qual vertice? ");
            scanf("%d", &v1);
            vertice = getVertice(&grafo, v1);
            printf("\n\t O Vertice %s (ID: %d) tem grau %d\n",
                   vertice.cidade, vertice.id, vertice.arestas.grau);
            break;
        case 4:
            printf("\n\t Qual o Vertice de origem? ");
            scanf("%d", &v1);
            printf("\n\t Qual o Vertice de Destino? ");
            scanf("%d", &v2);
            printf("\n\t Qual o tamanho do arquivo a ser transferido? ");
            scanf("%f", &tamanhoArquivo);
            printf("\n");
            menor_custo(&grafo, v1, v2, tamanhoArquivo);
            break;
        case 5:
            memset(visited, 0, grafo.qntVertices * sizeof(int));
            printf("\n\t Qual o Vertice que se deseja saber o alcance? ");
            scanf("%d", &v1);
            printf("\n\t Qual o alcance da busca que deseja? ");
            scanf("%d", &v2);
            alcance_k(&grafo, v1, v2, 0,visited);
            break;
        case 6:
            memset(visited, 0, grafo.qntVertices * sizeof(int));
            memset(parent, -1, grafo.qntVertices * sizeof(int));
            printf("\n\t Qual o Vertice de origem? ");
            scanf("%d", &v1);
            printf("\n\t Qual o Vertice de destino? ");
            scanf("%d", &v2);
            printf("\n\t Qual o tamanho do arquivo a ser transferido? ");
            scanf("%f", &tamanhoArquivo);
            printf("\n");
            cinco_caminhos(&grafo, v1, v2, tamanhoArquivo, 0.0, visited, parent);
            break;
        case 0:
            end = 0;
            break;
        default:
            printf("Esse valor nao esta nas opcoes.\n");
        }
    }


    //imprimeRnp(&grafo);

    //menor_custo(&grafo, 2, 8, 5550);

    //alcance_k(&grafo, 0, 3, 0,visited);

    //cinco_caminhos(&grafo, 10, 2, 50, 0.0, visited, parent);

}
