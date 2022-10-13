create table users
(
    id                   character varying(64)  not null primary key default uuid_generate_v4(),
    username             character varying(100) not null unique,
    password             character varying(255) not null unique,
    email                character varying(50)  not null,
    is_active            boolean                not null             default false,
    is_keep_active       boolean                not null             default false,
    is_locked            boolean                not null             default false,
    is_sudo              boolean                not null             default false,
    login_failed_counter int                    not null             default 0,
    created_by           character varying(100) not null,
    created_date         timestamp              not null             default now(),
    last_update_by       character varying(1000),
    last_update_date     timestamp
);
