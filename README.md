# EvaluationSystem

...

## Backend

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



### Authentication
Após fazer login e receber o **token**, enviar no cabeçalho HTTP:

> Authorization = "Bearer TOKEN"

### API

#### POST auth/login

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

##### HttpStatus
- **OK (200)**
- **INTERNAL_SERVER_ERROR (500)**
- **UNAUTHORIZED (401)** - *Invalid authentication*, or *Unconfirmed email*
---

#### POST auth/signup

##### Body
```json
{
  "email": "something@something",
  "password": "some_password",
  "firstName": "Foo",
  "lastName": "Bar",
  "type": "student | teacher"
}
```

##### Response
```json
{
  "email": "something@something",
  "firstName": "Foo",
  "lastName": "Bar",
  "type": "student | teacher",
  "id": 1
}
```

##### HttpStatus
- **OK (200)**
- **INTERNAL_SERVER_ERROR (500)**
- **NOT_ACCEPTABLE (406)** - *Missing information*, *Email already in use*, *Invalid user type*
---

#### GET /classes/{id}
### Response
```json
{
  "id": 1,
  "abbreviation": "AA",
  "name": "Arquiteturas Aplicacionais",
  "teacher": {
      "email": "something@something",
      "firstName": "Foo",
      "lastName": "Bar",
      "type": "student | teacher",
      "id": 1
  }
}
```
### HttpStatus
- **OK (200)**
- **INTERNAL_SERVER_ERROR (500)**
- **NOT_FOUND (404)** - *No such class*
---

#### ~~PUT /classes/{id}~~
#### ~~DELETE /classes/{id}~~

#### ~~GET /classes/{class_id}/questions~~
#### ~~GET /classes/{class_id}/questions/{question_id}~~
#### ~~POST /classes/{class_id}/questions~~
#### ~~PUT /classes/{class_id}/questions/{question_id}~~
#### ~~DELETE /classes/{class_id}/questions/{id}~~
#### ~~GET /classes/{class_id}/categories~~
#### ~~POST /classes/{class_id}/groups~~

#### ~~GET /groups/{id}~~
#### ~~PUT /groups/{id}~~
#### ~~DELETE /groups/{id}~~

#### ~~GET /groups/{group_id}/exams?upcoming,history~~
#### ~~POST /groups/{group_id}/exams~~
#### ~~DELETE /groups/{group_id}/exams/{id}~~
#### ~~POST /groups/{group_id}/exams/generate~~

#### ~~GET /groups/{group_id}/scores~~
#### ~~GET /groups/{group_id}/exams/{exam_id}/scores~~
#### ~~GET /groups/{group_id}/exams/{exam_id}/submissions/{submission_id}~~

#### ~~POST /groups/{group_id}/exams/{exam_id}/submit?user # Array de respostas~~

#### ~~GET /exams/{id}~~
#### ~~GET /exams?user,class,upcoming,history~~


#### ~~GET /groups/{group_id}/students~~
#### ~~POST /groups/{group_id}/students~~
#### ~~DELETE /groups/{group_id}/students/{id}~~


#### GET /users/{user_id}
### Response
```json
{
  "email": "something@something",
  "firstName": "Foo",
  "lastName": "Bar",
  "type": "student | teacher",
  "id": 1
}
```

### HttpStatus
- **OK (200)**
- **INTERNAL_SERVER_ERROR (500)**
- **NOT_FOUND (404)** - *No such user*
---

#### ~~PUT /users/{user_id}~~
#### ~~DELETE /users/{user_id}~~
#### ~~GET /users/{user_id}/notifications~~

#### ~~GET /students/{student_id}/scores~~

#### GET /teachers/{teacher_id}/classes
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

#### POST /teachers/{teacher_id}/classes
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
- **NOT_ACCEPTABLE (406)** - *Missing information*
---
