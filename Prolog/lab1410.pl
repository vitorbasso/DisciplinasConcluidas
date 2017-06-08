:-op(600,xfx,equivalente).
:-op(600,fx,not).
:-op(600,xfx,and).
:-op(600,xfx,or).

and(A,B):-
	A,
	B.

or(A,_):-
	A,!.
or(_,B):-
	B.

equivalente(A,B):-
	(A and B) or ((not A) and (not B)).

morgan(A,B):-
	(not (A and B)) equivalente ((not A) or (not B)).

:-op(600,xfx,se2).

se2(X,Y):-
        X,!,Y.

se3(X,Y,Z):-
	X,Y,!,Z.
