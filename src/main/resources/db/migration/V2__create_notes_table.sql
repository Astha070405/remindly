CREATE TABLE notes (

    id VARCHAR(255) PRIMARY KEY,

    title VARCHAR(255),

    content TEXT,

    pinned BOOLEAN DEFAULT FALSE,

    archived BOOLEAN DEFAULT FALSE,

    created_at TIMESTAMP,

    updated_at TIMESTAMP,

    user_id UUID,

    CONSTRAINT fk_notes_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);