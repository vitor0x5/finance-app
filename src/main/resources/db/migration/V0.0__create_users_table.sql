CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE  users (
  id uuid DEFAULT uuid_generate_v4(),
  name VARCHAR NOT NULL,
  email VARCHAR NOT NULL UNIQUE ,
  password VARCHAR NOT NULL,
  updated_at TIMESTAMP NOT NULL DEFAULT NOW(),

  PRIMARY KEY (id)
)