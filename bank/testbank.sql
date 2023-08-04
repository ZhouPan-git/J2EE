#银行 存，取，转账 事务管理

#创建库
create database testbank;
use testbank;
#账号信息表 java的UUID-字符串-》auto_increment
create table accounts
(
    accountid int primary key auto_increment,
    balance numeric(10,2)
);
#创建流水表
create table oprecord
(
    id int primary key auto_increment,
    accountid int references accounts(accountid),
    opmoney numeric(10,2),
    optime datetime,
    optype enum('deposite','withdraw','transfer') not null,
    transferid varchar(50)
);
ALTER TABLE accounts ADD CONSTRAINT bla CHECK (balance>=0);
