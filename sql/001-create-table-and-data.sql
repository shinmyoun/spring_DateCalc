DROP TABLE IF EXISTS datecalc;

CREATE TABLE datecalc (
    id INT(50) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    plusyear INT(50),
    plusmonth INT(50),
    plusday INT(50)
);

INSERT IGNORE INTO datecalc (id, name, plusyear, plusmonth, plusday) VALUES (1, 'A', 1, 1, 1);
INSERT IGNORE INTO datecalc (id, name, plusyear, plusmonth, plusday) VALUES (2, 'B', 2, 2, 2);
INSERT IGNORE INTO datecalc (id, name, plusyear, plusmonth, plusday) VALUES (3, 'C', 3, 3, 3);


DROP TABLE IF EXISTS login_user;

CREATE TABLE login_user (
    id INT(50) PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(64) NOT NULL,
    password VARCHAR(128) NOT NULL
);

INSERT INTO login_user(username, password) VALUES('hoge', '$2a$08$YvT9gQJ0aZo4/FHpQRKioe2d5q1//M8C2W889C9NQgZNk8.uPTCmu');
