INSERT INTO message
  (id, text)
VALUES
  (1, 'Hello, how ara you'),
  (2, 'Hy, I am fine and you');

INSERT INTO users
  (id, email, password, first_name, last_name)
VALUES
  (1, 'test@gmail.com', '$2a$04$kDaKwBckpCiw/PFvV4qpqOdMl9oypQVKaXvANn.oeKC9xrGiYdfmO', 'Test', 'Testovich'),
  (2, 'nikolai.blashchuk@gmail.com', '$2a$04$kDaKwBckpCiw/PFvV4qpqOdMl9oypQVKaXvANn.oeKC9xrGiYdfmO', 'Nick', 'Blashchuk');

INSERT INTO users_roles
  (user_id, role)
VALUES
  (1, 'ADMIN'),
  (2, 'USER');