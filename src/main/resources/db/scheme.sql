create table building
(
    building_id           bigint not null,
    address               varchar(255),
    area                  double precision,
    enters_amount         smallint,
    max_customers_allowed smallint,
    name                  varchar(255),
    workers_amount        smallint,
    user_id               bigint,
    primary key (building_id)
) engine = InnoDB;
create table building_customers
(
    building_building_id  bigint not null,
    customers_customer_id bigint not null,
    primary key (building_building_id, customers_customer_id)
) engine = InnoDB;
create table building_histories
(
    building_building_id bigint not null,
    histories_history_id bigint not null,
    primary key (building_building_id, histories_history_id)
) engine = InnoDB;
create table building_workers
(
    building_building_id bigint not null,
    workers_worker_id    bigint not null,
    primary key (building_building_id, workers_worker_id)
) engine = InnoDB;
create table customer
(
    customer_id                  bigint not null,
    entry_time                   time,
    is_sicking                   bit,
    out_time                     time,
    temperature                  double precision,
    current_building_building_id bigint,
    primary key (customer_id)
) engine = InnoDB;
create table hibernate_sequence
(
    next_val bigint
) engine = InnoDB;
insert into hibernate_sequence
values (1);
insert into hibernate_sequence
values (1);
insert into hibernate_sequence
values (1);
insert into hibernate_sequence
values (1);
insert into hibernate_sequence
values (1);
insert into hibernate_sequence
values (1);
create table history
(
    history_id                      bigint  not null,
    customers_inside_amount         integer not null,
    measuring_date                  date,
    sicking_customers_inside_amount integer not null,
    sicking_workers_inside_amount   integer not null,
    workers_inside_amount           integer not null,
    building_building_id            bigint,
    primary key (history_id)
) engine = InnoDB;
create table recommendation
(
    recommendation_id bigint not null,
    maximal_density   double precision,
    minimal_density   double precision,
    text_english      varchar(10240),
    text_ukrainian    varchar(10240),
    primary key (recommendation_id)
) engine = InnoDB;
ALTER TABLE recommendation
    CONVERT TO CHARACTER SET utf8 COLLATE utf8_general_ci;
ALTER TABLE recommendation
    DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
create table user_role
(
    user_id bigint not null,
    roles   varchar(255)
) engine = InnoDB;
create table usr
(
    user_id                    bigint       not null,
    birth_date                 date,
    email                      varchar(100) not null,
    first_name                 varchar(45),
    is_account_non_expired     bit,
    is_account_non_locked      bit,
    is_credentials_non_expired bit,
    is_enabled                 bit,
    last_name                  varchar(45),
    password                   varchar(450) not null,
    token                      varchar(255),
    token_expiration_date      datetime(6),
    primary key (user_id)
) engine = InnoDB;
create table worker
(
    worker_id                    bigint not null,
    entry_time                   time,
    is_sicking                   bit,
    out_time                     time,
    temperature                  double precision,
    current_building_building_id bigint,
    primary key (worker_id)
) engine = InnoDB;
alter table building_customers
    add constraint UK_9718gyy5b97jv7gqr9t1oedp9 unique (customers_customer_id);
alter table building_histories
    add constraint UK_sxaha6n9rv5oc21hv6pknh70a unique (histories_history_id);
alter table building_workers
    add constraint UK_2echy5xm0bdkr7yvw1x3o2q8e unique (workers_worker_id);
alter table usr
    add constraint UK_g9l96r670qkidthshajdtxrqf unique (email);
alter table building
    add constraint FK42cs4ypy3a9kqg56mojk0hlc3 foreign key (user_id) references usr (user_id);
alter table building_customers
    add constraint FK9afvsw9al8wfjwynp23ia2chq foreign key (customers_customer_id) references customer (customer_id);
alter table building_customers
    add constraint FKee982quf8e3t4mxdxm7w9bcmj foreign key (building_building_id) references building (building_id);
alter table building_histories
    add constraint FKrjqhrl9kunbhghmjkm5shrwib foreign key (histories_history_id) references history (history_id);
alter table building_histories
    add constraint FKquhpx2t35mb8afwce3vmckdk4 foreign key (building_building_id) references building (building_id);
alter table building_workers
    add constraint FKns909k5ayk2hwy79a284gnvkl foreign key (workers_worker_id) references worker (worker_id);
alter table building_workers
    add constraint FKpm5e01y4emc0ox1yhi8tsmric foreign key (building_building_id) references building (building_id);
alter table customer
    add constraint FK9n0gu43tsogbdvtmxb8tf3so2 foreign key (current_building_building_id) references building (building_id);
alter table history
    add constraint FKkf0wiix150vey2b81krij1gh foreign key (building_building_id) references building (building_id);
alter table user_role
    add constraint FKfpm8swft53ulq2hl11yplpr5 foreign key (user_id) references usr (user_id);
alter table worker
    add constraint FK3jqdk8uehqd89xmumkn9kwljh foreign key (current_building_building_id) references building (building_id);

