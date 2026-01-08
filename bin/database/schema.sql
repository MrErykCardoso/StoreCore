-- STORECORE - MVP Pessoas (Customer + Collaborator)

CREATE EXTENSION IF NOT EXISTS pgcrypto;

-- CUSTOMER
CREATE TABLE IF NOT EXISTS customer (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  name TEXT NOT NULL,
  cpf TEXT UNIQUE,
  email TEXT,
  phone_number TEXT
);

-- COLLABORATOR (CEO / MANAGER / WORKER)
CREATE TABLE IF NOT EXISTS collaborator (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  name TEXT NOT NULL,
  cpf TEXT NOT NULL UNIQUE,
  email TEXT,
  phone_number TEXT,
  auth TEXT NOT NULL,
  position TEXT NOT NULL,
  hierarchy INT NOT NULL,
  wage NUMERIC(12,2) NOT NULL DEFAULT 0.00,
  role TEXT NOT NULL,
  CONSTRAINT chk_role CHECK (role IN ('CEO', 'MANAGER', 'WORKER'))
);
