% This buffer is for notes you don't want to save.
% If you want to create a file, visit that file with C-x C-f,
% then enter the text in that file's own buffer.

cons(X,Y,[X|Y]).

membro(X,[X|_]):-!.
membro(X,[_|C]):-
	membro(X,C),!.

concatena([],L,L).
concatena([X|L1],L2,[X|L3]):-
	concatena(L1,L2,L3).

membro1(X,L):-
	concatena(_,[X|_],L),!.

remove(X,[X|L1],L1).
remove(X,[Y|L1],[Y|L2]):-
	remove(X,L1,L2).

inserir(X,L,L1):-
	remove(X,L1,L).

membro2(X,L):-
	remove(X,L,_).

inverte([],[]).
inverte([X|L],L2):-
	inverte(L,L1),
	concatena(L1,[X],L2).

inverte1(L,L1):-
	aux([],L,L1).

aux(L,[],L).
aux(L,[X|Y],L1):-
	aux([X|L],Y,L1).

sublista(S,L):-
	concatena(_,L2,L),
	concatena(S,_,L2).

permutar([],[]).
permutar([X|L],L2):-
	permutar(L,L1),
	inserir(X,L1,L2).

tamanho([],0).
tamanho([_|L],N):-
	tamanho(L,N1),
	N is N1+1.

grande(100).
grande(150).
grande(200).
grande(250).

propGrande(X,[X|_]):-
	grande(X).
propGrande(X,[_|Y]):-
	propGrande(X,Y).

nesimo(1,X,[X|_]).
nesimo(N,X,[_|Y]):-
	N2 is N-1,
	nesimo(N2,X,Y).

seleciona([],_,[]).
seleciona([M|N],L,[X|Y]):-
	nesimo(M,X,L),
	seleciona(N,L,Y).

soma([],0).
soma([X|Y],S):-
	soma(Y,R),
	S is R+X.

produto([],0).
produto([X],X).
produto([X|Y],S):-
	produto(Y,R),
	S is R*X,!.

intersec([X|Y],L,[X|Z]):-
	membro(X,L),
	intersec(Y,L,Z).
intersec([_|X],L,Y):-
	intersec(X,L,Y).
intersec(_,_,[]).

acomoda([],[]):-!.
acomoda([L|Y],Z):-
	acomoda(L,R),
	acomoda(Y,O),
	concatena(R,O,Z),!.
acomoda([X|Y],[X|R]):-
	acomoda(Y,R),!.

inv([],[]).
inv([[L|Y]|O],P):-
	inv(Y,R),
	inv(O,I),
	concatena(R,[L],Z),
	concatena(I,[Z],P).
inv([X|Y],R):-
	inv(Y,Z),
	concatena(Z,[X],R).

limpa(_,[],[]).
limpa(X,[X|T],R):-
	limpa(X,T,R),!.
limpa(X,[Y|T],[Y|R]):-
	limpa(X,T,R),!.

comida(queijo).
queijo(fungo).
queijo(leite).
queijo(sal).
tem(A,B):-
	comida(A),
	queijo(B).

pessoa(vitor).
pessoa(lorena).
amor(vitor,lorena).
amor(lorena,vitor).
ama(A,B):-
	pessoa(A),
	pessoa(B),
	amor(A,B).

:- op(600,xfx,tem).
:- op(600,xfx, ama).
