#ifndef ARVORE_H_INCLUDED
#define ARVORE_H_INCLUDED


typedef struct Node{
    int info;
    struct Node *filhos;
    struct Node *irmaos;
}Node;

typedef struct Node* Arvore;

Arvore cria_arvore(int elem);

int insere(Arvore arvore, Arvore subArvore);

void exibe_arvore(Arvore arvore);

int busca(Arvore arvore, int elem);

void libera_arvore(Arvore *arvore);


#endif // ARVORE_H_INCLUDED
