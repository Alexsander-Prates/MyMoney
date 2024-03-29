-- V3__Create_Tabela_Categoria.sql

CREATE TABLE IF NOT EXISTS CATEGORIA (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL UNIQUE,
    descricao VARCHAR(255)
);