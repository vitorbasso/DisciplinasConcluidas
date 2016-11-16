/*
************************************************************************

        Universidade Federal de Uberl�ndia (UFU)
        Faculdade de Ci�ncia da Computa��o (FACOM)


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


//Esse arquivo tem o prop�sito de simplificar a interface para o usu�rio
//das bibliotecas aqui montadas para o trabalho espec�fico, de forma a
//facilitar a implementa��o das RNP em espec�fico - prop�sito do
//trabalho


//Recebe o nome(com endere�o) dos arquivos a serem usados(com os
//vertices e as arestas) e chama as devidas fun��es para gerar o
//grafo RNP completo
Grafo gerarRnp(const char *file_vertices, const char *file_arestas);


//Retorna o numero de vertices presente no Grafo fornecido
int numVertices(Grafo *grafo);


//Retorna o grau do vertice especificado no grafo fornecido (retorna
// o valor -1 caso o vertice n�o esteja presente no grafo
int grauVertice(Grafo *grafo, int vertice);


//Retorna 1 se o vertice1 for adjacente ao vertice2 no grafo fornecido
//retorna 0 caso n�o seja adjacente e retorna -1 caso o vertice1 n�o
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
