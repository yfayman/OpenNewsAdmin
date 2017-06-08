
# --- !Ups

CREATE SEQUENCE user_type_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE user_type(
	user_type_id INTEGER PRIMARY KEY,
	ref_code char varying(256) NOT NULL UNIQUE
);

CREATE SEQUENCE user_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE "user"(
	user_id SERIAL PRIMARY KEY,
	username char varying(256) UNIQUE NOT NULL,
	email char varying(256) UNIQUE NOT NULL,
	"password" char varying(256) UNIQUE NOT NULL,
	user_type_id INTEGER NOT NULL,
	disabled BOOLEAN NOT NULL,
	auth_token char varying(256),
	auth_expiration TIMESTAMP,
	created_date TIMESTAMP NOT NULL,
	updated_date TIMESTAMP NOT NULL,
	FOREIGN KEY (user_type_id) REFERENCES user_type(user_type_id)
);

ALTER TABLE "user" ALTER COLUMN user_id set default nextval('user_seq');

INSERT INTO user_type VALUES(nextval('user_type_seq'), 'ADMIN');
INSERT INTO user_type VALUES(nextval('user_type_seq'), 'USER');

CREATE SEQUENCE article_status_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE article_status(
	article_status_id INTEGER PRIMARY KEY,
	ref_code char varying(256) UNIQUE NOT NULL
);

CREATE SEQUENCE export_status_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE export_status(
	export_status_id INTEGER PRIMARY KEY,
	ref_code char varying(256) UNIQUE NOT NULL
);

INSERT INTO article_status VALUES (nextval('article_status_seq'),'ACTIVE');
INSERT INTO article_status VALUES (nextval('article_status_seq'),'PENDING');
INSERT INTO article_status VALUES (nextval('article_status_seq'),'DELETED');

CREATE SEQUENCE article_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE article(
	article_id SERIAL PRIMARY KEY,
	url char varying(256) UNIQUE NOT NULL,
	title char varying(256) NOT NULL,
	short_description char varying(256) NOT NULL,
	html text NOT NULL,
	article_status_id INTEGER NOT NULL,
	user_id INTEGER NOT NULL,
	created_date TIMESTAMP NOT NULL,
	updated_date TIMESTAMP NOT NULL,
	FOREIGN KEY (user_id) REFERENCES "user"(user_id),
	FOREIGN KEY (article_status_id) REFERENCES article_status(article_status_id)
);

ALTER TABLE article ALTER COLUMN article_id set default nextval('article_seq');

# --- !Downs

DROP TABLE "user" CASCADE;
DROP TABLE user_type CASCADE;
DROP TABLE article_status CASCADE;
DROP TABLE export_status CASCADE;
DROP TABLE article CASCADE;
DROP SEQUENCE user_seq CASCADE;
DROP SEQUENCE user__type_seq CASCADE;
DROP SEQUENCE article_status_seq CASCADE;
DROP SEQUENCE export_status_seq CASCADE;
DROP SEQUENCE article_seq CASCADE;
