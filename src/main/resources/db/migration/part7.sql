alter table tab_user add column salt varchar(8);
alter table tab_user modify column password varchar(128);