CREATE SCHEMA author_service
    AUTHORIZATION postgres;

CREATE TABLE author_service.author
(
    uuid uuid,
    fio character varying(120) NOT NULL,
    PRIMARY KEY (uuid)
);

ALTER TABLE IF EXISTS author_service.author
    OWNER to postgres;