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

#include <stdio.h>
#include <stdlib.h>
#include "Grafo.h"
#include <string.h>


//Define constantes para o número de cidades e um tamanho máximo para
//o nome de cada cidade que estarão presentes nos arquivos.txt que
//formarão a RNP
const int NUM_CIDADES = 27;
const int TAMANHO_NOME = 20;


//=====================================================================
ListaAresta criarListaAresta(){

    ListaAresta lista;
    lista.first = malloc(sizeof(Aresta));

    lista.last = lista.first;
    lista.last->next = NULL;
    lista.grau = 0;

    return lista;

}//Fim de ListaAresta criarListaAresta();


//=====================================================================
//inicializa o Grafo e a lista de arestas de cada vertice
Grafo criarGrafo(){

    ListaAresta aresta = criarListaAresta();
    Grafo grafo;
    grafo.first = malloc(sizeof(Vertice));

    grafo.last = grafo.first;
    grafo.last->next = NULL;
    grafo.first->arestas = aresta;
    grafo.qntVertices = 0;

    return grafo;

}//Fim de Grafo criarGrafo();


//=====================================================================
void addElemento(Grafo** grafo, int id, char *cidade, float latitude, float longitude){

    Vertice *aux = (*grafo)->first;
    ListaAresta aresta = criarListaAresta();

    aresta.first->next = NULL;

    //Percorre o Grafo até chegar no último elemento
    while(aux->next != NULL){
        aux = aux->next;
    }

    //Adiciona o novo vertice no final do Grafo (fila)
    aux->next = malloc(sizeof(Vertice));

    aux = aux->next;
    aux->id = id;
    //inicializa aux->cidade como string de \0
    memset(aux->cidade, "\0", sizeof(aux->cidade));
    strcpy(aux->cidade, cidade);//Realiza a copia de char *cidade para aux->cidade
    aux->latitude = latitude;
    aux->longitude = longitude;
    aux->arestas = aresta;
    aux->next = NULL;

    (*grafo)->last = aux;//Atualiza ultimo elemento do grafo usando de ponteiro para ponteiro
    (*grafo)->qntVertices++; //mantem a quantidade de vertices no Grafo

}//Fim de void addElemento();


//=====================================================================
void addAresta(ListaAresta** lista, Vertice *origem, Vertice *destino, float peso){

    Aresta *aux = (*lista)->first;

    //Percorre a lista de arestas até achar a ultima aresta na lista
    while(aux->next != NULL){
        aux = aux->next;
    }

    //Adiciona a nova aresta no final da lista de arestas
    aux->next = malloc(sizeof(Aresta));
    aux = aux->next;
    aux->origem = origem;
    aux->destino = destino;
    aux->peso = peso;
    aux->next = NULL;

    (*lista)->grau++;//mantem o grau do vertice

}//Fim de void addAresta();


//=====================================================================
//Utiliza de imprimeListaAresta para imprimir todo o grafo - incluindo
//as arestas de cada vertice
void imprimeGrafo(const Grafo *grafo){

    Vertice *aux = grafo->first->next;
    ListaAresta *auxAresta;

    //Percorre todo o Grafo - alcançando todas as arestas
    while(aux != NULL){

        auxAresta = &aux->arestas;
        //Imprime o valor do vertice
        printf("%d - %s. Latitude: %.2f. Longitude: %.2f.\n\tEssa cidade tem as arestas:\n" ,
               aux->id, aux->cidade, aux->latitude, aux->longitude);
        //Imprime as arestas do vertice em questão
        imprimeGrafoAresta(auxAresta);
        printf("\n");
        aux = aux->next;
    }

}//Fim de void imprimeGrafo();


//=====================================================================
//Imprime todas as arestas de uma dada lista de aresta de um vertice
void imprimeGrafoAresta(const ListaAresta *listaAresta){

    Aresta *aux = listaAresta->first->next;

    //Percorre toda a lista de arestas
    while(aux != NULL){
        //Imprime os valores da aresta
        printf("\t\tAresta de %s(id: %d) para %s(id: %d) com peso %.3f.\n",
               aux->origem->cidade,aux->origem->id,aux->destino->cidade, aux->destino->id, aux->peso);
        aux = aux->next;
    }

}//Fim de imprimeGrafoAresta();


