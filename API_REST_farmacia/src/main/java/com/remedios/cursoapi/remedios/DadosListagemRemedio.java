package com.remedios.cursoapi.remedios;

import java.time.LocalDate;

public record DadosListagemRemedio(	
		String nome, 
		Via via,
		String lote,
		int quantidade,
		LocalDate validade, 
		Laboratorio laboratorio) {
	
	public DadosListagemRemedio(Remedio remedio) {
		this(remedio.getNome(), 
			 remedio.getVia(),
			 remedio.getLote(),
			 remedio.getQuantidade()
			 ,remedio.getValidade(),
			 remedio.getLaboratorio()
			 );
	}

}
