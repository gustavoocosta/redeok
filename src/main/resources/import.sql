CREATE TABLE address (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    street VARCHAR(255) NOT NULL,
    number VARCHAR(50),
    complement VARCHAR(255),
    neighborhood VARCHAR(255),
    city VARCHAR(255) NOT NULL,
    state VARCHAR(2) NOT NULL,
    zip_code VARCHAR(20) NOT NULL,
    primary_address BOOLEAN DEFAULT FALSE,
    client_id BIGINT NOT NULL,
    CONSTRAINT fk_client FOREIGN KEY (client_id) REFERENCES client(id)
);
