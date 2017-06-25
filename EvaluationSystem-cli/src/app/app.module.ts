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

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    DashboardComponent,
    DefaultComponent
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
    UserService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
