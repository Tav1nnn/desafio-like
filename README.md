# Desafio like
Este aplicativo é projetado para gerar orçamentos de produtos personalizados para cada cliente. O cliente simplesmente 
precisa fornecer seu nome, a data atual e uma lista de produtos desejados junto com suas respectivas quantidades. 
Em seguida, o sistema calcula o orçamento total desses produtos. O cliente tem a opção de confirmar o orçamento, 
que então é salvo em nosso banco de dados, ou pode optar por recalcular o orçamento se necessário.
## Como rodar a aplicação com Docker

### Pré-requisitos

Antes de começar, certifique-se de que você tem o Docker instalado em sua máquina.

### Configuração

1. Crie um arquivo `.env` na raiz do projeto (mesmas pasta que fica o pom.xml).

2. Defina as seguintes variáveis no arquivo `.env`:

- `DB_PASSWORD`
- `DB_TEST_PASSWORD`
- `DB_USER`
- `DB_TEST_USER`

Exemplo do arquivo [.env](.env.example)

Adicione os valores correspondentes a cada uma dessas variáveis.

### Execução

1. Em seguida, construa a imagem Docker com o seguinte comando:

   `docker-compose build`

2. Finalmente, inicie a aplicação com o seguinte comando:

   `docker-compose up`

Agora, sua aplicação deve estar rodando em um contêiner Docker.

## Como acessar o swagger da Aplição

### Pré-requisitos
Estar com a aplicação rodando em sua máquina.

### Execução
Depois de rodar a aplicação, basta acessar o link: http://localhost:3000/swagger-ui/index.html#/

## Rotas
- a API responde na porta 3000.

### Calcular Orçamento
`POST localhost:3000/api/v1/orcamento/calcular`

`Request`
```jsx
{
    "nomeCliente": "Nome Cliente",
    "data": "2024-12-31",
    "ListaProdutos": [
        {
            "nome": "Produto 1",
            "valor": 100.0,
            "quantidade": 2
        },
        {
            "nome": "Produto 2",
            "valor": 200.0,
            "quantidade": 1
        }
    ]
}
```
`Reponse`
```jsx
{
	"nomeCliente": "Nome Cliente",
	"data": "2024-12-30",
	"ListaProdutos": [
		{
			"nome": "Produto 1",
			"valor": 100.0,
			"quantidade": 2,
			"valorTotal": 200.0
		},
		{
			"nome": "Produto 2",
			"valor": 200.0,
			"quantidade": 1,
			"valorTotal": 200.0
		}
	],
	"totalOrcamento": 400.0
}
```
Nesta rota, o usuário insere seu nome, a data atual e uma lista detalhada dos produtos desejados, incluindo suas 
quantidades e preços individuais. Após o envio dessas informações, o sistema processa os dados e retorna um orçamento 
detalhado, mostrando o valor total estimado dos produtos solicitados.

---
### Confirmar Orçamento
`POST localhost:3000/api/v1/orcamento/confirmar`

`Request`
```jsx
{
    "nomeCliente": "Nome Cliente",
    "data": "2024-12-31",
    "ListaProdutos": [
        {
            "nome": "Produto 1",
            "valor": 100.0,
            "quantidade": 2
        },
        {
            "nome": "Produto 2",
            "valor": 200.0,
            "quantidade": 1
        }
    ]
}
```
`Reponse`
```jsx
201 created
```
Se o cliente concordar com o orçamento gerado, ele deve reenviar as mesmas informações, mas desta vez através da rota
`/confirmar`. Esta ação fará com que o sistema registre o orçamento no banco de dados. A resposta do sistema será 
apenas um código de status HTTP 201, indicando que o recurso foi criado com sucesso.

---
### Deletar Orçamento
`DELETE localhost:3000/api/v1/orcamento/deletar/1`

`Reponse`
```jsx
200 ok
```
Para remover um orçamento específico, simplesmente forneça o ID correspondente. O sistema irá então processar a exclusão do orçamento selecionado.

---

### Buscar um Orçamento
`GET localhost:3000/api/v1/orcamento/buscarPorId/1`

`Reponse`
```jsx
{
    "nomeCliente": "Nome Cliente",
    "data": "2024-12-31",
    "ListaProdutos": [
        {
            "nome": "Produto 1",
            "valor": 100.0,
            "quantidade": 2
        },
        {
            "nome": "Produto 2",
            "valor": 200.0,
            "quantidade": 1
        }
    ]
}
```
---
### Buscar todos os Orçamentos
`GET localhost:3000/api/v1/orcamento/buscar`

`Reponse`
```jsx
[
	{
	    "nomeCliente": "Nome Cliente",
	    "data": "2024-12-31",
	    "ListaProdutos": [
	        {
	            "nome": "Produto 1",
	            "valor": 100.0,
	            "quantidade": 2
	        },
	        {
	            "nome": "Produto 2",
	            "valor": 200.0,
	            "quantidade": 1
	        }
	    ]
	}
]
```
## Técnologias utilizadas
- Java
- Spring Boot
- JPA
- MySql
- Docker
- Swagger

## Funcionalidades desenvolvidas
- Rotas de calcular e confirmar orçamento
- Validação dos `Schema` com Beans Validation
- Documentação com Swagger
- Testes unitários
