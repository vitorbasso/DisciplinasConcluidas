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

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "Rnp.h"
#include "Grafo.h"


//=====================================================================
//Retorna o Grafo montado da RNP usando os dois arquivos fornecidos
Grafo gerarRnp(const char *file_vertices, const char *file_arestas){

    Grafo rnp = gerarGrafo(file_vertices);
    gerarArestas(&rnp, file_arestas);

    return rnp;

}//Fim de Grafo gerarRNP();


//=====================================================================
int numVertices(Grafo *grafo){

    return grafo->qntVertices;

}//Fim de int numVertices();


//=====================================================================
int grauVertice(Grafo *grafo, int vertice){

    Vertice *aux = grafo->first->next;

    //Percorre o Grafo e verifica se o id de algum vertice corresponde
    //ao fornecido para a função
    while(aux != NULL && aux->id != vertice){
        aux = aux->next;
    }

    //não foi encontrado um vertice no grafo com id correspondente ao
    // int vertice fornecido a função e retorna um sinal de erro
    if(aux == NULL)
        return -1;

    return aux->arestas.grau; // retorna o grau do vertice especificado

}//Fim de int grauVertice();


//=====================================================================
int adjacente(Grafo *grafo, int vertice1, int vertice2){
    Vertice *aux = grafo->first->next;
    Aresta *aresta;

    //Percorre o Grafo em busca do vertice com id correspondente ao
    //fornecido para a função (vertice1) como vertice de origem
    while(aux != NULL && aux->id != vertice1){
        aux = aux->next;
    }

    //Nesse caso, o vertice não foi encontrado no grafo e retorna-se
    //uma mensagem de erro
    if(aux == NULL)
        return -1;

    aresta = aux->arestas.first->next;

    //Percorre a lista de arestas do vertice de origem em busca de
    //um vertice de destino com id correspondente ao fornecido a
    //função (vertice2)
    while(aresta != NULL && aresta->destino->id != vertice2){
        aresta = aresta->next;
    }

    //Nesse caso o vertice de destino não foi encontrado e retorna-se
    // zero, simbolizando que não são vertices adjacentes
    if(aresta == NULL)
        return 0;

    return 1; // Retorna-se 1 simbolizando que vertice1 e vertice2 são
              // adjacentes

}//Fim de int adjacente();


//=====================================================================
void imprimeRnp(Grafo *grafo){

    imprimeGrafo(grafo);

}//Fim de void imprimeRnp();

//=====================================================================

int menor_custo(Grafo *grafo, int u, int v, float tamanho_arquivo) {

    //transforma Mbytes em Gbytes
    tamanho_arquivo = tamanho_arquivo/1000.0;

    float* distance;
    int* parent, *visited;
    int n = grafo->qntVertices, i;

    parent = (int*)malloc(n * sizeof(int));
    distance = (float*)calloc(n , sizeof(float));
    visited = (int*)calloc(n , sizeof(int));

    memset(parent, -1, sizeof (int) * n);

    for (i=0;i<n;i++)
        distance[i] = 1e9;

    distance[u] = 0;
    visited[u] = 1;

    Vertice* v_atual = grafo->first->next;
    Aresta* a_aux;

    int id_atual = u;

    //Calcula efetivamente o menor caminho da origem para todos os outros vertices
    for (i=0;i<n;i++) {

        //Eh garantido q sempre que um vertice eh o id_atual, temos a menor distancia ate ele
        //e nao precisaremos mais considera-lo
        visited[id_atual] = 1;

        v_atual = grafo->first->next;
        //seleciona o vertice sendo considerado na lista de arestas
        while (v_atual->next != NULL && v_atual->id != id_atual) {
            v_atual = v_atual->next;
        }

        a_aux = v_atual->arestas.first->next;

        //para cada aresta adjacente ao vertice, atualize a distancia
        //se valer a pena utilizar essa aresta para chegar no vertice adjacente
        while (a_aux != NULL) {
            int vx = a_aux->destino->id;
            if (!visited[vx]) {
                //Calcula como sendo "distancia" de um vertice pro outro o tempo de transferencia
                //de um vertice para o outro
                float d_novo = distance[v_atual->id] + ((tamanho_arquivo*8.0)/a_aux->peso);
                if (d_novo < distance[vx]) {
                    distance[vx] = d_novo;
                    parent[vx] = v_atual->id;
                }
            }
            a_aux = a_aux->next;
        }

        //A cada iteracao encontra o vertice com a menor distancia acumulada e passar a considerar ele
        int k;
        float menor = 1e9;
        for (k=0;k<n;k++) {
            if (!visited[k] && distance[k] < menor) {
                menor = distance[k];
                id_atual = k;
            }
        }

    }
    id_atual = v;
    while (id_atual != -1) {
        v_atual = grafo->first->next;
        while (v_atual->id != id_atual) {
            v_atual = v_atual->next;
        }

        printf("%s", v_atual->cidade);
        if (id_atual == v) {
            printf(" (Destino)\n");
        } else if (id_atual == u) {
            printf(" (Origem)\n");
        } else {
            printf("\n");
        }

        if (id_atual != u) {
            printf("       ^\n       |\n       |\n       |\n");
        }
        id_atual = parent[id_atual];
    }

    printf("\n\n A transferencia vai demorar %.6f segundos no total.\n\n", distance[v]);


    return distance[v];
}//Fim de int menor_custo()

