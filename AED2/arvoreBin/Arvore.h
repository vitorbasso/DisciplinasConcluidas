#ifndef ARVORE_H_INCLUDED
#define ARVORE_H_INCLUDED

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

void exibe_arvore_preorder(Arvore arvore);

void exibe_arvore_postorder(Arvore arvore);

void exibe_arvore_inorder(Arvore arvore);

void percorre_arvore(Arvore arvore);

int folha(Arvore arvore);

int grau_arvore(Arvore arvore);

#endif // ARVORE_H_INCLUDED
