# Lista-de-Eventos

Avaliação Java, desenvolver um API REST de lista de Eventos.

## Começando

Essas instruções fornecerão uma cópia do projeto para execução na sua máquina local para fins de avaliação. Consulte implantação para obter notas sobre como implantar o projeto em sua máquina.


## Pré-requisitos

```
- É necessário ter o Java 11 ou superior instalado.
- Essa aplicação está configurada para executar na porta 8080.
```

## Execução

- Você pode importar o projeto em sua IDE favorita.
- Importar como projeto Mavem e instalar as dependencias.
- Rodar a aplicação pelo arquivo EventosApplication.java contido no pacote br.com.eventos.eventos.
- Para ter acesso aos endpoint, é necessário gerar a chave de autorização passando um usuário e senha cadastrados no banco de dados.

## Sobre o projeto

> ### Segurança
Esse sistema foi desenvolvido com spring-security, porém por ser uma aplicação stateless, foi usado a biblioteca jjwt para gerar chave de autenticação.
Toda vez que uma requisição for feita, a chave precisa ser enviada no cabeçalho.
A chave tem duração de 24h.
O tipo de autenticação usada é "Bearer Token".

- Usuário para teste:
```
Usuário: andre.tondin@email.com
Senha: eventos@2020
```

> ### Banco de dados.
O Banco de dados utilizado é o h2, que roda em memório junto com a aplicação, mas nada impede de usarmos um banco de dados diferente.
Para alterar o banco de dados, basta configurar o arquivo "aplication.properties" que fica dentro de resources.

- Para acessar as tabelas, basta acessar o link abaixo:
```
http://<<host>>/h2-console/
```

> ### OpenAPI
A aplicação usa o Swagger a fim de documentar os endpoints e sua utilização.

- Para acessar o Swagger, basta acessar o link abaixo:
```
http://<<host>>/swagger-ui.html
```

## Testando o projeto

Além do swagger, é possível testar os endpoints utlizando o Postman.
Basta importar o arquivo EventsSantander.postman_collection.json que está disponível junto ao projeto.
O Arquivo já contém requisições cadastradas para testes.

```
- Antes de começar, é necessário gerar um token, na opção "Authentication get token", e adicionar o token gerado na variável "token" em "MANAGE ENVIRONMENTS".
- Assim, o Postman automaticamnete enviara o token no cabeçalho da requisição.
```



