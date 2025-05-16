create database if not exists ss9;
use ss9;

drop table if exists ticket_seat;
drop table if exists ticket;
drop table if exists seat;
drop table if exists schedule;
drop table if exists screen_room;
drop table if exists movie;
drop table if exists customer;
create table customer(
                         c_id bigint auto_increment primary key,
                         c_username varchar(150) not null unique,
                         c_password varchar(16) not null,
                         c_phone varchar(10) not null unique,
                         c_address varchar(150),
                         c_gender enum('MALE', 'FEMALE', 'OTHER') not null
);

create table movie(
                      m_id bigint primary key auto_increment,
                      m_title varchar(150) not null,
                      m_director varchar(150) not null,
                      m_genre varchar(150) not null,
                      m_description varchar(255),
                      m_duration int check ( m_duration > 0 ),
                      m_language varchar(150) not null
);


create table screen_room(
                            sr_id bigint primary key auto_increment,
                            sr_name varchar(150) not null,
                            sr_total_seats int check ( sr_total_seats > 0 )
);

create table schedule(
                         sc_id bigint primary key auto_increment,
                         m_id bigint not null,
                         sc_show_time date default(curdate()),
                         sr_id bigint not null,
                         sc_available_seats int,
                         sc_format enum('TWO_D', 'THREE_D'),
                         foreign key (m_id) references movie(m_id),
                         foreign key (sr_id) references screen_room(sr_id)
);

create table seat(
    s_id bigint primary key auto_increment,
    sr_id bigint not null,
    s_price decimal(15,2) default(500000),
    s_status bit not null,
    foreign key (sr_id) references screen_room(sr_id)
);

create table ticket(
    t_id bigint primary key auto_increment,
    c_id bigint not null,
    sc_id bigint not null,
    t_total_price decimal(15,2) check ( t_total_price > 0 ),
    t_created_at date default(curdate()),
    foreign key (c_id) references customer(c_id),
    foreign key (sc_id) references schedule(sc_id)
);

create table ticket_seat(
    t_id bigint not null,
    s_id bigint not null,
    primary key(t_id, s_id),
    foreign key (t_id) references ticket(t_id),
    foreign key (s_id) references seat(s_id)
);