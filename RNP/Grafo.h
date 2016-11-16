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

#ifndef GRAFO_H_INCLUDED
#define GRAFO_H_INCLUDED


//======================================================================
//Criando estruturas para guardar as arestas de cada vertice
typedef struct Aresta{ //Estrutura da aresta individual
    struct Vertice *origem;
    struct Vertice *destino;
    float peso;
    struct Aresta *next;
}Aresta;

typedef struct ListaAresta{ //Lista (na verdade fila) que guarda as arestas
    Aresta *first;
    Aresta *last;
    int grau;
}ListaAresta;
//Fim da estrutura para guardar as arestas de cada vertice
//======================================================================

//======================================================================
//Criando estrutura para guardar cada Vertice do Grafo
typedef struct Vertice{ //Estrutura do vertice individual
    int id;
    char cidade[20];
    float latitude;
    float longitude;
    struct Vertice* next;
    ListaAresta arestas; //Lista de arestas do vertice especifico
}Vertice;

typedef struct Grafo{ //Lista(Fila) que compoem o Grafo
    int qntVertices;
    Vertice* first;
    Vertice* last;
}Grafo;
//Fim da estrutura do Grafo
//======================================================================


//Inicializa a lista de arestas (apenas inicializa, não inclui nenhum elemento
ListaAresta criarListaAresta();


//Inicializa o Grafo - tambem so inicializa, nao coloca elemento
Grafo criarGrafo();


//adiciona uma aresta a lista de arestas de cada vertice no formato fila (sempre no final)
void addAresta(ListaAresta** lista, Vertice* origem, Vertice* destino, float peso);


//adiciona um vertice no Grafo no formato fila - sempre no final
void addElemento(Grafo** grafo, int id, char *cidade, float latidude, float longitude);


//imprime o grafo completo, incluindo as arestas de cada vertice (faz uso de imprimeGrafoAresta()
void imprimeGrafo(const Grafo* grafo);


//imprime todas as arestas da lista de arestas do dado vertice
void imprimeGrafoAresta(const ListaAresta* listaAresta);


//Recebe o arquivo.txt contendo as vertices no formato: "id nome_cidade latitude longitude" e preenche o grafo com os dados
Grafo gerarGrafo(const char *file_vertices);


//Devolve o Vertice baseado no id fornecido
Vertice getVertice(Grafo *grafo, int ver);


//Recebe o arquivo.txt contendo as arestas no formato: "id_origem id_destino peso" e preenche a lista de arestas de cada vertice
void gerarArestas(Grafo *grafo, const char *file_arestas);


#endif // GRAFO_H_INCLUDED
