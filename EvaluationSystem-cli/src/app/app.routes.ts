/**
 * Created by rjaf on 30/05/2017.
 */

import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './components/login/login.component';
import {RegisterComponent} from './components/register/register.component';
import {HomeComponent} from './components/home/home.component';
import {LoginGuardService} from './services/login-guard.service';
import {DashboardComponent} from './components/dashboard/dashboard.component';

export const routes: Routes = [
  { path: '', component: HomeComponent , pathMatch: 'full', canActivate: [LoginGuardService]   },
  { path: 'login', component: LoginComponent, canActivate: [LoginGuardService] },
  { path: 'register', component: RegisterComponent, canActivate: [LoginGuardService] },
  { path: 'dashboard', component: DashboardComponent,
    children: [
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' }
      // { path: 'classes', component: Overview },
      // { path: 'results', component: Specs }
    ]
  }

];

export const routing: ModuleWithProviders = RouterModule.forRoot(routes);
