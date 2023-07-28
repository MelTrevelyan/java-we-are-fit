  CREATE TABLE IF NOT EXISTS users (
  id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  email VARCHAR(100),
  first_name VARCHAR(50),
  last_name VARCHAR(100),
  phone_number VARCHAR(20),
  membership_start TIMESTAMP,
  membership_expiration TIMESTAMP,
  is_active BOOLEAN,
  CONSTRAINT uq_user_email UNIQUE (email),
  CONSTRAINT uq_user_phone UNIQUE (phone_number)
  );

  CREATE TABLE IF NOT EXISTS trainers (
  id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  email VARCHAR(100),
  phone_number VARCHAR(20),
  first_name VARCHAR(50),
  last_name VARCHAR(100),
  employment_date TIMESTAMP,
  education VARCHAR(300),
  specifications VARCHAR(300)
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
  day VARCHAR(15),
  date TIMESTAMP
  );