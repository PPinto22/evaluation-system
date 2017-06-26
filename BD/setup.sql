ALTER TABLE Class DROP CONSTRAINT "teacher-classes";
ALTER TABLE Question DROP CONSTRAINT "class-questions";
ALTER TABLE Answer DROP CONSTRAINT answers;
ALTER TABLE GroupStudent DROP CONSTRAINT "group-students";
ALTER TABLE "Group" DROP CONSTRAINT "class-groups";
ALTER TABLE QuestionScore DROP CONSTRAINT "exam-questions";
ALTER TABLE Exam DROP CONSTRAINT "group-exams";
ALTER TABLE QuestionScore DROP CONSTRAINT question;
ALTER TABLE Submission DROP CONSTRAINT "student-submission";
ALTER TABLE Submission DROP CONSTRAINT "resolution-exam";
ALTER TABLE QuestionSubmission DROP CONSTRAINT "question-submissions";
ALTER TABLE QuestionSubmission DROP CONSTRAINT "questionSubmission-question";
ALTER TABLE QuestionSubmission DROP CONSTRAINT "questionSubmission-Answer";
ALTER TABLE Notification DROP CONSTRAINT "user-notifications";
ALTER TABLE Notification DROP CONSTRAINT groupInvitations;
ALTER TABLE GroupStudent DROP CONSTRAINT "student-groups";
DROP TABLE IF EXISTS "User" CASCADE;
DROP TABLE IF EXISTS Class CASCADE;
DROP TABLE IF EXISTS Question CASCADE;
DROP TABLE IF EXISTS Answer CASCADE;
DROP TABLE IF EXISTS "Group" CASCADE;
DROP TABLE IF EXISTS Exam CASCADE;
DROP TABLE IF EXISTS QuestionScore CASCADE;
DROP TABLE IF EXISTS Submission CASCADE;
DROP TABLE IF EXISTS QuestionSubmission CASCADE;
DROP TABLE IF EXISTS Notification CASCADE;
DROP TABLE IF EXISTS GroupStudent CASCADE;
CREATE TABLE "User" (
  ID               SERIAL NOT NULL, 
  Email            varchar(255), 
  Password         varchar(255), 
  FirstName        varchar(255), 
  LastName         varchar(255), 
  Registered       bool, 
  Deleted          bool, 
  RegistrationCode varchar(255), 
  Discriminator    varchar(255) NOT NULL, 
  PRIMARY KEY (ID));
CREATE TABLE Class (
  ID           SERIAL NOT NULL, 
  UserID       int4 NOT NULL, 
  Name         varchar(255), 
  Abbreviation varchar(255), 
  PRIMARY KEY (ID));
CREATE TABLE Question (
  ID         SERIAL NOT NULL, 
  ClassID    int4 NOT NULL, 
  Text       varchar(255), 
  Category   varchar(255), 
  Difficulty int4, 
  PRIMARY KEY (ID));
CREATE TABLE Answer (
  ID         SERIAL NOT NULL, 
  QuestionID int4 NOT NULL, 
  Text       varchar(255), 
  Correct    bool, 
  "Order"    int4 NOT NULL, 
  PRIMARY KEY (ID));
CREATE TABLE "Group" (
  ID      SERIAL NOT NULL, 
  ClassID int4 NOT NULL, 
  Name    varchar(255), 
  PRIMARY KEY (ID));
CREATE TABLE Exam (
  ID        SERIAL NOT NULL, 
  GroupID   int4 NOT NULL, 
  BeginDate int8, 
  Duration  int4, 
  Name      varchar(255), 
  PRIMARY KEY (ID));
CREATE TABLE QuestionScore (
  ID         SERIAL NOT NULL, 
  QuestionID int4 NOT NULL, 
  ExamID     int4 NOT NULL, 
  Score      float4 NOT NULL, 
  "Order"    int4 NOT NULL, 
  PRIMARY KEY (ID));
CREATE TABLE Submission (
  ID     SERIAL NOT NULL, 
  ExamID int4 NOT NULL, 
  UserID int4 NOT NULL, 
  Score  float4 NOT NULL, 
  PRIMARY KEY (ID));
CREATE TABLE QuestionSubmission (
  ID              SERIAL NOT NULL, 
  AnswerID        int4, 
  QuestionScoreID int4 NOT NULL, 
  SubmissionID    int4 NOT NULL, 
  Correct         bool NOT NULL, 
  PRIMARY KEY (ID));
CREATE TABLE Notification (
  ID            SERIAL NOT NULL, 
  GroupID       int4 NOT NULL, 
  UserID        int4 NOT NULL, 
  Discriminator varchar(255) NOT NULL, 
  PRIMARY KEY (ID));
CREATE TABLE GroupStudent (
  ID       SERIAL NOT NULL, 
  UserID   int4 NOT NULL, 
  GroupID  int4 NOT NULL, 
  Accepted bool NOT NULL, 
  PRIMARY KEY (ID));
ALTER TABLE Class ADD CONSTRAINT "teacher-classes" FOREIGN KEY (UserID) REFERENCES "User" (ID);
ALTER TABLE Question ADD CONSTRAINT "class-questions" FOREIGN KEY (ClassID) REFERENCES Class (ID);
ALTER TABLE Answer ADD CONSTRAINT answers FOREIGN KEY (QuestionID) REFERENCES Question (ID);
ALTER TABLE GroupStudent ADD CONSTRAINT "group-students" FOREIGN KEY (GroupID) REFERENCES "Group" (ID);
ALTER TABLE "Group" ADD CONSTRAINT "class-groups" FOREIGN KEY (ClassID) REFERENCES Class (ID);
ALTER TABLE QuestionScore ADD CONSTRAINT "exam-questions" FOREIGN KEY (ExamID) REFERENCES Exam (ID);
ALTER TABLE Exam ADD CONSTRAINT "group-exams" FOREIGN KEY (GroupID) REFERENCES "Group" (ID);
ALTER TABLE QuestionScore ADD CONSTRAINT question FOREIGN KEY (QuestionID) REFERENCES Question (ID);
ALTER TABLE Submission ADD CONSTRAINT "student-submission" FOREIGN KEY (UserID) REFERENCES "User" (ID);
ALTER TABLE Submission ADD CONSTRAINT "resolution-exam" FOREIGN KEY (ExamID) REFERENCES Exam (ID);
ALTER TABLE QuestionSubmission ADD CONSTRAINT "question-submissions" FOREIGN KEY (SubmissionID) REFERENCES Submission (ID);
ALTER TABLE QuestionSubmission ADD CONSTRAINT "questionSubmission-question" FOREIGN KEY (QuestionScoreID) REFERENCES QuestionScore (ID);
ALTER TABLE QuestionSubmission ADD CONSTRAINT "questionSubmission-Answer" FOREIGN KEY (AnswerID) REFERENCES Answer (ID);
ALTER TABLE Notification ADD CONSTRAINT "user-notifications" FOREIGN KEY (UserID) REFERENCES "User" (ID);
ALTER TABLE Notification ADD CONSTRAINT groupInvitations FOREIGN KEY (GroupID) REFERENCES "Group" (ID);
ALTER TABLE GroupStudent ADD CONSTRAINT "student-groups" FOREIGN KEY (UserID) REFERENCES "User" (ID);
