CREATE  TABLE IF NOT EXISTS user (
  id_user INT NOT NULL AUTO_INCREMENT,
  vc_name varchar(128) NOT NULL ,
  vc_lastname varchar(128) NOT NULL ,
  vc_email varchar(64) not null,
  PRIMARY KEY (`id_user`) )
ENGINE = INNODB;