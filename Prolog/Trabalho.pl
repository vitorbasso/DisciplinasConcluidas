/************************INICIALIZAÇÃO DA MATRIZ***************************/
menu:-
	retractall(ocupada(_)),
	retractall(sim(_)),
	retractall(naim(_)),
	tamanho(R),
	mapa(R),
	insere,
	pontos,
	!,
	repeat,
	not(imprime(R)),
	chamacaminho(X),
	(sim(0),!,write('Deu Bad'),fail;
	sim(2),!,write(X);
	sim(1),fail;
	fail).

tamanho(R):-
	repeat,
	write('Digite o tamanho do mapa: '),
	read(R),
	(R>=2,R=<15,!;write('Digite um numero valido'),nl,fail).

mapa(R):-
	R2 is R - 1,
	retractall(ocupada(_)),
	not(criay(-1,-1,R)),
	not(criay(R,-1,R)),
	not(criax(-1,0,R2)),
	not(criax(R,0,R2)).
	%listing(ocupada/1),

criay(X,Y,Y1):-
	Y =< Y1,
	assertz(ocupada(X - Y)),
	Y2 is Y + 1,
	!,
	criay(X,Y2,Y1).

criax(Y,X,X1):-
	X =< X1,
	assertz(ocupada(X - Y)),
	X2 is X + 1,
	!,
	criax(Y,X2,X1).

insere:-
	!,
	write('Digite os pontos obstaculo:'),
	nl,
	repeat,
	write('X:'),
	read(X),
	write('Y:'),
	read(Y),
	nl,
	(X == -5, Y == -5,!;assert(ocupada(X - Y)),fail).

/*
insere:-
	write('Digite 1 para adicionar obstaculo e 0 caso contrario'),
	repeat,
	read(N),
	(N == 0,!,fail; N == 1,add,fail).

add:-
	!,
	write('Digite o ponto obstaculo:'),
	nl,
	write('X:'),
	read(X),
	write('Y:'),
	read(Y),
	assert(ocupada(X - Y)).
*/

pontos:-
	retractall(inicio(_)),
	retractall(final(_)),
	write('Digite O inicio:'),
	nl,
	write('X:'),
	read(X),
	write('Y:'),
	read(Y),
	assert(inicio(X - Y)),
	write('Digite O final:'),
	nl,
	write('X:'),
	read(W),
	write('Y:'),
	read(K),
	assert(final(W - K)).

chamacaminho(P):-
	inicio(X - Y),
	final(W - K),
	!,
	caminho(X -Y, W - K, P).

/*******************************FIM INICIALIZAÇÃO*****************************/

/*******************************MOVIMENTAÇÃO**********************************/
passo(X1- Y1,ne,X2- Y2):-   %nee
	Y2 is Y1 +1,
	X2 is X1 +1.
passo(X1- Y1,se,X2- Y2):-
	Y2 is Y1-1,
	X2 is X1 +1.
passo(X1- Y1,so,X2- Y2):-
	Y2 is Y1 -1,
	X2 is X1 - 1.
passo(X1- Y1,no,X2- Y2):-
	X2 is X1 -1,
	Y2 is Y1 +1.
passo(X1- Y1,n,X1- Y2):-
	Y2 is Y1 +1.
passo(X1- Y1,e,X2- Y1):-
	X2 is X1 + 1.
passo(X1- Y1,s,X1- Y2):-
	Y2 is Y1 -1.
passo(X1- Y1,o,X2- Y1):-
	X2 is X1 -1.
/******************************************************************************/

/*****************************VERIFICAÇÃO DO CAMINHO***************************/
verifica(Xi-Yi,X2-Y2,C):-
	passo(Xi- (Yi),C,X2- (Y2)),
	not(ocupada(X2-Y2)),
	not(naim(X2-Y2)),
	!.
/*VERIFICA RETORNA VERDADEIRO QUANDO TIVER OPÇÃO PARA ANDAR*/

verifica_inicio:-
	inicio(X - Y),
	passo(X - Y,_,X2 - Y2),
	not(ocupada(X2-Y2)),
	!.
/*VERIFICA_INICIO RETORNA VERDADEIRO QUANDO TIVER OPÇÃO PARA ANDAR*/
/******************************************************************************/

/***************************GERANDO CAMINHOS***********************************/

caminho(Xi-Yi,Xi-Yi,[]):-
	!,
	retractall(sim(_)),
	assertz(sim(2)).

/**************** 8 CAMINHOS POSSIVEIS************/
caminho(Xi-Yi,Xf-Yf,[ne|Y]):-
	Xi<Xf,
	Yi<Yf,
	passo(Xi-(Yi),ne,X2-Y2),
	not(ocupada(X2-Y2)),
	not(naim(X2-Y2)),
	assertz(naim(X2-Y2)),
	!,
	caminho(X2- Y2,Xf-Yf,Y).
caminho(Xi-Yi,Xf-Yf,[se|Y]):-
	Xi<Xf,
	Yi>Yf,
	passo(Xi-(Yi),se,X2-Y2),
	not(ocupada(X2-Y2)),
	not(naim(X2-Y2)),
	assertz(naim(X2-Y2)),
	!,
	caminho(X2- Y2,Xf-Yf,Y).
caminho(Xi-Yi,Xf-Yf,[so|Y]):-
	Xi>Xf,
	Yi>Yf,
	passo(Xi-(Yi),so,X2-Y2),
	not(ocupada(X2-Y2)),
	not(naim(X2-Y2)),
	assertz(naim(X2-Y2)),
	!,
	caminho(X2- Y2,Xf-Yf,Y).
