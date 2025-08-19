# Projeto Técnico REDE OK

Este projeto é uma API REST desenvolvida em **Java 21** com **Quarkus**, atendendo aos requisitos técnicos da vaga.  
A aplicação expõe recursos de **Clientes** e seus **Endereços**, com persistência em banco de dados relacional.

---

## 🧩 Funcionalidades da API

### 📌 Clientes
- **1.1 Consultar todos os clientes**  
  - Suporte a paginação.  
  - Filtro por **nome** e/ou **data de criação**.  

- **1.2 Consultar um cliente por ID**

- **1.3 Criar um novo cliente**

- **1.4 Alterar parcialmente um cliente**  
  - Atualização via `PATCH` ou `PUT`.  

### 📌 Endereços
- **2.1 Consultar o(s) endereço(s) de um cliente**

- **2.2 Criar um endereço para um cliente específico**

- **2.3 Deletar um endereço de um cliente**

---

## 🗂️ Domínio de Dados

### Cliente
- `nome`
- `telefone`
- `e-mail`
- `documento`
- `tipoDocumento` (CPF ou CNPJ)
- `dataCriacao`

### Endereço
- O domínio foi definido de forma flexível, incluindo atributos como:  
  `logradouro`, `numero`, `bairro`, `cidade`, `estado`, `cep`.

---

## ✅ Regras de Negócio e Validações
- API segue os padrões REST (verbos HTTP e códigos de status).  
- Validação de campos obrigatórios: **CPF, e-mail e documento**.  
- Clientes e endereços sempre vinculados corretamente.  

---

## 🛠️ Tecnologias Utilizadas
- [Java 21](https://openjdk.org/projects/jdk/21/) — Versão utilizada.
- [Quarkus](https://quarkus.io/) — Framework principal.
- [JDBI 3](https://jdbi.org/) — Acesso ao banco de dados.
- [JUnit 5](https://junit.org/junit5/) — Testes unitários.
- [Gradle](https://gradle.org/) — Build e gerenciamento de dependências.
- [Jackson](https://github.com/FasterXML/jackson) — Serialização JSON.
- [PostgreSQL](https://www.postgresql.org/) — Banco de dados relacional.
- [Flyway](https://www.red-gate.com/products/flyway/community/) — Controle de migrações.

---

## 🚀 Como Rodar o Projeto

### Pré-requisitos
- **Java 21**
- **Docker e Docker Compose**
- **Gradle**

### Passos
1. Clone o repositório:
   ```bash
   git clone https://github.com/gustavoocosta/redeok.git
   cd redeok
