telefone(maria,123).
telefone(joaquim,234).
telefone(marco,345).
telefone(pedro,456).
telefone(ana,567).
telefone(juliana,678).

estrada(a,b,25).
estrada(a,d,23).
estrada(b,e,32).
estrada(b,c,19).
estrada(c,d,14).
estrada(c,f,28).
estrada(d,f,30).
estrada(e,f,26).

aluno(joao,ppi).
aluno(pedro,ppi).
aluno(maria,ppiii).
aluno(rui,ppiii).
aluno(manuel,ppiii).
aluno(pedro,ppiii).
aluno(rui,ppiv).

estuda(joao).
estuda(maria).
estuda(manuel).

pai(pedro,ana).
pai(pedro,maria).
pai(joaquim,pedro).
pai(manoel,joaquim).

visita(juliana,maria).
visita(ana,joaquim).
visita(marco,juliana).
visita(pedro,juliana).

emCasa(joaquim).
emCasa(maria).

acompanhada(X):-
	visita(_,X).

inconsistente:-
	emCasa(X),
	visita(X,_).

em_casa_de(X,Y):-
	visita(X,Y).
em_casa_de(X,Y):-
	visita(X,Z),
	em_casa_de(Z,Y).

contato(X,Y):-
	em_casa_de(X,Z),
	telefone(Z,Y).

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
	not(Z=W),!.

ancestral(X,Y):-
	pai(X,Y).
ancestral(X,Y):-
	pai(X,Z),
	ancestral(Z,Y).

dist(A,B,D):-
	estrada(A,B,D).
dist(A,B,D):-
	estrada(A,C,E),
	dist(C,B,F),
	D is F+E.

fazppiii(X):-
	aluno(X,ppiii).

socio(joao).
socio(suzana).
socio(basilio).
socio(elvira).

casado(joao,suzana).
irma(elvira,basilio).
