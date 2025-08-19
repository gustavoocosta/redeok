CREATE TABLE endereco (
    id SERIAL PRIMARY KEY,
    cliente_id INT NOT NULL,
    rua VARCHAR(150) NOT NULL,
    numero VARCHAR(20),
    cidade VARCHAR(100) NOT NULL,
    estado VARCHAR(2) NOT NULL,
    cep VARCHAR(10) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id) ON DELETE CASCADE
);
