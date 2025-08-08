# clientes-api

![Spring Boot](https://img.shields.io/badge/Spring_Boot-gray?style=for-the-badge&logo=spring-boot)
![Java 21](https://img.shields.io/badge/Java-21-orange.svg?style=for-the-badge&logo=openJDK)
![H2 Database](https://img.shields.io/badge/H2-Database-orange.svg?style=for-the-badge&logo=h2)
![Maven](https://img.shields.io/badge/Maven-3.x-orange.svg?style=for-the-badge&logo=apache-maven)
![Docker](https://img.shields.io/badge/Docker-gray?style=for-the-badge&logo=docker)

---

## Descrição do Projeto

Uma API REST completa para gerenciar o cadastro de clientes e seus endereços, desenvolvida com **Java 21** e **Spring Boot 3.x**. O projeto implementa um CRUD (Create, Read, Update, Delete) seguindo as boas práticas de separação de responsabilidades (Controller, Service, Repository, Domain) e é ideal para servir como base de portfólio. A aplicação é **containerizada com Docker**, facilitando a execução em qualquer ambiente.

---

## Tecnologias Utilizadas

-   ☕️ **Linguagem:** Java 21
-   🍃 **Framework:** Spring Boot 3.x
-   🗄️ **Banco de Dados:** H2 Database (em memória para desenvolvimento)
-   ⚙️ **Gerenciador de Dependências:** Maven
-   🐳 **Containerização:** Docker e Docker Compose
-   ✅ **Validação de Dados:** Bean Validation e regras de negócio personalizadas
-   🚀 **Testes de API:** Postman

---

## Como Rodar a Aplicação com Docker Compose

Para rodar a aplicação e o banco de dados H2 de forma containerizada, você precisa ter o **Docker** e o **Docker Compose** instalados.

1.  **Clone o repositório:**
    ```script
    git clone https://github.com/gfbueno/clientes-api.git
    
    cd clientes-api
    ```
2.  **Inicie os contêineres:**
    ```script
    docker-compose up --build
    ```
    *Isso irá construir a imagem da aplicação e iniciar os serviços da API e do banco de dados H2 na porta `8080` e `8082` respectivamente.*

---

## Endpoints da API

A API possui a seguinte base URL: `http://localhost:8080/api/clientes`

### **1. Criar um novo cliente**

-   🟢 **Método:** `POST`
-   🔗 **URL:** `/api/clientes`
-   📝 **Corpo da Requisição (JSON):**
    ```json
    {
      "nome": "Exemplo Nome",
      "cpf": "123.456.789-00",
      "email": "exemplo@email.com",
      "telefone": "11987654321",
      "endereco": {
        "logradouro": "Rua das Flores",
        "numero": "123",
        "cep": "01234-567",
        "cidade": "São Paulo",
        "estado": "SP"
      }
    }
    ```
-   ✅ **Resposta de Sucesso (201 Created):** Retorna o objeto do cliente criado com o ID.
-   ❌ **Respostas de Erro:**
    - `400 Bad Request`: Falha de validação de formato.
    - `409 Conflict`: CPF ou e-mail já cadastrados.

### **2. Listar todos os clientes**

-   🟢 **Método:** `GET`
-   🔗 **URL:** `/api/clientes`
-   ✅ **Resposta de Sucesso (200 OK):** Retorna uma lista de clientes com seus respectivos endereços.

### **3. Buscar cliente por ID**

-   🟢 **Método:** `GET`
-   🔗 **URL:** `/api/clientes/{id}`
-   ✅ **Resposta de Sucesso (200 OK):** Retorna o objeto do cliente.
-   ❌ **Resposta de Erro (404 Not Found):** Se o cliente não for encontrado.

### **4. Atualizar um cliente**

-   🟡 **Método:** `PUT`
-   🔗 **URL:** `/api/clientes/{id}`
-   📝 **Corpo da Requisição (JSON):** Os dados completos do cliente a ser atualizado.
-   ✅ **Resposta de Sucesso (200 OK):** Retorna o objeto do cliente atualizado.
-   ❌ **Respostas de Erro:**
    - `404 Not Found`: Se o cliente não for encontrado.
    - `409 Conflict`: CPF já cadastrado em outro cliente.

### **5. Deletar um cliente**

-   🔴 **Método:** `DELETE`
-   🔗 **URL:** `/api/clientes/{id}`
-   ✅ **Resposta de Sucesso (204 No Content):** Deleta o cliente com sucesso.
-   ❌ **Resposta de Erro (404 Not Found):** Se o cliente não for encontrado.