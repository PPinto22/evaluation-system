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

- [POST /auth/login](#post-authlogin) [x]
- [POST /auth/signup](#post-authsignup) [x]
- [GET /api/classes/{class_id}](#get-apiclassesclass_id) [x]
- [~~PUT /api/classes/{class_id}~~](#put-apiclassesclass_id)
- [~~DELETE /api/classes/{class_id}~~](#delete-apiclassesclass_id)
- [GET /api/classes/{class_id}/questions](#get-apiclassesclass_idquestions) [x]
- [POST /api/classes/{class_id}/questions](#post-apiclassesclass_idquestions) [x]
- [GET /api/classes/{class_id}/categories](#get-apiclassesclass_idcategories) [x]
- [GET /api/classes/{class_id}/groups](#get-apiclassesclass_idgroups) [x]
- [POST /api/classes/{class_id}/groups](#post-apiclassesclass_idgroups) [x]
- [GET /api/questions/{question_id}](#get-apiquestionsquestion_id)
- [~~PUT /api/questions/{question_id}~~](#put-apiquestionsquestion_id)
- [~~DELETE /api/questions/{question_id}~~](#delete-apiquestionsquestion_id)
- [GET /api/groups/{group_id}](#get-apigroupsgroup_id) [x]
- [~~PUT /api/groups/{group_id}~~](#put-apigroupsgroup_id)
- [~~DELETE /api/groups/{group_id}~~](#delete-apigroupsgroup_id)
- [GET /api/groups/{group_id}/students](#get-apigroupsgroup_idstudents) [x]
- [POST /api/groups/{group_id}/students](#post-apigroupsgroup_idstudents) [x]
- [DELETE /api/groups/{group_id}/students/{student_id}](#delete-apigroupsgroup_idstudentsstudent_id) [x]
- [GET /api/groups/{group_id}/questions/available](#get-apigroupsgroup_idquestionsavailable)
- [GET /api/groups/{group_id}/exams](#get-apigroupsgroup_idexams)
- [POST /api/groups/{group_id}/exams](#post-apigroupsgroup_idexams)
- [POST /api/groups/{group_id}/exams/generate](#post-apigroupsgroup_idexamsgenerate)
- [~~GET /api/groups/{group_id}/scores~~](#get-apigroupsgroup_idscores)
- [GET /api/exams/{exam_id}](#get-apiexamsexam_id)
- [~~DELETE /api/exams/{exam_id}~~](#delete-apiexamsexam_id)
- [~~PUT /api/exams/{exam_id}~~](#put-apiexamsexam_id)
- [~~GET /api/exams/{exam_id}/scores~~](#get-apiexamsexam_idscores)
- [~~POST /api/exams/{exam_id}/submission~~](#post-apiexamsexam_idsubmissions)
- [~~GET /api/submissions/{submission_id}~~](#get-apisubmissionssubmission_id)
- [~~PUT /api/submissions/{submission_id}~~](#put-apisubmissionssubmission_id)
- [~~DELETE /api/submissions/{submission_id}~~](#delete-apisubmissionssubmission_id)
- [GET /api/users/{user_id}](#get-apiusersuser_id) [x]
- [~~PUT /api/users/{user_id}~~](#put-apiusersuser_id)
- [~~DELETE /api/users/{user_id}~~](#delete-apiusersuser_id)
- [POST /api/users/{user_id}/classes](#post-apiusersuser_idclasses) [x] MUDEI O NOME DE /teachers/ para /users/.
- [GET /api/users/{user_id}/classes](#get-apiusersuser_idclasses)
- [GET /api/users/{user_id}/groups](#get-apiusersuser_idgroups)
- [GET /api/users/{user_id}/notifications](#get-apiusersuser_idnotifications) [x]
- [~~GET /api/users/{user_id}/scores~~](#get-apiusersuser_idscores)
- [GET /api/users/{user_id}/exams](#get-apiusersuser_idexams)
- [GET /api/invitations/{invitation_id}/accept](#get-apiinvitationsinvitation_idaccept) [x]
- [GET /api/invitations/{invitation_id}/decline](#get-apiinvitationsinvitation_iddecline) [x]

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
     "type": "Student | Teacher",
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
  "type": "Student | Teacher"
}
```

##### Response
```json
{
  "email": "email1@email.com",
  "firstName": "Foo",
  "lastName": "Bar",
  "type": "Student | Teacher",
  "active": true,
  "id": 1
}
```

##### HttpStatus
- **OK (200)**
- **INTERNAL_SERVER_ERROR (500)**
- **NOT_ACCEPTABLE (406)** - *Missing information*, *Email already in use*, *Invalid user type*
___

#### GET /api/classes/{class_id}
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
      "type": "Student | Teacher",
      "active": true,
      "id": 1
  }
}
```
### HttpStatus
- **OK (200)**
- **INTERNAL_SERVER_ERROR (500)**
- **NOT_FOUND (404)** - *No such class*

#### ~~PUT /api/classes/{class_id}~~
#### ~~DELETE /api/classes/{class_id}~~

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
        "text": "Alternativa 1",
        "correct": false,
        "order": 0
      },
      {
        "id": 2,
        "text": "Alternativa 2",
        "correct": true,
        "order": 1
      },
      {
        "id": 3,
        "text": "Alternativa 3",
        "correct": true,
        "order": 2
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
            "text": "Alternativa 1",
            "correct": false,
            "order": 0,
        },
        {
            "id": 2,
            "text": "Alternativa 2",
            "correct": false,
            "order": 1,
        },
        {
            "id": 3,
            "text": "Alternativa 3",
            "correct": true,
            "order": 2,
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
            "type": "Teacher",
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

#### GET /api/questions/{question_id}
### Response
```json
"id": 6,
"text": "Solve for x: 5 + x = 9",
"category": "Category1",
"difficulty": 3,
"answers": [
  {
    "id": 24,
    "text": "6",
    "correct": false,
    "order": 0
  },
  {
    "..."
  }
]
```
### HttpStatus
- **OK (200)**
- **INTERNAL_SERVER_ERROR (500)**
- **NOT_FOUND (404)** - *No such question*
- **UNAUTHORIZED (401)** - *No permission*

#### ~~PUT /api/questions/{question_id}~~
#### ~~DELETE /api/questions/{question_id}~~
___

#### GET /api/groups/{group_id}
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
            "type": "Teacher",
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

#### ~~PUT /api/groups/{group_id}~~
#### ~~DELETE /api/groups/{group_id}~~

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
      "type": "Student",
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
      "type": "Student",
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
      "type": "Student",
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

#### DELETE /api/groups/{group_id}/students/{student_id}
### HttpStatus
- **OK (200)**
- **INTERNAL_SERVER_ERROR (500)**
- **NOT_FOUND (404)** - *No such group*, *No such student*

#### GET /api/groups/{group_id}/questions/available
Este método devolve as perguntas disponíveis para um dado grupo, organizadas por categoria e dificuldade.
Não são necessariamente as mesmas perguntas associadas à disciplina porque algumas dessas podem já ter sido utilizadas num exame anterior do grupo em questão.
### Response
```json
{
  "Category1": {
    "1": {
      "available": 1,
      "questionIDs": [
        1,
      ]
    },
    "3": {
      "available": 3,
      "questionIDs": [
        6,
        21,
        36
      ]
    }
  },
  "Category2": {
    "1": {
      "available": 2,
      "questionIDs": [
        22,
        37
      ]
    }
  }
}
```
### HttpStatus
- **OK (200)**
- **INTERNAL_SERVER_ERROR (500)**
- **NOT_FOUND (404)** - *No such group*
- **UNAUTHORIZED (401)** - *No permission*

#### GET /api/groups/{group_id}/exams
### Response
```json
{
  "exams": {
    "History": [
      {
        "id": 1,
        "name": "Exam 1",
        "beginDate": 1498908500000,
        "duration": 90,
      }
    ],
    "Ongoing": [
      {
        "..."
      }
    ],
    "Upcoming": [
      {
        "..."
      }
    ],
  }
}
```
### HttpStatus
- **OK (200)**
- **INTERNAL_SERVER_ERROR (500)**
- **NOT_FOUND (404)** - *No such group*
- **UNAUTHORIZED (401)** - *No permission*

#### POST /api/groups/{group_id}/exams
### Body
```json
{
	"beginDate": "1498908600000",
	"duration": 60,
	"name": "Exam 1",
	"questionIDs": [
		31, 7, 11, 6
	]
}
```
### Response
```json
{
  "id": 4,
  "name": "Exam 4",
  "beginDate": 1498908600000,
  "duration": 60,
  "questions": [
    {
      "id": 6,
      "text": "Solve for x: 5 + x = 9",
      "category": "Category1",
      "difficulty": 3,
      "answers": [
        {
          "id": 24,
          "text": "6",
          "correct": false,
          "order": 0
        },
        {
          "..."
        }
      ],
      "score": 5,
      "order": 0
    },
    {
      "..."
    }
  ]
}
```
### HttpStatus
- **OK (200)**
- **INTERNAL_SERVER_ERROR (500)**
- **NOT_FOUND (404)** - *No such group*
- **UNAUTHORIZED (401)** - *No permission*
- **NOT_ACCEPTABLE (406)** - *Invalid name*, *Invalid duration*, *Invalid date*, *Duplicate questions*, *Invalid exam*, *Invalid question* (id **ID**)

#### POST /api/groups/{group_id}/exams/generate
### Body
```json
[
	{
		"category": "Category1",
		"difficulty": 1
	},
	{
		"category": "Category2",
		"difficulty": 3
	}
]
```
### Response
```json
[
    {
        "id": 31,
        "text": "Solve for x: 30 + x = 34",
        "category": "Category1",
        "difficulty": 1,
        "answers": [
            {
                "id": 121,
                "text": "31",
                "correct": false,
                "order": 0,
            },
            {
              "id": 122,
              "text": "34",
              "correct": true,
              "order": 1,
            }
            {
                "id": 123,
                "text": "32",
                "correct": false,
                "order": 2,
            },
            {
                "id": 124,
                "text": "33",
                "correct": false,
                "order": 3,
            },
        ]
    },
    {
      "..."
    }
]
```
### HttpStatus
- **OK (200)**
- **INTERNAL_SERVER_ERROR (500)**
- **NOT_FOUND (404)** - *No such group*
- **UNAUTHORIZED (401)** - *No permission*
- **NOT_ACCEPTABLE (406)** - *Invalid questions*, *Insufficient questions*

#### ~~GET /api/groups/{group_id}/scores~~
___

#### GET /api/exams/{exam_id}
Se o utilizador for um aluno e o exame ainda não tiver começado, este método retorna apenas as informações do exame sem as questões; se o exame já tiver começado, então retorna também as perguntas mas sem a indicação de qual a resposta certa.
### Response
```json
{
  "id": 7,
  "name": "Exam 7",
  "beginDate": 1498908600000,
  "duration": 60,
  "questions": [
    {
      "id": 31,
      "text": "Solve for x: 30 + x = 34",
      "category": "Category1",
      "difficulty": 1,
      "answers": [
        {
          "id": 123,
          "text": "31",
          "correct": false,
          "order": 0
        },
        {
          "..."
        }
      ],
      "score": 5,
      "order": 0
    },
    {
      "..."
    }
  ]
}
```
### HttpStatus
- **OK (200)**
- **INTERNAL_SERVER_ERROR (500)**
- **NOT_FOUND (404)** - *No such exam*
- **UNAUTHORIZED (401)** - *No permission*

#### ~~DELETE /api/exams/{exam_id}~~
#### ~~PUT /api/exams/{exam_id}~~
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
  "type": "Student | Teacher",
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

#### POST api/users/{user_id}/classes
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

#### GET /api/users/{user_id}/classes
### Response
Caso o utilizador seja um professor, nao e enviado o professor.
```json
[
  {
    "name": "Name1",
    "abbreviation": "Abbreviation1",
    "id": 1,
    "teacher": {
      "id": 16,
      "email": "email16",
      "firstName": "firstName16",
      "lastName": "lastName16",
      "type": "Teacher",
      "active": true
    },
  }
]
```
### HttpStatus
- **OK (200)**
- **INTERNAL_SERVER_ERROR (500)**
- **NOT_FOUND (404)** - *No such user*

#### GET /api/users/{user_id}/groups
### Response
```json
[
  {
    "id": 1,
    "name": "Name1",
    "_class": {
      "name": "Name1",
      "abbreviation": "Abbreviation1",
      "teacher": {
        "id": 16,
        "email": "email16",
        "firstName": "firstName16",
        "lastName": "lastName16",
        "type": "Teacher",
        "active": true
      },
      "id": 1
    }
  },
]
```
### HttpStatus
- **OK (200)**
- **INTERNAL_SERVER_ERROR (500)**
- **NOT_FOUND (404)** - *No such user*


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

#### GET /api/users/{user_id}/exams
### Response
```json
{
  "exams": {
    "History": [
      {
        "id": 1,
        "name": "Exam 1",
        "beginDate": 1498908500000,
        "duration": 90,
        "group": {
          "id": 1,
          "name": "Name1",
          "_class": {
            "name": "Name1",
            "abbreviation": "Abbreviation1",
            "teacher": {
              "id": 16,
              "email": "email16",
              "firstName": "firstName16",
              "lastName": "lastName16",
              "type": "Teacher",
              "active": true
            },
            "id": 1
          }
        }
      }
    ],
    "Ongoing": [
      {
        "..."
      }
    ],
    "Upcoming": [
      {
        "..."
      }
    ],
  }
}
```
### HttpStatus
- **OK (200)**
- **INTERNAL_SERVER_ERROR (500)**
- **NOT_FOUND (404)**
- **UNAUTHORIZED (401)**

#### ~~GET /api/users/{user_id}/scores~~
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
