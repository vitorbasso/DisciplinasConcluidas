% This buffer is for notes you don't want to save.
% If you want to create a file, visit that file with C-x C-f,
% then enter the text in that file's own buffer.

progenitor(maria,jos�).
progenitor(jo�o,jos�).
progenitor(jo�o,ana).
progenitor(jos�,j�lia).
progenitor(jos�,�ris).
progenitor(�ris,jorge).
progenitor(j�lia,gustavo).

masculino(jo�o).
masculino(jos�).
masculino(jorge).

feminino(maria).
feminino(j�lia).
feminino(ana).
feminino(�ris).

filho(X,Y):-
	progenitor(Y,X).

m�e(X,Y):-
	progenitor(X,Y),
	feminino(X).

av�(X,Y):-
	masculino(X),
	progenitor(X,Z),
	progenitor(Z,Y).

av�(X,Y):-
	feminino(X),
	progenitor(X,Z),
	progenitor(Z,Y).

irm�(X,Y):-
	feminino(X),
	progenitor(Z,X),
	progenitor(Z,Y),
	not(X=Y).

antepassado(X,Y):-
	progenitor(X,Y).
antepassado(X,Z):-
	progenitor(X,Y),
	antepassado(Y,Z).

tio(X,Y):-
	progenitor(Z,X),
	progenitor(Z,W),
	progenitor(W,Y),
	not(W=X).

prima(X,Y):-
	tio(Z,X),
	progenitor(Z,Y).

descendente(X,Y):-
	progenitor(Y,X).
descendente(X,Z):-
	progenitor(Y,X),
	descendente(Y,Z).
