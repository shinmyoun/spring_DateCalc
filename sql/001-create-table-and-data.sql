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
