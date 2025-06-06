CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE alert (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    detection_id UUID REFERENCES detection(id),
    danger_level VARCHAR(50), -- LOW, MEDIUM, HIGH, CRITICAL
    confirmed BOOLEAN DEFAULT FALSE,
    reported_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);