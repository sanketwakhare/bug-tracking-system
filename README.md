# Bug Tracking System

### Requirements

	1. Multiple Users can login at a same time
	2. There will be Developers, Managers, Testers, Admin
	3. There will be Publically accessible pages
		1. Login Page (Users will be created by Admin on direction from Manager)
	4. There are following types of users
		1. Admin
		2. Developers
		3. Managers
		4. Testers
	5. Public Users shoud be allowed following operation
		1. Login Page
	6. Managers can perform following 
		1. Create a project
			A project will have following attributes
			1. Project ID
			2. Name
			3. Description
			4. Start Date
			5. Closed Date
			6. It will have all the bugs under it
		2. Assign a Developer to project
		3. Assign a Tester to project
		4. Track Status of Bugs in a project
		5. View All projects with Status of bugs
		6. Find the total number of bugs Fixed by a Developer in all Projects during specified duration
		7. Find the total Number of Bugs Approved by a tester in all Projects during specified duration
		6. Close a Project
	7. Tester 
		1. Tester Dashboard
			1. View all the assigned projects
			2. Open a Selected project for editing
		2. tester can perform following
			1. A Tester can only view/update the projects assigned
			2. Create a Bug under project
				A Bug will have following attributes
				1. Bug ID
				2. Bug will be under a project
				3. Bug Description
				4. Bug Creation Date
				5. Bug Closed Date
				6. Bug Status
			3. Assign a bug to a developer 
			4. Delete a Bug
			5. Update a Bug
			6. Change the status of bug from Fixed to Approved
	8. Developers can perform following
		1. Developer Dashboard
			1. View all the assigned projects
			2. Open a Selected project for editing
		2. A developer can only view/update the projects assigned
		3. A developer can change the status of bugs
		4. Change status of bug to Fixed. A developer can only change the status to Fixed
	9. Admin Operations
		1. Admin Dasboard with following features
			1. Create a Developer account
			2. Create a tester Account
			3. Create a Manager Account
			4. Display Count of ongoing projects
			5. Display the Count of completed projects 
		2. View list of all Developers
		3. View list of all Testers
		4. View list of all Managers
	10. Create GUI in HTML, Javascript and CSS
	11. Backend should be done using Spring Boot
	12. Spring Security should be used for authenticating users. role based acces should 
	13. Use MySQL as a backend database to store users and tasks information

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
