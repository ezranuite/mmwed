# --- !Ups

CREATE TABLE users (
    id BIGSERIAL, 
    first_name VARCHAR(40) NOT NULL,
    last_name VARCHAR(40) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(1000) NOT NULL,
    phone VARCHAR(15)
);

CREATE TABLE beers ( 
    id BIGSERIAL,
    name VARCHAR(250) NOT NULL,
    brewery VARCHAR(120),
    style VARCHAR(100),
    country VARCHAR(100),
    state VARCHAR(2)
);

CREATE TABLE food (
   id BIGSERIAL,
   name VARCHAR(250) NOT NULL
);

CREATE TABLE movies (
    id BIGSERIAL,
    name VARCHAR(150) NOT NULL,
    watch_date DATE,
    brought_by_user_id BIGINT NOT NULL,
    release_date DATE,
    imdb_url VARCHAR(2500)
);

CREATE TABLE meetings (
   id BIGSERIAL,
   date DATE NOT NULL,
   movie_id BIGINT
);

CREATE TABLE attendance (
   id BIGSERIAL,
   meeting_id BIGINT NOT NULL,
   user_id BIGINT NOT NULL,
   assigned VARCHAR(15),
   beer_id BIGINT,
   food_id BIGINT,
   movie_id BIGINT,
   expense INTEGER
);  

# --- !Downs

DROP TABLE users, beers, food, movies, meetings, attendance;
