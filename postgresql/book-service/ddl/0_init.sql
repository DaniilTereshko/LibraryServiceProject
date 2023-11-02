CREATE SCHEMA book_service
    AUTHORIZATION root;

CREATE TABLE book_service.books
(
    uuid uuid,
    isbn character varying(13) NOT NULL,
    title character varying(60) NOT NULL,
    genre character varying(40) NOT NULL,
    description text,
    author uuid NOT NULL,
    create_date timestamp(3) without time zone,
    update_date timestamp(3) without time zone,
    PRIMARY KEY (uuid)
);

ALTER TABLE IF EXISTS book_service.books
    OWNER to root;