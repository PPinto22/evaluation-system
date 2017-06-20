# EvaluationSystem

...

## Backend

### API


##### [X] POST    auth/login

##### Body
```json
{
 "email": "something@something",
 "password": "some_password"
}
```
##### Response
```json
{
 "token": "header.body.signature",
 "user":{
     "email": "something@something",
     "firstName": "Foo",
     "lastName": "Bar",
     "type": "student | teacher",
     "id": 1
 }
}
```

##### HttpStatus:
- OK
- INTERNAL_SERVER_ERROR
- UNAUTHORIZED (Invalid authentication, or Unconfirmed email)

##### [X] POST    auth/signup

##### Body
```json

```

GET     /exams/:id
GET     /exams?user,class,upcoming,history

GET     /classes/:id
PUT     /classes/:id
DELETE  /classes/:id

GET     /classes/:class_id/questions
GET     /classes/:class_id/questions/:question_id
POST    /classes/:class_id/questions
PUT     /classes/:class_id/questions/:question_id # Melhoria
DELETE  /classes/:class_id/questions/:id

GET     /classes/:class_id/categories

GET     /groups/:id
POST    /groups
PUT     /groups/:id
DELETE  /groups/:id

GET     /groups/:group_id/exams?upcoming,history
POST    /groups/:group_id/exams
DELETE  /groups/:group_id/exams/:id
POST    /groups/:group_id/exams/generate

GET     /groups/:group_id/scores
GET     /groups/:group_id/exams/:exam_id/scores
GET     /groups/:group_id/exams/:exam_id/submissions/:submission_id

POST    /groups/:group_id/exams/:exam_id/submit?user # Array de respostas

GET     /groups/:group_id/students
POST    /groups/:group_id/students
DELETE  /groups/:group_id/students/:id


GET     /users/:user_id
POST    /users
PUT     /users/:user_id
DELETE  /users/:user_id
GET     /users/:user_id/notifications

GET     /teachers/:teacher_id/classes

GET     /students/:student_id/scores

### Build and Run
---
```
cd EvaluationSystem/
mvn package
cp target/evalsys-backend-1.0.jar ../Docker/spring/
cd ../Docker/spring
docker build -t evalsys-backend .
docker run --net="bridge" -p 8080:8080 evalsys-backend
```
