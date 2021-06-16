CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE  cards (
    id uuid DEFAULT uuid_generate_v4(),
    user_id uuid NOT NULL,
    nickname VARCHAR DEFAULT '',
    provider VARCHAR NOT NULL,
    type VARCHAR(1) NOT NULL
        CONSTRAINT ck_card_type CHECK (type = 'c' or type = 'b' or type = 'o'),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW(),

    FOREIGN KEY (user_id) references users (id),
    PRIMARY KEY (id)
);

CREATE TABLE  accounts (
    id uuid DEFAULT uuid_generate_v4(),
    user_id uuid NOT NULL,
    nickname VARCHAR DEFAULT '',
    bank VARCHAR NOT NULL,
    balance NUMERIC(19,2) NOT NULL,
    updated_at TIMESTAMP NOT NULL DEFAULT NOW(),

    FOREIGN KEY (user_id) references users (id),
    PRIMARY KEY (id)
);

