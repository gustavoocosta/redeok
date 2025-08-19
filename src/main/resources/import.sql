-- Dados de teste para desenvolvimento
INSERT INTO client (name, email, document, document_type) 
VALUES 
    ('João Silva', 'joao@teste.com', '12345678901', 'CPF'),
    ('Empresa LTDA', 'contato@empresa.com', '12345678000199', 'CNPJ');

INSERT INTO address (client_id, street, number, neighborhood, city, state, zip_code, primary_address)
VALUES 
    (1, 'Rua das Flores', '123', 'Centro', 'São Paulo', 'SP', '01000000', true),
    (2, 'Av. Paulista', '1000', 'Bela Vista', 'São Paulo', 'SP', '01310000', true);