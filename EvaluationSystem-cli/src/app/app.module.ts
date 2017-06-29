import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import {routing} from './app.routes';
import {AuthenticationService} from './services/authentication.service';
import { HomeComponent } from './components/home/home.component';
import {LoginGuardService} from './services/login-guard.service';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import {HttpUtilService} from './services/http-util.service';
import { DefaultComponent } from './components/dashboard/default/default.component';
import {CategoriesService} from './services/categories.service';
import {ClassesService} from './services/classes.service';
import {InvitationsService} from './services/invitations.service';
import {NotificationService} from './services/notification.service';
import {QuestionsService} from './services/questions.service';
import {StudentsService} from './services/students.service';
import {UserService} from './services/user.service';
import {GroupService} from './services/group.service';
import { ListClassComponent } from './components/dashboard/default/list-class/list-class.component';
import { ListExameComponent } from './components/dashboard/default/list-exame/list-exame.component';
import { ClassComponent } from './components/dashboard/class/class.component';
import { GroupsComponent } from './components/dashboard/class/groups/groups.component';
import { ScheduleComponent } from './components/dashboard/schedule/schedule.component';
import {CalendarComponent} from 'ap-angular2-fullcalendar';
import { BreadCrumbService } from './services/breadcrumb.service';
import { GroupCreateComponent } from './components/dashboard/class/groups/group-create/group-create.component';
import { ResultsComponent } from './components/dashboard/results/results.component';
import { GroupViewComponent } from './components/dashboard/class/groups/group-view/group-view.component';
import {ExamsService} from './services/exams.service';
import { QuestionAddComponent } from './components/dashboard/class/exams/exam-create/question-add/question-add.component';
import { QuestionPreviewComponent } from './components/dashboard/class/exams/exam-create/question-preview/question-preview.component';
import {Exception} from './execption/exception';
import { QuestionCreateComponent } from './components/dashboard/class/questions/question-create/question-create.component';
import { QuestionsComponent } from './components/dashboard/class/questions/questions.component';
import { QuestionCreateItemComponent } from './components/dashboard/class/questions/question-create/question-create-item/question-create-item.component';
import { AnswerCreateComponent } from './components/dashboard/class/questions/question-create/question-create-item/answer-create/answer-create.component';
import { ExamsComponent } from './components/dashboard/class/exams/exams.component';
import {ExamCreateComponent} from 'app/components/dashboard/class/exams/exam-create/exam-create.component';
import {StudentsFilter} from './filters/students_filter';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    DashboardComponent,
    DefaultComponent,
    ListClassComponent,
    ListExameComponent,
    ClassComponent,
    GroupsComponent,
    ScheduleComponent,
    CalendarComponent,
    GroupCreateComponent,
    ResultsComponent,
    GroupViewComponent,
    ExamCreateComponent,
    QuestionAddComponent,
    QuestionPreviewComponent,
    QuestionCreateComponent,
    QuestionsComponent,
    QuestionCreateItemComponent,
    AnswerCreateComponent,
    ExamsComponent,
    StudentsFilter
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    routing
  ],
  providers: [
    HttpUtilService,
    AuthenticationService,
    LoginGuardService,
    CategoriesService,
    ClassesService,
    GroupService,
    InvitationsService,
    NotificationService,
    QuestionsService,
    StudentsService,
    UserService,
    BreadCrumbService,
    ExamsService,
    Exception
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
