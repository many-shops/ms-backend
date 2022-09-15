INSERT INTO person (username, password, enabled)
values ('user', 'password', true),
       ('admin', 'password', true),
       ('seller', 'password', true);

INSERT into role(name)
values ('ROLE_USER'),
       ('ROLE_ADMIN'),
       ('ROLE_SELLER');

INSERT into person__role (role_id, person_id)
values (1, 1),
       (2, 2),
       (3, 3);

insert into test.public.company(admin_id, name, description)
values (3, 'c_name1', 'desc1');

insert into test.public.item(company_id, name, description)
values (1, 'i_name1', 'desc1');