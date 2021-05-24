CREATE SCHEMA ers;

drop table IF exists ers.ers_user_roles cascade;
CREATE TABLE ers.ers_user_roles (
	ers_user_role_id int4 NOT NULL,
	user_role varchar(10) NULL,
	CONSTRAINT ers_user_roles_pk PRIMARY KEY (ers_user_role_id)
);

DROP table IF EXISTS ers.ers_reimbursement_type cascade;
CREATE TABLE ers.ers_reimbursement_type (
	reimb_type_id int4 NOT NULL,
	reimb_type varchar(10) NULL,
	CONSTRAINT ers_reimbursement_type_pk PRIMARY KEY (reimb_type_id)
);

DROP TABLE IF exists ers.ers_reimbursement_status cascade;
CREATE TABLE ers.ers_reimbursement_status (
	reimb_status_id int4 NOT NULL,
	reimb_status varchar(10) NULL,
	CONSTRAINT ers_reimbursement_status_pk PRIMARY KEY (reimb_status_id)
);

 DROP TABLE  IF exists ers.ers_users cascade;
CREATE TABLE ers.ers_users (
	ers_users_id bigserial NOT NULL,
	ers_username varchar(50) NULL,
	ers_password varchar(50) NULL,
	user_first_name varchar(100) NULL,
	user_last_name varchar(100) NULL,
	user_email varchar(150) NULL,
	user_role_id int4 NULL,
	CONSTRAINT ers_users_pk PRIMARY KEY (ers_users_id),
	CONSTRAINT ers_users_un UNIQUE (ers_username, user_email),
	CONSTRAINT ers_users_fk FOREIGN KEY (user_role_id) REFERENCES ers.ers_user_roles(ers_user_role_id)
);


 DROP TABLE  IF exists ers.ers_reimbursement;
CREATE TABLE ers.ers_reimbursement (
	reimb_id bigserial NOT NULL,
	reimb_amount numeric(10,2) NULL,
	reimb_submitted timestamp(0) NULL,
	reimb_resolved timestamp(0) NULL,
	reimb_description varchar(250) NULL,
	reimb_receipt bytea NULL,
	reimb_author int8 NULL,
	reimb_resolver int8 NULL,
	reimb_status_id int4 NULL,
	reimb_type_id int4 NULL,
	CONSTRAINT ers_reimbursement_pk PRIMARY KEY (reimb_id),
	CONSTRAINT ers_reimbursement_fk FOREIGN KEY (reimb_status_id) REFERENCES ers.ers_reimbursement_status(reimb_status_id),
	CONSTRAINT ers_reimbursement_fk_1 FOREIGN KEY (reimb_type_id) REFERENCES ers.ers_reimbursement_type(reimb_type_id),
	CONSTRAINT ers_reimbursement_fk_2 FOREIGN KEY (reimb_author) REFERENCES ers.ers_users(ers_users_id) ON DELETE CASCADE,
	CONSTRAINT ers_reimbursement_fk_3 FOREIGN KEY (reimb_resolver) REFERENCES ers.ers_users(ers_users_id) ON DELETE CASCADE
);

insert into ers.ers_reimbursement_type(reimb_type_id, reimb_type) values(1,'lodging');
insert into ers.ers_reimbursement_type(reimb_type_id, reimb_type) values(2,'travel');
insert into ers.ers_reimbursement_type(reimb_type_id, reimb_type) values(3,'food');
insert into ers.ers_reimbursement_type(reimb_type_id, reimb_type) values(4,'other');

insert into ers.ers_reimbursement_status(reimb_status_id, reimb_status) values(1,'pending');
insert into ers.ers_reimbursement_status(reimb_status_id, reimb_status) values(2,'approved');
insert into ers.ers_reimbursement_status(reimb_status_id, reimb_status) values(3,'denied');


insert into ers.ers_user_roles(ers_user_role_id, user_role) values(1,'re1');
insert into ers.ers_user_roles(ers_user_role_id, user_role) values(2,'fm1');


--populating the users table--
insert into ers.ers_users (ers_username, ers_password, user_first_name, user_last_name, user_email, user_role_id) values ('user1', 'pass', 'Jason', 'Thow', 'jthow0@google.ru', 1);
insert into ers.ers_users (ers_username, ers_password, user_first_name, user_last_name, user_email, user_role_id) values ('user2', 'pass', 'Kain', 'Spridgeon', 'kspridgeon1@youtube.com', 2);
insert into ers.ers_users (ers_username, ers_password, user_first_name, user_last_name, user_email, user_role_id) values ('mdemongeot2', 'yXEeSi4nvgM', 'Myrwyn', 'Demongeot', 'mdemongeot2@i2i.jp', 1);
insert into ers.ers_users (ers_username, ers_password, user_first_name, user_last_name, user_email, user_role_id) values ('uweaver3', '0Lx5RYChm', 'Ulysses', 'Weaver', 'uweaver3@mozilla.com', 2);
insert into ers.ers_users (ers_username, ers_password, user_first_name, user_last_name, user_email, user_role_id) values ('mzealander4', 'HpwEnQ', 'Merrilee', 'Zealander', 'mzealander4@nba.com', 1);
insert into ers.ers_users (ers_username, ers_password, user_first_name, user_last_name, user_email, user_role_id) values ('joverstall5', '9veMNcS0db', 'Jaquenette', 'Overstall', 'joverstall5@nymag.com', 1);
insert into ers.ers_users (ers_username, ers_password, user_first_name, user_last_name, user_email, user_role_id) values ('mcollabine6', '8XsWcz3O', 'Mychal', 'Collabine', 'mcollabine6@uiuc.edu', 2);
insert into ers.ers_users (ers_username, ers_password, user_first_name, user_last_name, user_email, user_role_id) values ('jroncelli7', 'Mw5en1tY', 'Jon', 'Roncelli', 'jroncelli7@elegantthemes.com', 1);
insert into ers.ers_users (ers_username, ers_password, user_first_name, user_last_name, user_email, user_role_id) values ('nelacoate8', 'Ajrj1kRqlaW', 'Natividad', 'Elacoate', 'nelacoate8@ibm.com', 1);
insert into ers.ers_users (ers_username, ers_password, user_first_name, user_last_name, user_email, user_role_id) values ('linstone9', 'MI0Y8u', 'Lilli', 'Instone', 'linstone9@lycos.com', 2);

