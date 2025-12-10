enfermedad(gripe,sintomas([fiebre,tos,dolor_cabeza,dolor_muscular]),categoria(viral),recomendaciones([descansar,hidratar,consultar_medico])).
enfermedad(resfriado,sintomas([tos,estornudos,dolor_garganta]),categoria(viral),recomendaciones([descansar,hidratar])).
enfermedad(diabetes,sintomas([sed,cansancio,perdida_peso]),categoria(cronica),recomendaciones([controlar_dieta,consultar_especialista])).
enfermedad(covid_19,sintomas([fiebre,tos,cansancio,perdida_gusto_olfato]),categoria(viral),recomendaciones([aislamiento,consultar_medico])).
enfermedad(varicela,sintomas([fiebre,erupcion,picazon]),categoria(viral),recomendaciones([descansar,evitar_rascar_lesiones])).
enfermedad(migrania,sintomas([dolor_cabeza,nausea,sensibilidad_luz]),categoria(cronica),recomendaciones([descansar,evitar_luz_intensa])).
enfermedad(alergia,sintomas([estornudos,picazon,ojos_lagrimosos]),categoria(alergia),recomendaciones([evitar_alergenos,antihistaminicos])).
enfermedad(hipotiroidismo,sintomas([cansancio,aumento_peso,piel_seca]),categoria(cronica),recomendaciones([control_medico_y_medicacion])).
enfermedad(gastroenteritis,sintomas([vomito,diarrea,dolor_abdominal,fiebre]),categoria(viral),recomendaciones([hidratacion,dieta_ligera])).
enfermedad(faringitis,sintomas([dolor_garganta,fiebre,tos]),categoria(viral/bacteriana),recomendaciones([consultar_medico])).

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