package com.isaac.sisgreja.persist;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.isaac.sisgreja.domain.Fiel;
import com.isaac.sisgreja.domain.Pastor;
import com.isaac.sisgreja.util.Funcoes;

public class BancoDados {

	public static List<Pastor> pastores = new ArrayList<>();
	public static List<Fiel> fieis = new ArrayList<>();
	
	static {
		
		Pastor pastor = new Pastor();
		pastor.setCpf(1);
		pastor.setNome("João");
		
		try {
			pastor.setDataNacimento(Funcoes.stringToDate("15/20/1981"));
		} catch (ParseException ignore) { }
		pastor.setSalario(100000);		
		pastores.add(pastor);

		pastor = new Pastor();
		pastor.setCpf(2);
		pastor.setNome("João 2");
		try {
			pastor.setDataNacimento(Funcoes.stringToDate("15/20/1982"));
		} catch (ParseException ignore) { }
		pastor.setSalario(200000);		
		pastores.add(pastor);
		pastor = new Pastor();
		pastor.setCpf(3);
		pastor.setNome("João 3");
		try {
			pastor.setDataNacimento(Funcoes.stringToDate("15/20/1983"));
		} catch (ParseException ignore) { }
		pastor.setSalario(300000);		
		pastores.add(pastor);
		
		Fiel fiel = new Fiel();
		fiel.setCpf(1);
		fiel.setNome("João");
		try {
			fiel.setDataNacimento(Funcoes.stringToDate("15/20/1981"));
		} catch (ParseException ignore) { }
		fiel.setDisimo(10);		
		fieis.add(fiel);

		fiel = new Fiel();
		fiel.setCpf(2);
		fiel.setNome("João 2");
		try {
			fiel.setDataNacimento(Funcoes.stringToDate("15/20/1982"));
		} catch (ParseException ignore) { }
		fiel.setDisimo(200000);		
		fieis.add(fiel);

		fiel = new Fiel();
		fiel.setCpf(3);
		fiel.setNome("João 3");
		try {
			fiel.setDataNacimento(Funcoes.stringToDate("15/20/1983"));
		} catch (ParseException ignore) { }
		fiel.setDisimo(300000);		
		fieis.add(fiel);

}

}
