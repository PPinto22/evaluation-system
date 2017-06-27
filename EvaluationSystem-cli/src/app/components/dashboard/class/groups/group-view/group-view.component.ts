import {AfterViewInit, Component, OnInit} from '@angular/core';
import {AuthenticationService} from '../../../../../services/authentication.service';


declare var $: any;
declare var panels: any;

@Component({
  selector: 'app-group-view',
  templateUrl: './group-view.component.html',
  styleUrls: ['./group-view.component.css']
})
export class GroupViewComponent implements OnInit, AfterViewInit  {

  private order_date: boolean; // toggle booblen true if most recent frist or false for oldest frist
  private order_date_text: string;
  constructor(
    private authentication: AuthenticationService
  ) { }

  ngOnInit() {
    this.order_date = true;
    this.order_date_text = 'most recent frist';

  }

  ngAfterViewInit(): void {
    panels();
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

  public toggleOrderDate(): void {
    this.order_date = !this.order_date;
    this.order_date_text = this.order_date ? 'most recent frist' : 'oldest frist';
    // TODO fazer função para mudar a ordem dos exames
  }

  private isTeacher(): boolean {
    return this.authentication.isTeacher();
  }




}
