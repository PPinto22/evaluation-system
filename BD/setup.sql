ALTER TABLE Class DROP CONSTRAINT IF EXISTS "teacher-classes";
ALTER TABLE Question DROP CONSTRAINT IF EXISTS "class-questions";
ALTER TABLE Answer DROP CONSTRAINT IF EXISTS answers;
ALTER TABLE User_Group DROP CONSTRAINT IF EXISTS "group-students";
ALTER TABLE User_Group DROP CONSTRAINT IF EXISTS "group-students2";
ALTER TABLE "Group" DROP CONSTRAINT IF EXISTS "class-groups";
ALTER TABLE QuestionScore DROP CONSTRAINT IF EXISTS "exam-questions";
ALTER TABLE Exam DROP CONSTRAINT IF EXISTS "group-exams";
ALTER TABLE QuestionScore DROP CONSTRAINT IF EXISTS question;
ALTER TABLE Submission DROP CONSTRAINT IF EXISTS "student-submission";
ALTER TABLE Submission DROP CONSTRAINT IF EXISTS "resolution-exam";
ALTER TABLE QuestionSubmission DROP CONSTRAINT IF EXISTS "question-submissions";
ALTER TABLE QuestionSubmission DROP CONSTRAINT IF EXISTS "questionSubmission-question";
ALTER TABLE QuestionSubmission DROP CONSTRAINT IF EXISTS "questionSubmission-Answer";
ALTER TABLE Notification DROP CONSTRAINT IF EXISTS "user-notifications";
ALTER TABLE Notification DROP CONSTRAINT IF EXISTS groupInvitations;
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
DROP TABLE IF EXISTS User_Group CASCADE;
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
  ID        SERIAL NOT NULL, 
  ClassID   int4 NOT NULL, 
  Text      varchar(255), 
  Category  varchar(255), 
  Dificulty int4, 
  PRIMARY KEY (ID));
CREATE TABLE Answer (
  ID         SERIAL NOT NULL, 
  QuestionID int4 NOT NULL, 
  Text       varchar(255), 
  Correct    bool, 
  PRIMARY KEY (ID));
CREATE TABLE "Group" (
  ID      SERIAL NOT NULL, 
  ClassID int4 NOT NULL, 
  Name    varchar(255), 
  PRIMARY KEY (ID));
CREATE TABLE Exam (
  ID        SERIAL NOT NULL, 
  GroupID   int4 NOT NULL, 
  BeginDate date, 
  Duration  date, 
  PRIMARY KEY (ID));
CREATE TABLE QuestionScore (
  ID         SERIAL NOT NULL, 
  QuestionID int4 NOT NULL, 
  ExamID     int4 NOT NULL, 
  Score      float4 NOT NULL, 
  PRIMARY KEY (ID));
CREATE TABLE Submission (
  ID     SERIAL NOT NULL, 
  ExamID int4 NOT NULL, 
  UserID int4 NOT NULL, 
  Score  float4 NOT NULL, 
  PRIMARY KEY (ID));
CREATE TABLE QuestionSubmission (
  ID           SERIAL NOT NULL, 
  AnswerID     int4, 
  QuestionID   int4 NOT NULL, 
  SubmissionID int4 NOT NULL, 
  Correct      bool NOT NULL, 
  PRIMARY KEY (ID));
CREATE TABLE Notification (
  ID            SERIAL NOT NULL, 
  GroupID       int4 NOT NULL, 
  UserID        int4 NOT NULL, 
  Message       varchar(255), 
  Discriminator varchar(255) NOT NULL, 
  PRIMARY KEY (ID));
CREATE TABLE User_Group (
  UserID  int4 NOT NULL, 
  GroupID int4 NOT NULL, 
  PRIMARY KEY (UserID, 
  GroupID));
ALTER TABLE Class ADD CONSTRAINT "teacher-classes" FOREIGN KEY (UserID) REFERENCES "User" (ID);
ALTER TABLE Question ADD CONSTRAINT "class-questions" FOREIGN KEY (ClassID) REFERENCES Class (ID);
ALTER TABLE Answer ADD CONSTRAINT answers FOREIGN KEY (QuestionID) REFERENCES Question (ID);
ALTER TABLE User_Group ADD CONSTRAINT "group-students" FOREIGN KEY (UserID) REFERENCES "User" (ID);
ALTER TABLE User_Group ADD CONSTRAINT "group-students2" FOREIGN KEY (GroupID) REFERENCES "Group" (ID);
ALTER TABLE "Group" ADD CONSTRAINT "class-groups" FOREIGN KEY (ClassID) REFERENCES Class (ID);
ALTER TABLE QuestionScore ADD CONSTRAINT "exam-questions" FOREIGN KEY (ExamID) REFERENCES Exam (ID);
ALTER TABLE Exam ADD CONSTRAINT "group-exams" FOREIGN KEY (GroupID) REFERENCES "Group" (ID);
ALTER TABLE QuestionScore ADD CONSTRAINT question FOREIGN KEY (QuestionID) REFERENCES Question (ID);
ALTER TABLE Submission ADD CONSTRAINT "student-submission" FOREIGN KEY (UserID) REFERENCES "User" (ID);
ALTER TABLE Submission ADD CONSTRAINT "resolution-exam" FOREIGN KEY (ExamID) REFERENCES Exam (ID);
ALTER TABLE QuestionSubmission ADD CONSTRAINT "question-submissions" FOREIGN KEY (SubmissionID) REFERENCES Submission (ID);
ALTER TABLE QuestionSubmission ADD CONSTRAINT "questionSubmission-question" FOREIGN KEY (QuestionID) REFERENCES Question (ID);
ALTER TABLE QuestionSubmission ADD CONSTRAINT "questionSubmission-Answer" FOREIGN KEY (AnswerID) REFERENCES Answer (ID);
ALTER TABLE Notification ADD CONSTRAINT "user-notifications" FOREIGN KEY (UserID) REFERENCES "User" (ID);
ALTER TABLE Notification ADD CONSTRAINT groupInvitations FOREIGN KEY (GroupID) REFERENCES "Group" (ID);
