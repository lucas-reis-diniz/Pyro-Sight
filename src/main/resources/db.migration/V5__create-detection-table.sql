CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE detection (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    sensor_id UUID REFERENCES sensor(id),
    drone_id UUID REFERENCES drone(id),
    satellite_image_id UUID REFERENCES satellite_image(id),
    weather_data_id UUID REFERENCES weather_data(id),
    confidence_level DECIMAL(5,2), -- ex: 92.5%
    detected_at TIMESTAMP NOT NULL
);