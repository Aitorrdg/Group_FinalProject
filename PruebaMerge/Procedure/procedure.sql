DELIMITER $$

DROP PROCEDURE IF EXISTS `cleaning_service`.`generate_service_primary_key` $$
CREATE PROCEDURE `cleaning_service`.`generate_service_primary_key` (in cod_worker varchar(9), in price double,in finished boolean,out code_service varchar(4))
BEGIN
  declare counter integer default 0;
  declare code varchar(4)default"";
  select count(cod_service) from service into counter;
  if counter>=10 then
    set code=concat('SR',counter+1);
  else
    set code=concat('SR',0,counter+1);
  end if;
  insert into service values(code,cod_worker,price,finished);
  set code_service=code;
END $$

DELIMITER ;

