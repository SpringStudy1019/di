create table bookmark(
	bookmark_idx int primary key auto_increment,
    user_id varchar(20),
    content_id int,
    foreign key (user_id ) references user(user_id),
    foreign key (content_id) references attraction_info(content_id)
);