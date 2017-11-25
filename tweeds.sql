DROP TABLE IF EXISTS topics;
CREATE TABLE topics(
 id int(11) NOT NULL AUTO_INCREMENT,
 name varchar(20) NOT NULL,
 PRIMARY KEY (id)
);

DROP TABLE IF EXISTS regions;
CREATE TABLE regions(
 id int(5) NOT NULL AUTO_INCREMENT,
 name varchar(40) NOT NULL,
 PRIMARY KEY (id)
);

DROP TABLE IF EXISTS tweets_topic;
CREATE TABLE tweets_topic(
  id int(11) NOT NULL AUTO_INCREMENT,
  topic_id int(11) NOT NULL,
  value int(11) NOT NULL,
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (topic_id) REFERENCES topics(id)
);

DROP TABLE IF EXISTS history_tweets_topic;
CREATE TABLE history_tweets_topic(
  id int(11) NOT NULL AUTO_INCREMENT,
  topic_id int(11) NOT NULL,
  value int(11) NOT NULL,
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (topic_id) REFERENCES topics(id)
);

DROP TABLE IF EXISTS approval_topic;
CREATE TABLE approval_topic(
  id int(11) NOT NULL AUTO_INCREMENT,
  topic_id int(11) NOT NULL,
  approval long NOT NULL,
  disapproval long NOT NULL,
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (topic_id) REFERENCES topics(id)
);

DROP TABLE IF EXISTS approval_topic_by_region;
CREATE TABLE approval_topic_by_region(
  id int(11) NOT NULL AUTO_INCREMENT,
  topic_id int(11) NOT NULL,
  region_id int(5) NOT NULL,
  approval long NOT NULL,
  disapproval long NOT NULL,
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (topic_id) REFERENCES topics(id),
 FOREIGN KEY (region_id) REFERENCES regions(id)
);

DROP TABLE IF EXISTS history_approval_topic;
CREATE TABLE history_approval_topic(
  id int(11) NOT NULL AUTO_INCREMENT,
  topic_id int(11) NOT NULL,
  approval long NOT NULL,
  disapproval long NOT NULL,
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (topic_id) REFERENCES topics(id)
);


INSERT INTO topics (name)
VALUES
('Legal'),
('Medicinal'),
('Recreativo');

INSERT INTO regions (name)
VALUES
('Región de Tarapacá'),
('Región de Antofagasta'),
('Región de Atacama'),
('Región de Coquimbo'),
('Región de Valparaíso'),
('Región de O Higgins'),
('Región del Maule'),
('Región de Bío Bío'),
('Región de la Araucanía'),
('Región de Los Lagos'),
('Región de Aysén'),
('Región de Magallanes'),
('Región Metropolitana'),
('Región de Los Ríos'),
('Región de Arica y Parinacota'),
('Sin Región');

INSERT INTO approval_topic_by_region (topic_id, region_id, approval, disapproval)
VALUES
(1,1,0.4,0.6),
(1,2,0.2,0.8),
(1,3,0.2,0.8),
(1,4,0.2,0.8),
(1,5,0.2,0.8),
(1,6,0.2,0.8),
(1,7,0.2,0.8),
(1,8,0.2,0.8),
(1,9,0.2,0.8),
(1,10,0.2,0.8),
(1,11,0.2,0.8),
(1,12,0.2,0.8),
(1,13,0.2,0.8),
(1,14,0.2,0.8),
(1,15,0.2,0.8),
(1,16,0.2,0.8),
(2,1,0.4,0.6),
(2,2,0.2,0.8),
(2,3,0.2,0.8),
(2,4,0.2,0.8),
(2,5,0.2,0.8),
(2,6,0.2,0.8),
(2,7,0.2,0.8),
(2,8,0.2,0.8),
(2,9,0.2,0.8),
(2,10,0.2,0.8),
(2,11,0.2,0.8),
(2,12,0.2,0.8),
(2,13,0.2,0.8),
(2,14,0.2,0.8),
(2,15,0.2,0.8),
(2,16,0.2,0.8);


