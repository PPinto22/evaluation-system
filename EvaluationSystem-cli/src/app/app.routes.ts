/**
 * Created by rjaf on 30/05/2017.
 */

import { ModuleWithProviders }  from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {RegisterComponent} from "./components/register/register.component";
import {HomeComponent} from "./components/home/home.component";
import {LoginGuardService} from "./services/login-guard.service";
import {ClassesComponent} from "./components/classes/classes.component";

export const routes: Routes = [
  { path: 'login', component: LoginComponent, canActivate: [LoginGuardService] },
  { path: 'register', component: RegisterComponent, canActivate: [LoginGuardService] },
  { path: 'classes', component: ClassesComponent, canActivate: [] },
  { path: '', component: HomeComponent }
]

export const routing: ModuleWithProviders = RouterModule.forRoot(routes);
