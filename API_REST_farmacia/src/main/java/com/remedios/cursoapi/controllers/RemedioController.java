package com.remedios.cursoapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.remedios.cursoapi.remedios.DadosAtualizarRemedio;
import com.remedios.cursoapi.remedios.DadosCadastroRemedio;
import com.remedios.cursoapi.remedios.DadosDetalhamentoRemedio;
import com.remedios.cursoapi.remedios.DadosListagemRemedio;
import com.remedios.cursoapi.remedios.Remedio;
import com.remedios.cursoapi.remedios.RemedioRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/remedio")
public class RemedioController {
	@Autowired
	private RemedioRepository repository;
	
	@PostMapping("/cadastrar")
	@Transactional
	@Operation(summary = "Cadastrar um remedio",
			tags = {"Remédios"},
			responses = {
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content),
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "204",content = @Content),
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
			})
	public ResponseEntity<DadosDetalhamentoRemedio> cadastrarRemedio(@RequestBody @Valid DadosCadastroRemedio dados, UriComponentsBuilder uribuilder) {
		var remedio = new Remedio(dados);
		repository.save(remedio);
	
		var uri = uribuilder.path("/remedio/{id}").buildAndExpand(remedio.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosDetalhamentoRemedio(remedio));
	}
	
	@GetMapping("/listar")
	@Operation(summary = "Busque todos remedios cadastrados",
			tags = {"Remédios"},
			responses = {
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content),
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "204",content = @Content),
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
			})
	public ResponseEntity<List<DadosListagemRemedio>> listar() {
		var lista = repository.findAllByAtivoTrue().stream().map(DadosListagemRemedio::new).toList();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/listarInativos")
	@Operation(summary = " busque todos remédios inativados",
			tags = {"Remédios"},
			responses = {
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content),
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "204",content = @Content),
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
			})
	public ResponseEntity<List<DadosListagemRemedio>> listarInativos() {
		var lista = repository.findAllByAtivoFalse().stream().map(DadosListagemRemedio::new).toList();
		
		return ResponseEntity.ok(lista);
	}
	
	@PutMapping("/atualizar")
	@Transactional
	@Operation(summary = "Atualize cadastro de algum remédio",
			tags = {"Remédios"},
			responses = {
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content),
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "204",content = @Content),
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
			})
	public ResponseEntity<DadosDetalhamentoRemedio> atualizarRemedio(@RequestBody @Valid DadosAtualizarRemedio dados) {
		var remedio = repository.getReferenceById(dados.id());
		remedio.atualizar(dados);
		
		return ResponseEntity.ok(new DadosDetalhamentoRemedio(remedio));
	}
	
	@DeleteMapping("/excluir/{id}")
	@Transactional
	@Operation(summary = "Exclua um remédio",
			tags = {"Remédios"},
			responses = {
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content),
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "204",content = @Content),
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
			})
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		repository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/inativar/{id}")
	@Transactional
	@Operation(summary = "Desative o cadastro de um remedio pelo id",
			tags = {"Remédios"},
			responses = {
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content),
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "204",content = @Content),
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
			})
	public ResponseEntity<Void> inativar(@PathVariable Long id) {
		var remedio = repository.getReferenceById(id);
		remedio.inativar();
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/ativar/{id}")
	@Transactional
	@Operation(summary = "Reative o cadastro de um remédio pelo id",
			tags = {"Remédios"},
			responses = {
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content),
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "204",content = @Content),
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
					@io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
			})
	public ResponseEntity<Void> ativar(@PathVariable Long id) {
		var remedio = repository.getReferenceById(id);
		remedio.ativar();
		return ResponseEntity.noContent().build();
	}
}
