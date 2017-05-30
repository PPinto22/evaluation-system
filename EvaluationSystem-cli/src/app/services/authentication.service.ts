import { Injectable } from '@angular/core';
import {User} from "../models/user";
import {Router} from "@angular/router";

@Injectable()
export class AuthenticationService {

  userLogged : User; //User with login ok

  constructor( private router: Router ) { }

  login(email: string, pass: string){
    //fazer o login
    this.userLogged = new User(1,"rui","rui","","");
    localStorage['currentUser'] = this.userLogged; //passar a ser o token
  }

  logout(){
    this.userLogged = null;
    localStorage.removeItem('currentUser');
  }

  isLogged():boolean{
    return localStorage['currentUser'] != null ? true : false;
  }

  isTeacher() {
    return localStorage['currentUser'].isTeacher();
  }

  getUserName():string {
    return "Rui Freitas"
    //return localStorage['currentUser'] != null ? localStorage['currentUser'].name : "Rui Freitas";
  }
}
