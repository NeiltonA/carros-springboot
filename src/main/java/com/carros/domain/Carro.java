package com.carros.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;


@Entity //pegar a tabela do BD
@Data
public class Carro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//cria a chave primária com a própria notação de "auto_increment" do MySQL.
	private Long id;
	private String nome;
	private String tipo;
	
	/*
	 * //construtor padrao para funcionar o projeto public Carro() {
	 * 
	 * }
	 * 
	 * //construtor public Carro(long id, String nome) { this.id = id; this.nome =
	 * nome; }
	 * 
	 * //gat end set
	 * 
	 * 
	 * public long getId() { return id; }
	 * 
	 * 
	 * public String getTipo() { return tipo; }
	 * 
	 * public void setTipo(String tipo) { this.tipo = tipo; }
	 * 
	 * public void setId(long id) { this.id = id; }
	 * 
	 * 
	 * public String getNome() { return nome; }
	 * 
	 * 
	 * public void setNome(String nome) { this.nome = nome; }
	 */
	
	
}
