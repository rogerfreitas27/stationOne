# StationOne-gerenciador-filmes

# Objetivo do projeto 

Criar uma API REST para gerenciar filmes.


Tecnologias  usadas:
- Java
- Spring Boot 
- Maven
- Mysql;
- Swagger


## Banco de dados:

- Criando  o banco de dados

```mysql
create database stationone;

USE stationone;
```

- Criando a tabela
```mysql
CREATE TABLE filme (
  id bigint NOT NULL AUTO_INCREMENT ,
  autor varchar(100) NOT NULL,
  descricao varchar(255) NOT NULL,
  titulo varchar(255) NOT NULL,
  UNIQUE(titulo),
  PRIMARY KEY (id)
);


```



- Criando função para  verificar se um determinado titulo existe antes da gravação no banco de dados
```mysql
DELIMITER $$
CREATE FUNCTION fn_retornaSemEspacoEMaiuscula(frase varchar (255))
RETURNS VARCHAR(255)
READS SQL DATA		
NOT DETERMINISTIC
BEGIN
DECLARE
texto VARCHAR(255);
SET texto = REPLACE(frase," ","");
RETURN  UPPER(texto);
END $$
DELIMITER ;

```



- Insert no banco de dados
```mysql
INSERT INTO filme VALUES
(1,'RAWSON MARSHALL THURBER','Um alerta  vermelho é emitido  e o agente  do FBI  John Hartley assume o caso Durante sua busca,ele se vê diante de um assalto ousado e é forçado  a se aliar ao maior ladrão da historia ','Alerta Vermelho'),
(2,'Burke Sharpless','O bioquímico Michel Morbius tenta curar-se de uma doença rara no sangue mas, sem perceber, ele fica infectado com uma forma de vampirismo','Morbius'),
(3,'Dario Scardapane','Alex, um assassino profissional, se recusa a completar um trabalho para uma organização criminosa e acaba se tornando um alvo. Agentes do FBI e da inteligência mexicana são trazidos para investigar seu rastro de corpos, levando-os para mais perto de Alex','Assassino Sem Rastro'),
(4,'Stan Lee','Além de receber ajuda de novos aliados místicos e outros já conhecidos do público, o personagem atravessa as realidades alternativas incompreensíveis e perigosas do Multiverso para enfrentar um novo e misterioso adversário','Doutor Estranho no Multiverso da Loucura'),
(5,'Amy Hennig','Nathan Drake e seu parceiro canastrão Victor \'Sully\' Sullivan embarcam em uma perigosa busca para encontrar o maior tesouro jamais encontrado. Enquanto isso, eles também rastreiam pistas que podem levar ao irmão perdido de Nathan','Uncharted: Fora do Mapa'),
(6,'Johannes Roberts','Racoon City já foi uma próspera cidade do Meio-Oeste, graças à gigante farmacêutica The Umbrella Corporation. Mas abaixo da superfície algo maligno se esconde. Após o êxodo da empresa, o mal é desencadeado nos habitantes desavisados da cidade.','Resident Evil: Bem-Vindo a Raccoon City'),
(7,'Nicolas Boukhrief','Harry, conhecido apenas como H, é um homem misterioso que trabalha para uma empresa de carros-fortes e movimenta grandes quantias de dinheiro pela cidade de Los Angeles','Infiltrado'),
(8,'Alfredo Botelho','Dominic Toretto e Letty vivem uma vida pacata ao lado do filho. Mas eles logo são ameaçados pelo passado de Dom: seu irmão desaparecido Jakob, que retorna e está trabalhando ao lado de Cipher. Cabe a Dom reunir a equipe novamente para enfrentá-los.','Velozes e Furiosos 9'),
(9,'Laurits Munch-Petersen','Laurits Munch-Petersen é um diretor de cinema dinamarquês que se formou na Escola Nacional de Cinema da Dinamarca com seu filme vencedor do Oscar \'Between Us\' em 2004.','Ambulância - Um Dia de Crime'),
(10,'Derek Kolstad','Mansell é um pacato pai e marido que sempre arca com as injustiças da vida, sem revidar. Quando dois ladrões invadem sua casa, Hutch se recusa a defender a si mesmo e sua família na esperança de evitar qualquer violência','Anônimo'),
(11,'Kaz Firpo','Os Eternos são uma raça de seres imortais que viveram durante a antiguidade da Terra, moldando sua história e suas civilizações enquanto batalhavam os malignos Deviantes.','Eternos'),
(12,'Seth Gordon','A brilhante e reclusa autora Loretta Sage escreve romances populares de aventura, cujas capas são estreladas pelo belo modelo Alan. Durante a turnê de promoção de seu novo livro, Loretta é raptada.','Cidade Perdida'),
(13,'Destin Cretton','Shang-Chi é o filho do líder de uma organização criminosa poderosa. O rapaz foi criado desde criança para ser um guerreiro, mas decidiu abandonar esse caminho e fugiu para viver uma vida pacífica.','Shang-Chi e a Lenda dos Dez Anéis'),
(14,'Ian Fleming','James Bond deixa o MI6 e se muda para a Jamaica, mas um antigo amigo aparece e pede sua ajuda para encontrar um cientista desaparecido. Bond mergulha no caso e percebe que a busca é, na verdade, uma corrida para salvar o mundo.','007: Sem Tempo para Morrer');



```
  


