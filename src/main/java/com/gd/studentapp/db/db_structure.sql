CREATE TABLE `group`
(
  group_id INT AUTO_INCREMENT
    PRIMARY KEY,
  name     CHAR(255) NOT NULL
);

CREATE TABLE student
(
  id       INT AUTO_INCREMENT
    PRIMARY KEY,
  name     CHAR(255) NOT NULL,
  surname  CHAR(255) NOT NULL,
  iq_level INT       NOT NULL,
  group_id INT       NULL,
  birthday DATE      NOT NULL,
  CONSTRAINT student___fk
  FOREIGN KEY (group_id) REFERENCES students_dev.`group` (group_id)
    ON UPDATE CASCADE
);

CREATE INDEX student___fk
  ON student (group_id);


INSERT INTO `group` (group_id, name) VALUES (1, "smart");
INSERT INTO `group` (group_id, name) VALUES (2, "middle");
INSERT INTO `group` (group_id, name) VALUES (3, "lasy");