import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-list-class',
  templateUrl: './list-class.component.html',
  styleUrls: ['./list-class.component.css']
})
export class ListClassComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  protected deleteGroup( id: string ): void {
    // TODO fazer delete do group
  }

  protected  editGroup( id: string ): void {
    // TODO fazer edit do group
  }

}
