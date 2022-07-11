package com.stationone.filme.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Filme implements Serializable {

	
	private static final long serialVersionUID = -2455887834804293778L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	@NotBlank(message="Campo Obrigatório")
	@Size(min = 3, max=255,message="campo com no mínimo 3 e no máximo de 255 caracteres.")	
	private String titulo;
	
	@NotBlank(message="Campo Obrigatório")
	@Size(min = 3, max=255,message="campo com no mínimo 3 e no máximo de 255 caracteres.")
	private String descricao;
	
	@NotBlank(message="Campo Obrigatório")
	@Size(min = 3, max=100,message="campo com no mínimo 3 e no máximo de 100 caracteres.")
	private String autor;
	
	
	public Filme() {
		
	}
	
	


	public Filme(Long id,
			@NotBlank(message = "Campo Obrigatório") @Size(min = 3, max = 255, message = "campo com no mínimo 3 e no máximo de 255 caracteres.") String titulo,
			@NotBlank(message = "Campo Obrigatório") @Size(min = 3, max = 255, message = "campo com no mínimo 3 e no máximo de 255 caracteres.") String descricao,
			@NotBlank(message = "Campo Obrigatório") @Size(min = 3, max = 100, message = "campo com no mínimo 3 e no máximo de 100 caracteres.") String autor) {
		
		this.id = id;
		this.titulo = titulo.toUpperCase();
		this.descricao = descricao;
		this.autor = autor.toUpperCase();
	}




	@Override
	public String toString() {
		return "Filme [id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", autor=" + autor + "]";
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getAutor() {
		return autor;
	}


	public void setAutor(String autor) {
		this.autor = autor.toUpperCase();
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Filme other = (Filme) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	

	
	
	
}
