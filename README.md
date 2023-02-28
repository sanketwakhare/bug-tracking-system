# Bug Tracking System

### Entities:

#### User
	Types
	1. Admin
	2. Developers
	3. Managers
	4. Testers

	id | name | password | role

#### Project

	id | name | description | startDate | closedDate | status

#### Bug

	id | title | description | status | creationDate | closedDate | lastUpdatedDate | isDelete | project_id

---

### Relationships:

	User 1 -> M Project
	Project 1-> M User
	M <-> M

	Mapping Table
	user_id | project_id
	u1 | pr01
	u2 | pr01
	u2 | pr02

	pr01 -> [u1, u2, u3]
	pr02 -> [u2, u4]

	Project 1 -> M Bug
	Bug 1 -> 1 Project
	1 <-> M

---

### APIs:

#### user:

1. signup
{
name:
password:
role: BASE_USER		
}
2. login
{
name:
password:		
}
3. create-user (admin service)
{
name:
password:
role:
}
4. getall {
role:
}

#### project:

1. create-project
{
name
description
}
2. getproject
{
project_id
}
3. assign-project-to-user
{
project_id
user_id: []
}
4. getall
{
user_id?
status?
}
5. count
{
user_id?
status?
}
6. change-status
{
project_id
status
}
7. edit-project
{
project object
}

#### bugs:

1. create_bug
{
description
}
2. getall
{
project_ids?: []
status?
}
3. assign-bug
{
bug_id
user_id: []
}
4. change-status:
{
user_id
bug_id
status
}
