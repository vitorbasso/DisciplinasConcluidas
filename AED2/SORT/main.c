#include <stdio.h>
#include <stdlib.h>

void insertionSort(int vetor[], int n){
    int i, iteracao, elem;
    for(iteracao = 1; iteracao<n; iteracao++){
        elem = vetor[iteracao];
        i = iteracao-1;
        while(i>=0 && vetor[i]>elem){
            vetor[i+1] = vetor[i];
            i--;
        }
        vetor[i+1] = elem;
    }
}

void selectionSort(int vetor[], int n){
    int i, iteracao, aux, menor;
    for(iteracao = 0; iteracao<n-1; iteracao++){
        menor = iteracao;
        for(i = iteracao+1; i< n; i++){
            if(vetor[i]<vetor[menor]){
                menor = i;
            }
        }
        if(iteracao!=menor){
            aux = vetor[iteracao];
            vetor[iteracao] = vetor[menor];
            vetor[menor] = aux;
        }
    }
}

void bubbleSort(int vetor[], int n){
    int i, iteracao, aux, troca;
    for(iteracao = n-1; iteracao>0; iteracao--){
        troca = 0;
        for(i = 0; i<iteracao; i++){
            if(vetor[i]>vetor[i+1]){
                aux=vetor[i+1];
                vetor[i+1] = vetor[i];
                vetor[i] = aux;
                troca = 1;
            }
        }
        if(troca == 0){
            break;
        }
    }
}

void quickSort(int vetor[], int p, int r){
    int aux;
    if(p<r){
        int v = (rand()%(r-p))+p;
        int pivo = vetor[v];
        vetor[v] = vetor[r];
        vetor[r] = pivo;
        int i = p-1;
        int j = r;

        do{
            do{
                i++;
            }while(vetor[i]<pivo);
            do{
                j--;
            }while((vetor[j]>pivo) && (j>p));
            if(i<j){
                aux = vetor[i];
                vetor[i] = vetor[j];
                vetor[j] = aux;
            }
        }while(i<j);
        vetor[r] = vetor[i];
        vetor[i] = pivo;

        quickSort(vetor, p, i-1);
        quickSort(vetor, i+1, r);
    }
}

void mergeSort(int vetor[], int min, int max){
    if(min<max){
        int mid = (min+max)/2;
        mergeSort(vetor, min, mid);
        mergeSort(vetor, mid+1, max);
        merge(vetor, min, mid, max);
    }
}

void merge(int vetor[], int min, int mid, int max){
    int l1, l2, i;
    int aux[max];
    for(l1 = min, l2 = mid+1, i = min; l1<=mid && l2<=max;i++){
        if(vetor[l1]<=vetor[l2])
            aux[i] = vetor[l1++];
        else
            aux[i] = vetor[l2++];
    }

    while(l1<=mid)
        aux[i++] = vetor[l1++];
    while(l2<=max)
        aux[i++] = vetor[l2++];

    for(i = min; i<=max; i++){
        vetor[i] = aux[i];
    }
}

int main()
{
    int i=0, n=10;
    int vetor[10] = {10,9,6,7,8,5,3,4,2,1};
    printf("Antes da ordenacao\n");
    for(i=0; i<n; i++){
        printf("%d\n", vetor[i]);
    }
    mergeSort(vetor, 0, n);
    printf("Depois da ordenacao\n");
    for(i=0; i<n; i++){
        printf("%d\n", vetor[i]);
    }
    return 0;
}
