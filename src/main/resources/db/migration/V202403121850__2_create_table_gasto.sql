
-- V2__Create_Tabela_Gasto.sql

CREATE TABLE IF NOT EXISTS GASTO (
    id SERIAL PRIMARY KEY,
    valor NUMERIC,
    descricao VARCHAR(255),
    dia TIMESTAMP NOT NULL
);