- Criação de usuario para acessar o banco de dados
```mysql
CREATE USER 'stationOne'@'localhost' IDENTIFIED BY 'stationOne';

```




- Dar permissao ao usuario criado para usar banco de dados 
```mysql
CREATE USER 'stationOne'@'localhost' IDENTIFIED BY 'stationOne';

```









## Cadastro de filmes:
- Dados: id, titulo, descricao e autor;

Verbo Http:
Post

Url  http://localhost:8080/api/filme

Parametros esperados  pela Api:

```json
{

"id":"",
"titulo":"Campo com no minimo 3 e no maximo 255 caracteres",
"descricao":"Campo com no minimo 3 e no maximo 255 caracteres",
"autor":"Campo com no minimo 3 e no maximo 100 caracteres "

}
 ```



## Buscar Filme por Id

Verbo Http:
Get

Url  http://localhost:8080/api/filme/1

Dados retornados pela Api
```json
{

"id":"1",
"titulo":"Alerta Vermelho",
"descricao":"Um alerta  vermelho é emitido  e o agente  do FBI  John Hartley assume o caso Durante sua busca,ele se vê diante de um assalto ousado e é forçado  a se aliar ao maior ladrão da historia ",
"autor":"RAWSON MARSHALL THURBER"

}
 ```


## Listagem de filmes

A api retorna a lista de filmes paginada

Verbo Http:
Get

Url  http://localhost:8080/api/filme?page=0&sort=id,asc 



Dados retornados pela Api
```json
{"content":[{"id":1,"titulo":"Alerta Vermelho","descricao":"Um alerta  vermelho é emitido  e o agente  do FBI  John Hartley assume o caso Durante sua busca,ele se vê diante de um assalto ousado e é forçado  a se aliar ao maior ladrão da historia ","autor":"RAWSON MARSHALL THURBER"},{"id":2,"titulo":"Morbius","descricao":"O bioquímico Michel Morbius tenta curar-se de uma doença rara no sangue mas, sem perceber, ele fica infectado com uma forma de vampirismo","autor":"Burke Sharpless"},{"id":3,"titulo":"Assassino Sem Rastro","descricao":"Alex, um assassino profissional, se recusa a completar um trabalho para uma organização criminosa e acaba se tornando um alvo. Agentes do FBI e da inteligência mexicana são trazidos para investigar seu rastro de corpos, levando-os para mais perto de Alex","autor":"Dario Scardapane"},{"id":4,"titulo":"Doutor Estranho no Multiverso da Loucura","descricao":"Além de receber ajuda de novos aliados místicos e outros já conhecidos do público, o personagem atravessa as realidades alternativas incompreensíveis e perigosas do Multiverso para enfrentar um novo e misterioso adversário","autor":"Stan Lee"},{"id":5,"titulo":"Uncharted: Fora do Mapa","descricao":"Nathan Drake e seu parceiro canastrão Victor 'Sully' Sullivan embarcam em uma perigosa busca para encontrar o maior tesouro jamais encontrado. Enquanto isso, eles também rastreiam pistas que podem levar ao irmão perdido de Nathan","autor":"Amy Hennig"},{"id":6,"titulo":"Resident Evil: Bem-Vindo a Raccoon City","descricao":"Racoon City já foi uma próspera cidade do Meio-Oeste, graças à gigante farmacêutica The Umbrella Corporation. Mas abaixo da superfície algo maligno se esconde. Após o êxodo da empresa, o mal é desencadeado nos habitantes desavisados da cidade.","autor":"Johannes Roberts"},{"id":7,"titulo":"Infiltrado","descricao":"Harry, conhecido apenas como H, é um homem misterioso que trabalha para uma empresa de carros-fortes e movimenta grandes quantias de dinheiro pela cidade de Los Angeles","autor":"Nicolas Boukhrief"},{"id":8,"titulo":"Velozes e Furiosos 9","descricao":"Dominic Toretto e Letty vivem uma vida pacata ao lado do filho. Mas eles logo são ameaçados pelo passado de Dom: seu irmão desaparecido Jakob, que retorna e está trabalhando ao lado de Cipher. Cabe a Dom reunir a equipe novamente para enfrentá-los.","autor":"Alfredo Botelho"},{"id":9,"titulo":"Ambulância - Um Dia de Crime","descricao":"Laurits Munch-Petersen é um diretor de cinema dinamarquês que se formou na Escola Nacional de Cinema da Dinamarca com seu filme vencedor do Oscar 'Between Us' em 2004.","autor":"Laurits Munch-Petersen"},{"id":10,"titulo":"Anônimo","descricao":"Mansell é um pacato pai e marido que sempre arca com as injustiças da vida, sem revidar. Quando dois ladrões invadem sua casa, Hutch se recusa a defender a si mesmo e sua família na esperança de evitar qualquer violência","autor":"Derek Kolstad"}],"pageable":{"sort":{"empty":false,"sorted":true,"unsorted":false},"offset":0,"pageSize":10,"pageNumber":0,"paged":true,"unpaged":false},"totalPages":2,"last":false,"totalElements":14,"size":10,"number":0,"sort":{"empty":false,"sorted":true,"unsorted":false},"first":true,"numberOfElements":10,"empty":false}
 ```


## Documentação da Api:
Url  http://localhost:8080/swagger-ui/index.html#/
