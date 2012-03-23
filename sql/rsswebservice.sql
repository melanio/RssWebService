DROP TABLE IF EXISTS `feeditems`;
DROP TABLE IF EXISTS `feed`;
CREATE TABLE `feed` (
 `id` Bigint NOT NULL auto_increment,
 `url_string` varchar(200),
  PRIMARY KEY (`id`)
);

CREATE TABLE `feeditems` (
  `id` Bigint NOT NULL auto_increment,
  `feedid` Bigint NOT NULL,
  `title` varchar(250) default NULL,
  `link` varchar(300) default NULL,
  `content` longtext ,
  `pubdate` datetime,
   PRIMARY KEY (`id`),
   foreign key (feedid) references feed(id) 
	ON DELETE CASCADE ON UPDATE CASCADE   );