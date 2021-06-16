CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE  transactions (
  id uuid DEFAULT uuid_generate_v4(),
  user_id uuid NOT NULL,
  card_id uuid,
  account_id uuid,
  source VARCHAR,
  description VARCHAR NOT NULL,
  type VARCHAR(1) NOT NULL
        CONSTRAINT ck_transactions_type CHECK (type = 'i' or type = 'o'),
  value NUMERIC(19,2) NOT NULL,
  transaction_date DATE NOT NULL,
  updated_at TIMESTAMP NOT NULL DEFAULT NOW(),

  FOREIGN KEY (user_id) references users (id),
  FOREIGN KEY (card_id) references cards (id),
  FOREIGN KEY (account_id) references accounts (id),
  PRIMARY KEY (id, user_id)
)

