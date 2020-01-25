import { Component, OnInit } from '@angular/core';
import {Angular2Csv} from 'angular2-csv';
import {ActivatedRoute} from '@angular/router';
import {BreadCrumbService} from '../../../../../services/breadcrumb.service';
import {ScoresService} from '../../../../../services/scores.service';

@Component({
  selector: 'app-group-score',
  templateUrl: './group-score.component.html',
  styleUrls: ['./group-score.component.css']
})
export class GroupScoreComponent implements OnInit {


  private class_id: number;
  private group_id: number;
  private studentsScores: any[];
  private header: string[];
  private model: any = {};

  constructor(
    private scoresService: ScoresService,
    private breadCrumbService: BreadCrumbService,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.header = ['Name', 'Email'];
    this.studentsScores = [];
    this.route.params.subscribe( params => {
        this.class_id = +params['class_id'];
        this.group_id = +params['group_id'];
        this.getGroupScores(this.group_id);
        this.setBreadCrumb();
      }
    );
  }

  public setBreadCrumb(): void {
    // FIXME alterar para a forma dinamica
    this.breadCrumbService.setBreadCrum(['Classes > Arquiteturas Aplicacionais > Results' ]);
  }

  public exportResults(): void {
    const export_scores: any[] = [];
    export_scores.push(this.header);
    for (const score of this.studentsScores) {
      const line = [];
      line.push(score.name);
      line.push(score.email);
      for (const exam of score.exams) {
        line.push(exam);
      }
      export_scores.push(line);
    }
    new Angular2Csv( export_scores, 'Result ' + this.class_id + ' ' + this.group_id);
  }

  public getGroupScores(group_id: number): void {
    this.scoresService.getGroupScores(group_id).subscribe(
      result => {
        for (const student of result.students) {
          const studentScore: any = {};
          student.student.active ? studentScore.name = student.student.firstName + ' ' + student.student.lastName : studentScore.name = '';
          studentScore.email = student.student.email;
          studentScore.exams = [];
          for (const studentExam of student.exams) {
            this.addExamToHeader(studentExam.exam.name);
            studentScore.exams.push(studentExam.score.score);
          }
          this.studentsScores.push( studentScore );
        }
      },
      error => {
        console.log(error);
      }
    );
  }

  private addExamToHeader(exam_name): void {
    if (!this.header.find( obj => obj === exam_name)) {
      this.header.push(exam_name);
    }
  }


}
