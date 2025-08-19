REDE OK API
API REST desenvolvida em Java 21 com o framework Quarkus, que gerencia Clientes e seus Endereços, garantindo boas práticas de REST, validação de dados e persistência em banco relacional.
✨ Funcionalidades
Clientes

Listar clientes com paginação e filtros (nome e/ou data de criação)
Consultar cliente por ID
Criar cliente
Atualizar cliente (parcial via PATCH ou completa via PUT)
Excluir cliente

Endereços

Listar endereços de um cliente
Adicionar endereço a um cliente
Remover endereço de um cliente

🗂 Estrutura dos dados
Cliente
{
  "id": 1,
  "nome": "Maria Silva",
  "telefone": "11999999999",
  "email": "maria@email.com",
  "documento": "12345678901",
  "tipoDocumento": "CPF",
  "dataCriacao": "2025-08-19T12:00:00Z"
}

Endereço
{
  "id": 1,
  "logradouro": "Rua das Flores",
  "numero": "123",
  "bairro": "Centro",
  "cidade": "São Paulo",
  "estado": "SP",
  "cep": "01000-000"
}

🔗 Endpoints principais
Clientes

GET /clientes?page=0&size=10&nome=Maria
GET /clientes/{id}
POST /clientes

{
  "nome": "João Souza",
  "telefone": "11988887777",
  "email": "joao@email.com",
  "documento": "98765432100",
  "tipoDocumento": "CPF"
}


PUT /clientes/{id}
PATCH /clientes/{id}
DELETE /clientes/{id}

Endereços

GET /clientes/{id}/enderecos
POST /clientes/{id}/enderecos

{
  "logradouro": "Av. Paulista",
  "numero": "1000",
  "bairro": "Bela Vista",
  "cidade": "São Paulo",
  "estado": "SP",
  "cep": "01310-000"
}


DELETE /clientes/{id}/enderecos/{idEndereco}

✅ Regras de negócio

Um cliente pode ter múltiplos endereços
Documento (CPF/CNPJ) e e-mail devem ser válidos e únicos
Não é possível cadastrar endereço sem cliente associado
Segue padrões REST e retorna os códigos HTTP adequados

🛠 Tecnologias

Java 21
Quarkus (RESTEasy Reactive, Jackson, CDI, Hibernate ORM)
PostgreSQL como banco de dados
Flyway para migração de schema
Gradle como build tool
JUnit 5 para testes automatizados
Docker + Docker Compose para orquestração

⚙️ Como rodar o projeto
Pré-requisitos

Java 21+
Docker e Docker Compose
Gradle

Passos para rodar

Clone o repositório:

git clone https://github.com/gustavoocosta/redeok.git
cd redeok


Suba os containers (banco e aplicação):

docker-compose up -d


Acesse a aplicação em:👉 http://localhost:8080

⚡ Variáveis de ambiente
No arquivo .env (ou exportadas no sistema):
DB_HOST=localhost
DB_PORT=5432
DB_NAME=redeok
DB_USER=postgres
DB_PASSWORD=postgres

🧪 Testes
Para rodar os testes automatizados:
./gradlew test

📦 Build
Para compilar a aplicação:
./gradlew clean build

Para rodar localmente sem Docker:
./gradlew quarkusDev


Autor
Gustavo Costa
