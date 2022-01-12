create sequence address_sequence start 1 increment 1;
create sequence child_sequence start 1 increment 1;
create sequence district_sequence start 1 increment 1;
create sequence ed_inst_sequence start 1 increment 1;
create sequence parent_sequence start 1 increment 1;

create table address (
    id int8 not null,
    title TEXT not null,
    district_id int8,
    primary key (id)
);

create table child (
    id int8 not null,
    age int4,
    first_name TEXT not null,
    last_name TEXT not null,
    patronymic_name TEXT,
    address_id int8,
    ed_inst_id int8,
    primary key (id)
);

create table district (
    id int8 not null,
    title TEXT not null,
    primary key (id)
);

create table educational_institution (
    id int8 not null,
    number TEXT not null,
    address_id int8,
    primary key (id)
);

create table parent (
    id int8 not null,
    first_name TEXT not null,
    last_name TEXT not null,
    patronymic_name TEXT,
    primary key (id)
);

create table parent_child (
    parent_id int8 not null,
    child_id int8 not null
);

alter table if exists address
    add constraint address_title_unique unique (title);

alter table if exists district
    add constraint district_title_unique unique (title);

alter table if exists educational_institution
    add constraint ed_inst_number_unique unique (number);

alter table if exists address
    add constraint address_district_fk
    foreign key (district_id)
    references district;

alter table if exists child
    add constraint child_address_fk
    foreign key (address_id)
    references address;

alter table if exists child
    add constraint child_inst_fk
    foreign key (ed_inst_id)
    references educational_institution;

alter table if exists educational_institution
    add constraint inst_address_fk
    foreign key (address_id)
    references address;

alter table if exists parent_child
    add constraint parentchild_child_fk
    foreign key (child_id)
    references child;

alter table if exists parent_child
    add constraint parentchild_parent_fk
    foreign key (parent_id)
    references parent;
