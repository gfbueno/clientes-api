# clientes-api

![Spring Boot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)
![Java 21](https://img.shields.io/badge/Java-21-blue.svg?style=for-the-badge&logo=java)
![H2 Database](https://img.shields.io/badge/H2-Database-orange.svg?style=for-the-badge&logo=h2)
![Maven](https://img.shields.io/badge/Maven-3.x-red.svg?style=for-the-badge&logo=apache-maven)

---

## Descrição do Projeto

Uma API REST completa para gerenciar o cadastro de clientes, desenvolvida com **Java 21** e **Spring Boot 3.x**. O projeto implementa um CRUD (Create, Read, Update, Delete) seguindo as boas práticas de separação de responsabilidades (Controller, Service, Repository, Domain) e é ideal para servir como base de portfólio.

---

## Tecnologias Utilizadas

-   **Linguagem:** Java 21
-   **Framework:** Spring Boot 3.x
-   **Banco de Dados:** H2 Database (em memória para desenvolvimento)
-   **Gerenciador de Dependências:** Maven
-   **Testes de API:** Postman

---

## Como Rodar a Aplicação

1.  **Clone o repositório:**
2.  **Abra o projeto na sua IDE** (IntelliJ, por exemplo).
3.  **Execute a classe principal** `ClientesApplication.java`. O servidor será iniciado na porta `8080`.

---

## Endpoints da API

A API possui a seguinte base URL: `http://localhost:8080/api/clientes`

### **1. Criar um novo cliente**

-   **Método:** `POST`
-   **URL:** `/api/clientes`
-   **Corpo da Requisição (JSON):**
    ```json
    {
      "nome": "Exemplo Nome",
      "cpf": "123.456.789-00",
      "email": "exemplo@email.com",
      "telefone": "11987654321"
    }
    ```
-   **Resposta de Sucesso (201 Created):** Retorna o objeto do cliente criado com o ID.

### **2. Listar todos os clientes**

-   **Método:** `GET`
-   **URL:** `/api/clientes`
-   **Resposta de Sucesso (200 OK):** Retorna uma lista de clientes.

### **3. Buscar cliente por ID**

-   **Método:** `GET`
-   **URL:** `/api/clientes/{id}`
-   **Resposta de Sucesso (200 OK):** Retorna o objeto do cliente.
-   **Resposta de Erro (404 Not Found):** Se o cliente não for encontrado.

### **4. Atualizar um cliente**

-   **Método:** `PUT`
-   **URL:** `/api/clientes/{id}`
-   **Corpo da Requisição (JSON):** Os dados completos do cliente a ser atualizado.
-   **Resposta de Sucesso (200 OK):** Retorna o objeto do cliente atualizado.
-   **Resposta de Erro (404 Not Found):** Se o cliente não for encontrado.

### **5. Deletar um cliente**

-   **Método:** `DELETE`
-   **URL:** `/api/clientes/{id}`
-   **Resposta de Sucesso (204 No Content):** Deleta o cliente com sucesso.
-   **Resposta de Erro (404 Not Found):** Se o cliente não for encontrado.