insert into usr (birth_date, email, first_name, is_account_non_expired, is_account_non_locked,
                 is_credentials_non_expired, is_enabled, last_name, password, token, token_expiration_date, user_id)
values ('1991-12-12', 'superadmin@ukr.net', 'Superadmin', true, true, true, true, 'Superadminenko',
        '$2a$10$/rrIE1YV9BJjiMOG8yJa5OaLpFsbzNqBYPzxuNUMuJyliJf.t.OwG', null, null, 3);
insert into usr (birth_date, email, first_name, is_account_non_expired, is_account_non_locked,
                 is_credentials_non_expired, is_enabled, last_name, password, token, token_expiration_date, user_id)
values ('1996-04-18', 'admin@ukr.net', 'Admin1', true, true, true, true, 'Adminenko1',
        '$2a$10$/rrIE1YV9BJjiMOG8yJa5OaLpFsbzNqBYPzxuNUMuJyliJf.t.OwG', null, null, 4);
insert into usr (birth_date, email, first_name, is_account_non_expired, is_account_non_locked,
                 is_credentials_non_expired, is_enabled, last_name, password, token, token_expiration_date, user_id)
values ('1999-12-12', 'adminych@ukr.net', 'Admin2', true, true, true, true, 'Adminenko2',
        '$2a$10$/rrIE1YV9BJjiMOG8yJa5OaLpFsbzNqBYPzxuNUMuJyliJf.t.OwG', null, null, 5);
insert into usr (birth_date, email, first_name, is_account_non_expired, is_account_non_locked,
                 is_credentials_non_expired, is_enabled, last_name, password, token, token_expiration_date, user_id)
values ('1986-12-12', 'adminyara@ukr.net', 'Admin3', true, true, true, true, 'Adminenko3',
        '$2a$10$/rrIE1YV9BJjiMOG8yJa5OaLpFsbzNqBYPzxuNUMuJyliJf.t.OwG', null, null, 6);
insert into usr (birth_date, email, first_name, is_account_non_expired, is_account_non_locked,
                 is_credentials_non_expired, is_enabled, last_name, password, token, token_expiration_date, user_id)
values ('1956-12-12', 'tester@ukr.net', 'Tester1', true, true, true, true, 'Testeryuk',
        '$2a$10$/rrIE1YV9BJjiMOG8yJa5OaLpFsbzNqBYPzxuNUMuJyliJf.t.OwG', null, null, 7);
insert into usr (birth_date, email, first_name, is_account_non_expired, is_account_non_locked,
                 is_credentials_non_expired, is_enabled, last_name, password, token, token_expiration_date, user_id)
values ('1992-12-12', 'user@ukr.net', 'User1', true, true, true, true, 'Userenko',
        '$2a$10$/rrIE1YV9BJjiMOG8yJa5OaLpFsbzNqBYPzxuNUMuJyliJf.t.OwG', null, null, 8);
insert into usr (birth_date, email, first_name, is_account_non_expired, is_account_non_locked,
                 is_credentials_non_expired, is_enabled, last_name, password, token, token_expiration_date, user_id)
values ('1998-12-12', 'user2@ukr.net', 'User2', true, true, true, true, 'Userenisyan',
        '$2a$10$/rrIE1YV9BJjiMOG8yJa5OaLpFsbzNqBYPzxuNUMuJyliJf.t.OwG', null, null, 9);
insert into usr (birth_date, email, first_name, is_account_non_expired, is_account_non_locked,
                 is_credentials_non_expired, is_enabled, last_name, password, token, token_expiration_date, user_id)
values ('2000-12-12', 'user3@ukr.net', 'User3', true, true, true, true, 'Useryak',
        '$2a$10$/rrIE1YV9BJjiMOG8yJa5OaLpFsbzNqBYPzxuNUMuJyliJf.t.OwG', null, null, 10);
insert into usr (birth_date, email, first_name, is_account_non_expired, is_account_non_locked,
                 is_credentials_non_expired, is_enabled, last_name, password, token, token_expiration_date, user_id)
values ('1981-12-12', 'user4@ukr.net', 'User4', true, true, true, true, 'UserFour',
        '$2a$10$/rrIE1YV9BJjiMOG8yJa5OaLpFsbzNqBYPzxuNUMuJyliJf.t.OwG', null, null, 11);

insert into user_role (user_id, roles)
values (3, 'SUPERADMIN');
insert into user_role (user_id, roles)
values (4, 'ADMIN');
insert into user_role (user_id, roles)
values (5, 'ADMIN');
insert into user_role (user_id, roles)
values (6, 'ADMIN');
insert into user_role (user_id, roles)
values (7, 'USER');
insert into user_role (user_id, roles)
values (8, 'USER');
insert into user_role (user_id, roles)
values (9, 'USER');

insert into building (address, area, enters_amount, max_customers_allowed, name, user_user_id, workers_amount,
                      building_id)
values ('pr. Nauky, 70', 40, 2, 10, 'AromaKava', 3, 4, 3);
insert into building (address, area, enters_amount, max_customers_allowed, name, user_user_id, workers_amount,
                      building_id)
