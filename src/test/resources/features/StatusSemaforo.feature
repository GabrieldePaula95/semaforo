#language: pt

@regressivo
Funcionalidade: Status do Semáforo
  Como um usuário do sistema
  Eu quero verificar o status do semáforo
  Para garantir que o semáforo esteja funcionando corretamente

  Contexto:
    Dado que o sistema exige autenticação básica para os endpoints protegidos

  Cenário: Cadastro bem-sucedido de status do semáforo
    Dado que eu tenha um token de autenticação válido
    E que eu tenha os seguintes dados do status do semáforo:
      | campo     | valor |
      | id        | 1     |
      | descricao | Verde |
    Quando eu enviar a requisição para o endpoint "/status-semaforo" de cadastro de status
    Então o status code da criação deve ser 200

  Cenário: Buscar status existente por ID
    Dado que exista um status de semáforo com os seguintes dados:
      | campo     | valor |
      | id        | 1     |
      | descricao | Verde |
    Então o status code da consulta deve ser 200
    E a resposta deve conter a descrição "Verde"