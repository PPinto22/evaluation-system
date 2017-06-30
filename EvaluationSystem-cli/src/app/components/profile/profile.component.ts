import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  private user: any = {};
  private loading = false;
  private differentPassword = false;

  constructor() { }

  ngOnInit() {
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
    return this.user.firstName && this.user.lastName;
  }

}
