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

  constructor(
    private authentication: AuthenticationService,
    private router: Router
  ) { }

  ngOnInit() {
  }

  private register() {
    this.loading = true;
    console.log(this.user);
    this.authentication.register(this.user.email, this.user.password, this.user.firstName, this.user.lastName, User._Teacher).subscribe(
      result => {
        console.log(result);
        this.router.navigate(['/dashboard']);
      },
      error => {
        console.log(error);
      }
    );
  }

  onSubmit(): void {
    this.register();
  }
}
