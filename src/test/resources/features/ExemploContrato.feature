#language: pt
Funcionalidade: Validar o contrato ao atualizar um usuário com autenticação
  Como consumidor da API de usuários
  Quero atualizar um usuário existente com dados válidos
  Para garantir que a resposta da API siga o contrato esperado

  Cenario: Validar contrato do cadastro bem-sucedido de um usuário
    Dado que eu tenha os seguintes dados:
      | campo | valor              |
      | nome  | Bianca Barbosa     |
      | email | bianca@barbosa.com |
      | senha | 123456             |
      | papel | Moderador           |
    Quando eu enviar a requisição para o endpoint "/usuarios" de cadastro de entregas
    Então o status code da resposta deve ser 201
    E que o arquivo de contrato esperado é o "Cadastro bem-sucedido usuario"
    Então a resposta da requisição deve estar em conformidade com o contrato selecionado
