CREATE SCHEMA "library-service"
    AUTHORIZATION root;

CREATE TABLE "library-service".records
(
    uuid uuid,
    "from" timestamp(3) without time zone NOT NULL,
    "to" timestamp(3) without time zone NOT NULL,
    dt_create timestamp(3) without time zone NOT NULL,
    dt_update timestamp(3) without time zone NOT NULL,
    PRIMARY KEY (uuid)
);

ALTER TABLE IF EXISTS "library-service".records
    OWNER to root;