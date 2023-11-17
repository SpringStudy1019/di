create table if not exists enjoytrip.file_info(
	file_idx int primary key auto_increment,
    save_file varchar(200) not null,
    review_idx int not null,
    foreign key (review_idx) references review(review_idx)
);