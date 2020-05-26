
#For setting filefolderpath
insert into file_folder_path values('D:\Upload_CV');


#For inserting atsmasterdata
insert into ats_master_table (candidate_id,candidate_contact_number, candidate_email_id, 
candidate_name, candidate_profile_status,
 recruiter_id, recruiter_name) values 
(1, 7895276611, 'j@gmail.com', 'salman','DeclineDrop','89u','Ramesh');




#For inserting candidate profile
insert into candidate_profile(candidate_id,candidate_name, 
candidate_profile_comments, 
candidate_profile_created_at, 
contact_no, current_salary_in_lpa, 

designation, dob, email_id, expected_salary_in_lpa, 
experience, 
father_name, location, notice_period_in_days, 
profile_status,
 recruiter_id, recruiter_name, 
relevant_experience, resume_storage_path, 

technical_skill, technology) 

values (1,'salman','good', '2020-03-22 12:32:11.025000', 
7895276611, 8, 'software developer', 
'1991-11-23',
 'j@gmail.com', 9, 1.5, 'Akhbar', 'dehradun', 10, 'DeclineDrop',
 '89u', 'Ramesh',
1, 'D:\recovery\67c4f6a3-2675-47e3-9ed3-df0aeac1cd10Notification-UKSSSC-Forester-Posts.pdf', 
'java','Java');