//=====================================================================
//Inicializa e monta o Grafo - sem montar as arestas - fazendo uso de
// criarGrafo()
 Grafo gerarGrafo(const char *file_vertices){

    //Abre o arquivo especificado por file_vertices fornecido para a
    //função e o armazena em *cidades
    FILE *cidades = fopen(file_vertices , "r");

    Grafo grafo = criarGrafo();//inicializa-se o Grafo

    //Inicializa-se variáveis para ler o arquivo e criar os vertices
    char buff[NUM_CIDADES][TAMANHO_NOME];
    int id;
    float latitude, longitude;
    int i = 0;

    //Percorre todo o arquivo e obtém as informações dele
    while(!feof(cidades)){

        fscanf(cidades, "%d", &id);
        fscanf(cidades, "%s", buff[i]);
        fscanf(cidades, "%f", &latitude);
        fscanf(cidades, "%f", &longitude);
        Grafo *Grafo2 = &grafo;
        addElemento(&Grafo2, id, buff[i], latitude, longitude);
        i++;

    }

    Grafo grafo2 = grafo;

    fclose(cidades); //Fecha o arquivo
    return grafo2;

}//Fim de Grafo gerarGrafo();


//=====================================================================
Vertice getVertice(Grafo *grafo, int ver){

    Vertice aux = *grafo->first->next;
    while(aux.next != NULL && aux.id != ver){
        aux = *aux.next;
    }

    return aux;

}//Fim de getVertice();


//=====================================================================
//Preenche a lista de arestas de cada vertice presente no grafo
//fornecido a função por meio do arquivo.txt também fornecido
void gerarArestas(Grafo *grafo, const char *file_arestas){

    //Abre o arquivo file_arestas fornecido a função contendo os dados
    //de cada aresta de cada vertice
    FILE *arestas = fopen(file_arestas, "r");

    Vertice *nodeOrigem = NULL;
    Vertice *nodeDestino = NULL;

    //inicializa-se as variáveis para ler o arquivo e criar as arestas
    int idOrigem, idDestino;
    float peso;

    ListaAresta *listaAresta = NULL;
    int falhou;

    //Percorre todo o arquivo.txt lendo e criando as arestas para cada
    //vertice
    while(!feof(arestas)){

        fscanf(arestas, "%d", &idOrigem);
        fscanf(arestas, "%d", &idDestino);
        fscanf(arestas, "%f", &peso);
        falhou = 0;
        nodeOrigem = grafo->first->next;
        nodeDestino = grafo->first->next;

        //Percorre todo o Grafo em busca do vertice cujo id corresponde
        //ao lido no arquivo como id de origem
        while(nodeOrigem->id != idOrigem && nodeOrigem->next != NULL){
            nodeOrigem = nodeOrigem->next;

            //verifica se foi encontrado tal id de origem no Grafo
            if(nodeOrigem->next == NULL && nodeOrigem->id != idOrigem){
                falhou = 1;
            }
        }

        if(falhou == 0){ //Se tiver encontrado o id esse if é executado

            listaAresta = &nodeOrigem->arestas;

            //Percorre o Grafo em busca do vertice cujo id corresponde
            //ao lido no arquivo como id de destino
            while(nodeDestino != NULL){

                //Se o vertice for encontrado, adiciona essa aresta a
                //lista de arestas do vertice de origem
                if(nodeDestino->id == idDestino){

                    addAresta(&listaAresta, nodeOrigem, nodeDestino, peso);
                    break;// otimiza o loop parando-o quando tal
                    //for encontrado
                }
                nodeDestino = nodeDestino->next;
            }
        }
    }

    fclose(arestas);//Fecha o arquivo

}//Fim de void gerarArestas();
