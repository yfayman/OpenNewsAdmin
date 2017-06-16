CREATE DATABASE OpenNews;

-- TABLE : user_type
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

INSERT INTO user_type VALUES(nextval('user_type_seq'), 'ADMIN');
INSERT INTO user_type VALUES(nextval('user_type_seq'), 'USER');

-- TABLE : user

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


-- TABLE : article_status


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

INSERT INTO article_status VALUES (nextval('article_status_seq'),'ACTIVE');
INSERT INTO article_status VALUES (nextval('article_status_seq'),'PENDING');
INSERT INTO article_status VALUES (nextval('article_status_seq'),'DELETED');

-- TABLE : article

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

-- TABLE : job

CREATE SEQUENCE job_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE job(
	job_id SERIAL PRIMARY KEY,
        job_name char varying(256) UNIQUE NOT NULL,
	schedule char varying(256) NOT NULL,
	created_date TIMESTAMP NOT NULL,
	updated_date TIMESTAMP NOT NULL,
)

ALTER TABLE job ALTER COLUMN job_id set default nextval('job_seq');


-- TABLE : job_status

CREATE SEQUENCE job_status
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE job_status(
	job_status_id SERIAL PRIMARY KEY,
        ref_code char varying(256) UNIQUE NOT NULL
)

ALTER TABLE job_status ALTER COLUMN job_status_id set default nextval('job_status_seq');


-- TABLE : job_instance

CREATE SEQUENCE job_instance_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE job_instance(
	job_instance_id SERIAL PRIMARY KEY,
	job_status_id INTEGER NOT NULL,
	start_date TIMESTAMP NOT NULL,
	end_date TIMESTAMP NOT NULL,
	FOREIGN KEY (job_status_id) REFERENCES job_status(job_status_id)
)

ALTER TABLE job_instance ALTER COLUMN job_instance_id set default nextval('job_instance_seq');


-- TABLE : job_instance_message

CREATE SEQUENCE job_instance_message_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE job_instance_message(
	job_instance_message_id SERIAL PRIMARY KEY,
	job_instance_id INTEGER NOT NULL,
	message char varying(2048) NOT NULL,
	FOREIGN KEY (job_instance_id) REFERENCES job_instance(job_instance_id)
)

ALTER TABLE job_instance_message ALTER COLUMN job_instance_message_id set default nextval('job_instance_message_seq');
