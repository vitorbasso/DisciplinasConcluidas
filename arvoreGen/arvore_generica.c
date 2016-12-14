#include <stdlib.h>
#include <stdio.h>
#include "fila_arvore_generica.h"

Arv cria_arvore(int a) {
    Arv ret = malloc(sizeof(Arv));

    if (ret == NULL)
        return NULL;

    ret->info = a;
    ret->esq = NULL;
    ret->dir = NULL;

    return ret;
}

int insere(Arv a, Arv sa) {
    if (a == NULL)
        return 0;

    sa->dir = a->esq;
    a->esq = sa;

    return 1;
}


void preorder (Arv a) {
    if (a == NULL) {
        return;
    }
    printf(" %d ",a->info);
    preorder(a->esq);
    preorder(a->dir);
}

void postorder (Arv a) {
    if (a == NULL) {
        return;
    }
    postorder(a->esq);
    printf(" %d ",a->info);
    postorder(a->dir);

}

void percorre_nivel(Arv A) {

    if (A == NULL)
        return;

    Fila *fila = (Fila*)malloc(sizeof(Fila));
    criarF(fila);
    enfilar(&fila, A);

    while (fila->qnt != 0) {
        Arv nxt = fila->first->next->arvore;

        Arv aux_irmao = nxt;
        while (aux_irmao != NULL) {
            printf("%d ", aux_irmao->info);
            if (aux_irmao->esq != NULL) enfilar(&fila, aux_irmao->esq);
            aux_irmao = aux_irmao->dir;
        }

        desenfilar(&fila);
    }
}

int nro_folha (Arv A) {

	int cnt = 0;

	if (A == NULL)
        return 0;

    Fila *fila = (Fila*)malloc(sizeof(Fila));
    criarF(fila);
    enfilar(&fila, A);

    while (fila->qnt != 0) {
        Arv nxt = fila->first->next->arvore;

        Arv aux_irmao = nxt;
        while (aux_irmao != NULL) {
            if (aux_irmao->esq == NULL)
            	cnt++;
            else
            	enfilar(&fila, aux_irmao->esq);
            aux_irmao = aux_irmao->dir;
        }

        desenfilar(&fila);
    }

    return cnt;
}

int main () {
    Arv A, B, C, D, E, F, G, H, I;
    A = cria_arvore(1);
    B = cria_arvore(2);
    C = cria_arvore(3);  D = cria_arvore(4);
    E = cria_arvore(5);  F = cria_arvore(6);
    G = cria_arvore(7);  H = cria_arvore(8);
    I = cria_arvore(9);

    insere(F,I);  insere(F,H);
    insere(C,F);  insere(C,E);
    insere(D,G);
    insere(A,D); insere(A,C); insere (A,B);

    int loop = 0, escolha;

    while(loop == 0){
        printf("\t\tArvore Generica.\n\n");
        printf("1 - numero de folhas da arvore.\n");
        printf("2 - Percorrer a arvore em largura.\n");
        printf("3 - sair.\n");
        printf("Escolha: ");
        scanf("%d", &escolha);

        switch(escolha){
        case 1:
            printf("\n\n");
            printf("Numero de folhas: %d\n",nro_folha(A));
            printf("\n\n");
            break;
        case 2:
            printf("\n\n");
            percorre_nivel(A);
            printf("\n\n");
            break;
        case 3:
            loop = 1;
            break;
        default:
            printf("\n\n");
            printf("Escolha uma opcao valida.");
            printf("\n\n");
        }
    }

}
