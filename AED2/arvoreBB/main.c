#include <stdio.h>
#include <stdlib.h>
#include "Arvore.h"

int main()
{
    Registro reg1;
    reg1.chave = 50;
    strcpy(reg1.nome, "Vitor");

    Registro reg2;
    reg2.chave = 5;
    strcpy(reg2.nome, "Weuler");

    Registro reg3;
    reg3.chave = 20;
    strcpy(reg3.nome, "Robson");

    Registro reg4;
    reg4.chave = 15;
    strcpy(reg4.nome, "Marcelo");

    Registro reg5;
    reg5.chave = 25;
    strcpy(reg5.nome, "Diogo");

    Arvore arvore = cria_arvore();

    insere_ord(&arvore, reg3);
    insere_ord(&arvore, reg5);
    insere_ord(&arvore, reg1);
    insere_ord(&arvore, reg2);
    insere_ord(&arvore, reg4);

    printf("\nExibir normal: ");
    exibe_arvore(arvore);


    printf("\n\n");
    printf("Exibir ordenado: ");
    exibe_ordenado(arvore);
    printf("\n\n");

    remove_ord(&arvore, 5);

    printf("Exibir ordenado: ");
    exibe_ordenado(arvore);
    printf("\n\n");

    Arvore busca = busca_bin(arvore, 15);

    if(!arvore_vazia(&busca))
        printf("Achei!");
    else
        printf("Nao achei!");
    printf("\n\n");

    libera_arvore(&arvore);

    return 0;
}
