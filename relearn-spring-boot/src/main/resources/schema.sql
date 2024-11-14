--Step 6
create table course
(
    id int not null,
    name varchar(255) not null,
    description varchar(255) not null,
    author varchar(255) not null,
    primary key (id)
);

insert into course(id, name, description, author)
values (1,'Learn AWS', 'Amazon Web Services', 'Amazon');
insert into course(id, name, description, author)
values (2,'Learn DevOps', 'Developer Operations', 'Github');
insert into course(id, name, description, author)
values (3,'Learn Azure', 'Microsoft Azure', 'Microsoft');
insert into course(id, name, description, author)
values (4,'Learn Figma', 'Design Figma', 'Designer');


