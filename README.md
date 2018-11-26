Backend de E-Commerce em Spring Boot

## Endpoints

### Autenticação
**Endpoint:** /authentication

| Verbo | Ação                                               | 
|-------|----------------------------------------------------|
| POST  | Autentica no sistema com as credenciais informadas |

Objeto de Entrada
```
{
   "email": STRING
   "password: STRING
   "name": STRING (Saída)
}
```

Objeto de saída
```
{
  "token" : STRING (Token JWT)
}
```

### Produtos
**Endpoint:** /product

| Verbo  | Ação                                | 
|--------|-------------------------------------|
| GET    | Lista todos os produtos             |
| POST   | Cadastra um produto no sistema      |

**Endpoint:** /product/:id

| Verbo  | Ação                                | 
|--------|-------------------------------------|
| GET    | Visualiza o produto pelo id         |
| PUT    | Atualiza dados do produto pelo id   |
| DELETE | Deleta o produto pelo if            |

Objeto de Entrada e Saída
```
{
   "id": LONG (Saída)
   "name": STRING
   "value": DOUBLE
   "photo": STRING (Base64 Image)
   "description": STRING
   "shortDescription": STRING
   "category": STRING
}
```

### Compras
**Endpoint:** /order

| Verbo | Ação                   | 
|-------|------------------------|
| GET   | Lista todos os pedidos |
| POST  | Efetua um pedido       |
   
```
{
   "id": LONG: Saída
   "products": (List<OrderItem>)[
	{
           "id": LONG (Saída)
           "product": PRODUCT (Para entrada apenas o id do produto basta),
           "amount": LONG
        }
    ],
   "account": (Account: Saída)
}
```

## DevOps

Ambos projetos contém scripts Docker e Jenkins, o build será enviado 
para uma instancia ECS na AWS automaticamente quando um build for agendado,
no entanto é necessário:

* Definir qual repositorio o Jenkins deve monitorar
* Instalar o [plugin AWS-ECS para o Jenkins](https://plugins.jenkins.io/amazon-ecr) 
* Atualizar as variáveis de ambiente.
