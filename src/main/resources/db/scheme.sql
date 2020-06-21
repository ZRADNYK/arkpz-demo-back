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
    entry_time                   datetime,
    is_sicking                   bit,
    out_time                     datetime,
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

insert into building (address, area, enters_amount, max_customers_allowed, name, user_id, workers_amount,
                      building_id)
values ('pr. Nauky, 70', 40, 2, 10, 'AromaKava', 3, 4, 3);
insert into building (address, area, enters_amount, max_customers_allowed, name, user_id, workers_amount,
                      building_id)
values ('pr. Peremohy, 20', 10, 1, 1, 'AromaKava', 3, 1, 4);
insert into building (address, area, enters_amount, max_customers_allowed, name, user_id, workers_amount,
                      building_id)
values ('str. Romena Rollana, 16', 40, 2, 10, 'AromaKava', 3, 2, 5);
insert into building (address, area, enters_amount, max_customers_allowed, name, user_id, workers_amount,
                      building_id)
values ('str. Poltavskii Shliah, 140', 70, 3, 15, 'AromaKava', 3, 4, 6);
insert into building (address, area, enters_amount, max_customers_allowed, name, user_id, workers_amount,
                      building_id)
values ('nab. Kharkivska, 7/9', 140, 3, 14, 'Antikafe 7/9', 4, 4, 7);
insert into building (address, area, enters_amount, max_customers_allowed, name, user_id, workers_amount,
                      building_id)
values ('str. Beketova, 17', 35, 2, 10, 'Oblomov', 4, 3, 8);
insert into building (address, area, enters_amount, max_customers_allowed, name, user_id, workers_amount,
                      building_id)
values ('str. Poltavskii Shliah, 155', 45, 2, 12, 'McDonalds', 4, 6, 9);
insert into building (address, area, enters_amount, max_customers_allowed, name, user_id, workers_amount,
                      building_id)
values ('str. Nauky, 41', 45, 2, 12, 'McDonalds', 5, 6, 10);
insert into building (address, area, enters_amount, max_customers_allowed, name, user_id, workers_amount,
                      building_id)
values ('str. Beketova, 117', 45, 2, 12, 'McDonalds', 5, 6, 11);
insert into building (address, area, enters_amount, max_customers_allowed, name, user_id, workers_amount,
                      building_id)
values ('str. Romena Rollana, 16', 45, 2, 12, 'McDonalds', 5, 6, 12);
insert into building (address, area, enters_amount, max_customers_allowed, name, user_id, workers_amount,
                      building_id)
values ('sq. Povstannia, 16', 45, 2, 12, 'Kulynychy', 6, 6, 13);
insert into building (address, area, enters_amount, max_customers_allowed, name, user_id, workers_amount,
                      building_id)
values ('sq. Zakhysnykiv Ukrayiny, 18', 45, 2, 12, 'Kulynychy', 6, 6, 14);
insert into building (address, area, enters_amount, max_customers_allowed, name, user_id, workers_amount,
                      building_id)
values ('pr. Metrobudyvnykicv, 198', 45, 2, 12, 'Kulynychy', 6, 6, 15);


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
values (1, 0.1, 0.0, 'There is no infectious threat in this building. You can slightly weaken antiviral measures',
        'Інфекційна загроза у даному закладі відсутня. Можливе послаблення противірусних заходів');
insert into recommendation
values (2, 0.2, 0.1,
        'There is a slight infectious threat in this building. Take care of the social distance between customers and the make sure that employees are wearing protective masks and gloves.',
        'Існує незначна інфекційна загроза. Подбайте про соціальну дістанцію між клієнтами та наявність захисних масок та перчаток у працівників.');
insert into recommendation
values (3, 0.9, 0.2,
        'There is an infectious threat in this building. You need to clean building regularly using. Carefully monitor temperature and possible disease symptoms of your employees',
        'Присутня інфекційна загроза. Необхідні регулярні вологі прибирання з дезінфікуючими засобами. Уважно слідкуйте за самопочуттям працівників вашого закладу');
insert into recommendation
values (4, 100, 0.9,
        'There is significant infectious threat in this building. You need to close it immediately, clean it using disinfectants and let your employees move to self-isolation.',
        'Значна інфекційна загроза. Необхідно негайно зачинити приміщення для проведення заходів з дезинфекції та помістити його працівників у режим обсервації.');

