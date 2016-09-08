--商品分类表01--
CREATE TABLE `classify` (
	`gid` INT NOT NULL,
	`gname` VARCHAR(255) NOT NULL,
	PRIMARY KEY ( `gid` )
);


--用户表vip
CREATE TABLE `vip` (
`username` VARCHAR(255) NOT NULL,
`userpass` VARCHAR(255) NOT NULL,
`phone` VARCHAR(255),
`address` VARCHAR(255),
`realname` VARCHAR(255),
PRIMARY KEY ( `username` )
);
DROP TABLE vip;
INSERT INTO vip(username,userpass,phone,address,realname) VALUES('ryan','justdoit','13530411702','深圳','刘刚')