caminho(Xi-Yi,Xf-Yf,[no|Y]):-
	Xi>Xf,
	Yi<Yf,
	passo(Xi-(Yi),no,X2-Y2),
	not(ocupada(X2-Y2)),
	not(naim(X2-Y2)),
	assertz(naim(X2-Y2)),
	!,
	caminho(X2- Y2,Xf-Yf,Y).
caminho(Xi-Yi,Xf-Yf,[n|Y]):-
	Yi<Yf,
	passo(Xi-(Yi),n,X2-Y2),
	not(ocupada(X2-Y2)),
	not(naim(X2-Y2)),
	assertz(naim(X2-Y2)),
	!,
	caminho(X2- Y2,Xf-Yf,Y).
caminho(Xi-Yi,Xf-Yf,[e|Y]):-
	Xi<Xf,
	passo(Xi- Yi,e,X2-Y2),
	not(ocupada(X2-Y2)),
	not(naim(X2-Y2)),
	assertz(naim(X2-Y2)),
	!,
	caminho(X2- Y2,Xf-Yf,Y).
caminho(Xi-Yi,Xf-Yf,[s|Y]):-
	Yi>Yf,
	passo(Xi- Yi,s,X2-Y2),
	not(ocupada(X2-Y2)),
	not(naim(X2-Y2)),
	assertz(naim(X2-Y2)),
	!,
	caminho(X2- Y2,Xf-Yf,Y).
caminho(Xi-Yi,Xf-Yf,[o|Y]):-
	Xi>Xf,
	passo(Xi- Yi,o,X2-Y2),
	not(ocupada(X2-Y2)),
	not(naim(X2-Y2)),
	assertz(naim(X2-Y2)),
	!,
	caminho(X2- Y2,Xf-Yf,Y).
/************************************************/


/***************CAMINHO POSSIVEL*****************/
caminho(Xi-Yi,Xf-Yf,[C|Y]):-
	verifica(Xi- Yi,X2- Y2,C),
	not(naim(X2-Y2)),
	assertz(naim(X2-Y2)),
	!,
	caminho(X2- Y2,Xf- Yf,Y).
/************************************************/

/*VERIFICA SE EXISTE CAMINHO AINDA POSSIVEL NO COMEÇO*/
caminho(_ - _,_ - _,_):-
	not(verifica_inicio),
	retractall(sim(_)),
	assertz(sim(0)),
	!.
/******************************************************/

/****************VOLTANDO AO INICIO*******************/
caminho(Xi-Yi,_-_,_):-
	assertz(ocupada(Xi-Yi)),
	retractall(naim(_)),
	retractall(sim(_)),
	assertz(sim(1)),
	!.
/*****************************************************/


/********************************IMPRIME********************************/
imprime(R):-
	imprimematriz(-1, R, R).

imprimematriz(X,Y, R):-
	not(imprimelinha(X,Y, R)),
	nl,
	Y1 is Y - 1,
	!,
	Y1 >= -1,
	imprimematriz(X,Y1, R).

imprimelinha(X,Y, R):-
	elemento(X,Y),
	X1 is X + 1,
	!,
	X1 =< R,
	imprimelinha(X1,Y, R).

/*elemento(X,Y):-
	naim(X - Y),
	write('@'),
	!.*/

elemento(X,Y):-
	ocupada(X - Y),
	write('*'),
	!.
elemento(X,Y):-
	not(ocupada(X - Y)),
	write('0'),
	!.
/**************************************************************************/


/**********************************DYNAMIC*********************************/
:-dynamic(ocupada/1).
:-dynamic(inicio/1).
:-dynamic(final/1).
:-dynamic(naim/1).
:-dynamic(sim/1).
/**************************************************************************/


/********************************PREDICADO /1******************************/
/**************************************************************************/

/*MAPA 3-4
ocupada(1-1).
ocupada(1-2).
ocupada(1-3).
ocupada(1-4).
ocupada(2-3).
ocupada(3-1).
ocupada(3-2).
ocupada(3-3).
*/

/*MAPA 9
ocupada(0-0).
ocupada(0-1).
ocupada(0-2).
ocupada(0-3).
ocupada(0-4).
ocupada(0-5).
ocupada(0-7).
ocupada(0-8).
ocupada(1-0).
ocupada(1-1).
ocupada(1-2).
ocupada(1-3).
ocupada(1-7).
ocupada(1-8).
ocupada(2-0).
ocupada(2-1).
ocupada(2-2).
ocupada(2-3).
ocupada(2-5).
ocupada(2-6).
ocupada(2-8).
ocupada(3-0).
ocupada(3-1).
ocupada(3-5).
ocupada(3-6).
ocupada(3-7).
ocupada(4-3).
ocupada(4-4).
ocupada(4-6).
ocupada(4-8).
ocupada(5-0).
ocupada(5-1).
ocupada(5-5).
ocupada(5-7).
ocupada(5-8).
ocupada(6-2).
ocupada(6-3).
ocupada(6-4).
ocupada(6-5).
ocupada(6-6).
ocupada(6-7).
ocupada(6-8).
ocupada(7-3).
ocupada(7-4).
ocupada(7-5).
ocupada(7-6).
ocupada(7-7).
ocupada(7-8).
ocupada(8-0).
ocupada(8-1).
ocupada(8-2).
ocupada(8-4).
ocupada(8-5).
ocupada(8-6).
ocupada(8-7).
ocupada(8-8).
*/
