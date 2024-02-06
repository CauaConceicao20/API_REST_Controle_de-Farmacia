package com.remedios.cursoapi.remedios;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name="remedios")
@Entity(name="Remedio")

public class Remedio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	
	@Enumerated(EnumType.STRING)
	private Via via;
	private String lote;
	private int quantidade;
	private LocalDate validade;
	
	@Enumerated(EnumType.STRING)
	private Laboratorio laboratorio;
	
	private Boolean ativo;
	
	public Remedio(DadosCadastroRemedio dados) {
		this.ativo = true;
		this.nome = dados.nome();
		this.via = dados.via();
		this.lote = dados.lote();
		this.quantidade = dados.quantidade();
		this.validade = dados.validade();
		this.laboratorio = dados.laboratorio();
	}
	
	public void atualizar(@Valid DadosAtualizarRemedio dados) {
		if(dados.nome() != null) {
			this.nome = dados.nome();
		}
		if(dados.via() != null) {
			this.via = dados.via();
		}
		if(dados.laboratorio() != null) {
			this.laboratorio = dados.laboratorio();
		}
	}
	
	public void inativar() {
		this.ativo = false;
	}
	
	public void ativar() {
		this.ativo = true;
	}
}
