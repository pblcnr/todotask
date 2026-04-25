# TodoTask — API de Gerenciamento de Tarefas

API REST para gerenciamento de tarefas do dia a dia, desenvolvida com Java Spring Boot e PostgreSQL.

---

## Tecnologias

- Java 21
- Spring Boot 4.0.5
- PostgreSQL 16
- Docker e Docker Compose
- JUnit 5 e Mockito

---

## Pré-requisitos

- Java 21
- Maven
- Docker e Docker Compose

---

## Como executar

### 1. Clone o repositório

```bash
git clone https://github.com/pblcnr/todotask.git
cd todotask
```

### 2. Suba o banco de dados

```bash
docker compose up -d
```

### 3. Redefina a senha do usuário do banco

Após subir o container, execute o comando abaixo para garantir que a senha está configurada corretamente:

```bash
docker exec -it todotask_postgres psql -U todouser -d todotask -c "ALTER USER todouser WITH PASSWORD 'todopassword';"
```

### 4. Execute a aplicação

```bash
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

---

## Endpoints

| Método | Rota          | Descrição               |
| ------ | ------------- | ----------------------- |
| POST   | /tarefas      | Cria uma nova tarefa    |
| GET    | /tarefas      | Lista todas as tarefas  |
| GET    | /tarefas/{id} | Busca uma tarefa por ID |
| PUT    | /tarefas/{id} | Atualiza uma tarefa     |
| DELETE | /tarefas/{id} | Remove uma tarefa       |

### Exemplo de body para criar ou atualizar uma tarefa

```json
{
  "nome": "Estudar Spring Boot",
  "descricao": "Revisar conceitos de JPA e REST",
  "status": "PENDENTE",
  "observacao": "Focar nos testes"
}
```

Os valores aceitos para `status` são: `PENDENTE`, `EM_ANDAMENTO`, `CONCLUIDA`.

---

## Banco de dados

O script de criação da tabela está em `sql/schema.sql`. O Hibernate também cria a tabela automaticamente ao subir a aplicação com `ddl-auto=update`.

---

## Testes

Para executar os testes, certifique-se de que o container do banco está rodando e execute:

```bash
./mvnw test
```

O projeto conta com testes unitários para a camada de serviço e testes de integração para os endpoints da API.
