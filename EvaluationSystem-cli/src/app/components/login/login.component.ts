import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "../../services/authentication.service";
import {Router} from "@angular/router";
import {log} from "util";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  model: any = {};
  loading : boolean = false;

  constructor(
    private authentication: AuthenticationService,
    private router:Router
  ) { }

  ngOnInit() {

  }

  login(){
    console.log("email:" + this.model.email + "->" + "pass:" + this.model.password);
    this.loading = true;
    this.authentication.login(this.model.email, this.model.password);
    this.router.navigate(['/classes']);
  }
}
