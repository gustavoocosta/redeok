CREATE TABLE client (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    document VARCHAR(20) UNIQUE NOT NULL
);

CREATE TABLE address (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    client_id BIGINT NOT NULL,
    street VARCHAR(150) NOT NULL,
    number VARCHAR(20),
    complement VARCHAR(50),
    neighborhood VARCHAR(100),
    city VARCHAR(100),
    state VARCHAR(2),
    zip_code VARCHAR(9),
    primary_address BOOLEAN DEFAULT false,
    FOREIGN KEY (client_id) REFERENCES client(id)
);
