Backend de E-Commerce em Spring Boot

## Endpoints

### Contas
Endpoint: /account

Verbo  Ação
POST   Cria uma conta no sistema

### Autenticação
Endpoint: /authentication

Verbo  Ação
POST   Autentica no sistema com as credenciais informadas

### Produtos
Endpoint: /product

Verbo  Ação
GET    Lista todos os produtos
POST   Cadastra um produto no sistema

Endpoint: /product/:id
Verbo  Ação
GET    Visualiza o produto pelo id
PUT    Atualiza dados do produto pelo id
DELETE Deleta o produto pelo if

### Compras
Endpoint: /order

Verbo  Ação
GET    Lista todos os pedidos
POST   Efetua uma compra 

