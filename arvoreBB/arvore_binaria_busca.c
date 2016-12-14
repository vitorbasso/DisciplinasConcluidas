#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct no no;

struct registro {
    char curso[30];
    char nome[30];
    int idade;
};

typedef struct registro reg;

struct no {
    reg info;
    no *sae;
    no *sad;
};

typedef no* Arv;

Arv cria_arvore () {
    return NULL;
}

int arvore_vazia(Arv *a) {
    if (*a == NULL)
        return 1;

    return 0;
}

void libera_arvore(Arv *a) {
    if (*a != NULL) {
        libera_arvore(&(*a)->sae);
        libera_arvore(&(*a)->sad);
        free(*a);
    }

    *a = NULL;
}

void exibe_arvore(Arv *a) {
    if (arvore_vazia(a)) {
        printf(" <x> ");
        return;
    }

    printf("<");

    printf("%s",(*a)->info.nome);
    exibe_arvore(&(*a)->sae);
    exibe_arvore(&(*a)->sad);

    printf(">");
}

void exibe_ordenado(Arv *a) {
    if (a == NULL) {
        return;
    }

    if (arvore_vazia(a)) {
        printf("<x>");
        return;
    }

    printf("<");

    exibe_ordenado(&(*a)->sae);
    printf("%s",(*a)->info.nome);
    exibe_ordenado(&(*a)->sad);

    printf(">");
}

int insere_ordenado(Arv *a, reg elem) {
    if (a == NULL)
        return 0;

    if (arvore_vazia(a)) {
        //printf("Inseri %d\n", elem.idade);
        *a = (Arv)malloc(sizeof(no));

        (*a)->info = elem;
        (*a)->sae = NULL;
        (*a)->sad = NULL;

        return 1;
    }

    if (elem.idade <= (*a)->info.idade) {
        return insere_ordenado(&(*a)->sae, elem);
    } else {
        return insere_ordenado(&(*a)->sad, elem);
    }

    return 0;

}

int remove_ord (Arv *a, int idade) {
    if (a == 0 || arvore_vazia(a)) {
        return 0;
    }

    if (idade > (*a)->info.idade)
        return remove_ord(&(*a)->sad, idade);

    if (idade < (*a)->info.idade)
        return remove_ord(&(*a)->sae, idade);

    if ((*a)->sae == NULL && (*a)->sad == NULL) {
        free(*a);
        a = NULL;
        return 1;
    }

    if ((*a)->sae != NULL && (*a)->sad == NULL) {
        Arv aux = *a;
        *a = (*a)->sae;
        free(aux);
        return 1;
    }

    if ((*a)->sae == NULL && (*a)->sad != NULL) {
        Arv aux = *a;
        *a = (*a)->sad;
        free(aux);
        return 1;
    }

    Arv *aux = &(*a)->sae;
    while ((*aux)->sad != NULL) {
        aux = &(*aux)->sad;
    }

    reg tmp = (*a)->info;
    (*a)->info = (*aux)->info;
    (*aux)->info = tmp;

    return remove_ord(&(*a)->sae, idade);
}

Arv busca_bin(Arv A, int idade) {
    if (A == 0)
        return NULL;

    if (arvore_vazia(&A))
        return NULL;

    if (idade == A->info.idade)
        return (A);

    if (idade > A->info.idade)
        return busca_bin(A->sad, idade);

    if (idade < A->info.idade)
        return busca_bin(A->sae, idade);

}

reg* maior (Arv A) {
    if (A == NULL)
        return NULL;

    if (arvore_vazia(&A))
        return NULL;

    reg *md, *me;
    md = maior(A->sad);
    me = maior(A->sae);

    if (md == NULL && me == NULL)
        return &(A->info);

    if (me != NULL && md == NULL) {
        if (me->idade > A->info.idade)
            return me;
        else
            return &(A->info);
    }

    if (me == NULL && md != NULL) {
        if (md->idade > A->info.idade)
            return md;
        else
            return &(A->info);
    }

    reg *maior;

    if (md->idade > me->idade)
        maior = md;
    else
        maior = me;

    if (maior->idade > A->info.idade)
        return maior;
    else
        return &(A->info);
}

int de_maior (Arv A) {
    if (A == NULL)
        return 0;

    if (arvore_vazia(&A))
        return 0;

    int cnt = 0;

    if (A->info.idade >= 18) cnt++;

    return cnt + de_maior(A->sae) + de_maior(A->sad);
}

int qtde_nos(Arv A, int ini, int fim) {
    if (A == NULL)
        return 0;

    if (arvore_vazia(&A))
        return 0;

    int cnt = 0;

    if (A->info.idade >= ini && A->info.idade <= fim) cnt++;

    return cnt + qtde_nos(A->sae, ini, fim) + qtde_nos(A->sad, ini, fim);
}

Arv juntar (Arv A1, Arv A2) {
    //TODO
}

int main () {

    char nome[20];
    Arv a ; a = cria_arvore();

    reg r1;
    strcpy(r1.nome, "Weuler");
    strcpy(r1.curso, "BCC");
    r1.idade = 21;

    reg r2;
    strcpy(r2.nome, "Josias");
    strcpy(r2.curso, "BCC");
    r2.idade = 15;

    reg r3;
    strcpy(r3.nome, "Alano");
    strcpy(r3.curso, "BCC");
    r3.idade = 22;

    reg r4;
    strcpy(r4.nome, "Locao");
    strcpy(r4.curso, "BSI");
    r4.idade = 10;

    reg r5;
    strcpy(r5.nome, "Vitor");
    strcpy(r5.curso, "BBC");
    r5.idade = 23;


    insere_ordenado(&a, r1);
    insere_ordenado(&a, r2);
    insere_ordenado(&a, r4);
    insere_ordenado(&a, r3);
    insere_ordenado(&a, r5);

    remove_ord(&a, 22);
    int loop = 0;
    int escolha;

    reg* teste = maior(a);

    while (loop == 0){
        printf("\t\tArvore Binaria de Busca.\n\n");
        printf("1 - exibir a arvore ordenada.\n");
        printf("2 - econtrar o com maior idade.\n");
        printf("3 - sair.\n");
        printf("Escolha: ");
        scanf("%d", &escolha);

        switch(escolha){
        case 1:
            printf("\n\n");
            exibe_ordenado(&a);
            printf("\n\n");
            break;
        case 2:
            printf("\n\n");
            printf("O maior e: %s\n", teste->nome);
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

  //  exibe_ordenado(&a);
    //printf("\n");

//    reg* teste = maior(a);

    //printf("Maiorzao : %s\n", teste->nome);

    return 1;

}
