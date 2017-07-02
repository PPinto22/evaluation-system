import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from '../../services/authentication.service';
import {Router} from '@angular/router';
import {unescape} from 'querystring';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user: any = {};
  loading = false;
  invalidEmail = false;
  emailOrPasswordWrong = false;

  constructor(
    private authentication: AuthenticationService,
    private router: Router
  ) { }

  ngOnInit() {

  }


  onSubmit(): boolean {
    this.loading = true;
    this.emailOrPasswordWrong = false;
    let res: boolean;
    if ( this.validateEmail(this.user.email) && this.user.password ) {
      this.invalidEmail = false;
      this.authentication.login(this.user.email, this.user.password).subscribe(
        result => {
          this.router.navigate(['/dashboard']);
          res = true;
        },
        error => {
          this.emailOrPasswordWrong = true;
          console.log(error);
          res = false;
        }
      );
    } else {
      if ( this.user.email && this.user.password ) {
        this.invalidEmail = true;
      }
      res = false;
    }
    this.loading = false;
    return res;
  }

  validateEmail(email: any): boolean {
    const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
  }
}
