CREATE SCHEMA "user-service"
    AUTHORIZATION root;

CREATE TABLE "user-service".users
(
    uuid uuid,
    email character varying(255) NOT NULL,
    password character varying(250) NOT NULL,
    role character varying(35) NOT NULL,
    PRIMARY KEY (uuid),
    UNIQUE (email)
);

ALTER TABLE IF EXISTS "user-service".users
    OWNER to root;