:- dynamic(enfermedad/4).

buscar_en(X, [X | _]).
buscar_en(X, [_ | Cola]) :-
    buscar_en(X, Cola).

coincide_sintomas([], _).
coincide_sintomas([H | T], N) :-
    enfermedad(N, sintomas(X), _, _),
    buscar_en(H, X),
    coincide_sintomas(T, N).
  
diagnostico([], _).
diagnostico(SintomasU, E) :-
    enfermedad(E, sintomas(X), _, _),
    buscar_en(S, SintomasU),
    buscar_en(S, X).
    
diagnostico_categoria(C, E) :-
    enfermedad(E,_, categoria(C), _).

recomendacion(E, R):-
    enfermedad(E,_,_,R).

enfermedades_cronicas(E):-
    enfermedad(E,_,categoria(cronica),_).

enfermedades_por_sintoma(E, S):-
    enfermedad(E, sintomas(X),_,_),
    buscar_en(S, X).