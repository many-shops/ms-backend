INSERT INTO person (username, password, enabled)
values ('user', 'password', true);

INSERT into role(name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

INSERT into person__role (role_id, person_id)
values (1, 1);