values ('pr. Peremohy, 20', 10, 1, 1, 'AromaKava', 3, 1, 4);
insert into building (address, area, enters_amount, max_customers_allowed, name, user_user_id, workers_amount,
                      building_id)
values ('str. Romena Rollana, 16', 40, 2, 10, 'AromaKava', 3, 2, 5);
insert into building (address, area, enters_amount, max_customers_allowed, name, user_user_id, workers_amount,
                      building_id)
values ('str. Poltavskii Shliah, 140', 70, 3, 15, 'AromaKava', 3, 4, 6);
insert into building (address, area, enters_amount, max_customers_allowed, name, user_user_id, workers_amount,
                      building_id)
values ('nab. Kharkivska, 7/9', 140, 3, 14, 'Antikafe 7/9', 4, 4, 7);
insert into building (address, area, enters_amount, max_customers_allowed, name, user_user_id, workers_amount,
                      building_id)
values ('str. Beketova, 17', 35, 2, 10, 'Oblomov', 4, 3, 8);
insert into building (address, area, enters_amount, max_customers_allowed, name, user_user_id, workers_amount,
                      building_id)
values ('str. Poltavskii Shliah, 155', 45, 2, 12, 'McDonalds', 4, 6, 9);
insert into building (address, area, enters_amount, max_customers_allowed, name, user_user_id, workers_amount,
                      building_id)
values ('str. Nauky, 41', 45, 2, 12, 'McDonalds', 5, 6, 10);
insert into building (address, area, enters_amount, max_customers_allowed, name, user_user_id, workers_amount,
                      building_id)
values ('str. Beketova, 117', 45, 2, 12, 'McDonalds', 5, 6, 11);
insert into building (address, area, enters_amount, max_customers_allowed, name, user_user_id, workers_amount,
                      building_id)
values ('str. Romena Rollana, 16', 45, 2, 12, 'McDonalds', 5, 6, 12);
insert into building (address, area, enters_amount, max_customers_allowed, name, user_user_id, workers_amount,
                      building_id)
values ('sq. Povstannia, 16', 45, 2, 12, 'Kulynychy', 6, 6, 13);
insert into building (address, area, enters_amount, max_customers_allowed, name, user_user_id, workers_amount,
                      building_id)
values ('sq. Zakhysnykiv Ukrayiny, 18', 45, 2, 12, 'Kulynychy', 6, 6, 14);
insert into building (address, area, enters_amount, max_customers_allowed, name, user_user_id, workers_amount,
                      building_id)
values ('pr. Metrobudyvnykicv, 198', 45, 2, 12, 'Kulynychy', 6, 6, 15);

insert into customer
values (25, '2020-11-05 18:00:00', 0, '2020-11-05 08:45:00', 37.6, 3);
insert into customer
values (26, '2020-11-05 08:05:00', 0, '2020-11-05 08:37:00', 36.6, 3);
insert into customer
values (27, '2020-11-06 08:10:00', 0, '2020-11-06 08:45:23', 36.7, 3);
insert into customer
values (28, '2020-11-06 09:00:00', 0, '2020-11-06 09:45:00', 36.9, 3);
insert into customer
values (29, '2020-11-06 11:00:23', 1, '2020-11-06 12:45:00', 37.7, 3);
insert into customer
values (30, '2020-11-06 14:00:00', 0, '2020-11-06 14:05:00', 37.2, 3);
insert into customer
values (31, '2020-11-06 18:06:00', 0, '2020-11-06 18:45:00', 36.8, 3);
insert into customer
values (32, '2020-11-06 18:04:00', 0, '2020-11-06 18:45:00', 36.6, 3);
insert into customer
values (33, '2020-11-07 18:01:00', 0, '2020-11-07 18:45:00', 36.6, 3);
insert into customer
values (34, '2020-11-07 18:00:00', 1, '2020-11-07 18:45:00', 39.0, 3);
insert into customer
values (35, '2020-11-07 19:14:45', 0, '2020-11-07 19:18:31', 36.7, 3);
insert into customer
values (36, '2020-11-07 21:00:00', 0, '2020-11-07 21:13:00', 36.6, 3);
insert into customer
values (1, '2020-11-05 08:00:00', 0, '2020-11-05 08:45:00', 36.6, 3);
insert into customer
values (2, '2020-11-05 08:05:00', 0, '2020-11-05 08:37:00', 36.6, 3);
insert into customer
values (3, '2020-11-05 08:10:00', 0, '2020-11-05 08:45:23', 36.7, 3);
insert into customer
values (4, '2020-11-05 09:00:00', 0, '2020-11-05 09:45:00', 36.9, 3);
insert into customer
values (5, '2020-11-05 11:00:23', 0, '2020-11-05 12:45:00', 36.7, 3);
insert into customer
values (6, '2020-11-05 14:00:00', 1, '2020-11-05 14:05:00', 38.2, 3);
insert into customer
values (7, '2020-11-06 18:00:00', 0, '2020-11-06 18:45:00', 36.6, 3);
insert into customer
values (8, '2020-11-06 18:00:00', 0, '2020-11-06 18:45:00', 36.6, 3);
insert into customer
values (9, '2020-11-06 18:00:00', 1, '2020-11-06 18:45:00', 38.6, 3);
insert into customer
values (10, '2020-11-07 18:00:00', 0, '2020-11-07 18:45:00', 36.7, 3);
insert into customer
values (11, '2020-11-07 19:14:45', 0, '2020-11-07 19:18:31', 36.7, 3);
insert into customer
values (12, '2020-11-08 21:00:00', 0, '2020-11-08 21:13:00', 36.6, 3);

