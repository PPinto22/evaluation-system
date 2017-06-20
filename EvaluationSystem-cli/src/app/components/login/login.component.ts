import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from '../../services/authentication.service';
import {Router} from '@angular/router';

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
    this.authentication.login( this.user.email, this.user.password ).subscribe(
      result => {
        this.router.navigate(['/dashboard']);
        res = true;
        this.loading = false;
      },
      error => {
        res = true;
        this.loading = false;
      }
    );
    return res;
  }
}
