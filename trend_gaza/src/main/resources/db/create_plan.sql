create table if not exists enjoytrip.plan(
	plan_idx int primary key auto_increment,
    start_date timestamp not null,
    end_date timestamp not null,
    user_id varchar(20) not null,
    foreign key (user_id) references user(user_id)
);

create table if not exists enjoytrip.user_plan(
	user_plan_id int primary key auto_increment,
    user_id varchar(20) not null,
    plan_idx int not null,
    foreign key(user_id) references user(user_id),
    foreign key(plan_idx) references plan(plan_idx)
);

create table if not exists enjoytrip.attraction_plan(
	attraction_plan_id int primary key auto_increment,
    attraction json,
    plan_idx int not null,
    foreign key(plan_idx) references plan(plan_idx)
);