--UPDATE ers.ers_users SET ers_password='pass',ers_username='user1' WHERE ers_users_id=1;




insert into ers.ers_reimbursement (reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_receipt, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id) 
values (369.50, '2020-10-06 19:59:29', NULL, 'Lodging decription', '13QzGk8FzdsUcRgASaYQVzWh7GbnzS3aoP', 1, NULL, 1, 1);
insert into ers.ers_reimbursement (reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_receipt, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id) 
values (37.77, '2021-02-08 06:14:23', NULL, 'Open-architected empowering circuit', '1EXHzw1Srzt5Q851RXDXRuRJVHa3hgQazX', 3, NULL, 1, 2);
insert into ers.ers_reimbursement (reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_receipt, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id) 
values (442.56, '2020-09-02 08:15:03', NULL, 'Right-sized encompassing capability', '1K3PNW18sgyvEE3mCUrve4fr7Mzq1kzNvs', 3, NULL, 1, 3);
insert into ers.ers_reimbursement (reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_receipt, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id) 
values (115.55, '2020-10-19 19:25:23', NULL, 'Future-proofed clear-thinking task-force', '16brKU5ncZyioA2gaMqhrkJngbjZ2ddX9e', 5, NULL, 1, 1);
insert into ers.ers_reimbursement (reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_receipt, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id) 
values (455.81, '2020-08-28 16:48:48', '2020-09-30 00:24:31', 'Exclusive coherent encryption', '1Js84zx7xMWNoFoRAbxuzcUMpaGs2b8akp', 6, 2, 2, 1);
insert into ers.ers_reimbursement (reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_receipt, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id) 
values (293.22, '2021-01-22 22:33:00', '2021-03-06 21:28:51', 'Universal client-driven extranet', '14xH1imomtQDDHtj1urwymk4rknVDcsszL', 6, 10, 3, 2);
insert into ers.ers_reimbursement (reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_receipt, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id) 
values (200.01, '2021-03-29 03:27:17', NULL, 'Cross-group clear-thinking open system', '1AHGnGZVL3zNA9DzkjvSrcrH38T7D92oCb', 7,NULL, 1, 3);
insert into ers.ers_reimbursement (reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_receipt, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id) 
values (63.87, '2020-05-27 08:47:35', NULL, 'Cross-group static service-desk', '1GNYhCtdtSr1wQGTe13T4JXktk8hT8xvTa', 8, NULL, 1, 4);
insert into ers.ers_reimbursement (reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_receipt, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id) 
values (399.32, '2021-03-14 21:04:06', NULL, 'Fundamental real-time local area network', '1FZRNukahh46auB29hpGzNQbmkNgcDHs74', 9, NULL, 1, 1);
insert into ers.ers_reimbursement (reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_receipt, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id) 
values (239.97, '2021-02-22 07:50:12', NULL, 'Customer-focused holistic moderator', '1PphQA77JFmNZ7foEnDcBK8sDBNchabKpY', 1, NULL, 1, 1);
insert into ers.ers_reimbursement (reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_receipt, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id) 
values (383.56, '2021-04-12 15:36:08', NULL, 'Open-source 24 hour hierarchy', '1HJGwyvSufev4673RSH2MPe3g7zScyeT83', 3, NULL, 1, 2);
insert into ers.ers_reimbursement (reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_receipt, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id) 
values (304.62, '2021-03-03 00:41:00', '2021-04-30 02:26:50', 'Focused maximized success', '1ktNpouzDEeZJrGQQCuA5MpbXgDmcDwQQ', 1, 2, 2, 2);
insert into ers.ers_reimbursement (reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_receipt, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id) 
values (314.44, '2020-12-11 00:12:05', NULL, 'Assimilated coherent extranet', '1eko9m18pLPY9x9jt46PY1JGpNQLh27Mw', 8, NULL,1, 2);
insert into ers.ers_reimbursement (reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_receipt, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id) 
values (77.44, '2020-09-03 08:55:36', NULL, 'Monitored bifurcated customer loyalty', '1CasPvhNRMJEzFvqjZLMYaji5axWf1W5Ps', 9, NULL, 1, 3);
insert into ers.ers_reimbursement (reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_receipt, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id)
values (249.09, '2020-11-19 07:39:39', '2020-06-08 13:23:06', 'Phased 3rd generation initiative', '12oxYNqcauUEBgVHwDMXFjLc8q6hAuyVA3', 3, 10, 3, 3);

