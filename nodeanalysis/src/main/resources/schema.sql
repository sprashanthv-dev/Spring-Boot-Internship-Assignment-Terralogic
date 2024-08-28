CREATE TABLE IF NOT EXISTS NODE (
    id VARCHAR(255) PRIMARY KEY UNIQUE,
    name VARCHAR(255) not null,
    description VARCHAR(255) not null,
    memo VARCHAR(255) not null,
    type VARCHAR(255) not null,
    parent_group_name VARCHAR(255) not null,
    parent_group_id VARCHAR(255) not null,
    parent_name VARCHAR(255) not null
);