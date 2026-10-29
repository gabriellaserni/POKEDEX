# API Pokédex

API Restful (Spring Boot + Oracle) com CRUD de pokémons.

Endpoint base: `/pokedex`

| Verbo  | URI               | Ação              | Sucesso | Falha |
|--------|-------------------|-------------------|---------|-------|
| GET    | /pokedex          | listar todos      | 200     | 404   |
| GET    | /pokedex/{codigo} | buscar por código | 200     | 404   |
| POST   | /pokedex          | cadastrar         | 201     | 400   |
| PUT    | /pokedex/{codigo} | atualizar         | 201     | 400   |
| DELETE | /pokedex/{codigo} | excluir           | 204     | 404   |

Variáveis de ambiente: `DB_URL`, `DB_USER`, `DB_PASSWORD`.
