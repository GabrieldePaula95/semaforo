#language: pt

@regressivo
Funcionalidade: Gerenciar usuários
  Como um sistema protegido por autenticação
  Eu quero permitir a atualização de usuários existentes
  Para que apenas usuários autorizados possam alterar informações

  Cenário: Atualizar um usuário existente com autenticação
    Dado que eu tenha cadastrado um usuário com os seguintes dados:
      | campo | valor            |
      | nome  | João da Silva    |
      | email | joao@exemplo.com |
      | senha | 123456           |
      | papel | Operador         |
    Quando eu atualizar esse usuário com o nome "João Atualizado" e papel "Administrador"
    Então o status code da resposta deve ser 200
    E a resposta deve conter o nome "João Atualizado" e papel "Administrador"

  Cenário: Tentar atualizar um usuário inexistente
    Dado que eu tenha uma autenticação válida
    E que eu tente atualizar um usuário com os seguintes dados:
      | campo | valor            |
      | nome  | João da Silva    |
      | email | joao@exemplo.com |
      | id    | 0                |
      | papel | Visitante        |
    Quando eu enviar a requisição de atualização para o endpoint "/usuarios/0"
    Então a resposta esperada deve ser 404
