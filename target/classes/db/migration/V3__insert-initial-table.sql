-- Inserts iniciais para tabela product
INSERT INTO product (id, name, price) VALUES ('p1', 'Notebook Dell', 4500);
INSERT INTO product (id, name, price) VALUES ('p2', 'Monitor LG 24"', 950);
INSERT INTO product (id, name, price) VALUES ('p3', 'Teclado Mecânico', 350);
INSERT INTO product (id, name, price) VALUES ('p4', 'Mouse Gamer', 250);

-- Inserts iniciais para tabela users
INSERT INTO users (id, login, password, role)
VALUES ('11111111-1111-1111-1111-111111111111', 'admin', '{noop}admin123', 'ADMIN');

INSERT INTO users (id, login, password, role)
VALUES ('22222222-2222-2222-2222-222222222222', 'user1', '{noop}user123', 'USER');
