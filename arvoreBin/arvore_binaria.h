#ifndef ARVORE_BINARIA_H_INCLUDED
#define ARVORE_BINARIA_H_INCLUDED

typedef struct Node{
    int info;
    struct Node *sae;
    struct Node *sad;
}Node;

typedef struct Node* Arvore;


Arvore cria_vazia();


Arvore cria_arvore(int elemento, Arvore sae, Arvore sad);

int arvore_vazia(Arvore arvore);

void libera_arvore(Arvore* arvore);

int existe(Arvore arvore, int elemento);

int remove_folha(Arvore *arvore, int elemento);

void preorder(Arvore arvore);

void postorder(Arvore arvore);

void inorder(Arvore arvore);

void percorre_nivel(Arvore arvore);

int folha(Arvore arvore);

int nro_folha(Arvore A);

int grau_arvore(Arvore arvore);

#endif // ARVORE_BINARIA_H_INCLUDED
