create database minigame;

use minigame;

create table login
(
	acc varchar(100),
    pass varchar(100),
    primary key (acc)
);

Insert into login(acc,pass) values ('nhom1','1'), ('nhat','1'),('dong','2'),  ('hoang','3'),  ('long','4') ;
create table category
(
	id int auto_increment,
    catename varchar(100),
    primary key (id)
);
Insert into category(catename) values ('Math Topic'), ('Geographical Topics'),('Football Topic');

create table question
(
	ID int auto_increment,
    Ques varchar(900),
    Ans varchar(900),
	IDcategory int,
    
    primary key (ID),
    foreign key (IDcategory) references category (ID)
);
create table Mask
(
	ID int auto_increment,
    userr varchar (100),
    mark int,
    primary key(ID),
    foreign key (userr) references login(acc)
)
select * from login;
select * from category;
select * from question;
update Mask set mark = 0 where userr = 'nhom1';
insert into question (Ques, Ans, IDcategory) values ('Câu 1 1 + 1 = ?','2',1),('Câu 2 6 / 2 * ( 2 + 1 ) = ?','9',1),('Câu 3 2 * 2 = ?','4',1),('Câu 4 10 * 10 = ?','100',1),('Câu 5 100 / 2 = ?','50',1),('Câu 6 1000 / 20 = ?','50',1);
insert into question (Ques, Ans, IDcategory) values ('Câu 1 Thủ đô Việt Nam?','Hà Nội',2), ('Câu 2 Thủ đô Trung Quốc?','Bắc Kinh',2),('Câu 3 Thủ đô Italia?','Rome',2),('Câu 4 Thủ đô Đức?','Berlin',2),('Câu 5 Thủ đô Pháp?','Paris',2);

insert into question (Ques, Ans, IDcategory) values ('Câu 1 Một đội bóng có bao nhiêu cầu thủ?','11',3),('Câu 2 Thời gian nghỉ giữa 2 hiệp là bao nhiêu phút?','15',3),('Câu 3 Đội bóng nào vô địch ngoại hạng anh đầu tiên?','MU',3),('Câu 4 Có bao nhiêu CLB thi đấu trong mùa giải Ngoại hạng Anh đầu tiên?','22',3), ('Câu 5 Đội tuyển nào vô địch World Cup nhiều lần nhất?','Brazil',3),('Câu 6 Đội tuyển nào có 3 lần vào chung kết, nhưng đều thất bại?','Hà Lan',3);
insert into mask (userr, mark) values ('nhat',10),('nhom1',0);
insert into mask (userr, mark) values ('dong',5),('long',2);

