package com.stationone.filme.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import com.stationone.filme.model.Filme;

@Repository
public interface FilmeRepository extends JpaRepository<Filme,Long> {

	

   //@Query(value = "SELECT COUNT(titulo) from  filme  where replace(UPPER(titulo),' ','')=UPPER(:tituloAplicacao)", nativeQuery = true)
	@Query(value = "SELECT   COUNT(titulo) from  filme  where fn_retornaSemEspacoEMaiuscula(titulo) =UPPER(:tituloAplicacao)", nativeQuery = true)
	public int findTitulo(String tituloAplicacao);
	
}
