# Command 2A: Query: list all supplier information.
select *
from suppliers;

# Command 2B: add a new record to shipments table (S5, P6, J7, 400)
insert into shipments
values ('S5', 'P6', 'J7', 400);

#Updates by 5 the status of all suppliers with shipment greater than 100
update suppliers 
set status = status + 5 
where snum in (
				select snum 
				from shipments 
                where quantity > 99);
                
# Command 2C: list all supplier information.
select * 
from suppliers;