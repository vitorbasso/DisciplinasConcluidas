% This buffer is for notes you don't want to save.
% If you want to create a file, visit that file with C-x C-f,
% then enter the text in that file's own buffer.

f(a).
f(b).
g(a).
g(b).
h(b).

k(X):-
	f(X),
	g(X),
	h(X).
