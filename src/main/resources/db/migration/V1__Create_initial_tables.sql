-- Criação da tabela client
CREATE TABLE client (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL,
    document VARCHAR(20) UNIQUE NOT NULL,
    document_type VARCHAR(10) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Criação da tabela address
CREATE TABLE address (
    id BIGSERIAL PRIMARY KEY,
    client_id BIGINT NOT NULL,
    street VARCHAR(150) NOT NULL,
    number VARCHAR(20) NOT NULL,
    complement VARCHAR(50),
    neighborhood VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(2) NOT NULL,
    zip_code VARCHAR(9) NOT NULL,
    primary_address BOOLEAN DEFAULT FALSE,
    CONSTRAINT fk_address_client FOREIGN KEY (client_id) REFERENCES client(id) ON DELETE CASCADE
);

-- Índices para performance
CREATE INDEX idx_client_document ON client(document);
CREATE INDEX idx_client_email ON client(email);
CREATE INDEX idx_address_client_id ON address(client_id);