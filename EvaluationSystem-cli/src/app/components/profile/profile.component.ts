import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from 'app/services/authentication.service';
import {UserService} from '../../services/user.service';
import {Exception} from '../../execption/exception';

declare var x_navigation: any;
declare var page_content_onresize: any;


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  private user: any = {};
  private loading = false;
  private differentPassword = false;
  private updatedSuccess = false;
  private updatedError = false;



  constructor(
    private autentication: AuthenticationService,
    private usersService: UserService,
    private exception: Exception
  ) { }

  ngOnInit() {
    this.getUser();
    x_navigation();
    page_content_onresize();
  }

  public getUser(): void {
    this.user.firstName = this.autentication.userLogged.firstName;
    this.user.lastName = this.autentication.userLogged.lastName;
    this.user.email = this.autentication.userLogged.email;
    this.user.type = this.autentication.userLogged.type;
  }

  public updateProfile(): void {
    // TODO fazer o service do update do user
    console.log(this.user);
    this.usersService.updateUserById(this.autentication.getUserId(), this.user.firstName, this.user.lastName, this.user.password).subscribe(
      result => {
        this.autentication.userLogged.firstName = result.firstName;
        this.autentication.userLogged.lastName = result.lastName;
        this.updatedSuccess = true;
      },
      error => {
        this.exception.errorHandlingInvalidToken(error);
        this.updatedError = true;
        // TODO ver se isto funciona do error
      }
    );
  }

  onSubmit(): void {
    this.updatedError = false;
    this.updateProfile();
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