insert into customer
values (37, '2020-11-05 18:00:00', 0, '2020-11-05 08:45:00', 36.6, 4);
insert into customer
values (38, '2020-11-06 08:05:00', 0, '2020-11-06 08:37:00', 36.6, 4);
insert into customer
values (39, '2020-11-06 08:10:00', 0, '2020-11-06 08:45:23', 36.7, 4);
insert into customer
values (40, '2020-11-06 09:00:00', 0, '2020-11-06 09:45:00', 36.9, 4);
insert into customer
values (41, '2020-11-07 11:00:23', 1, '2020-11-07 12:45:00', 38.7, 4);
insert into customer
values (42, '2020-11-07 14:00:00', 0, '2020-11-07 14:05:00', 39.2, 4);
insert into customer
values (43, '2020-11-07 18:06:00', 0, '2020-11-07 18:45:00', 36.6, 4);
insert into customer
values (44, '2020-11-07 18:04:00', 0, '2020-11-07 18:45:00', 36.6, 4);
insert into customer
values (45, '2020-11-07 18:01:00', 0, '2020-11-07 18:45:00', 36.6, 4);
insert into customer
values (46, '2020-11-07 18:00:00', 0, '2020-11-07 18:45:00', 36.7, 4);
insert into customer
values (47, '2020-11-07 19:14:45', 0, '2020-11-07 19:18:31', 36.7, 4);
insert into customer
values (48, '2020-11-07 21:00:00', 0, '2020-11-07 21:13:00', 36.6, 4);
insert into customer
values (13, '2020-11-05 18:00:00', 1, '2020-11-05 08:45:00', 37.6, 4);
insert into customer
values (14, '2020-11-05 08:05:00', 0, '2020-11-05 08:37:00', 36.6, 4);
insert into customer
values (15, '2020-11-05 08:10:00', 1, '2020-11-05 08:45:23', 36.7, 4);
insert into customer
values (16, '2020-11-05 09:00:00', 0, '2020-11-05 09:45:00', 36.9, 4);
insert into customer
values (17, '2020-11-06 11:00:23', 0, '2020-11-06 12:45:00', 36.7, 4);
insert into customer
values (18, '2020-11-06 14:00:00', 1, '2020-11-06 14:05:00', 38.2, 4);
insert into customer
values (19, '2020-11-06 18:06:00', 0, '2020-11-06 18:45:00', 36.6, 4);
insert into customer
values (20, '2020-11-06 18:04:00', 0, '2020-11-06 18:45:00', 36.6, 4);
insert into customer
values (21, '2020-11-07 18:01:00', 0, '2020-11-07 18:45:00', 36.6, 4);
insert into customer
values (22, '2020-11-07 18:00:00', 0, '2020-11-07 18:45:00', 36.7, 4);
insert into customer
values (23, '2020-11-07 19:14:45', 0, '2020-11-07 19:18:31', 36.7, 4);
insert into customer
values (24, '2020-11-07 21:00:00', 0, '2020-11-07 21:13:00', 36.6, 4);

insert into customer
values (49, '2020-11-07 18:00:00', 1, '2020-11-07 08:45:00', 37.6, 5);
insert into customer
values (50, '2020-11-07 08:05:00', 0, '2020-11-07 08:37:00', 36.6, 5);
insert into customer
values (51, '2020-11-07 08:10:00', 0, '2020-11-07 08:45:23', 36.7, 5);
insert into customer
values (52, '2020-11-07 09:00:00', 0, '2020-11-07 09:45:00', 36.9, 5);
insert into customer
values (53, '2020-11-07 11:00:23', 0, '2020-11-07 12:45:00', 36.7, 5);
insert into customer
values (54, '2020-11-07 14:00:00', 0, '2020-11-07 14:05:00', 37.2, 5);
insert into customer
values (55, '2020-11-07 18:06:00', 0, '2020-11-07 18:45:00', 36.6, 5);
insert into customer
values (56, '2020-11-08 18:04:00', 0, '2020-11-08 18:45:00', 36.6, 5);
insert into customer
values (57, '2020-11-08 18:01:00', 0, '2020-11-08 18:45:00', 36.6, 5);
insert into customer
values (58, '2020-11-08 18:00:00', 1, '2020-11-08 18:45:00', 37.7, 5);
insert into customer
values (59, '2020-11-09 19:14:45', 0, '2020-11-09 19:18:31', 36.7, 5);
insert into customer
values (60, '2020-11-09 21:00:00', 0, '2020-11-09 21:13:00', 36.6, 5);


