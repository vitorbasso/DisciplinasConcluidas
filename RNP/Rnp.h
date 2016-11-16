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

#ifndef RNP_H_INCLUDED
#define RNP_H_INCLUDED
#include "Grafo.h"


//Esse arquivo tem o propósito de simplificar a interface para o usuário
//das bibliotecas aqui montadas para o trabalho específico, de forma a
//facilitar a implementação das RNP em específico - propósito do
//trabalho


//Recebe o nome(com endereço) dos arquivos a serem usados(com os
//vertices e as arestas) e chama as devidas funções para gerar o
//grafo RNP completo
Grafo gerarRnp(const char *file_vertices, const char *file_arestas);


//Retorna o numero de vertices presente no Grafo fornecido
int numVertices(Grafo *grafo);


//Retorna o grau do vertice especificado no grafo fornecido (retorna
// o valor -1 caso o vertice não esteja presente no grafo
int grauVertice(Grafo *grafo, int vertice);


//Retorna 1 se o vertice1 for adjacente ao vertice2 no grafo fornecido
//retorna 0 caso não seja adjacente e retorna -1 caso o vertice1 não
//esteja no grafo fornecido
int adjacente(Grafo *grafo, int vertice1, int vertice2);


//simplifica a interface das bibliotecas
void imprimeRnp(Grafo *grafo);

//Calcula menor custo entre u e v mostrando qual o caminho utilizado
int menor_custo(Grafo *grafo, int u, int v, float tamanho_arquivo);

//Encontra todos os PoPs a K pulos de distancia da origem
void alcance_k (Grafo *grafo, int u, int k, int depth, int *visited);

void cinco_caminhos(Grafo *grafo,int u, int v, float tamanho_arquivo, float t_acumulado, int *visited, int *parent);


#endif // RNP_H_INCLUDED
