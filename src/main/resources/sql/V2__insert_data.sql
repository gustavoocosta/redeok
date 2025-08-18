-- Clientes exemplo
INSERT INTO client (name, phone, email, document, document_type) 
VALUES 
('João Silva', '11999999999', 'joao@email.com', '12345678901', 'CPF'),
('Empresa XYZ', '1122222222', 'contato@xyz.com', '12345678000199', 'CNPJ');

-- Endereços exemplo
INSERT INTO address (client_id, street, number, complement, neighborhood, city, state, zip_code)
VALUES
(1, 'Rua das Flores', '100', 'Apto 101', 'Centro', 'São Paulo', 'SP', '01001000'),
(1, 'Avenida Brasil', '2000', '', 'Jardins', 'São Paulo', 'SP', '01414000');