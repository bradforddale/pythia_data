drop table Profiles if exists;
create table Profiles(
    profile_id varchar(255) PRIMARY KEY,
    fullname varchar(255),
    cell varchar(255),
    email varchar(255)
);

insert into Profiles(profile_ID, fullname, cell, email) values('2169d99d-0ed2-4e00-a09d-a78096ff052a', 'James Aglioti II', '071318333', 'gafhjd@gmail.com');
insert into Profiles(profile_ID, fullname, cell, email) values('938edbac-f288-420b-ae6f-c6d80b5be527', 'Billy dfd', '0834445555', 'fdsfdsd@gmail.com');

drop table Awards_Achieved if exists;
create table Awards_Achieved(
     profile_id varchar(255) NOT NULL ,
    award_id numeric auto_increment PRIMARY KEY,
    description varchar(255),
    date_achieved TIMESTAMP,
    foreign key (profile_id) references Profiles(profile_id),
);

insert into Awards_Achieved(profile_id, description, date_achieved) values ('2169d99d-0ed2-4e00-a09d-a78096ff052a', 'Competent Communicator', CURRENT_TIMESTAMP());
insert into Awards_Achieved(profile_id, description, date_achieved) values ('2169d99d-0ed2-4e00-a09d-a78096ff052a', 'Competent Leadership', CURRENT_TIMESTAMP());
insert into Awards_Achieved(profile_id, description, date_achieved) values ('938edbac-f288-420b-ae6f-c6d80b5be527', 'Competent Communicator', CURRENT_TIMESTAMP());
insert into Awards_Achieved(profile_id, description, date_achieved) values ('938edbac-f288-420b-ae6f-c6d80b5be527', 'Advanced Communicator Bronze', CURRENT_TIMESTAMP());

drop table Positions if exists;
create table Positions(
     profile_id varchar(255) NOT NULL,
     position_id numeric auto_increment PRIMARY KEY,
    clubPosition varchar(255),
    club varchar(255),
    date_started TIMESTAMP,
    date_ended TIMESTAMP,
    foreign key (profile_id) references Profiles(profile_id),
);

insert into Awards_Achieved(profile_id, clubPosition, club, date_started, date_ended) values ('938edbac-f288-420b-ae6f-c6d80b5be527', 'VPE', 'Melrose Arch Toastmasters', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into Awards_Achieved(profile_id, clubPosition, club, date_started, date_ended) values ('2169d99d-0ed2-4e00-a09d-a78096ff052a', 'PRESIDENT', 'Woodlands Toastmasters', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());