insert into customer
values (61, '2020-11-08 18:00:00', 0, '2020-11-08 08:45:00', 36.6, 6);
insert into customer
values (62, '2020-11-08 08:05:00', 0, '2020-11-08 08:37:00', 36.6, 6);
insert into customer
values (63, '2020-11-08 08:10:00', 0, '2020-11-08 08:45:23', 36.7, 6);
insert into customer
values (64, '2020-11-08 09:00:00', 0, '2020-11-08 09:45:00', 36.9, 6);
insert into customer
values (65, '2020-11-09 11:00:23', 0, '2020-11-09 12:45:00', 36.7, 6);
insert into customer
values (66, '2020-11-09 14:00:00', 1, '2020-11-09 14:05:00', 38.2, 6);
insert into customer
values (67, '2020-11-09 18:06:00', 0, '2020-11-09 18:45:00', 36.6, 6);
insert into customer
values (68, '2020-11-09 18:04:00', 0, '2020-11-09 18:45:00', 36.6, 6);
insert into customer
values (69, '2020-11-10 18:01:00', 0, '2020-11-10 18:45:00', 36.6, 6);
insert into customer
values (70, '2020-11-10 18:00:00', 0, '2020-11-10 18:45:00', 36.7, 6);
insert into customer
values (71, '2020-11-10 19:14:45', 0, '2020-11-10 19:18:31', 36.7, 6);
insert into customer
values (72, '2020-11-10 21:00:00', 0, '2020-11-10 21:13:00', 36.6, 6);


insert into customer
values (73, '2020-11-05 18:00:00', 0, '2020-11-05 08:45:00', 36.6, 7);
insert into customer
values (74, '2020-11-05 08:05:00', 1, '2020-11-05 08:37:00', 38.4, 7);
insert into customer
values (75, '2020-11-05 08:10:00', 0, '2020-11-05 08:45:23', 36.7, 7);
insert into customer
values (76, '2020-11-05 09:00:00', 1, '2020-11-05 09:45:00', 37.9, 7);
insert into customer
values (77, '2020-11-05 11:00:23', 0, '2020-11-05 12:45:00', 36.7, 7);
insert into customer
values (78, '2020-11-05 14:00:00', 0, '2020-11-05 14:05:00', 37.2, 7);
insert into customer
values (79, '2020-11-05 18:06:00', 0, '2020-11-05 18:45:00', 36.6, 7);
insert into customer
values (80, '2020-11-05 18:04:00', 0, '2020-11-05 18:45:00', 36.6, 7);
insert into customer
values (81, '2020-11-05 18:01:00', 0, '2020-11-05 18:45:00', 36.6, 7);
insert into customer
values (82, '2020-11-05 18:00:00', 0, '2020-11-05 18:45:00', 36.7, 7);
insert into customer
values (83, '2020-11-05 19:14:45', 0, '2020-11-05 19:18:31', 36.7, 7);
insert into customer
values (84, '2020-11-05 21:00:00', 0, '2020-11-05 21:13:00', 36.6, 7);


insert into customer
values (85, '2020-11-05 18:00:00', 0, '2020-11-05 08:45:00', 36.6, 8);
insert into customer
values (86, '2020-11-05 08:05:00', 0, '2020-11-05 08:37:00', 36.6, 8);
insert into customer
values (87, '2020-11-05 08:10:00', 0, '2020-11-05 08:45:23', 36.7, 8);
insert into customer
values (88, '2020-11-05 09:00:00', 0, '2020-11-05 09:45:00', 36.9, 8);
insert into customer
values (89, '2020-11-05 11:00:23', 1, '2020-11-05 12:45:00', 37.7, 8);
insert into customer
values (90, '2020-11-05 14:00:00', 0, '2020-11-05 14:05:00', 37.2, 8);
insert into customer
values (91, '2020-11-05 18:06:00', 0, '2020-11-05 18:45:00', 36.6, 8);
insert into customer
values (92, '2020-11-05 18:04:00', 0, '2020-11-05 18:45:00', 36.6, 8);
insert into customer
values (93, '2020-11-05 18:01:00', 1, '2020-11-05 18:45:00', 38.6, 8);
insert into customer
values (94, '2020-11-05 18:00:00', 0, '2020-11-05 18:45:00', 36.7, 8);
insert into customer
values (95, '2020-11-05 19:14:45', 0, '2020-11-05 19:18:31', 36.7, 8);
insert into customer
values (96, '2020-11-05 21:00:00', 1, '2020-11-05 21:13:00', 37.6, 8);


