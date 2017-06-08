% This buffer is for notes you don't want to save.
% If you want to create a file, visit that file with C-x C-f,
% then enter the text in that file's own buffer.

progenitor(maria,josé).
progenitor(joão,josé).
progenitor(joão,ana).
progenitor(josé,júlia).
progenitor(josé,íris).
progenitor(íris,jorge).
progenitor(júlia,gustavo).

masculino(joão).
masculino(josé).
masculino(jorge).

feminino(maria).
feminino(júlia).
feminino(ana).
feminino(íris).

filho(X,Y):-
	progenitor(Y,X).

mãe(X,Y):-
	progenitor(X,Y),
	feminino(X).

avô(X,Y):-
	masculino(X),
	progenitor(X,Z),
	progenitor(Z,Y).

avó(X,Y):-
	feminino(X),
	progenitor(X,Z),
	progenitor(Z,Y).

irmã(X,Y):-
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
