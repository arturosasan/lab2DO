countTo(1,[1]).
countTo(2,[1,2]).
countTo(3,[1,2,3]).
countTo(4,[1,2,3,4]).

% EJERCICIO 4
mymember(H,[H,T]):- E==H.
mymember(H,[H|T]) :- (E=/=H),mymember(H,L).

% EJERCICIO 6
myappendL([], L, L).
myappendL([E|L1], L2, [E|L3]) :-myappendL(L1, L2, L3).