insert into customer
values (97, '2020-11-05 18:00:00', 0, '2020-11-05 08:45:00', 36.6, 9);
insert into customer
values (98, '2020-11-05 08:05:00', 0, '2020-11-05 08:37:00', 36.6, 9);
insert into customer
values (99, '2020-11-05 08:10:00', 0, '2020-11-05 08:45:23', 36.7, 9);
insert into customer
values (100, '2020-11-05 09:00:00', 0, '2020-11-05 09:45:00', 36.9, 9);
insert into customer
values (101, '2020-11-05 11:00:23', 0, '2020-11-05 12:45:00', 36.7, 9);
insert into customer
values (102, '2020-11-05 14:00:00', 0, '2020-11-05 14:05:00', 37.2, 9);
insert into customer
values (103, '2020-11-05 18:06:00', 0, '2020-11-05 18:45:00', 36.6, 9);
insert into customer
values (104, '2020-11-05 18:04:00', 1, '2020-11-05 18:45:00', 38.6, 9);
insert into customer
values (105, '2020-11-05 18:01:00', 0, '2020-11-05 18:45:00', 36.6, 9);
insert into customer
values (106, '2020-11-05 18:00:00', 0, '2020-11-05 18:45:00', 36.7, 9);
insert into customer
values (107, '2020-11-05 19:14:45', 0, '2020-11-05 19:18:31', 36.7, 9);
insert into customer
values (108, '2020-11-05 21:00:00', 0, '2020-11-05 21:13:00', 36.6, 9);


insert into customer
values (109, '2020-11-05 18:00:00', 1, '2020-11-05 08:45:00', 39.0, 10);
insert into customer
values (110, '2020-11-05 08:05:00', 0, '2020-11-05 08:37:00', 36.6, 10);
insert into customer
values (111, '2020-11-05 08:10:00', 0, '2020-11-05 08:45:23', 36.7, 10);
insert into customer
values (112, '2020-11-05 09:00:00', 0, '2020-11-05 09:45:00', 36.9, 10);
insert into customer
values (113, '2020-11-05 11:00:23', 0, '2020-11-05 12:45:00', 36.7, 10);
insert into customer
values (114, '2020-11-05 14:00:00', 0, '2020-11-05 14:05:00', 37.2, 10);
insert into customer
values (115, '2020-11-05 18:06:00', 0, '2020-11-05 18:45:00', 36.6, 10);
insert into customer
values (116, '2020-11-05 18:04:00', 0, '2020-11-05 18:45:00', 36.6, 10);
insert into customer
values (117, '2020-11-05 18:01:00', 0, '2020-11-05 18:45:00', 36.6, 10);
insert into customer
values (118, '2020-11-05 18:00:00', 0, '2020-11-05 18:45:00', 36.7, 10);
insert into customer
values (119, '2020-11-05 19:14:45', 0, '2020-11-05 19:18:31', 36.7, 10);
insert into customer
values (120, '2020-11-05 21:00:00', 0, '2020-11-05 21:13:00', 36.6, 10);


insert into customer
values (121, '2020-11-05 18:00:00', 0, '2020-11-05 08:45:00', 36.6, 11);
insert into customer
values (122, '2020-11-05 08:05:00', 0, '2020-11-05 08:37:00', 37.6, 11);
insert into customer
values (123, '2020-11-05 08:10:00', 0, '2020-11-05 08:45:23', 36.7, 11);
insert into customer
values (124, '2020-11-05 09:00:00', 0, '2020-11-05 09:45:00', 36.9, 11);
insert into customer
values (125, '2020-11-05 11:00:23', 0, '2020-11-05 12:45:00', 36.7, 11);
insert into customer
values (126, '2020-11-05 14:00:00', 0, '2020-11-05 14:05:00', 37.2, 11);
insert into customer
values (127, '2020-11-05 18:06:00', 0, '2020-11-05 18:45:00', 36.6, 11);
insert into customer
values (128, '2020-11-05 18:04:00', 0, '2020-11-05 18:45:00', 37.6, 11);
insert into customer
values (129, '2020-11-05 18:01:00', 0, '2020-11-05 18:45:00', 36.6, 11);
insert into customer
values (130, '2020-11-05 18:00:00', 0, '2020-11-05 18:45:00', 36.7, 11);
insert into customer
values (131, '2020-11-05 19:14:45', 0, '2020-11-05 19:18:31', 36.6, 11);
insert into customer
values (132, '2020-11-05 21:00:00', 0, '2020-11-05 21:13:00', 36.6, 11);

insert into customer
values (133, '2020-11-05 18:00:00', 0, '2020-11-05 08:45:00', 38.1, 12);
insert into customer
values (134, '2020-11-05 08:05:00', 0, '2020-11-05 08:37:00', 36.6, 12);
insert into customer
values (135, '2020-11-05 08:10:00', 0, '2020-11-05 08:45:23', 36.7, 12);
insert into customer
values (136, '2020-11-05 09:00:00', 0, '2020-11-05 09:45:00', 36.9, 12);
insert into customer
values (137, '2020-11-05 11:00:23', 0, '2020-11-05 12:45:00', 36.7, 12);
insert into customer
values (138, '2020-11-05 14:00:00', 0, '2020-11-05 14:05:00', 37.2, 12);
insert into customer
values (139, '2020-11-05 18:06:00', 0, '2020-11-05 18:45:00', 36.6, 12);
insert into customer
values (140, '2020-11-05 18:04:00', 0, '2020-11-05 18:45:00', 37.4, 12);
insert into customer
values (141, '2020-11-05 18:01:00', 0, '2020-11-05 18:45:00', 36.6, 12);
insert into customer
values (142, '2020-11-05 18:00:00', 0, '2020-11-05 18:45:00', 36.7, 12);
insert into customer
values (143, '2020-11-05 19:14:45', 0, '2020-11-05 19:18:31', 36.7, 12);
insert into customer
values (144, '2020-11-05 21:00:00', 0, '2020-11-05 21:13:00', 36.6, 12);

