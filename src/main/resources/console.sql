
create or replace FUNCTION find_all_vehicles() returns refcursor as
$$
declare
vehicle_cursor refcursor;
begin
open vehicle_cursor for SELECT * from vehicle;
return vehicle_cursor;
end;
$$ language plpgsql;


begin;
select find_all_vehicles();
fetch all in "<unnamed portal 1>";
end;
commit;

-- Не безопасно
create or replace FUNCTION find_all(p_db_name IN varchar(20)) returns refcursor as
$$
declare
db_cursor refcursor;
    l_sql varchar(1000);
begin
    l_sql := 'select * from ' || p_db_name;
open db_cursor for execute l_sql;
return db_cursor;
end;
$$ language plpgsql;

-- Более безопасно
create or replace FUNCTION find_all(p_db_name IN varchar(20)) returns refcursor as
$$
declare
db_cursor refcursor;
begin
open db_cursor for execute format('select * from %I', p_db_name);
return db_cursor;
end;
$$ language plpgsql;

begin;
select find_all('stop');
fetch all in "<unnamed portal 9>";
end;
commit;