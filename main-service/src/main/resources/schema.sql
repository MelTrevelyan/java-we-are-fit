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
  name VARCHAR(50)                          NOT NULL,
  description VARCHAR(500)                  NOT NULL,
  trainer_id BIGINT                         NOT NULL,
  place_info VARCHAR(50)                    NOT NULL,
  date TIMESTAMP                            NOT NULL,
  is_paid BOOLEAN                           NOT NULL,
  status VARCHAR(15)                        NOT NULL,
  CONSTRAINT fk_workouts_to_trainers FOREIGN KEY(trainer_id) REFERENCES trainers(id)
  );

  CREATE TABLE IF NOT EXISTS workout_visitors (
  workout_id BIGINT                         NOT NULL,
  visitor_id BIGINT                         NOT NULL,
  CONSTRAINT pk_workout_visitors_to_workouts PRIMARY KEY (workout_id, visitor_id),
  CONSTRAINT fk_workout_visitors_to_workouts FOREIGN KEY(workout_id) REFERENCES workouts(id),
  CONSTRAINT fk_workout_visitors_to_users FOREIGN KEY(visitor_id) REFERENCES users(id)
  );

  CREATE TABLE IF NOT EXISTS schedules (
  id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  sch_day VARCHAR(15),
  sch_date TIMESTAMP
  );