import { Injectable } from '@angular/core';
import { Http} from '@angular/http';
import { Observable } from 'rxjs/Observable';
import {HttpUtilService} from './http-util.service';
import {Router} from '@angular/router';

@Injectable()
export class QuestionsService {

  constructor( private router: Router,
               private http: Http,
               private httpUtil: HttpUtilService) {
  }

  // GET /api/classes/{class_id}/questions
  getAllQuestionsFromClasse( classeId: number): Observable<any> {
    return this.http.get( this.httpUtil.url('/api/classes/' + classeId + '/questions' ), this.httpUtil.headers() )
               .map( this.httpUtil.extrairDados );
  }
  // POST /api/classes/{class_id}/questions TODO não tenhoa acerteza a parte das answers
  createQuestionByClasse( classeId: number, questionName: string, categoryName: string, difficulty: number, answers: any[] ): Observable<any> {
    return this.http.post( this.httpUtil.url('api/classes/' + classeId + '/questions'),
      JSON.stringify({
        text: questionName,
        category: categoryName,
        difficulty: difficulty,
        answers: answers
      }), this.httpUtil.headers())
      .map(this.httpUtil.extrairDados);
  }


}
