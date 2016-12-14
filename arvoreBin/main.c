#include <stdio.h>
#include <stdlib.h>
#include "arvore_binaria.h"

int main()
{

    int loop = 0;
    int choice;
    int ret = 0;



    Arvore a = cria_arvore(1, NULL, NULL);
    Arvore b = cria_arvore(2, NULL, NULL);

    Arvore c = cria_arvore(3, NULL, NULL);
    Arvore d = cria_arvore(4, NULL, NULL);

    Arvore e = cria_arvore(5, NULL, b);
    Arvore f = cria_arvore(6, c, d);

    Arvore g = cria_arvore(7, e, f);

    ret = gera_notacao(g);
    int elem;

    while(loop == 0){
       elem = 0;
       printf("\t\tArvore Binaria.\n\n");
       printf("1 - exibir arvore em pre-order.\n");
       printf("2 - exibir arvore em inorder.\n");
       printf("3 - exibir arvore em post-order\n");
       printf("4 - percorrer arvore em largura.\n");
       printf("5 - Numero de folhas na arvore.\n");
       printf("6 - Quantos nos possuem apenas um filho.\n");
       printf("7 - Altura da arvore.\n");
       printf("8 - A arvore esta cheia?\n");
       printf("9 - Ver nivel do elemento.\n");
       printf("10 - Vez altura do elemento.\n");
       printf("11 - sair.\n");
       printf("Escolha: ");
       scanf("%d", &choice);

       switch(choice){
           case 1:
                printf("\n\n");
                preorder(g);
                printf("\n\n");
                break;
           case 2:
                printf("\n\n");
                inorder(g);
                printf("\n\n");
                break;
           case 3:
                printf("\n\n");
                postorder(g);
                printf("\n\n");
                break;
           case 4:
                printf("\n\n");
                percorre_nivel(g);
                printf("\n\n");
                break;
           case 5:
                printf("\n\n");
                printf("A arvore tem %d folhas.", nro_folha(g));
                printf("\n\n");
                break;
           case 6:
                printf("\n\n");
                printf("A arvore tem %d nos com apenas um filho.", um_filho(g));
                printf("\n\n");
                break;
           case 7:
                printf("\n\n");
                printf("A altura da arvore e de %d.", altura_arv(g));
                printf("\n\n");
                break;
           case 8:
                printf("\n\n");
                if(cheia(g) == 1)
                    printf("A arvore esta cheia.");
                else
                    printf("A arvore nao esta cheia.");
                printf("\n\n");
                break;
           case 9:
                printf("\n\n");
                printf("De qual elemento deseja ver o nivel: ");
                scanf("%d", &elem);
                printf("O elemento %d esta no nivel %d.", elem, nivel_no(g, elem, 0));
                printf("\n\n");
                break;
           case 10:
                printf("\n\n");
                printf("De qual elemento deseja ver a altura: ");
                scanf("%d", &elem);
                printf("O elemento %d tem altura de %d.", elem, altura_no(g, elem));
                printf("\n\n");
                break;
           case 11:
                loop = 1;
                break;
           default:
                printf("\n Escolha uma opcao valida.\n");
       }


    }



    /*Arvore a = cria_arvore(1, NULL, NULL);
    Arvore b = cria_arvore(2, NULL, NULL);

    Arvore c = cria_arvore(3, NULL, NULL);
    Arvore d = cria_arvore(4, NULL, NULL);

    Arvore e = cria_arvore(5, NULL, b);
    Arvore f = cria_arvore(6, c, d);

    Arvore g = cria_arvore(7, e, f);

    Arvore h = cria_arvore(7, c, f);*/
    /*


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

    ret = gera_notacao(g);
    printf("\n");

    printf("Numero de folhas: %d\n",nro_folha(g));

    printf("Numero de nos com apenas um filho: %d\n", um_filho(g));

    printf("Altura da arvore : %d\n", altura_arv(g));

    if (cheia(g)) {
        printf("A arvore esta cheia!\n");
    } else {
        printf("A arvore nao esta cheia!\n");
    }

    if (iguais(g, h)) {
        printf("As arvores sao iguais!\n");
    } else {
        printf("As arvores nao sao iguais!\n");
    }

    libera_arvore(&g);*/




    return 0;
}
