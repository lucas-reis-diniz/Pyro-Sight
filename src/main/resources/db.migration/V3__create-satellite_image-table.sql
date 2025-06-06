CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE satellite_image (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    url TEXT NOT NULL,
    captured_at TIMESTAMP NOT NULL,
    image_type VARCHAR(50), -- THERMAL, OPTICAL
    latitude DOUBLE PRECISION,
    longitude DOUBLE PRECISION
);