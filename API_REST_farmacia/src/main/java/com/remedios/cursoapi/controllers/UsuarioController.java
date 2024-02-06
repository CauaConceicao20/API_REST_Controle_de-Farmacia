package com.remedios.cursoapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.remedios.cursoapi.infra.DadosTokenJWT;
import com.remedios.cursoapi.infra.TokenService;
import com.remedios.cursoapi.usuarios.DadosAutenticacao;
import com.remedios.cursoapi.usuarios.DadosCadastroUsuario;
import com.remedios.cursoapi.usuarios.Usuario;
import com.remedios.cursoapi.usuarios.UsuarioRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@Tag(name = "Usuários", description = "Endpoints para controle de usuarios")
public class UsuarioController {
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioRepository repository;
	
	@PostMapping("/login")
	@Operation(summary = "Fazer Login",
			tags = {"Usuários"},
			responses = {
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content),
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "204",content = @Content),
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
			})
	public ResponseEntity<?> login(@RequestBody @Valid DadosAutenticacao dados) {
		var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
		var autenticacao = manager.authenticate(token);
		
		var tokenJWT = tokenService.gerarToken((Usuario) autenticacao.getPrincipal());
		
		return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
	}
	@PostMapping("/register")
	@Operation(summary = "Fazer Cadastro",
			tags = {"Usuários"},
			responses = {
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content),
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "204",content = @Content),
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
			})
	public ResponseEntity<?> register(@RequestBody @Valid DadosCadastroUsuario dados) {
		if(repository.findByLogin(dados.login()) != null) 
			return ResponseEntity.badRequest().build();
		
		var encriptarSenha = new BCryptPasswordEncoder().encode(dados.senha());
		var usuario = new Usuario(dados.login(), encriptarSenha);
		
		repository.save(usuario);
		
		return ResponseEntity.ok().build();
		
	}

}
