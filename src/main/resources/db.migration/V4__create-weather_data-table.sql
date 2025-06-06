CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE weather_data (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    temperature_celsius DECIMAL(5,2),
    humidity_percent DECIMAL(5,2),
    wind_speed_kmh DECIMAL(5,2),
    pressure_hpa DECIMAL(6,2),
    recorded_at TIMESTAMP NOT NULL
);