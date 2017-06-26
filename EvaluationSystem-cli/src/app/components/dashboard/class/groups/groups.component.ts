import {AfterViewInit, Component, OnInit} from '@angular/core';

declare var $: any;
declare var panels: any;

@Component({
  selector: 'app-groups',
  templateUrl: './groups.component.html',
  styleUrls: ['./groups.component.css']
})
export class GroupsComponent implements OnInit, AfterViewInit {


  constructor() { }

  ngOnInit() {
  }

  ngAfterViewInit(): void {
    panels();
  }
}
