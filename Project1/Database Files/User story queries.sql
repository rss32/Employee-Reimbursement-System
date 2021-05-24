
--*task Q1----
select u.ers_users_id,u.user_first_name, u.user_last_name,u.user_email ,ur.user_role
from ers.ers_users u join ers.ers_user_roles ur on u.user_role_id = ur.ers_user_role_id
where u.ers_username = 'user1'and u.ers_password ='pass';

--*task Q2----
insert into ers.ers_reimbursement(reimb_amount, reimb_submitted,reimb_description, reimb_author,
reimb_status_id, reimb_type_id)
values(999,'5/15/2021','sample description',1,1,
(select reimb_type_id from ers.ers_reimbursement_type where reimb_type ='lodging' ));

--*task Q3----
select r.reimb_id, r.reimb_amount, r.reimb_submitted,r.reimb_description,rs.reimb_status, rt.reimb_type
 from ers.ers_reimbursement r join ers.ers_reimbursement_status rs on r.reimb_status_id =rs.reimb_status_id
 join ers.ers_reimbursement_type rt on r.reimb_type_id = rt.reimb_type_id
 where r.reimb_author = 1 and rs.reimb_status = 'pending';

--*task Q4----
select r.reimb_id, r.reimb_amount, r.reimb_submitted, r.reimb_resolved,
r.reimb_description,r.reimb_resolver,rs.reimb_status, rt.reimb_type
 from ers.ers_reimbursement r join ers.ers_reimbursement_status rs 
 on r.reimb_status_id =rs.reimb_status_id
 join ers.ers_reimbursement_type rt on r.reimb_type_id = rt.reimb_type_id
 where r.reimb_author = 1 and rs.reimb_status = 'approved';

--*task Q5--get from Q1----

--*task Q6----
update ers.ers_users set ers_password =?,user_first_name =?, user_last_name=?, user_email=?
where ers_users_id = 1;





---*task Q7--------------
update ers.ers_reimbursement set reimb_resolved = ?,reimb_resolver=? , 
reimb_status_id = (select reimb_status_id from ers.ers_reimbursement_status where reimb_status = ?) 
where reimb _id = ?;


---*task Q8--------------
select r.reimb_id, r.reimb_amount, r.reimb_submitted,r.reimb_description,rs.reimb_status, rt.reimb_type
 from ers.ers_reimbursement r join ers.ers_reimbursement_status rs on r.reimb_status_id =rs.reimb_status_id
 join ers.ers_reimbursement_type rt on r.reimb_type_id = rt.reimb_type_id
 where  rs.reimb_status = 'pending';

select u.ers_username, u.user_first_name, u.user_last_name,u.user_email ,ur.user_role from ers.ers_users u
join ers.ers_user_roles ur  on u.user_role_id = ur.ers_user_role_id where u.ers_users_id = 1;

---*task Q9--------
select  r.reimb_id, r.reimb_amount, r.reimb_submitted,r.reimb_resolved,r.reimb_description,
r.reimb_author,r.reimb_resolver,rs.reimb_status, rt.reimb_type from ers.ers_reimbursement r
join ers.ers_reimbursement_status rs on r.reimb_status_id =rs.reimb_status_id
join ers.ers_reimbursement_type rt on r.reimb_type_id =rt.reimb_type_id 
where rs.reimb_status = 'approved' or rs.reimb_status ='denied';

select u.ers_users_id u.ers_username, u.user_first_name, u.user_last_name, u.user_email, ur.user_role
from  join ers.ers_user_roles ur on = ur.ers_user_role_id;
