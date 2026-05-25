CREATE TABLE tasks (

    id VARCHAR(255) PRIMARY KEY,

    title VARCHAR(255),

    description TEXT,

    completed BOOLEAN DEFAULT FALSE,

    archived BOOLEAN DEFAULT FALSE,

    reminder_enabled BOOLEAN DEFAULT FALSE,

    priority VARCHAR(50),

    due_date TIMESTAMP,

    created_at TIMESTAMP,

    updated_at TIMESTAMP,

    user_id UUID,

    CONSTRAINT fk_tasks_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);