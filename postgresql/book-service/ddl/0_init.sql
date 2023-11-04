CREATE SCHEMA "book-service"
    AUTHORIZATION root;

CREATE TABLE "book-service".books
(
    uuid uuid,
    isbn character varying(13) NOT NULL,
    title character varying(60) NOT NULL,
    genre character varying(40) NOT NULL,
    description text,
    author character varying(100) NOT NULL,
    create_date timestamp(3) without time zone,
    update_date timestamp(3) without time zone,
    PRIMARY KEY (uuid)
);

ALTER TABLE IF EXISTS "book-service".books
    OWNER to root;