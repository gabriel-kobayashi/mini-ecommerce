# Mini E-commerce API

API REST desenvolvida com Java e Spring Boot para simular um mini e-commerce, aplicando conceitos de arquitetura em camadas, regras de negócio, persistência de dados e relacionamento entre entidades.

---

## 🚀 Tecnologias utilizadas

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Bean Validation
- PostgreSQL
- Lombok
- Maven

---

## 📌 Funcionalidades

### Categorias
- Criar categoria
- Listar categorias
- Buscar categoria por ID
- Atualizar categoria
- Remover categoria

### Produtos
- Criar produto
- Listar produtos
- Buscar produto por ID
- Atualizar produto
- Remover produto

### Pedidos
- Criar pedido
- Listar pedidos
- Buscar pedido por ID
- Remover pedido

### Regras de negócio
- Cálculo automático do valor total do pedido
- Cálculo automático do subtotal dos itens
- Validação de produto existente
- Validação de categoria existente
- Controle de estoque
- Redução automática do estoque ao criar pedido
- Impede compras sem estoque suficiente

---

# 📁 Estrutura do projeto

```bash
src/main/java/com/gabrieltk/mini_ecommerce
│
├── controller
├── dto
├── exception
├── mapper
├── model
├── repository
└── service
```

---

# 🧱 Modelagem

## Categoria

```java
private Long id;
private String nome;
```

## Produto

```java
private Long id;
private String nome;
private String descricao;
private BigDecimal preco;
private Integer estoque;
```

## Pedido

```java
private Long id;
private LocalDateTime dataPedido;
private BigDecimal valorTotal;
```

## ItemPedido

```java
private Long id;
private Integer quantidade;
private BigDecimal precoUnitario;
private BigDecimal subtotal;
```

---

# 🔗 Relacionamentos

- Uma categoria possui vários produtos
- Um produto pertence a uma categoria
- Um pedido possui vários itens
- Um item pertence a um pedido
- Um item referencia um produto

---

# 📡 Endpoints

## Categorias

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/categorias` | Criar categoria |
| GET | `/categorias` | Listar categorias |
| GET | `/categorias/{id}` | Buscar categoria por ID |
| PUT | `/categorias/{id}` | Atualizar categoria |
| DELETE | `/categorias/{id}` | Remover categoria |

---

## Produtos

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/produtos` | Criar produto |
| GET | `/produtos` | Listar produtos |
| GET | `/produtos/{id}` | Buscar produto por ID |
| PUT | `/produtos/{id}` | Atualizar produto |
| DELETE | `/produtos/{id}` | Remover produto |

---

## Pedidos

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/pedidos` | Criar pedido |
| GET | `/pedidos` | Listar pedidos |
| GET | `/pedidos/{id}` | Buscar pedido por ID |
| DELETE | `/pedidos/{id}` | Remover pedido |

---

# 🛒 Exemplo de criação de pedido

## Request

```json
{
  "itens": [
    {
      "productId": 1,
      "quantidade": 2
    },
    {
      "productId": 2,
      "quantidade": 1
    }
  ]
}
```

## Response

```json
{
  "id": 1,
  "valorTotal": 3500.00,
  "itens": [
    {
      "produto": "Notebook",
      "quantidade": 2,
      "precoUnitario": 1500.00,
      "subtotal": 3000.00
    },
    {
      "produto": "Mouse",
      "quantidade": 1,
      "precoUnitario": 500.00,
      "subtotal": 500.00
    }
  ]
}
```

---

# ⚠️ Tratamento de exceções

A aplicação possui exceções personalizadas para:

- Produto não encontrado
- Categoria não encontrada
- Pedido não encontrado
- Estoque insuficiente

---

# ▶️ Como executar o projeto

## Clone o repositório

```bash
git clone https://github.com/gabriel-kobayashi/mini-ecommerce.git
```

## Entre na pasta do projeto

```bash
cd mini-ecommerce
```

## Configure o PostgreSQL

No arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/mini_ecommerce
spring.datasource.username=postgres
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## Execute a aplicação

```bash
./mvnw spring-boot:run
```

ou

```bash
mvn spring-boot:run
```

---

# 📈 Melhorias futuras

- Spring Security + JWT
- Swagger/OpenAPI
- Docker
- Testes unitários
- Paginação
- Busca de produtos por filtros
- Upload de imagens
- Deploy da aplicação

---

# 🎯 Objetivo do projeto

Este projeto foi desenvolvido com foco em estudos de:

- APIs REST
- Spring Boot
- JPA/Hibernate
- Regras de negócio
- Arquitetura em camadas
- Relacionamentos entre entidades
- Tratamento de exceções
- Boas práticas no backend Java

---

# 👨‍💻 Autor

Desenvolvido por Gabriel Toshiyuki Kobayashi.
