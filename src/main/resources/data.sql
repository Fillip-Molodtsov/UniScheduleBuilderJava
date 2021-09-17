DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(250) NOT NULL,
    password VARCHAR(96)  NOT NULL
);

INSERT INTO users(username, password)
VALUES ('user1', '$2a$10$JQor2Lkc5wAN4hWUQQq.wudA2kb/vXUsGevKkmD.xOmCyqLnM.UHm')