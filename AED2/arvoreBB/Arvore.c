#include <stdio.h>
#include <stdlib.h>
#include "Arvore.h"


Arvore cria_arvore(){
    return NULL;
}

int arvore_vazia(Arvore *arvore){

    if((*arvore) == NULL)
        return 1;
    else
        return 0;

}

void libera_arvore(Arvore *arvore){

    if(!arvore_vazia(arvore)){

        libera_arvore(&((*arvore)->sae));
        libera_arvore(&((*arvore)->sad));

        free((*arvore));

    }

    (*arvore) = NULL;

}

void exibe_arvore(Arvore arvore){

    if(arvore_vazia(&arvore))
        printf("<>");
    else{

        printf("<");

        printf("%s", arvore->info.nome);

        exibe_arvore(arvore->sae);
        exibe_arvore(arvore->sad);

        printf(">");

    }

}

void exibe_ordenado(Arvore arvore){

    if(arvore_vazia(&arvore))
        printf("<>");
    else{
        printf("<");

        exibe_ordenado(arvore->sae);

        printf("%s(chave = %d)", arvore->info.nome, arvore->info.chave);

        exibe_ordenado(arvore->sad);

        printf(">");

    }


}

int insere_ord(Arvore *arvore, Registro registro){

    if(arvore == NULL)
        return 0;

    if(arvore_vazia(arvore)){
        Arvore novoNo = (Arvore)malloc(sizeof(Node));
        if(novoNo == NULL)
            return 0;
        novoNo->info = registro;
        novoNo->sae = NULL;
        novoNo->sad = NULL;
        (*arvore) = novoNo;
        return 1;
    }

    if((*arvore)->info.chave < registro.chave)
        insere_ord(&((*arvore)->sad), registro);
    else
        insere_ord(&((*arvore)->sae), registro);

}

int remove_ord(Arvore *arvore, int chave){

    if(arvore == NULL || arvore_vazia(arvore))
        return 0;

    if(chave > (*arvore)->info.chave)
        return remove_ord(&(*arvore)->sad, chave);
    else if( chave < (*arvore)->info.chave)
        return remove_ord(&(*arvore)->sae, chave);
    else{

        if(arvore_vazia(&(*arvore)->sae) && arvore_vazia(&(*arvore)->sad)){
            free(*arvore);
            *arvore = NULL;
            return 1;
        }else if(!arvore_vazia(&(*arvore)->sae) && arvore_vazia(&(*arvore)->sad)){
            Arvore aux = (*arvore);
            (*arvore) = (*arvore)->sae;
            free(aux);
            return 1;
        }else if(arvore_vazia(&(*arvore)->sae) && !arvore_vazia(&(*arvore)->sad)){
            Arvore aux = (*arvore);
            (*arvore) = (*arvore)->sad;
            free(aux);
            return 1;
        }else{
            Arvore aux = (*arvore)->sae;
            while(!arvore_vazia(&(aux->sad))){
                aux = aux->sad;
            }

            Registro temp = (*arvore)->info;
            (*arvore)->info = aux->info;
            aux->info = temp;
            return remove_ord(&(*arvore)->sae, chave);

        }

    }

}


Arvore busca_bin(Arvore arvore, int chave){

    if(arvore_vazia(&arvore))
        return NULL;

    if(arvore->info.chave == chave)
        return arvore;
    else if(chave > arvore->info.chave)
        return busca_bin(arvore->sad, chave);
    else
        return busca_bin(arvore->sae, chave);

}
