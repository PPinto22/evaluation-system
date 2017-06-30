import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ExamsService} from '../../../../services/exams.service';
import {Exam} from '../../../../models/exam';
import {GroupService} from '../../../../services/group.service';
import {AuthenticationService} from '../../../../services/authentication.service';
import {Question} from '../../../../models/question';
import {Group} from '../../../../models/group';
import {Answer} from '../../../../models/answer';
import {Class} from '../../../../models/class';
import {User} from '../../../../models/user';
import {BreadCrumbService} from '../../../../services/breadcrumb.service';

@Component({
  selector: 'app-exams',
  templateUrl: './exams.component.html',
  styleUrls: ['./exams.component.css']
})
export class ExamsComponent implements OnInit {

  private examId: number;
  private groupId: number;
  private exam: Exam;
  private questions: Question;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private examsServices: ExamsService,
    private groupsServices: GroupService,
    private authentication: AuthenticationService,
    private breadcrumbService: BreadCrumbService
  ) { }

  ngOnInit() {
    this.exam = new Exam( '', new Date().getMilliseconds(), 120);
    this.route.params.subscribe(params => {
      this.examId = +params['exam_id'];
      this.exam.id = +params['exam_id'];
      this.groupId = +params['group_id'];

      this.getExamById(this.examId);
      this.getGroupById(this.groupId);
    });
  }

  private setBreadCrumb(): void {
    if (this.exam.group && this.exam.group.class && this.exam.group.class.user) {
      this.breadcrumbService.setBreadCrum([
        this.exam.group.class.name,
        this.exam.group.name,
        this.exam.name
      ]);
    }
  }

  private getExamById(examId: number): void {
    this.examsServices.getExamById( examId ).subscribe(
      result => {
        this.exam.name = result.name;
        this.exam.beginDate = result.beginDate;
        this.exam.duration = result.duration;
        for ( const question_res of result.question ) {
          const question = new Question(question_res.difficulty, question_res.category);
          question.id = question_res.id;
          question.text = question_res.text;
          const answers: Answer[] = [];
          for ( const answer_res of question_res.answers ){
            const answer: Answer = new Answer( answer_res.correct, answer_res.text );
            answer.id = answer_res.id;
            answer.order = answer_res.order;
            answers.push( answer );
          }
          this.questions.answers = answers;
        }
        // TODO verificar isto
        console.log(this.exam);
        console.log(this.questions);
      },
      error => {
        console.log(error);
      }
    );
  }

  private getGroupById(groupId: number): void {
    this.groupsServices.getGroupById( groupId ).subscribe(
      result => {

        this.exam.group = new Group( result.name );
        this.exam.group.id = result.id;
        this.exam.group.class = new Class( result._class.name, result._class.abbreviation);
        this.exam.group.class.id = result._class.id;
        this.exam.group.class.user = new User(
          result._class.teacher.id,
          result._class.teacher.email,
          result._class.teacher.firstName,
          result._class.teacher.lastName,
          result._class.teacher.type,
          '');
        this.exam.group.class.user.active = result._class.teacher.type;
        this.setBreadCrumb();
      },
      error => {
        console.log(error);
      }
    );
  }

  private timeToMakeExam(): void {
    // TODO testar se a data está entre o inicio e a soma da duração para mandalo para a pagina de fazer o exame
  }

  public isTeacher(): boolean {
    return this.authentication.isTeacher();
  }

  public getClassName(): string {
    return this.exam.group && this.exam.group.class && this.exam.group.class.name ? this.exam.group.class.name : '';
  }

  public getClassAbbreviation(): string {
    return this.exam.group && this.exam.group.class && this.exam.group.class.abbreviation ? this.exam.group.class.abbreviation : '';
  }

  public getGroupName(): string {
    return this.exam.group && this.exam.group && this.exam.group.name ? this.exam.group.name : '';
  }

  public getTeacher(): string {
    return this.exam.group ? this.exam.group.class.user.firstName + '' + this.exam.group.class.user.lastName + '(' + this.exam.group.class.user.email + ')'  : '';
   }

}


