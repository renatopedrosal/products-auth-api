# 📦 product-auth-api

API de autenticação com **Spring Security + JWT**, com controle de usuários e produtos.  
Permite cadastro/login de usuários, autenticação via token JWT, proteção de rotas e operações básicas de produto.

---

## 🚀 Tecnologias Utilizadas
- **Java 21**
- **Spring Boot**
- **Spring Security**
- **JWT (JSON Web Token)**
- **H2 Database (em memória)**
- **Flyway (migrações de banco)**
- **Maven**
- **Swagger (OpenAPI 3)**

---

## ⚙️ Pré-requisitos
Antes de rodar o projeto, você precisa ter instalado:
- Java 21+
- Maven

---

## ▶️ Como rodar o projeto

### 1. Clonar o repositório
```bash
git clone https://github.com/seu-usuario/product-auth-api.git
cd product-auth-api
```

### 2. Rodar a aplicação
```bash
mvn spring-boot:run
```

A API estará disponível em:  
👉 `http://localhost:8080`

---

## 📖 Documentação da API (Swagger)

Este projeto utiliza Swagger UI para documentação e testes interativos.

- Acesse a interface em: 👉 `http://localhost:8080/swagger-ui.html`
- Para chamadas autenticadas, clique em Authorize e insira o token JWT no formato: `Bearer seu_token_aqui`

## 🔑 Configurações
O projeto usa variáveis de ambiente que podem ser configuradas no `application.properties`:

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password

spring.jpa.hibernate.ddl-auto=update

spring.flyway.enabled=true

api.security.token.secret=meuSegredoJWT

springdoc.api-docs.path=/v3/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```

### Variáveis principais:
- `DB_URL` → URL do banco (padrão: H2 em memória)  
- `JWT_SECRET` → Segredo usado para assinar os tokens (ex.: `api.security.token.secret`)  
- `SPRING_PROFILES_ACTIVE` → Perfil ativo (`dev`, `prod`, etc.)

---

## ✨ Funcionalidades
- Cadastro e login de usuário  
- Autenticação via JWT  
- Controle de roles (`ADMIN`, `USER`)  
- Proteção de rotas com Spring Security  
- Cadastro de produto (apenas ADMIN)  
- Listagem de produtos (qualquer usuário autenticado)
- Documentação interativa com Swagger

---

## 📂 Estrutura do Projeto
```
com.example.auth_api
├── controllers
│   ├── AuthenticationController.java
│   └── ProductController.java
│
├── domain
│   ├── product
│   └── user
│       ├── AuthenticationDTO.java
│       ├── LoginResponseDTO.java
│       ├── RegisterDTO.java
│       ├── User.java
│       └── UserRole.java
│
├── infra
│   ├── config
│       └── SwaggerConfig.java
│   └── user
│       ├── SecurityConfigurations.java
│       ├── SecurityFilter.java
│       └── TokenService.java
│
├── repositories
│   ├── ProductRepository.java
│   └── UserRepository.java
│
├── services
│   └── AuthorizationService.java
│
├── resources
│   ├── db.migration (scripts do Flyway)
│   └── application.properties
```

---

## 🗄️ Dados Iniciais
Ao iniciar, o banco H2 já terá alguns registros (inseridos via **Flyway**):

### Produtos
| ID | Name              | Price |
|----|-------------------|-------|
| p1 | Notebook Dell     | 4500  |
| p2 | Monitor LG 24"    | 950   |
| p3 | Teclado Mecânico  | 350   |
| p4 | Mouse Gamer       | 250   |

---

## 🔥 Endpoints Principais

### Autenticação
#### Login
```http
POST /auth/login
Content-Type: application/json

{
  "login": "admin",
  "password": "admin123"
}
```
✅ Retorna:
```json
{
  "token": "seu.jwt.token"
}
```

#### Cadastro
```http
POST /auth/register
Content-Type: application/json

{
  "login": "novoUser",
  "password": "123456",
  "role": "USER"
}
```

---

### Produtos
#### Listar Produtos (qualquer usuário autenticado)
```http
GET /product
Authorization: Bearer {token}
```

#### Cadastrar Produto (apenas ADMIN)
```http
POST /product
Authorization: Bearer {token}
Content-Type: application/json

{
  "name": "Cadeira Gamer",
  "price": 1200
}
```

---

## 📜 Licença
Este projeto é distribuído sob a licença **MIT**.  
Sinta-se livre para usar, estudar e contribuir! 💻

---
