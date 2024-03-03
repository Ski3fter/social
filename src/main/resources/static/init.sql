CREATE DATABASE social OWNER postgres;

create table public.users
(
    id       bigint                                         not null
        primary key,
    username    varchar(32)                                  not null,
    first_name varchar(42)                   not null,
    second_name varchar(42)                   not null,
    birthdate date not null,
    biography varchar (300) not null,
    city varchar(100) not null,
    password varchar(255)                                 not null,
    email     varchar(64)                                  not null,
    role     varchar(32)                                  not null
);

alter table public.users
    owner to postgres;

create unique index users_username_uindex
    on public.users (username);



create table public.friends
(
    id          bigint       not null
        primary key,
    user_id    bigint not null,
    friend_id  bigint  not null
);

CREATE UNIQUE INDEX friend_uniq ON public.friends (user_id, friend_id);

create table public.posts
(
    id          bigint       not null
        primary key,
    user_id    bigint not null,
    text  varchar(300)  not null
);

create table public.dialog
(
    id          bigint       not null
        primary key,
    from_user_id    bigint not null,
    to_user_id bigint not null,
    text varchar(300)  not null
);

CREATE SEQUENCE user_id_seq;
CREATE SEQUENCE friend_id_seq;
CREATE SEQUENCE post_id_seq;
CREATE SEQUENCE dialog_id_seq;