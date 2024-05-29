# todo

## Descrição

Uma aplicação Spring Boot que implementa uma API Todo simples para gerenciar tarefas. Os usuários podem criar, recuperar, atualizar e excluir tarefas.

## Pré-requisitos

Java 17+
Maven

## Instalação

1. Clonar o repositório:

     ```bash
        git clone https://github.com/davidsonaguiar/todo-api-spring-boot.git
     ```

2. Navegar para o diretório do projeto:

    ```bash
      cd todo
   ```
3. Instalar dependências:

    ```bash
      mvn install
    ```             

## Executando a Aplicação

1. Iniciar a aplicação:

    ```bash
        mvn spring-boot:run
   ```

    Isso iniciará a API na porta padrão do Spring Boot (geralmente 8080).

## Endpoints da API

(Todos os endpoints assumem uma URL base de `http://localhost:8080/tasks` a menos que especificado de outra forma.)

### GET /tasks

Recupera uma lista de todas as tarefas, ordenadas por status de conclusão (decrescente) e depois por título (decrescente).

### GET /tasks/{id}

Recupera uma tarefa específica por seu ID.

### POST /tasks

Cria uma nova tarefa com o título fornecido e a descrição opcional no corpo da solicitação. O corpo da solicitação deve ser formatado como JSON:

```json
    {
        "title": "Novo Título da Tarefa",
        "description": "Descrição opcional da tarefa"
    }
```
* Retorna um erro de conflito (status HTTP 409) se uma tarefa com o mesmo título já existir.

### PUT /tasks/{id}

Atualiza uma tarefa existente com as informações fornecidas no corpo da solicitação. O corpo da solicitação deve ser formatado como JSON:

```json
    {
        "title": "Título da Tarefa Atualizada",
        "description": "Descrição atualizada opcional"
    }
```

* Retorna um erro não encontrado (status HTTP 404) se a tarefa a ser atualizada não puder ser encontrada.

### PUT /tasks/{id}/complete

Alterna o status de conclusão de uma tarefa específica. Nenhum corpo de solicitação é necessário.

* Retorna um erro não encontrado (status HTTP 404) se a tarefa não puder ser encontrada.

### DELETE /tasks/{id}

Exclui uma tarefa por seu ID.

* Retorna um erro não encontrado (status HTTP 404) se a tarefa a ser excluída não puder ser encontrada.

## Tecnologias Utilizadas

* Spring Boot
* Spring Data JPA
* Banco de dados H2 (em memória para desenvolvimento)
* Lombok (opcional)
* Spring HATEOAS (opcional)
* Spring Doc OpenApi (opcional)

## Contribuindo

Pull requests e sugestões são bem-vindas! Siga as práticas padrão do Git para bifurcação, ramificação e envio de pull requests.