//=====================================================================

void alcance_k (Grafo *grafo, int u, int k, int depth, int *visited) {

    //Para a recursao no caso de a profundidade ja estar maior do que a autorizada
    if (depth > k)
        return;
    visited[u] = 1;
    Vertice* v_atual = grafo->first->next;
    Aresta* a_aux;

    while (v_atual->id != u)
        v_atual = v_atual->next;

    printf("Nos intermediarios = %d ----> %s\n\n",depth,v_atual->cidade);

    a_aux = v_atual->arestas.first->next;

    while (a_aux != NULL) {

        if (!visited[a_aux->destino->id])
            alcance_k(grafo, a_aux->destino->id, k, depth + 1, visited);

        a_aux = a_aux->next;
    }
} //fim void alcance_k()

//=====================================================================
//Essa funcao eh basicamente um DFS que para assim que encontra o vertice "v" que eh o destino
//e imprime o caminho feito ate ali, recuperado do vetor de parents
void cinco_caminhos(Grafo *grafo,int u,int v, float tamanho_arquivo, float t_acumulado, int *visited, int *parent) {

    if (visited[v] >= 5)
        return;
    tamanho_arquivo = tamanho_arquivo / 1000.0;
    visited[u] = 1;
    Vertice* v_atual = grafo->first->next;
    Aresta* a_aux;

    while (v_atual->id != u)
        v_atual = v_atual->next;

    a_aux = v_atual->arestas.first->next;

    while (a_aux != NULL) {
        if (a_aux->destino->id != v && !visited[a_aux->destino->id]) {
            float t_aresta = ((tamanho_arquivo*8.0)/a_aux->peso);
            parent[a_aux->destino->id] = u;
            cinco_caminhos(grafo, a_aux->destino->id, v, tamanho_arquivo * 1000, t_acumulado + t_aresta, visited, parent);
        } else if (a_aux->destino->id == v) {

            parent[v] = u;
            visited[v]++;
            int id_atual = v;

            printf("\n\nTempo total desse caminho: %0.6f\n\n", t_acumulado);

            while (id_atual != -1) {
                v_atual = grafo->first->next;
                while (v_atual->id != id_atual) {
                    v_atual = v_atual->next;
                }

                printf("%s", v_atual->cidade);
                if (id_atual == v) {
                    printf(" (Destino) ");
                } else if (parent[id_atual] == -1) {
                    printf(" (Origem)\n");
                } else {
                    printf(" ");
                }

                if (parent[id_atual] != -1) {
                    printf(" <= ");
                }
                id_atual = parent[id_atual];
            }

        }

        a_aux = a_aux->next;
    }
}

