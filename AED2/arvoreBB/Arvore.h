#ifndef ARVORE_H_INCLUDED
#define ARVORE_H_INCLUDED


typedef struct Registro{

    int chave;
    char nome[30];

}Registro;

typedef struct Node{

    Registro info;
    struct Node* sae;
    struct Node* sad;

}Node;

typedef Node* Arvore;


Arvore cria_arvore();

int arvore_vazia(Arvore *arvore);

void libera_arvore(Arvore *arvore);

void exibe_arvore(Arvore arvore);

void exibe_ordenado(Arvore arvore);

int insere_ord(Arvore *arvore, Registro registro);

int remove_ord(Arvore *arvore, int chave);

Arvore busca_bin(Arvore arvore, int chave);



#endif // ARVORE_H_INCLUDED
