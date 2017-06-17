import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "../../services/authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  model: any = {};
  loading : boolean = false;

  constructor(
    private authentication: AuthenticationService,
    private router:Router
  ) { }

  ngOnInit() {
  }

  register(){
    console.log("email:" + this.model.email + "->" + "pass:" + this.model.password);
    this.loading = true;
    this.authentication.login(this.model.email, this.model.password);
    this.router.navigate(['/classes']);
  }
}
