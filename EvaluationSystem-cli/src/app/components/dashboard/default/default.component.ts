import { Component, OnInit, AfterViewInit } from '@angular/core';
import {AuthenticationService} from '../../../services/authentication.service';

declare var $: any;
declare var x_navigation: any;
declare var page_content_onresize: any;

@Component({
  selector: 'app-default',
  templateUrl: './default.component.html',
  styleUrls: ['./default.component.css']
})
export class DefaultComponent implements OnInit, AfterViewInit {

  private new_class: boolean;
  private new_class_text: string;

  constructor( private authentication: AuthenticationService ) { }

  ngOnInit() {
    this.new_class = false;
    this.new_class_text = 'New Class';
  }

  newClass(){
    this.new_class = true;
    this.new_class_text = this.new_class ? 'New Class' : 'Save Class';
  }




  ngAfterViewInit() {
    x_navigation();
    page_content_onresize();
    this.scroll();
  }

  private scroll(): void {
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
