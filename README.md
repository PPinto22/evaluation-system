# EvaluationSystem

...
## FrontEnd - Angular 2

## Backend - Spring and Hibernate

### Build and Run Postgres

Build image of postgres for docker.
```
cd Docker/postgres/
docker build -t eg_postgresql .
```

First time create container and run with:
```
docker run --net="bridge" -p 5432:5432 -P --name pg_test eg_postgresql
```

Next times, to start container make:
```
docker start pg_test
```

### Build and Run Spring
```
cd EvaluationSystem/
mvn package
cp target/evalsys-backend-1.0.jar ../Docker/spring/
cd ../Docker/spring
docker build -t evalsys-backend .
docker run --net="bridge" -p 8080:8080 evalsys-backend
```

or

```
./docker_backend.sh
```

### Authentication
Após fazer login e receber o **token**, deve ser enviado no cabeçalho HTTP:

> Authorization = "Bearer TOKEN"

O servidor pode responder a qualquer pedido (excepto de autenticação) com um código HTTP **UNAUTHORIZED (401)**, caso o **token** seja inválido ou tenha expirado.
```json
{
 "message": "Invalid token"
}
```
```json
{
 "message": "Token expired"
}
```

### API

- [POST /auth/login](#post-authlogin)
- [POST /auth/signup](#post-authsignup)
- [GET /api/classes/{id}](#get-apiclassesid)
- [~~PUT /api/classes/{id}~~](#put-apiclassesid)
- [~~DELETE /api/classes/{id}~~](#delete-apiclassesid)
- [GET /api/classes/{class_id}/questions](#get-apiclassesclassidquestions)
- [POST /api/classes/{class_id}/questions](#post-apiclassesclassidquestions)
- [GET /api/classes/{class_id}/categories](#get-apiclassesclassidcategories)
- [GET /api/classes/{class_id}/groups](#get-apiclassesclassidgroups)
- [POST /api/classes/{class_id}/groups](#post-apiclassesclassidgroups)
- [~~GET /api/questions/{question_id}~~](#get-apiquestionsquestionid)
- [~~PUT /api/questions/{question_id}~~](#put-apiquestionsquestionid)
- [~~DELETE /api/questions/{question_id}~~](#delete-apiquestionsquestionid)
- [GET /api/groups/{id}](#get-apigroupsid)
- [~~PUT /api/groups/{id}~~](#put-apigroupsid)
- [~~DELETE /api/groups/{id}~~](#delete-apigroupsid)
- [GET /api/groups/{group_id}/students](#get-apigroupsgroupidstudents)
- [POST /api/groups/{group_id}/students](#post-apigroupsgroupidstudents)
- [DELETE /api/groups/{group_id}/students/{id}](#delete-apigroupsgroupidstudentsid)
- [~~GET /api/groups/{group_id}/exams?upcoming,history~~](#get-apigroupsgroupidexamsupcominghistory)
- [~~POST /api/groups/{group_id}/exams~~](#post-apigroupsgroupidexams)
- [~~POST /api/groups/{group_id}/exams/generate~~](#post-apigroupsgroupidexamsgenerate)
- [~~GET /api/groups/{group_id}/scores~~](#get-apigroupsgroupidscores)
- [~~GET /api/exams/{id}~~](#get-apiexamsid)
- [~~DELETE /api/exams/{id}~~](#delete-apiexamsid)
- [~~PUT /api/exams/{id}~~](#put-apiexamsid)
- [~~GET /api/exams/{exam_id}/scores~~](#get-apiexamsexamidscores)
- [~~POST /api/exams/{exam_id}/submissions~~](#post-apiexamsexamidsubmissions)
- [~~GET /api/submissions/{submission_id}~~](#get-apisubmissionssubmissionid)
- [~~PUT /api/submissions/{submission_id}~~](#put-apisubmissionssubmissionid)
- [~~DELETE /api/submissions/{submission_id}~~](#delete-apisubmissionssubmissionid)
- [GET /api/users/{user_id}](#get-apiusersuserid)
- [~~PUT /api/users/{user_id}~~](#put-apiusersuserid)
- [~~DELETE /api/users/{user_id}~~](#delete-apiusersuserid)
- [GET /api/users/{user_id}/notifications](#get-apiusersuseridnotifications)
- [GET /api/invitations/{invitation_id}/accept](#get-apiinvitationsinvitationidaccept)
- [GET /api/invitations/{invitation_id}/decline](#get-apiinvitationsinvitationiddecline)
- [~~GET /api/students/{student_id}/scores~~](#get-apistudentsstudentidscores)
- [~~GET /api/students/{student_id}/exams?upcoming,history~~](#get-apistudentsstudentidexamsupcominghistory)
- [GET /api/teachers/{teacher_id}/classes](#get-apiteachersteacheridclasses)
- [POST api/teachers/{teacher_id}/classes](#post-apiteachersteacheridclasses)

#### POST /auth/login
##### Body
```json
{
 "email": "email1@email.com",
 "password": "password"
}
```
##### Response
```json
{
 "token": "header.body.signature",
 "user":{
 "email": "email1@email.com",
 "firstName": "Foo",
 "lastName": "Bar",
 "type": "student | teacher",
 "active": true,
 "id": 1
 }
}
```

##### HttpStatus
- **OK (200)**
- **INTERNAL_SERVER_ERROR (500)**
- **UNAUTHORIZED (401)** - *Invalid authentication*, or *Unconfirmed email*


#### POST /auth/signup
##### Body
```json
{
 "email": "email1@email.com",
 "password": "password",
 "firstName": "Foo",
 "lastName": "Bar",
 "type": "student | teacher"
}
```

##### Response
```json
{
 "email": "email1@email.com",
 "firstName": "Foo",
 "lastName": "Bar",
 "type": "student | teacher",
 "active": true,
 "id": 1
}
```

##### HttpStatus
- **OK (200)**
- **INTERNAL_SERVER_ERROR (500)**
- **NOT_ACCEPTABLE (406)** - *Missing information*, *Email already in use*, *Invalid user type*
___

#### GET /api/classes/{id}
### Response
```json
{
 "id": 1,
 "abbreviation": "AA",
 "name": "Arquiteturas Aplicacionais",
 "teacher": {
 "email": "email1@email.com",
 "firstName": "Foo",
 "lastName": "Bar",
 "type": "student | teacher",
 "active": true,
 "id": 1
 }
}
```
### HttpStatus
- **OK (200)**
- **INTERNAL_SERVER_ERROR (500)**
- **NOT_FOUND (404)** - *No such class*

#### ~~PUT /api/classes/{id}~~
#### ~~DELETE /api/classes/{id}~~

#### GET /api/classes/{class_id}/questions
### Response
```json
[
 {
 "id": 1,
 "text": "Enunciado pergunta 1",
 "category": "Category 1",
 "difficulty": 1,
 "answers": [
 {
 "id": 1,
 "text": "Alternativa 3",
 "correct": false
 },
 {
 "id": 2,
 "text": "Alternativa 1",
 "correct": false
 },
 {
 "id": 3,
 "text": "Alternativa 2",
 "correct": true
 }
 ]
 }
]
```
### HttpStatus
- **OK (200)**
- **INTERNAL_SERVER_ERROR (500)**
- **NOT_FOUND (404)** - *No such class*
- **UNAUTHORIZED (401)** - *No permission*

#### POST /api/classes/{class_id}/questions
### Body
```json
{
	"text": "Enunciado pergunta 1",
	"category": "Category 1",
	"difficulty": 1,
	"answers": [
		{
			"text": "Alternativa 1",
			"correct": false
		},
		{
			"text": "Alternativa 2",
			"correct": true
		},
		{
			"text": "Alternativa 3",
			"correct": false
		}
	]
}
```
### Response
```json
{
 "id": 1,
 "text": "Enunciado pergunta 1",
 "category": "Category 1",
 "difficulty": 1,
 "answers": [
 {
 "id": 1,
 "text": "Alternativa 3",
 "correct": false
 },
 {
 "id": 2,
 "text": "Alternativa 1",
 "correct": false
 },
 {
 "id": 3,
 "text": "Alternativa 2",
 "correct": true
 }
 ]
}
```
### HttpStatus
- **OK (200)**
- **INTERNAL_SERVER_ERROR (500)**
- **NOT_FOUND (404)** - *No such class*
- **UNAUTHORIZED (401)** - *No permission*
- **NOT_ACCEPTABLE (406)** - *Invalid question*, *Question already exists*


#### GET /api/classes/{class_id}/categories
### Response
```json
[
 "Category1",
 "Category2"
]
```
### HttpStatus
- **OK (200)**
- **INTERNAL_SERVER_ERROR (500)**
- **NOT_FOUND (404)** - *No such class*
- **UNAUTHORIZED (401)** - *No permission*

#### GET /api/classes/{class_id}/groups
### Response
```json
[
 {
 "name": "class1",
 "id": 1
 },
 {
 "name": "class2",
 "id": 2
 }
]
```
### HttpStatus
- **OK (200)**
- **INTERNAL_SERVER_ERROR (500)**
- **NOT_FOUND (404)** - *No such class*

#### POST /api/classes/{class_id}/groups
### Body
```json
{
 "name": "Turma 16/17"
}
```
### Response
```json
{
 "name": "Turma 16/17",
 "_class": {
 "name": "Arquiteturas Aplicacionais",
 "abbreviation": "AA",
 "teacher": {
 "email": "teacher@teacher",
 "firstName": "John",
 "lastName": "Doe",
 "type": "teacher",
 "active": true,
 "id": 1
 },
 "id": 1
 },
 "id": 1
}
```
### HttpStatus
- **OK (200)**
- **INTERNAL_SERVER_ERROR (500)**
- **NOT_FOUND (404)** - *No such class*
- **NOT_ACCEPTABLE (406)** - *Group already exists*
- **UNAUTHORIZED (401)** - *No permission*
___

#### ~~GET /api/questions/{question_id}~~
#### ~~PUT /api/questions/{question_id}~~
#### ~~DELETE /api/questions/{question_id}~~
___

#### GET /api/groups/{id}
### Response
```json
{
 "name": "Name2",
 "_class": {
 "name": "Name1",
 "abbreviation": "Abbreviation1",
 "teacher": {
 "email": "email2",
 "firstName": "firstName2",
 "lastName": "lastName2",
 "type": "teacher",
 "active": true,
 "id": 2
 },
 "id": 1
 },
 "id": 2
}
```

### HttpStatus
- **OK (200)**
- **INTERNAL_SERVER_ERROR (500)**
- **NOT_FOUND (404)** - *No such group*

#### ~~PUT /api/groups/{id}~~
#### ~~DELETE /api/groups/{id}~~

#### GET /api/groups/{group_id}/students
### Response
```json
[
 {
 "accepted": false,
 "user": {
 "email": "email999",
 "firstName": "ND",
 "lastName": "ND",
 "type": "student",
 "active": false,
 "id": 21
 }
 },
 {
 "accepted": false,
 "user": {
 "email": "email1",
 "firstName": "firstName1",
 "lastName": "lastName1",
 "type": "student",
 "active": true,
 "id": 1
 }
 }
]
```
### HttpStatus
- **OK (200)**
- **INTERNAL_SERVER_ERROR (500)**
- **NOT_FOUND (404)**

#### POST /api/groups/{group_id}/students
### Body
```json
[
 "email1@email.com",
 "email2@email.com"
]
```
### Response
```json
[
 {
 "email": "email1@email.com",
 "user": {
 "email": "email1@email.com",
 "firstName": "John",
 "lastName": "Doe",
 "type": "student",
 "active": true,
 "id": 1
 }
 }
 {
 "email": "email2@email.com",
 "message": "User is a teacher | Student has already been added to the group"
 }
]
```
> Nota: Os utilizadores, mesmo que não existam, são adicionados. Nestes casos, a variável "active" toma o valor falso.

### HttpStatus
- **OK (200)**
- **INTERNAL_SERVER_ERROR (500)**
- **UNAUTHORIZED (401)**
- **NOT FOUND (404)**

#### DELETE /api/groups/{group_id}/students/{id}
### HttpStatus
- **OK (200)**
- **INTERNAL_SERVER_ERROR (500)**
- **NOT_FOUND (404)** - *No such group*, *No such student*

#### ~~GET /api/groups/{group_id}/exams?upcoming,history~~
#### ~~POST /api/groups/{group_id}/exams~~
#### ~~POST /api/groups/{group_id}/exams/generate~~

#### ~~GET /api/groups/{group_id}/scores~~
___

#### ~~GET /api/exams/{id}~~
#### ~~DELETE /api/exams/{id}~~
#### ~~PUT /api/exams/{id}~~
#### ~~GET /api/exams/{exam_id}/scores~~
#### ~~POST /api/exams/{exam_id}/submissions~~
___

#### ~~GET /api/submissions/{submission_id}~~
#### ~~PUT /api/submissions/{submission_id}~~
#### ~~DELETE /api/submissions/{submission_id}~~

___
#### GET /api/users/{user_id}
### Response
```json
{
 "email": "email1@email.com",
 "firstName": "Foo",
 "lastName": "Bar",
 "type": "student | teacher",
 "active": true,
 "id": 1
}
```

### HttpStatus
- **OK (200)**
- **INTERNAL_SERVER_ERROR (500)**
- **NOT_FOUND (404)** - *No such user*

#### ~~PUT /api/users/{user_id}~~
#### ~~DELETE /api/users/{user_id}~~
#### GET /api/users/{user_id}/notifications
### Response
```json
[
 {
 "type": "Group invitation",
 "group": {
 "name": "Name1",
 "_class": {
 "name": "Name1",
 "abbreviation": "Abbreviation1",
 "teacher": {
 "email": "email16",
 "firstName": "firstName16",
 "lastName": "lastName16",
 "type": "Teacher",
 "active": true,
 "id": 16
 },
 "id": 1
 },
 "id": 1
 },
 "id": 0
 }
]
```

### HttpStatus
- **OK (200)**
- **INTERNAL_SERVER_ERROR (500)**
- **NOT_FOUND (404)**
- **UNAUTHORIZED (401)**
___

#### GET /api/invitations/{invitation_id}/accept
### Response
```json
"name": "Name1",
"abbreviation": "Abbreviation1",
"teacher": {
 "email": "email16",
 "firstName": "firstName16",
 "lastName": "lastName16",
 "type": "Teacher",
 "active": true,
 "id": 16
},
"id": 1
```
### HttpStatus
- **OK (200)**
- **INTERNAL_SERVER_ERROR (500)**
- **NOT_FOUND (404)**
- **UNAUTHORIZED (401)**

#### GET /api/invitations/{invitation_id}/decline
### HttpStatus
- **OK (200)**
- **INTERNAL_SERVER_ERROR (500)**
- **NOT_FOUND (404)**
- **UNAUTHORIZED (401)**
___

#### ~~GET /api/students/{student_id}/scores~~
#### ~~GET /api/students/{student_id}/exams?upcoming,history~~
___

#### GET /api/teachers/{teacher_id}/classes
### Response
```json
[
 {
 "name": "class1",
 "abbreviation": "cl1",
 "id": 1
 }
]
```
### HttpStatus
- **OK (200)**
- **INTERNAL_SERVER_ERROR (500)**
- **NOT_FOUND (404)** - *No such teacher*

#### POST api/teachers/{teacher_id}/classes
### Body
```json
{
  "name": "class1",
  "abbreviation": "cl1",
}
```
### Response
```json
[
  {
   "name": "class1",
   "abbreviation": "cl1",
   "id": 1
  }
]
```
### HttpStatus
- **OK (200)**
- **INTERNAL_SERVER_ERROR (500)**
- **NOT_FOUND (404)** - *No such teacher*
- **NOT_ACCEPTABLE (406)** - *Missing information*, *Class already exists*
___
