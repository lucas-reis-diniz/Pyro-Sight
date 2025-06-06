CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE drone (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    model VARCHAR(100),
    identifier VARCHAR(100) NOT NULL,
    status VARCHAR(50), -- ACTIVE, MAINTENANCE, UNAVAILABLE
    autonomy_minutes INT,
    last_latitude DOUBLE PRECISION,
    last_longitude DOUBLE PRECISION
);