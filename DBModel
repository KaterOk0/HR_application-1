drop database if exists hr_department; 
create database hr_department default character set 'utf8'; 

use hr_department; 


create table user( 
id int not null auto_increment, 
email varchar(100) not null, 
password varchar(255) not null, 
name varchar(255) not null, 
surname varchar(255) not null, 
role varchar(255) not null, 
primary key(id) 
)engine=InnoDB; 

create table candidatestate( 
name varchar(255) not null, 
primary key(name) 
)engine=InnoDB; 

create table candidate( 
id int not null auto_increment, 
name varchar(255) not null, 
surname varchar(255) not null, 
birthday date not null, 
salary decimal(10,2) not null, 
candidateState varchar(255) not null, 
primary key(id), 
foreign key(candidateState) references candidatestate(name) 
)engine=InnoDB; 

create table skill( 
name varchar(255) not null, 
primary key(name) 
)engine=InnoDB; 

create table vacancy( 
id int not null auto_increment, 
position varchar(1000) not null, 
salaryfrom decimal(10,2) not null, 
salaryto decimal(10,2) not null, 
vacancystate enum('active','passive'), 
experienceYearRequire decimal(10,2) not null, 
idDeveloper int(11), 
primary key(id), 
foreign key(idDeveloper) references user(id) 
)engine=InnoDB; 

create table interview( 
id int not null auto_increment, 
idVacancy int(11), 
planDate date not null, 
idCandidate int(11), 
factDate date not null, 
name varchar(255) not null, 
primary key(id), 
foreign key(idVacancy) references vacancy(id), 
foreign key(idCandidate) references candidate(id) 
)engine=InnoDB; 

create table feedbackstate( 
name varchar(255) not null, 
primary key(name) 
)engine=InnoDB; 

create table interviewfeedback( 
id int not null auto_increment, 
idInterview int(11), 
idInterviewer int(11), 
feedbackState varchar(255) not null, 
reason varchar(4000) not null, 
primary key(id), 
foreign key(idInterview) references interview(id), 
foreign key(idInterviewer) references user(id), 
foreign key(feedbackState) references feedbackstate(name) 
)engine=InnoDB; 

create table candidatecompetence( 
idCandidate int(11), 
skill varchar(255), 
foreign key(idCandidate) references candidate(id), 
foreign key(skill) references skill(name) 
)engine=InnoDB; 

create table vacancyrequirement( 
idVacancy int(11), 
skill varchar(255), 
foreign key(idVacancy) references vacancy(id), 
foreign key(skill) references skill(name) 
)engine=InnoDB; 

create table suitablestate( 
name varchar(255) not null, 
primary key(name) 
)engine=InnoDB; 

create table vacancycandidates( 
idVacancy int(11), 
idCandidate int(11), 
suitableState varchar(255), 
reason varchar(1000), 
foreign key(idVacancy) references vacancy(id), 
foreign key(idCandidate) references candidate(id), 
foreign key(suitableState) references suitablestate(name) 
)engine=InnoDB; 

create table attachment( 
idCandidate int(11) not null, 
filepath varchar(1000) not null, 
--attachmentType enum(''), 
foreign key(idCandidate) references candidate(id) 
)engine=InnoDB; 

create table canditateexperience( 
idCandidate int(11) not null, 
dateForm date not null, 
dateTo date not null, 
jobDescription varchar(4000), 
jobPosition varchar(1000), 
companyName varchar(1000), 
foreign key(idCandidate) references candidate(id) 
)engine=InnoDB; 

create table contactdetails( 
idCandidate int(11) not null, 
contactType enum('telephone','email','address'), 
contactDetalis varchar(1000), 
foreign key(idCandidate) references candidate(id) 
)engine=InnoDB; 



insert into candidatestate(name) 
values('active'); 
insert into candidatestate(name) 
values('passive'); 

insert into feedbackstate(name) 
values('success'); 
insert into feedbackstate(name) 
values('failure'); 
insert into feedbackstate(name) 
values('awaiting');
