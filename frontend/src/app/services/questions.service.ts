import { Injectable } from '@angular/core';
import { Http} from '@angular/http';
import { Observable } from 'rxjs/Observable';
import {HttpUtilService} from './http-util.service';
import {Router} from '@angular/router';
import {AuthenticationService} from './authentication.service';

@Injectable()
export class QuestionsService {

  constructor( private router: Router,
               private http: Http,
               private httpUtil: HttpUtilService,
               private authentication: AuthenticationService) {
  }

  // GET /api/classes/{class_id}/questions
  getAllQuestionsFromClasse( classeId: number): Observable<any> {
    return this.http.get( this.httpUtil.url('/api/classes/' + classeId + '/questions' ),
                this.httpUtil.headers(this.authentication.getToken()) )
               .map( this.httpUtil.extrairDados );
  }
  // POST /api/classes/{class_id}/questions TODO não tenhoa acerteza a parte das answers
  createQuestionByClasse( classeId: number, questionName: string, categoryName: string, difficulty: number, answers: any[] ): Observable<any> {
    return this.http.post( this.httpUtil.url('/api/classes/' + classeId + '/questions'),
      JSON.stringify({
        text: questionName,
        category: categoryName,
        difficulty: difficulty,
        answers: answers
      }), this.httpUtil.headers(this.authentication.getToken()))
      .map(this.httpUtil.extrairDados);
  }

  // GET /api/groups/{group_id}/questions/available
  getAllQuestionsAvailableGroup( groupId: number): Observable<any> {
    return this.http.get(this.httpUtil.url('/api/groups/' + groupId + '/questions/available'),
      this.httpUtil.headers(this.authentication.getToken()))
      .map(this.httpUtil.extrairDados);
  }
  // POST /api/groups/{group_id}/exams/generate/question
  createQuestionByGenerate( groupId: number, categoryName: string, difficulty: number, exclude: number[] ): Observable<any> {
    return this.http.post(this.httpUtil.url('/api/groups/' + groupId + '/exams/generate/question'),
      JSON.stringify({
        category: categoryName,
        difficulty: difficulty,
        excluded: exclude
      }), this.httpUtil.headers(this.authentication.getToken()))
      .map(this.httpUtil.extrairDados);
  }

}
