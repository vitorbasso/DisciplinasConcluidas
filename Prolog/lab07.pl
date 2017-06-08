% This buffer is for notes you don't want to save.
% If you want to create a file, visit that file with C-x C-f,
% then enter the text in that file's own buffer.

concatena([],L,L).
concatena([X|R],L,[X|C]) :-
	concatena(R,L,C).

adiciona(X,L,[X|L]).

tira(_,[],[]).
tira(X,[X|R],R).
tira(X,[Y|R],[Y|R1]):-
	tira(X,R,R1).

adiciona_fim(X,L,NL):-
	concatena(L,[X],NL).

elimina(_,[],[]).
elimina(X,[X|Z],Z):-!.
elimina(X,[Y|Z],[Y|L1]):-
	X\=Y,
	elimina(X,Z,L1).

%Elimina todos os elementos X de uma lista
elimina_todos(_,[],[]).
elimina_todos(X,[X|B],L):-
	elimina_todos(X,B,L).
elimina_todos(X,[H|B],[H|L1]):-
	X\=H,
	elimina_todos(X,B,L1).

%Verifica se um elemento X pertence a uma lista L
pertence(_,[]).
pertence(X,[X|_]).
pertence(X,[H|B]):-
	H\=X,
	pertence(X,B).

%Verifica qual o último elemento de uma lista
ultimo(X,[X]).
ultimo(X,[_|L]):-
	ultimo(X,L).

%Cria uma sublista a partir de um valor X
%
