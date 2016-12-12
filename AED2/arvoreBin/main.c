#include <stdio.h>
#include <stdlib.h>
#include "Arvore.h"

int main()
{

    Arvore a = cria_arvore(1, NULL, NULL);
    Arvore b = cria_arvore(2, NULL, NULL);

    Arvore c = cria_arvore(3, NULL, NULL);
    Arvore d = cria_arvore(4, NULL, NULL);

    Arvore e = cria_arvore(5, a, b);
    Arvore f = cria_arvore(6, c, d);

    Arvore g = cria_arvore(7, e, f);

    printf("\n");
    printf("Pre order: ");
    exibe_arvore_preorder(g);
    printf("\n\n");

    printf("\n");
    printf("Post order: ");
    exibe_arvore_postorder(g);
    printf("\n\n");

    printf("\n");
    printf("In order: ");
    exibe_arvore_inorder(g);
    printf("\n\n");

    if(1 == existe(g, 1))
        printf("EXISTE!\n");

    printf("\ngrau de g: %d", grau_arvore(a));

    percorre_arvore(g);

    remove_folha(&g, 1);

    if(1 == existe(g, 1))
        printf("EXISTE2\n");

    libera_arvore(&g);



    return 0;
}
