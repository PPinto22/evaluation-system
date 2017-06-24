import { Injectable } from '@angular/core';
import { Http} from '@angular/http';
import { Observable } from 'rxjs/Observable';
import {HttpUtilService} from './http-util.service';
import {Router} from '@angular/router';

@Injectable()
export class StudentsService {

  constructor( private router: Router,
               private http: Http,
               private httpUtil: HttpUtilService) {
  }

  // GET /api/groups/{group_id}/students
  getUserById(groupId: number): Observable<any> {
    return this.http.get( this.httpUtil.url('/api/groups/' + groupId + '/students' ), this.httpUtil.headers() )
      .map( this.httpUtil.extrairDados );
  }

  // POST /api/groups/{group_id}/students TODO nao tenho acerteza disto
  createClasseByTeacher(groupId: number, allEmailsStudents: string[] ): Observable<any> {
    return this.http.post( this.httpUtil.url('api/groups/' + groupId + '/students'),
      JSON.stringify({
        allEmailsStudents
      }), this.httpUtil.headers())
      .map(this.httpUtil.extrairDados);
  }
}
