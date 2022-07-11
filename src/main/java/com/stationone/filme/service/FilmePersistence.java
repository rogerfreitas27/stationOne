package com.stationone.filme.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.stationone.filme.model.Filme;
import com.stationone.filme.repository.FilmeRepository;

@Service
public class FilmePersistence {

	private final FilmeRepository filmeRepository;
	
	public  FilmePersistence(FilmeRepository filmeRepository) {
		this.filmeRepository=filmeRepository;
	}
	
	public void  save(Filme filme) {
		filmeRepository.save(filme);
	}
	
	public Optional<Filme> findById(Long id){
	return filmeRepository.findById(id)	;
	}
	
	
	public Page<Filme> findAll(Pageable peageble){
		return filmeRepository.findAll(peageble);
	}
	
	
	/**Método recebe uma String com o valor do titulo informado no cadastro
	 * 
	 * @param String 
	 * @return true se o filme já existe , ou false se ainda não foi cadastrado 
	 */
	public boolean findTitulo(String titulo) {
		String tituloFormatado = titulo.replaceAll("\\s+","");
		int quantidadeDeFilmesIguais = filmeRepository.findTitulo(tituloFormatado); 
		if(quantidadeDeFilmesIguais > 0) {	
		return true;
		}else						
		return false;
	}
	
	
}
