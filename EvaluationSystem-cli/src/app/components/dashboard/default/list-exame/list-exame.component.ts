import {AfterViewInit, Component, OnInit} from '@angular/core';

declare var panels: any;

@Component({
  selector: 'app-list-exame',
  templateUrl: './list-exame.component.html',
  styleUrls: ['./list-exame.component.css']
})
export class ListExameComponent implements OnInit, AfterViewInit {

  constructor() { }

  ngOnInit() {
  }

  ngAfterViewInit() {
    panels();
  }

  private refreshUpComing(): void {
    // TODO fazer um novo pedido por novas notificações
  }

  private refreshHistory(): void {
    // TODO fazer um novo pedido por novas notificações
  }

}
