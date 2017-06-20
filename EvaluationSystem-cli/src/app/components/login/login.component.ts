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

  constructor(
    private authentication: AuthenticationService,
    private router: Router
  ) { }

  ngOnInit() {

  }


  onSubmit(): boolean {
    this.loading = true;
    let res: boolean;
    console.log(this.user);
    this.authentication.login( this.user.email, encodeURIComponent(this.user.password) ).subscribe(
      result => {
        this.router.navigate(['/dashboard']);
        res = true;
      },
      error => {
        console.log(error);
        res = false;
      }
    );
    this.loading = false;
    return res;
  }
}
