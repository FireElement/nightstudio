set character set utf8;

drop database if exists template;

create database template DEFAULT CHARACTER SET utf8;

use template;

#token表
drop table if exists token;
create table token (
  id bigint(20) auto_increment,   #id
  token varchar(32)	not null default '', 
  user_id bigint(20) not null default 0,
  create_time timestamp not null default '1970-01-02 00:00:01',
  update_time timestamp not null default current_timestamp, 
  PRIMARY KEY  (id),          #key
  unique (token)
) engine=InnoDB default charset=utf8;

#管理员用户表#
drop table if exists `admin_user`;
create table `admin_user` (
  id bigint(20) auto_increment,    #id
  name varchar(64) not null default '',    #name
  passwd varchar(64) not null default '',    #password
  email varchar(128) not null default '',       #email
  phone varchar(32) default null,        #phone number
  create_time timestamp not null default '1970-01-02 00:00:01',
  update_time timestamp not null default current_timestamp,
  primary key(id),              #key,
  key idx_name (name),
  unique(email)
) engine=InnoDB default charset=utf8;

insert into admin_user (name, passwd, email, create_time) values ('xcao', 'xcao', 'pire_cao@163.com', now());