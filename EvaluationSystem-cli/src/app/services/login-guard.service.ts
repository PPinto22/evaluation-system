import { Injectable } from '@angular/core';
import {Router} from "@angular/router";

@Injectable()
export class LoginGuardService {

  constructor(
    private router:Router
  ) { }

  canActivate(){
    if(!localStorage['currentUser']){
      return true
    }
    this.router.navigate(['/']);
  }

}
