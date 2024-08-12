create table if not exists person_entity
(
    id         int     not null,
    first_name varchar not null,
    last_name  varchar not null,
    constraint pk_person_entity primary key (id)
);

create table if not exists duplicated_entity
(
    id         int     not null,
    first_name varchar not null,
    last_name  varchar not null,
    constraint pk_duplicated primary key (id)
);

delete
from duplicated_entity
where true;

delete
from person_entity
where true;

insert into person_entity(id, first_name, last_name)
values (1, 'first', 'first_l'),
       (2, 'second', 'second_l'),
       (3, 'third', 'third_l');


