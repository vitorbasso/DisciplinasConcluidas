% This buffer is for notes you don't want to save.
telefone(maria,123).
telefone(joaquim,234).
telefone(marco,345).
telefone(pedro,456).
telefone(ana,567).
telefone(juliana,678).

visita(juliana,maria).
visita(ana,joaquim).
visita(marco,juliana).
visita(pedro,juliana).

pai(pedro,ana).
pai(pedro,maria).
pai(joaquim,pedro).
pai(manoel,joaquim).

emCasa(joaquim).
emCasa(maria).


acompanhada(X):-
	visita(Y,X).

inconsistente:-
	visita(X,Y),
	emCasa(X).

em_casa_de(X,Y):-
	emCasa(Y),
	visita(X,Y).
em_casa_de(X,Y):-
	visita(X,Z),
	em_casa_de(Z,Y).

contato(X,T):-
	emCasa(X),
	telefone(X,T).
contato(X,T):-
	em_casa_de(X,Y),
	telefone(Y,T).

festeiro(X):-
	emCasa(X),
	em_casa_de(Y,X),
	em_casa_de(Z,X),
	em_casa_de(W,X),
	not(X=Y),
	not(X=Z),
	not(X=W),
	not(Y=Z),
	not(Y=W),
	not(Z=W).

ancestral(X,Y):-
	pai(X,Y).
ancestral(X,Z):-
	pai(W,Z),
	ancestral(X,W).


