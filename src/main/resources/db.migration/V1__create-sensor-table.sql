CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE sensor (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    type VARCHAR(50), -- TEMPERATURE, SMOKE, HUMIDITY
    latitude DOUBLE PRECISION NOT NULL,
    longitude DOUBLE PRECISION NOT NULL,
    location_description TEXT
);