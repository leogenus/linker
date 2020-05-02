--{
create table l_link_status
(
    name varchar(128) not null
);

--constraints
alter table l_link_status
    add constraint l_link_status_pk primary key (name);

--comments
comment on table l_link_status is 'Belgilangan holatlar ruyhati (enum)';
--}

--{
create table l_link
(
    id            varchar(36)                   not null,
    is_deleted    boolean      default FALSE    not null,
    created_at    timestamp    default now()    not null,
    year_number   bigint                        not null,
    yearly_series bigint                        not null,
    link_status   varchar(128) default 'ACTIVE' not null,
    url           varchar(2048)                 not null,
    hash_id       varchar(64)                   not null
);

--constraints
alter table l_link
    add constraint l_link_pk primary key (id);
alter table l_link
    add constraint l_link_fk1 foreign key (link_status) references l_link_status (name);
alter table l_link
    add constraint l_link_uk1 unique (url);
alter table l_link
    add constraint l_link_uk2 unique (hash_id);

-- index
create index l_link_idx1 on l_link (year_number);
create index l_link_idx2 on l_link (hash_id);

--comments
comment on table l_link is 'Url linklar saqlanadigan jadval';
comment on column l_link.id is 'Unikal ID';
comment on column l_link.is_deleted is 'Rowning uchganligi (0/1)';
comment on column l_link.created_at is 'Yaratilish vaqti';
comment on column l_link.year_number is 'Joriy yil';
comment on column l_link.yearly_series is 'Yil bo`yicha ketma-ket qaram';
comment on column l_link.link_status is 'Holati';
comment on column l_link.url is 'Havola, ssilka, link';
comment on column l_link.hash_id is 'Hash kodi';
--}


--{
create table l_black_list
(
    id         varchar(36)             not null,
    is_deleted boolean   default FALSE not null,
    created_at timestamp default now() not null,
    updated_at timestamp,
    url        varchar(2048)           not null
);

--constraints
alter table l_black_list
    add constraint l_black_list_pk primary key (id);

--comments
comment on table l_black_list is 'Mumkin bo`lmagan url lar ruyhati';
comment on column l_black_list.id is 'Unikal ID';
comment on column l_black_list.is_deleted is 'Rowning uchganligi (0/1)';
comment on column l_black_list.created_at is 'Yaratilish vaqti';
comment on column l_black_list.updated_at is 'Yangilanish vaqti';
comment on column l_black_list.url is 'Havola, ssilka, link';
--}

--{
insert into l_link_status (name)
values ('ACTIVE');
insert into l_link_status (name)
values ('INACTIVE');
--}

