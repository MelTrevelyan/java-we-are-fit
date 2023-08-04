  DROP TABLE IF EXISTS  schedules;
  DROP TABLE IF EXISTS  workouts;
  DROP TABLE IF EXISTS  trainers;
  DROP TABLE IF EXISTS  users;

  CREATE TABLE IF NOT EXISTS users (
  id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  email VARCHAR(100)                        NOT NULL,
  first_name VARCHAR(50)                    NOT NULL,
  last_name VARCHAR(100)                    NOT NULL,
  phone_number VARCHAR(20)                  NOT NULL,
  membership_start TIMESTAMP                NOT NULL,
  membership_expiration TIMESTAMP           NOT NULL,
  is_active BOOLEAN                         NOT NULL,
  CONSTRAINT uq_user_email UNIQUE (email),
  CONSTRAINT uq_user_phone UNIQUE (phone_number)
  );

  CREATE TABLE IF NOT EXISTS trainers (
  id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  email VARCHAR(100)                        NOT NULL,
  phone_number VARCHAR(20)                  NOT NULL,
  first_name VARCHAR(50)                    NOT NULL,
  last_name VARCHAR(100)                    NOT NULL,
  employment_date TIMESTAMP                 NOT NULL,
  education VARCHAR(300)                    NOT NULL,
  specifications VARCHAR(300)               NOT NULL,
  CONSTRAINT uq_trainer_email UNIQUE (email),
  CONSTRAINT uq_trainer_phone UNIQUE (phone_number)
  );

  CREATE TABLE IF NOT EXISTS workouts (
  id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  name VARCHAR(50),
  description VARCHAR(500),
  trainer_id BIGINT,
  place_info VARCHAR(50),
  date TIMESTAMP,
  is_paid BOOLEAN,
  status VARCHAR(15)
  );

  CREATE TABLE IF NOT EXISTS schedules (
  id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  sch_day VARCHAR(15),
  sch_date TIMESTAMP
  );