insert into worker
values (1, '2020-11-05 08:00:00', 0, '2020-11-05 21:13:00', 36.6, 3);
insert into worker
values (2, '2020-11-05 08:01:00', 0, '2020-11-05 21:13:00', 36.6, 3);

insert into worker
values (3, '2020-11-05 21:00:00', 0, '2020-11-06 09:13:00', 36.6, 4);
insert into worker
values (4, '2020-11-05 21:00:00', 0, '2020-11-06 09:13:00', 37.2, 4);
insert into worker
values (5, '2020-11-05 21:00:00', 0, '2020-11-07 09:13:00', 36.6, 4);

insert into worker
values (6, '2020-11-06 09:00:00', 0, '2020-11-06 21:13:00', 36.6, 5);

insert into worker
values (7, '2020-11-06 09:00:00', 0, '2020-11-06 19:13:00', 36.6, 6);
insert into worker
values (8, '2020-11-06 09:00:00', 0, '2020-11-06 19:13:00', 36.6, 6);
insert into worker
values (9, '2020-11-06 09:00:00', 1, '2020-11-06 20:13:00', 37.6, 6);

insert into worker
values (10, '2020-11-07 07:00:00', 0, '2020-11-07 19:13:00', 36.6, 7);
insert into worker
values (11, '2020-11-07 07:00:00', 0, '2020-11-07 19:13:00', 36.7, 7);
insert into worker
values (12, '2020-11-07 07:00:00', 1, '2020-11-07 18:13:00', 38.2, 7);
insert into worker
values (13, '2020-11-07 07:00:00', 0, '2020-11-07 19:13:00', 36.6, 7);

insert into worker
values (14, '2020-11-05 08:00:00', 0, '2020-11-05 20:13:00', 36.6, 8);
insert into worker
values (15, '2020-11-05 08:01:00', 0, '2020-11-05 20:13:00', 37.2, 8);
insert into worker
values (16, '2020-11-05 08:00:00', 1, '2020-11-05 20:13:00', 38.0, 8);
insert into worker
values (17, '2020-11-05 20:00:00', 0, '2020-11-06 08:13:00', 36.6, 8);
insert into worker
values (18, '2020-11-05 21:00:00', 0, '2020-11-06 08:13:00', 36.6, 8);

insert into worker
values (19, '2020-11-05 08:00:00', 0, '2020-11-05 20:13:00', 36.6, 9);
insert into worker
values (20, '2020-11-05 20:00:00', 0, '2020-11-07 08:13:00', 36.6, 9);

insert into worker
values (21, '2020-11-05 08:00:00', 0, '2020-11-05 21:13:00', 36.6, 10);
insert into worker
values (22, '2020-11-05 08:00:00', 1, '2020-11-05 21:13:00', 37.6, 10);
insert into worker
values (23, '2020-11-05 08:00:00', 0, '2020-11-05 21:13:00', 36.6, 10);

insert into worker
values (24, '2020-11-05 08:00:00', 0, '2020-11-05 21:13:00', 36.6, 11);
insert into worker
values (25, '2020-11-05 08:00:00', 1, '2020-11-05 21:13:00', 37.6, 11);
insert into worker
values (26, '2020-11-05 08:00:00', 0, '2020-11-05 21:13:00', 36.6, 11);
insert into worker
values (27, '2020-11-05 08:00:00', 1, '2020-11-05 21:13:00', 37.7, 11);
insert into worker
values (28, '2020-11-05 08:00:00', 0, '2020-11-05 21:13:00', 36.6, 11);
insert into worker
values (29, '2020-11-05 08:00:00', 1, '2020-11-05 21:13:00', 37.9, 11);
insert into worker
values (30, '2020-11-05 08:00:00', 0, '2020-11-05 21:13:00', 36.6, 11);

insert into worker
values (31, '2020-11-05 08:00:00', 0, '2020-11-05 21:13:00', 36.6, 12);
insert into worker
values (32, '2020-11-05 08:00:00', 0, '2020-11-05 21:13:00', 36.7, 12);
insert into worker
values (33, '2020-11-05 08:00:00', 0, '2020-11-05 21:13:00', 36.6, 12);
insert into worker
values (34, '2020-11-05 08:00:00', 0, '2020-11-05 21:13:00', 36.6, 12);
insert into worker
values (35, '2020-11-05 08:00:00', 0, '2020-11-05 21:13:00', 36.8, 12);
insert into worker
values (36, '2020-11-05 08:00:00', 0, '2020-11-05 21:13:00', 36.6, 12);
insert into worker
values (37, '2020-11-05 08:00:00', 1, '2020-11-05 21:13:00', 37.6, 12);

