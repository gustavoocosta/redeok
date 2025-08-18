-- Inserir clientes de exemplo
INSERT INTO client (name, document, email, phone)
VALUES
('João Silva', '12345678901', 'joao.silva@email.com', '11999999999'),
('Maria Oliveira', '98765432100', 'maria.oliveira@email.com', '21988888888');

-- Inserir endereços de exemplo para João Silva (id = 1)
INSERT INTO address (client_id, street, number, complement, neighborhood, city, state, zip_code, primary_address)
VALUES
(1, 'Rua das Flores', '123', 'Apto 45', 'Jardim Primavera', 'São Paulo', 'SP', '01001000', TRUE),
(1, 'Av. Paulista', '1500', NULL, 'Bela Vista', 'São Paulo', 'SP', '01310000', FALSE);

-- Inserir endereços de exemplo para Maria Oliveira (id = 2)
INSERT INTO address (client_id, street, number, complement, neighborhood, city, state, zip_code, primary_address)
VALUES
(2, 'Rua do Sol', '50', NULL, 'Centro', 'Rio de Janeiro', 'RJ', '20010000', TRUE);
