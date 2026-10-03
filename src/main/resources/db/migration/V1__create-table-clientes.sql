CREATE TABLE clientes(
    id UUID PRIMARY KEY,
    nome VARCHAR(255),
    telefone VARCHAR(15),
    email VARCHAR(255) UNIQUE
);