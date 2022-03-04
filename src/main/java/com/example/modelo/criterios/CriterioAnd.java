package com.example.modelo.criterios;


import com.example.modelo.Archivo;
import com.example.modelo.Elemento;

public class CriterioAnd implements Criterio{
    private Criterio criterio1;
    private Criterio criterio2;

    public CriterioAnd(Criterio c1, Criterio c2){
        criterio1 = c1;
        criterio2 = c2;
    }

	@Override
	public Boolean cumple(Archivo e) {
		return criterio1.cumple(e) && criterio2.cumple(e);
	}
}
