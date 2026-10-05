CREATE TABLE servicos (
    id UUID PRIMARY KEY,
    nome VARCHAR(255),
    descricao TEXT,
    preco NUMERIC(10,2),
    duracao_em_minutos INTEGER,
    ativo BOOLEAN
);