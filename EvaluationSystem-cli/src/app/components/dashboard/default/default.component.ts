import { Component, OnInit, AfterViewInit } from '@angular/core';
import {AuthenticationService} from '../../../services/authentication.service';

declare var $: any;

@Component({
  selector: 'app-default',
  templateUrl: './default.component.html',
  styleUrls: ['./default.component.css']
})
export class DefaultComponent implements OnInit, AfterViewInit {

  constructor( private authentication: AuthenticationService ) { }

  ngOnInit() {

  }

  ngAfterViewInit() {
    $('.scroll').mCustomScrollbar({
      axis: 'y',
      autoHideScrollbar: true,
      scrollInertia: 20,
      advanced: {
        autoScrollOnFocus: false
      }
    }, {passive: true});
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
