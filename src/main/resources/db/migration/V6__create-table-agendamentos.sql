CREATE TABLE agendamentos
(
    id               UUID PRIMARY KEY,
    data_hora_inicio TIMESTAMP,
    data_hora_fim    TIMESTAMP,
    status           status_agendamento,
    observacao       VARCHAR(255),
    cliente_id       UUID,
    barbeiro_id      UUID,
    servico_id       UUID,
    preco_cobrado    NUMERIC(10, 2),

    FOREIGN KEY (cliente_id)
        REFERENCES clientes (id),
    FOREIGN KEY (barbeiro_id)
        REFERENCES barbeiros (id),
    FOREIGN KEY (servico_id)
        REFERENCES servicos (id)

);