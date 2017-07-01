import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from '../../services/authentication.service';
import {Router} from '@angular/router';
import {User} from '../../models/user';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  user: any = {};
  loading = false;
  invalidEmail = false;
  differentPassword = false;
  emailAlreadyExists = false;

  constructor(
    private authentication: AuthenticationService,
    private router: Router
  ) { }

  ngOnInit() {
    this.user.type = 'Student';
  }

  private register() {
    this.loading = true;
    this.emailAlreadyExists = false;
    if ( this.validateEmail(this.user.email) && this.validPassword() && this.validAll() ) {
      this.invalidEmail = false;
      this.differentPassword = false;
      this.authentication.register(this.user.email, this.user.password, this.user.firstName, this.user.lastName, this.user.type).subscribe(
        result => {
          this.router.navigate(['/login']);
        },
        error => {
          if ( error.status === 406) {
            this.errorHandling(error);
          }
          console.log(error);
        }
      );
    } else {
      if ( this.user.email && this.validPassword() && this.validAll()) {
        this.invalidEmail = true;
      }
    }
    this.loading = false;
  }

  onSubmit(): void {
    this.register();
  }

  validateEmail(email: any): boolean {
    const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
  }

  validPassword(): boolean {
    if ( ( this.user.password !== this.user.confirmPassword ) && this.user.password && this.user.confirmPassword && this.validAll()) {
      this.differentPassword = true;
      return false;
    } else {
      this.differentPassword = false;
    }
    return true;
  }

  validAll(): boolean {
    return this.user.firstName && this.user.lastName && this.user.email;
  }

  errorHandling(error: any): void {
    const p = JSON.parse(error._body);
    if ( p.message === 'Email already in use') {
      this.emailAlreadyExists = true;
    }
  }

}
