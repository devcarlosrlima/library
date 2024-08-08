-- Inserção de usuários
INSERT INTO users (username, password, role) VALUES ('admin', '$2a$10$Dow1Ghe5lvBO/JzN9/Ngeui4uYJdS.sJg2DdA3bzHrKNvfuGad0VO', 'ROLE_ADMIN'); -- senha: admin
INSERT INTO users (username, password, role) VALUES ('user', '$2a$10$Dow1Ghe5lvBO/JzN9/Ngeui4uYJdS.sJg2DdA3bzHrKNvfuGad0VO', 'ROLE_USER'); -- senha: user

-- Inserção de livros
INSERT INTO books (title, author, isbn) VALUES ('Book Title 1', 'Author 1', 'ISBN1234567890');
INSERT INTO books (title, author, isbn) VALUES ('Book Title 2', 'Author 2', 'ISBN0987654321');

-- Inserção de membros
INSERT INTO members (name, email) VALUES ('Member 1', 'member1@example.com');
INSERT INTO members (name, email) VALUES ('Member 2', 'member2@example.com');

-- Inserção de empréstimos
INSERT INTO loans (book_id, member_id, loan_date, return_date) VALUES (1, 1, '2024-01-01', NULL);
INSERT INTO loans (book_id, member_id, loan_date, return_date) VALUES (2, 2, '2024-01-02', NULL);
