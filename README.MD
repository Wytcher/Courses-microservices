# Micro serviços de cursos usando Spring Cloud Gateway e Eureka Server
Desenvolvi esses pequenos microsserviços para estudar o **Spring Cloud Gateway** como API Gateway e **Eureka Server** como Service Discover.

Para rodar o projeto é preciso subir dois containers docker, um do **Postgres** e outro do **RabbitMQ**

- Na raiz da aplicação possui um arquivo **docker-compose.yml**, execute o comando abaixo para subir os containers
  `docker-compose up`

Após subir os containers e inicar as aplicações o Flyway vai rodas as migrations para criar as tabelas e popular os bancos com alguns dados necessários

- Para fazer login como ADMIN use o email: admin@email.com e a senha: 12345678 e acesse todas as rotas da aplicação.

## Micro serviços
- ### Auth API
  Nesse micro serviço é feito o cadastro e pesquisa de estudantes e empregados rodando na porta 8081.

  É responsável por gerenciar a autenticação de toda a aplicação,  após verificar as informações de login o método de autenticação gera um JWT Token que será responsável por identificar o usuário no API Gateway e o nível de acesso as rotas das APIs.

- ### Course API

  Nesse micro serviço é feito o cadastro e pesquisa de categoria de cursos, cursos e matrícula em cursos rodando na porta 8082.

  Todas as rotas são protegidas por autenticação sendo algumas necessário determinador nível de permissão para serem acessadas.

- ### Eureka Server

  Server discover responsável pelo Registry de cada aplicação rodando na porta 8761.

- ### Email service

  Essa aplicação escuta filas do RabbitMQ e faz o disparo de emails rodando na porta 8083.

  Para que essa aplicação funcione é necessário inserir as informações do SMTP do gmail, ou pode alterar o projeto para funcionar com seu próprio SMTP no **application.yml**

- ### API Gateway

  Responsável por interceptar todas as requisições feitas e direcionar para os microsserviços requisitados rodando na porta 8080.

  Todas as rotas exceto a de login são protegidas por um filtro responsável por verificar o JWT Token enviado.

## Rotas
** As documentações Swagger estão disponíveis nas urls:

    http://localhost:8081/swagger-ui.html#/
    http://localhost:8082/swagger-ui.html#/

- Auth API
    - Rota de login
        - ` POST: http://localhost:8080/api/v1/auth/login`

    - Rotas de estudante
        - `GET: http://localhost:8080/api/v1/auth/students`
            - Gera uma paginação de todos os estudantes cadastrados

        - `POST: http://localhost:8080/api/v1/auth/students`
            - Rota de cadastro de estudantes acessada apenas pelas roles ADMIN e EMPLOYEE

        - `GET: http://localhost:8080/api/v1/auth/students/{id}`
            - Rota de pesquisa de estudante acessada apenas pelas roles ADMIN e EMPLOYEE

    - Rotas de empregado
        - `GET: http://localhost:8080/api/v1/auth/employees`
            - Gera uma paginação de todos os empregados cadastrados

        - `POST: http://localhost:8080/api/v1/auth/employees`
            - Rota de cadastro de empregados acessada apenas pela role ADMIN

        - `GET: http://localhost:8080/api/v1/auth/employees/{id}`
            - Rota de pesquisa de empregados acessada apenas pela role ADMIN

- Courses API

    - Rotas de categoria
        - `GET: http://localhost:8080/api/v1/courses/categories`
            - Gera uma paginação de todas as categorias cadastradas

        - ` POST: http://localhost:8080/api/v1/courses/categories`
            - Rota de cadastro de categorias acessada apenas pelas roles ADMIN e EMPLOYEE

    - Rotas de curso
        - `GET: http://localhost:8080/api/v1/courses`
            - Gera uma paginação de todos os cursos cadastrados

        - `POST: http://localhost:8080/api/v1/courses`
            - Rota de cadastro de cursos acessada apenas pelas roles ADMIN e EMPLOYEE

    - Rotas de matricula no curso
        - `GET: http://localhost:8080/api/v1/courses/enrollments`
            - Rota de cadastro de matricula em um curso acessada apenas pelas roles ADMIN e EMPLOYEE

        - `GET: http://localhost:8080/api/v1/courses/enrollments/{id}`
            - Rota de pesquisa de matriculas em um curso acessada apenas pela roles ADMIN e EMPLOYEE