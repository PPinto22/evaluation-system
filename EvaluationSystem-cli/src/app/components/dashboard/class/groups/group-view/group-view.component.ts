import {AfterViewInit, Component, OnInit} from '@angular/core';


declare var $: any;
declare var panels: any;

@Component({
  selector: 'app-group-view',
  templateUrl: './group-view.component.html',
  styleUrls: ['./group-view.component.css']
})
export class GroupViewComponent implements OnInit, AfterViewInit  {

  private order_date: boolean; // toggle booblen true if most recent frist or false for oldest frist
  constructor() { }

  ngOnInit() {
    this.order_date = true;
  }

  ngAfterViewInit(): void {
    this.scroll();
  }

  public toggleOrderDate(): void {
    this.order_date = !this.order_date;
    // TODO fazer função para mudar a ordem dos exames
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


}
