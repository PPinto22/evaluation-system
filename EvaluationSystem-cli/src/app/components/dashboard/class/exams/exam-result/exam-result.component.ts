import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';

@Component({
  selector: 'app-exam-result',
  templateUrl: './exam-result.component.html',
  styleUrls: ['./exam-result.component.css']
})
export class ExamResultComponent implements OnInit {

  constructor(
    private location: Location
  ) { }

  ngOnInit() {

  }

  public goBack(): void {
    this.location.back();
  }
}
