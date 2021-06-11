# Desafio Técnico Sicredi
API REST desenvolvida para gerenciar sessões de votação de pautas

# Funcionalidades 
* Cadastrar uma nova pauta
* Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um tempo
determinado na chamada de abertura ou 1 minuto por default)
* Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado  
identificado por um id único e pode votar apenas uma vez por pauta)
* Contabilizar os votos e dar o resultado da votação na pauta

# Tecnologias utilizadas
* Spring Boot
* JPA
* Hibernate
* Lombok
* JUnit/Mockito

# Rotas
![image](https://user-images.githubusercontent.com/62192319/121755910-cd47e400-caee-11eb-8d3c-8284ab41675c.png)

# Testes Unitários
Teste cobrindo 100% das classes, 68% dos métodos e 65% das linhas
