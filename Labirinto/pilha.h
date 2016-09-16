#ifndef PILHA_H_INCLUDED
#define PILHA_H_INCLUDED

typedef struct NodeP{
    int posN;
    int posS;
    //int escolha;
    struct NodeP *next;
}NodeP;

typedef struct Pilha{
    NodeP *first;
    NodeP *last;
    int nJogadas;
    int qnt;
}Pilha;

int criarP(Pilha *pilha);

int empilhar(Pilha **pilha, int posN, int posS);

int desempilhar(Pilha **pilha);

#endif // PILHA_H_INCLUDED
