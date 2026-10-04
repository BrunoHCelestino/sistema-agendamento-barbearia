# Rotas
Abaixo você poderá encontrar todas as rotas disponíveis do projeto para poder realizar as operações do sistema.
___
## Cliente
| Método | Rota | Descrição |
|--------|------|-----------|
| ![GET](https://img.shields.io/badge/GET-0077c0?style=flat-square) | `/v1/cliente/find-all` | Lista todos os clientes |
| ![GET](https://img.shields.io/badge/GET-0077c0?style=flat-square) | `/v1/cliente/find-all-activated` | Lista clientes ativos |
| ![GET](https://img.shields.io/badge/GET-0077c0?style=flat-square) | `/v1/cliente/find-all-by-id/{id}` | Busca cliente por ID |
| ![POST](https://img.shields.io/badge/POST-2ea44f?style=flat-square) | `/v1/cliente/create-cliente` | Cria um cliente |
| ![PATCH](https://img.shields.io/badge/PATCH-8250df?style=flat-square) | `/v1/cliente/toggle-cliente-status/{email}` | Ativa/desativa um cliente |
| ![PATCH](https://img.shields.io/badge/PATCH-8250df?style=flat-square) | `/v1/cliente/update-cliente` | Atualiza telefone e/ou email do cliente |