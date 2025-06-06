CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE notification (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    user_id UUID REFERENCES app_user(id),
    alert_id UUID REFERENCES alert(id),
    sent_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    message TEXT,
    status VARCHAR(50) -- SENT, FAILED, READ
);