create table detector
(
    detector_id          bigint       not null,
    model                varchar(255) not null,
    position             varchar(255) not null,
    description          varchar(255) not null,
    type                 varchar(255) not null,
    building_building_id bigint,
    primary key (detector_id)
) engine = InnoDB;

insert into detector
values (1, 'AB-100', 'Main entrance', 'Temperature detector, works on 2m distance', 'temperature', 1);
insert into detector
values (2, 'AB-100', 'Main entrance', 'Temperature detector, works on 2m distance', 'temperature', 1);

insert into detector
values (3, 'AB-100', 'Main entrance', 'Temperature detector, works on 2m distance', 'temperature', 1);
insert into detector
values (4, 'AB-100', 'Main entrance', 'Temperature detector, works on 2m distance', 'temperature', 1);

insert into detector
values (5, 'AB-100', 'Main entrance', 'Temperature detector, works on 2m distance', 'temperature', 2);
insert into detector
values (6, 'AB-100', 'Main entrance', 'Temperature detector, works on 2m distance', 'temperature', 2);

insert into detector
values (7, 'AB-100', 'Main entrance', 'Temperature detector, works on 2m distance', 'temperature', 3);
insert into detector
values (8, 'AB-100', 'Main entrance', 'Temperature detector, works on 2m distance', 'temperature', 3);

insert into detector
values (9, 'AB-100', 'Main entrance', 'Temperature detector, works on 2m distance', 'temperature', 4);
insert into detector
values (10, 'AB-100', 'Main entrance', 'Temperature detector, works on 2m distance', 'temperature', 4);

insert into detector
values (11, 'AB-100', 'Main entrance', 'Temperature detector, works on 2m distance', 'temperature', 5);
insert into detector
values (12, 'AB-100', 'Main entrance', 'Temperature detector, works on 2m distance', 'temperature', 5);

insert into detector
values (13, 'AB-100', 'Main entrance', 'Temperature detector, works on 2m distance', 'temperature', 6);
insert into detector
values (14, 'AB-100', 'Main entrance', 'Temperature detector, works on 2m distance', 'temperature', 6);

insert into detector
values (15, 'AB-100', 'Main entrance', 'Temperature detector, works on 2m distance', 'temperature', 7);
insert into detector
values (16, 'AB-100', 'Main entrance', 'Temperature detector, works on 2m distance', 'temperature', 7);

insert into detector
values (17, 'AB-100', 'Main entrance', 'Temperature detector, works on 2m distance', 'temperature', 8);
insert into detector
values (18, 'AB-100', 'Main entrance', 'Temperature detector, works on 2m distance', 'temperature', 8);

insert into detector
values (19, 'AB-100', 'Main entrance', 'Temperature detector, works on 2m distance', 'temperature', 9);
insert into detector
values (20, 'AB-100', 'Main entrance', 'Temperature detector, works on 2m distance', 'temperature', 9);

insert into detector
values (21, 'AB-100', 'Main entrance', 'Temperature detector, works on 2m distance', 'temperature', 10);
insert into detector
values (22, 'AB-100', 'Main entrance', 'Temperature detector, works on 2m distance', 'temperature', 10);

insert into detector
values (23, 'AB-100', 'Main entrance', 'Temperature detector, works on 2m distance', 'temperature', 11);
insert into detector
values (24, 'AB-100', 'Main entrance', 'Temperature detector, works on 2m distance', 'temperature', 11);

insert into detector
values (25, 'AB-100', 'Main entrance', 'Temperature detector, works on 2m distance', 'temperature', 12);
insert into detector
values (26, 'AB-100', 'Main entrance', 'Temperature detector, works on 2m distance', 'temperature', 12);

#TODO : history creates every time when user wants to get statistics. No need to create special script

insert into recommendation
values (1, 10.0, 0.0, 'There is no infectious threat in this building. You can slightly weaken antiviral measures',
        'Інфекційна загроза у даному закладі відсутня. Можливе послаблення противірусних заходів');
insert into recommendation
values (2, 20.0, 10.0,
        'There is a slight infectious threat in this building. Take care of the social distance between customers and the make sure that employees are wearing protective masks and gloves.',
        'Існує незначна інфекційна загроза. Подбайте про соціальну дістанцію між клієнтами та наявність захисних масок та перчаток у працівників.');
insert into recommendation
values (3, 40.0, 20.0,
        'There is an infectious threat in this building. You need to clean building regularly using. Carefully monitor temperature and possible disease symptoms of your employees',
        'Присутня інфекційна загроза. Необхідні регулярні вологі прибирання з дезінфікуючими засобами. Уважно слідкуйте за самопочуттям працівників вашого закладу');
insert into recommendation
values (4, 100.0, 40.0,
        'There is significant infectious threat in this building. You need to close it immediately, clean it using disinfectants and let your employees move to self-isolation.',
        'Значна інфекційна загроза. Необхідно негайно зачинити приміщення для проведення заходів з дезинфекції та помістити його працівників у режим обсервації.');

