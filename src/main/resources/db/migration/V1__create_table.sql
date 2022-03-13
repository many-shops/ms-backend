CREATE TABLE person
(
    id           BIGSERIAL PRIMARY KEY,
    username     text,
    name         text,
    surname      text,
    vk_link      text,
    email        text,
    password     text,
    enabled      boolean,
    created_time timestamp default (current_timestamp),
    updated_time timestamp
);

CREATE TABLE role
(
    id           BIGSERIAL PRIMARY KEY,
    name         text,
    created_time timestamp default (current_timestamp),
    updated_time timestamp
);

CREATE TABLE person__role
(
    id           BIGSERIAL PRIMARY KEY,
    role_id      bigint,
    person_id    bigint,
    created_time timestamp default (current_timestamp),
    updated_time timestamp,
    UNIQUE (role_id, person_id)
);

ALTER TABLE person__role
    ADD FOREIGN KEY (role_id) REFERENCES role (id);

ALTER TABLE person__role
    ADD FOREIGN KEY (person_id) REFERENCES person (id);

CREATE TABLE company
(
    id           BIGSERIAL PRIMARY KEY,
    admin_id     bigint references person (id),
    name         text,
    description  text,
    created_time timestamp default (current_timestamp),
    updated_time timestamp
);

CREATE TABLE item
(
    id           BIGSERIAL PRIMARY KEY,
    company_id   bigint references company (id),
    name         text,
    description  text,
    price        decimal(8,2),
    created_time timestamp default (current_timestamp),
    updated_time timestamp
)