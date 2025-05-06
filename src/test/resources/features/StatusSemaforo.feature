#language: pt

@regressivo
Funcionalidade: Status do Semáforo
  Como um usuário do sistema
  Eu quero verificar o status do semáforo
  Para garantir que o semáforo esteja funcionando corretamente

  Contexto:
    Dado que o sistema exige autenticação básica para os endpoints protegidos

  Cenário: Semáforo fora de serviço
    Dado que o semáforo esteja fora de serviço
    Quando eu verificar o status do semáforo
    Então o status deve ser "Fora de serviço"
    E a contagem regressiva não deve estar disponível

  Cenário: Cadastro bem-sucedido de status do semáforo
    Dado que eu tenha um token de autenticação válido
    E que eu tenha os seguintes dados do status do semáforo:
      | campo     | valor |
      | id        | 1     |
      | descricao | Verde |
    Quando eu enviar a requisição para o endpoint "/semaforos" de cadastro de status
    Então o status code da criação deve ser 200

  @padrão
  Cenário: Buscar status existente por ID
    Dado que exista um status de semáforo com descrição "Verde"
    Quando eu buscar o status pelo ID 1
    Então o status code da resposta deve ser 200
    E a resposta deve conter a descrição "Verde
