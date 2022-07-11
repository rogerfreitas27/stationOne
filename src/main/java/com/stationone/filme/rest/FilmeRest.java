package com.stationone.filme.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stationone.filme.model.Filme;
import com.stationone.filme.service.FilmePersistence;
import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/filme")
public class FilmeRest {

	
	private final FilmePersistence filmePersistence;
	
	
	
	 
	public FilmeRest(FilmePersistence filmePersistence) {
		this.filmePersistence=filmePersistence;
	}
	
	
	 @PostMapping
	  @Operation(summary = "Endpoint para criação de filmes")	 
	 @ApiResponses(value = { 		  
			  @ApiResponse(responseCode = "201", description = "Filme cadastrado com sucesso "), 
			  @ApiResponse(responseCode = "400", description = "Falha ao cadastrar")			
			  })
	  public ResponseEntity<?> save(@Valid @RequestBody Filme filme) {
			 
		 boolean resposta =  filmePersistence.findTitulo(filme.getTitulo());
		 
		 if(resposta==true) {
			  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Já existe um titulo com este nome");
		 }else
		 
		 filmePersistence.save(filme);
		  return ResponseEntity.status(HttpStatus.CREATED).body("Filme cadastrado com sucesso !");
		  
	  }
	 
	 
	
	 @GetMapping
	  @Operation(summary = "Endpoint para buscar todos os filmes, a api devolve a lista de filmes"
	  		+ "paginada")
	 @ApiResponses(value = { 		  
			  @ApiResponse(responseCode = "200", description = "Buscar todos os filmes "), 
			  @ApiResponse(responseCode = "404", description = "Não há registro de filmes")			  
			  })
	 
	 public ResponseEntity<?> findAll(@PageableDefault(size=10) Pageable peageable){
		  PageRequest pageRequest = null;
		  pageRequest=PageRequest.of(peageable.getPageNumber(),peageable.getPageSize(), peageable.getSort());
		
		  Page<Filme> filmes = filmePersistence.findAll(pageRequest);
		 
		  if( filmes.getTotalPages() < 1) {
			  return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ainda não há registros de filmes");
		  }
		  return  ResponseEntity.ok(filmePersistence.findAll(pageRequest));
	  }    
	 
	 
	 @GetMapping("/{id}")
	  @Operation(summary = "Endpoint para buscar filme por id")
	  @ApiResponses(value = { 		  
			  @ApiResponse(responseCode = "200", description = "Filme encontrado"), 
			  @ApiResponse(responseCode = "404", description = "Filme não encontrado") })
	  public ResponseEntity<?>findById(@PathVariable("id") Long id){
		  return filmePersistence.findById(id).map(record -> ResponseEntity.ok().body(record))
					.orElse(ResponseEntity.notFound().build());
		  
	  }
	  
	 
}
