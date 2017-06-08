#include <stdio.h>
#include <stdlib.h>
#include "Arvore.h"

int main()
{

    Arvore arvore = cria_arvore(1);
    Arvore a = cria_arvore(2);
    Arvore b = cria_arvore(3);
    Arvore c = cria_arvore(4);
    Arvore d = cria_arvore(5);
    Arvore e = cria_arvore(6);
    Arvore f = cria_arvore(7);
    Arvore g = cria_arvore(8);
    Arvore h = cria_arvore(9);

    insere(e, h);

    insere(e, g);

    insere(c, f);

    insere(b, e);

    insere(b, d);

    insere(arvore, c);

    insere(arvore, b);

    insere(arvore, a);

    printf("\n\n");
    printf("Representacao arvore: ");
    exibe_arvore(arvore);
    printf("\n\n");

    libera_arvore(&arvore);


    return 0;

}
