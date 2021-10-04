DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(250) NOT NULL,
    password VARCHAR(96)  NOT NULL
);

CREATE TABLE days
(
    id    INT PRIMARY KEY,
    value VARCHAR(32) NOT NULL
);

CREATE TABLE intervals
(
    id    INT PRIMARY KEY,
    value VARCHAR(32) NOT NULL
);

CREATE TABLE subjects
(
    id           IDENTITY PRIMARY KEY,
    name         VARCHAR(250) NOT NULL,
    lecturer     VARCHAR(96)  NOT NULL,
    practitioner VARCHAR(96)  NOT NULL,
    user_id      INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE slots
(
    id         IDENTITY PRIMARY KEY,
    day_id     INT        NOT NULL,
    time_id    INT        NOT NULL,
    lection    BOOLEAN    NOT NULL,
    room       VARCHAR(8) NOT NULL,
    user_id      INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (day_id) REFERENCES days (id),
    FOREIGN KEY (time_id) REFERENCES intervals (id)
);

CREATE TABLE subject_slots(
    id IDENTITY PRIMARY KEY,
    subject_id INT NOT NULL,
    slot_id INT NOT NULL,
    week INT NOT NULL,
    user_id      INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (subject_id) REFERENCES subjects (id),
    FOREIGN KEY (slot_id) REFERENCES slots (id)
);


INSERT INTO users(username, password)
VALUES ('user1', '$2a$10$JQor2Lkc5wAN4hWUQQq.wudA2kb/vXUsGevKkmD.xOmCyqLnM.UHm');


INSERT INTO days(id, value)
VALUES (1, 'Monday');
INSERT INTO days(id, value)
VALUES (2, 'Tuesday');
INSERT INTO days(id, value)
VALUES (3, 'Wednesday');
INSERT INTO days(id, value)
VALUES (4, 'Thursday');
INSERT INTO days(id, value)
VALUES (5, 'Friday');
INSERT INTO days(id, value)
VALUES (6, 'Saturday');
INSERT INTO days(id, value)
VALUES (7, 'Sunday');


INSERT INTO intervals(id, value)
VALUES (1, '08:30-09:50');
INSERT INTO intervals(id, value)
VALUES (2, '10:00-11:20');
INSERT INTO intervals(id, value)
VALUES (3, '11:40-13:00');
INSERT INTO intervals(id, value)
VALUES (4, '13:30-14:50');
INSERT INTO intervals(id, value)
VALUES (5, '15:00-16:20');
INSERT INTO intervals(id, value)
VALUES (6, '16:30-17:50');
INSERT INTO intervals(id, value)
VALUES (7, '18:00-19:20');

INSERT INTO subjects (name, lecturer, practitioner, user_id)
VALUES ( 'Subject1', 'Lecturer1', 'Practiom1', 1);