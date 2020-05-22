create table building (building_id bigint not null, address varchar(255), area double precision, enters_amount smallint, max_customers_allowed smallint, name varchar(255), workers_amount smallint, owner_user_id bigint, primary key (building_id)) engine=InnoDB;
 create table building_customers (building_building_id bigint not null, customers_customer_id bigint not null, primary key (building_building_id, customers_customer_id)) engine=InnoDB;
 create table building_histories (building_building_id bigint not null, histories_history_id bigint not null, primary key (building_building_id, histories_history_id)) engine=InnoDB;
 create table building_workers (building_building_id bigint not null, workers_worker_id bigint not null, primary key (building_building_id, workers_worker_id)) engine=InnoDB;
 create table customer (customer_id bigint not null, entry_time time, is_sicking bit, out_time time, temperature double precision, current_building_building_id bigint, primary key (customer_id)) engine=InnoDB;
 create table hibernate_sequence (next_val bigint) engine=InnoDB;
 insert into hibernate_sequence values ( 1 );
 insert into hibernate_sequence values ( 1 );
 insert into hibernate_sequence values ( 1 );
 insert into hibernate_sequence values ( 1 );
 insert into hibernate_sequence values ( 1 );
 insert into hibernate_sequence values ( 1 );
 create table history (history_id bigint not null, customers_inside_amount integer not null, measuring_date date, sicking_customers_inside_amount integer not null, sicking_workers_inside_amount integer not null, workers_inside_amount integer not null, building_building_id bigint, primary key (history_id)) engine=InnoDB;
 create table recommendation (recommendation_id bigint not null, maximal_density double precision, minimal_density double precision, text varchar(1024), type varchar(255), primary key (recommendation_id)) engine=InnoDB;
 create table user_role (user_id bigint not null, roles varchar(255)) engine=InnoDB;
 create table usr (user_id bigint not null, birth_date date, email varchar(100) not null, first_name varchar(45), is_account_non_expired bit, is_account_non_locked bit, is_credentials_non_expired bit, is_enabled bit, last_name varchar(45), password varchar(450) not null, token varchar(255), token_expiration_date datetime(6), primary key (user_id)) engine=InnoDB;
 create table worker (worker_id bigint not null, entry_time time, is_sicking bit, out_time time, temperature double precision, current_building_building_id bigint, primary key (worker_id)) engine=InnoDB;
 alter table building_customers add constraint UK_9718gyy5b97jv7gqr9t1oedp9 unique (customers_customer_id);
 alter table building_histories add constraint UK_sxaha6n9rv5oc21hv6pknh70a unique (histories_history_id);
 alter table building_workers add constraint UK_2echy5xm0bdkr7yvw1x3o2q8e unique (workers_worker_id);
 alter table usr add constraint UK_g9l96r670qkidthshajdtxrqf unique (email);
 alter table building add constraint FK42cs4ypy3a9kqg56mojk0hlc3 foreign key (owner_user_id) references usr (user_id);
 alter table building_customers add constraint FK9afvsw9al8wfjwynp23ia2chq foreign key (customers_customer_id) references customer (customer_id);
 alter table building_customers add constraint FKee982quf8e3t4mxdxm7w9bcmj foreign key (building_building_id) references building (building_id);
 alter table building_histories add constraint FKrjqhrl9kunbhghmjkm5shrwib foreign key (histories_history_id) references history (history_id);
 alter table building_histories add constraint FKquhpx2t35mb8afwce3vmckdk4 foreign key (building_building_id) references building (building_id);
 alter table building_workers add constraint FKns909k5ayk2hwy79a284gnvkl foreign key (workers_worker_id) references worker (worker_id);
 alter table building_workers add constraint FKpm5e01y4emc0ox1yhi8tsmric foreign key (building_building_id) references building (building_id);
 alter table customer add constraint FK9n0gu43tsogbdvtmxb8tf3so2 foreign key (current_building_building_id) references building (building_id);
 alter table history add constraint FKkf0wiix150vey2b81krij1gh foreign key (building_building_id) references building (building_id);
 alter table user_role add constraint FKfpm8swft53ulq2hl11yplpr5 foreign key (user_id) references usr (user_id);
 alter table worker add constraint FK3jqdk8uehqd89xmumkn9kwljh foreign key (current_building_building_id) references building (building_id);

