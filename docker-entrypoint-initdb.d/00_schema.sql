CREATE TABLE users (
  id BIGSERIAL PRIMARY KEY,
  login TEXT NOT NULL UNIQUE,
  password TEXT NOT NULL,
  roles TEXT[] NOT NULL DEFAULT '{ROLE_USER}',
  avatar TEXT NOT NULL DEFAULT 'noavatar.svg',
  removed BOOLEAN NOT NULL DEFAULT FALSE,
  secret text not null,
  created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE tokens (
    userId BIGINT NOT NULL REFERENCES users,
    token TEXT PRIMARY KEY
);

create table groups (
    id bigserial primary key,
    ownerId bigint not null references users,
    name text not null
);

create table memos(
    id bigserial primary key,
    authorId bigint not null references users,
    groupId bigint not null references groups,
    content text,
    attachment text
);

create table group_permissions (
    userId bigint not null references users,
    groupId bigint not null references groups,
    permissions int not null default 0
);