import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from '../../../services/authentication.service';

@Component({
  selector: 'app-default',
  templateUrl: './default.component.html',
  styleUrls: ['./default.component.css']
})
export class DefaultComponent implements OnInit {

  constructor( private authentication: AuthenticationService ) { }

  ngOnInit() {

  }


  private refreshUpComing(): void {
    // TODO fazer um novo pedido por novas notificações
  }

  private refreshHistory(): void {
    // TODO fazer um novo pedido por novas notificações
  }

  private isTeacher(): boolean {
    return this.authentication.isTeacher();
  }
}