insert into usr (birth_date, email, first_name, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, last_name, password, token, token_expiration_date, user_id) values ("1991-12-12", "superadmin@ukr.net", "Superadmin", true,  true, true, true, "Superadminenko", "$2a$10$/rrIE1YV9BJjiMOG8yJa5OaLpFsbzNqBYPzxuNUMuJyliJf.t.OwG", null, null, 1);
insert into usr (birth_date, email, first_name, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, last_name, password, token, token_expiration_date, user_id) values ("1996-04-18", "admin@ukr.net", "Admin1", true,  true, true, true, "Adminenko1", "$2a$10$/rrIE1YV9BJjiMOG8yJa5OaLpFsbzNqBYPzxuNUMuJyliJf.t.OwG", null, null, 2);
insert into usr (birth_date, email, first_name, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, last_name, password, token, token_expiration_date, user_id) values ("1999-12-12", "adminych@ukr.net", "Admin2", true,  true, true, true, "Adminenko2", "$2a$10$/rrIE1YV9BJjiMOG8yJa5OaLpFsbzNqBYPzxuNUMuJyliJf.t.OwG", null, null, 3);
insert into usr (birth_date, email, first_name, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, last_name, password, token, token_expiration_date, user_id) values ("1986-12-12", "adminyara@ukr.net", "Admin3", true,  true, true, true, "Adminenko3", "$2a$10$/rrIE1YV9BJjiMOG8yJa5OaLpFsbzNqBYPzxuNUMuJyliJf.t.OwG", null, null, 4);
insert into usr (birth_date, email, first_name, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, last_name, password, token, token_expiration_date, user_id) values ("1956-12-12", "tester@ukr.net", "Tester1", true,  true, true, true, "Testeryuk", "$2a$10$/rrIE1YV9BJjiMOG8yJa5OaLpFsbzNqBYPzxuNUMuJyliJf.t.OwG", null, null, 5);
insert into usr (birth_date, email, first_name, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, last_name, password, token, token_expiration_date, user_id) values ("1992-12-12", "user@ukr.net", "User1", true,  true, true, true, "Userenko", "$2a$10$/rrIE1YV9BJjiMOG8yJa5OaLpFsbzNqBYPzxuNUMuJyliJf.t.OwG", null, null, 6);
insert into usr (birth_date, email, first_name, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, last_name, password, token, token_expiration_date, user_id) values ("1998-12-12", "user2@ukr.net", "User2", true,  true, true, true, "Userenisyan", "$2a$10$/rrIE1YV9BJjiMOG8yJa5OaLpFsbzNqBYPzxuNUMuJyliJf.t.OwG", null, null, 7);
insert into usr (birth_date, email, first_name, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, last_name, password, token, token_expiration_date, user_id) values ("2000-12-12", "user3@ukr.net", "User3", true,  true, true, true, "Useryak", "$2a$10$/rrIE1YV9BJjiMOG8yJa5OaLpFsbzNqBYPzxuNUMuJyliJf.t.OwG", null, null, 8);
insert into usr (birth_date, email, first_name, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, last_name, password, token, token_expiration_date, user_id) values ("1981-12-12", "user4@ukr.net", "User4", true,  true, true, true, "UserFour", "$2a$10$/rrIE1YV9BJjiMOG8yJa5OaLpFsbzNqBYPzxuNUMuJyliJf.t.OwG", null, null, 9);

insert into user_role (user_id, roles) values (1, "SUPERADMIN");
insert into user_role (user_id, roles) values (2, "ADMIN");
insert into user_role (user_id, roles) values (3, "ADMIN");
insert into user_role (user_id, roles) values (4, "ADMIN");
insert into user_role (user_id, roles) values (5, "USER");
insert into user_role (user_id, roles) values (6, "USER");
insert into user_role (user_id, roles) values (7, "USER");
insert into user_role (user_id, roles) values (8, "USER");
insert into user_role (user_id, roles) values (9, "USER");

insert into building (address, area, enters_amount, max_customers_allowed, name, owner_user_id, workers_amount, building_id) values ("pr. Nauky, 70", 40, 2, 10, "AromaKava", 1, 4, 1);
insert into building (address, area, enters_amount, max_customers_allowed, name, owner_user_id, workers_amount, building_id) values ("pr. Peremohy, 20", 10, 1, 1, "AromaKava", 1, 1, 2);
insert into building (address, area, enters_amount, max_customers_allowed, name, owner_user_id, workers_amount, building_id) values ("str. Romena Rollana, 16", 40, 2, 10, "AromaKava", 1, 2, 3);
insert into building (address, area, enters_amount, max_customers_allowed, name, owner_user_id, workers_amount, building_id) values ("str. Poltavskii Shliah, 140", 70, 3, 15, "AromaKava", 1, 4, 4);
insert into building (address, area, enters_amount, max_customers_allowed, name, owner_user_id, workers_amount, building_id) values ("nab. Kharkiv'ska, 7/9", 140, 3, 14, "Antikafe 7/9", 2, 4, 5);
insert into building (address, area, enters_amount, max_customers_allowed, name, owner_user_id, workers_amount, building_id) values ("str. Beketova, 17", 35, 2, 10, "Oblomov", 2, 3, 6);
insert into building (address, area, enters_amount, max_customers_allowed, name, owner_user_id, workers_amount, building_id) values ("str. Poltavskii Shliah, 155", 45, 2, 12, "McDonalds", 3, 6, 7);
insert into building (address, area, enters_amount, max_customers_allowed, name, owner_user_id, workers_amount, building_id) values ("str. Nauky, 41", 45, 2, 12, "McDonalds", 3, 6, 8);
insert into building (address, area, enters_amount, max_customers_allowed, name, owner_user_id, workers_amount, building_id) values ("str. Beketova, 117", 45, 2, 12, "McDonalds", 3, 6, 9);
insert into building (address, area, enters_amount, max_customers_allowed, name, owner_user_id, workers_amount, building_id) values ("str. Romena Rollana, 16", 45, 2, 12, "McDonalds", 3, 6, 10);
insert into building (address, area, enters_amount, max_customers_allowed, name, owner_user_id, workers_amount, building_id) values ("sq. Povstannia, 16", 45, 2, 12, "Kulynychy", 6, 6, 10);
insert into building (address, area, enters_amount, max_customers_allowed, name, owner_user_id, workers_amount, building_id) values ("sq. Zakhysnykiv Ukrayiny, 18", 45, 2, 12, "Kulynychy", 6, 6, 11);
insert into building (address, area, enters_amount, max_customers_allowed, name, owner_user_id, workers_amount, building_id) values ("pr. Metrobudyvnykicv, 198", 45, 2, 12, "Kulynychy", 6, 6, 12);

