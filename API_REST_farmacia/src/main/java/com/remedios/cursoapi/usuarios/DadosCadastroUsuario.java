package com.remedios.cursoapi.usuarios;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuario(
		@NotBlank
		String login,
		@NotBlank
		String senha) {

}
