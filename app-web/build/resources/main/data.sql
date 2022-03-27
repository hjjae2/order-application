-- insert into member(id, email, password, type, created_at, updated_at) values (1, 'admin@toy.com', '$2a$10$Qlq2KTT1A1ADuXlJ64bcu.1YBQoK.0Z7/dcXfSlx2ds02/tvZE64y', 'ADMIN', now(), now());
-- insert into member(id, email, password, type, created_at, updated_at) values (2, 'seller@toy.com', '$2a$10$Tezl/bZ.t9/xBFRcpZfI5ulPLVTeVshfpaLFFEXN.u8SmryU45W/q', 'SELLER', now(), now());
-- insert into member(id, email, password, type, created_at, updated_at) values (3, 'user@toy.com', '$2a$10$KoftBGRexCyEg5FZ4aY7J.S0Qur/uF7rX8DL93F14ztJ.j4bai3X2', 'NORMAL', now(), now());

insert into product(id, name, price, quantity, member_id, created_at, updated_at) values (1, 'A0001', 12000, 129, 2, now(), now());
insert into product(id, name, price, quantity, member_id, created_at, updated_at) values (2, 'A0001', 5700, 767, 2, now(), now());
insert into product(id, name, price, quantity, member_id, created_at, updated_at) values (3, 'A0001', 4800, 5, 2, now(), now());
insert into product(id, name, price, quantity, member_id, created_at, updated_at) values (4, 'A0002', 17000, 3, 2, now(), now());
insert into product(id, name, price, quantity, member_id, created_at, updated_at) values (5, 'A0003', 25000, 1, 2, now(), now());
insert into product(id, name, price, quantity, member_id, created_at, updated_at) values (6, 'A0004', 30000, 0, 2, now(), now());
