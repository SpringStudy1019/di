create table if not exists enjoytrip.file_info(
	file_idx int primary key auto_increment,
    save_file varchar(200) not null,
    review_idx int not null,
    foreign key (review_idx) references review(review_idx) on delete cascade
);

/*
ALTER TABLE file_info
DROP FOREIGN KEY file_info_ibfk_1;

ALTER TABLE file_info
ADD CONSTRAINT file_info_ibfk_1
FOREIGN KEY (review_idx) REFERENCES review(review_idx) ON DELETE CASCADE;
*/