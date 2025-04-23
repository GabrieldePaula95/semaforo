# 🚦 Semáforo Inteligente

Sistema backend para o gerenciamento inteligente de semáforos em cruzamentos urbanos. O projeto visa melhorar a fluidez do trânsito com base em horários programados, estados do semáforo e localização dos cruzamentos.

---

## 📌 Objetivo

O objetivo deste sistema é simular o comportamento de semáforos inteligentes, permitindo:

- Cadastro de cruzamentos com nome e localização
- Registro de semáforos vinculados a cruzamentos
- Definição de status dos semáforos (Verde, Amarelo, Vermelho)
- Agendamento de horários para mudanças de status
- Integração futura com sensores e reconhecimento facial

---

## 🧰 Tecnologias Utilizadas

- Java 21  
- Spring Boot 3.2.5  
- Oracle Database (sugestão: Oracle XE)  
- Spring Data JPA  
- Hibernate  
- Lombok  
- Maven  
- Insomnia (para testar endpoints REST)  
- Docker (opcional, para banco Oracle)

---

## 📁 Estrutura do Projeto


---

## ⚙️ Como Rodar o Projeto Localmente

### ✅ Pré-requisitos

- Java 21
- Maven instalado
- Oracle Database ativo
- IDE como IntelliJ, VSCode ou Eclipse

### 🔧 Configuração do banco de dados

Atualize o arquivo `application.properties` com as credenciais do seu banco Oracle:

```properties
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:XE
spring.datasource.username=SEMAFORO
spring.datasource.password=suasenha
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
spring.jpa.database-platform=org.hibernate.dialect.Oracle12cDialect
spring.jpa.hibernate.ddl-auto=update


▶ Compilar e executar

./mvnw spring-boot:run

🧪 Como executar os testes no Insomnia
Para facilitar os testes da API do sistema Semáforo Inteligente, foi criada uma coleção no Insomnia contendo todas as requisições organizadas por entidade (Usuários, Semáforos, StatusSemaforo, etc.).

✅ Pré-requisitos
Ter o Insomnia instalado.

A API deve estar rodando localmente em http://localhost:8080/.

Usuário admin com senha admin deve existir no banco para autenticação básica (HTTP Basic Auth).

🚀 Passo a passo
Importar a coleção:

Abra o Insomnia.

Vá em Application (ícone de engrenagem no canto superior esquerdo) > Import/Export > Import Data > From File.

Selecione o arquivo .json da coleção (ex: semaforo-inteligente-insomnia.json).

Executar as requisições:

Após importar, você verá pastas como User, Status Semaforo e Semaforo.

Dentro de cada pasta, há requisições para:

Create (POST)

Read (GET)

Update (PUT)

Delete (DELETE)

Para autenticar, as requisições já vêm configuradas com autenticação básica (admin / admin).

Testar os endpoints:

Você pode alterar os corpos das requisições POST e PUT para simular diferentes dados.

Certifique-se de que os id utilizados nas rotas (/usuarios/{id} etc.) existam no banco de dados.

As respostas virão em JSON no painel de resposta do Insomnia.


🐳 Rodando com Docker 

- Docker Desktop (instalado)
- Git instalado
- [Docker Compose](https://docs.docker.com/compose/)

Navegue até o diretório da aplicação.

# 1. Gerar a imagem da aplicação
docker-compose up --build
- Criar o container do banco Oracle XE
- Fazer o build da aplicação Spring Boot
- Iniciar a aplicação na porta 8080

# 2. Acesse a aplicação
exemplo:
http://localhost:8080/usuarios

# 3. login: user
senha: (gerada no terminal)

Teste com Postman ( caso necessário )
- Aba Authorization
- Tipo: No Auth ( colocado apenas para testar, é possivel dar um Get, Post e Delet.)


POST
POST http://localhost:8080/usuarios
Content-Type: application/json

{
  "nome": "João da Silva",
  "email": "joao@exemplo.com",
  "papel": "Moderador",
  "senha": "123456"
}

GET http://localhost:8080/usuarios

Para intorremper os containers
docker-compose down

