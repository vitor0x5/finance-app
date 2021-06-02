CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE  transactions (
  id uuid DEFAULT uuid_generate_v4(),
  user_id uuid NOT NULL,
  source VARCHAR,
  description VARCHAR NOT NULL,
  type VARCHAR(7) NOT NULL
        CONSTRAINT ck_type CHECK (type = 'income' or type = 'outcome'),
  value NUMERIC(19,2) NOT NULL,
  transaction_date DATE NOT NULL,
  updated_at TIMESTAMP NOT NULL DEFAULT NOW(),

  FOREIGN KEY (user_id) references users (id),
  PRIMARY KEY (id, user_id)
)

