package com.remedios.cursoapi.remedios;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarRemedio(
			@NotNull
			Long id, 
			
			@NotBlank
			String nome, 
			
			@Enumerated
			Via via, 
	
			@Enumerated
			Laboratorio laboratorio) {
	
}

