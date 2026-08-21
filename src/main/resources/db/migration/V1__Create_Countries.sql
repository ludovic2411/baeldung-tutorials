CREATE TABLE countries (
    id serial primary key ,
    name varchar(255) not null unique,
    code varchar(3) not null unique
);

insert into countries(name,code) values ('Belgium','BEL');
insert into countries(name,code) values ('France','FRA');
insert into countries(name,code) values ('Italy','ITA');
insert into countries(name,code) values ('Nederlands','NED');
insert into countries(name,code) values ('Germany','GER');
insert into countries(name,code) values ('United Kingdom','UK');
insert into countries(name,code) values ('Ireland','IRL');
insert into countries(name,code) values ('Poland','POL');
insert into countries(name,code) values ('Austria','AU');
insert into countries(name,code) values ('Spain','SPA');
insert into countries(name,code) values ('Portugal','POR');
insert into countries(name,code) values ('Slovakia','SLO');
insert into countries(name,code) values ('Czech Republic','CZE');
insert into countries(name,code) values ('Hungary','HUN');
insert into countries(name,code) values ('Lettonia','LET');
insert into countries(name,code) values ('Finland','FIN');