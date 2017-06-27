import { Injectable } from '@angular/core';
import { Http} from '@angular/http';
import { Observable } from 'rxjs/Observable';
import {HttpUtilService} from './http-util.service';
import {Router} from '@angular/router';
import {AuthenticationService} from './authentication.service';

@Injectable()
export class StudentsService {

  constructor( private router: Router,
               private http: Http,
               private httpUtil: HttpUtilService,
               private authentication: AuthenticationService) {
  }

  // GET /api/groups/{group_id}/students
  getUserById(groupId: number): Observable<any> {
    return this.http.get( this.httpUtil.url('/api/groups/' + groupId + '/students' ),
          this.httpUtil.headers(this.authentication.getToken()) )
      .map( this.httpUtil.extrairDados );
  }

  // POST /api/groups/{group_id}/students TODO nao tenho acerteza disto
  postStudentByGroup(groupId: number, allEmailsStudents: string[] ): Observable<any> {
    console.log(allEmailsStudents);
    console.log(JSON.stringify(allEmailsStudents));
    return this.http.post( this.httpUtil.url('/api/groups/' + groupId + '/students'),
      JSON.stringify(
        allEmailsStudents
      ), this.httpUtil.headers(this.authentication.getToken()))
      .map(this.httpUtil.extrairDados);
  }

  // DELETE /api/groups/{group_id}/students/{student_id}
  deleteStudentById( groupId: number, studentId: number): Observable<any> {
    return this.http.delete( this.httpUtil.url('/api/groups/' + groupId + '/students/' + studentId ),
            this.httpUtil.headers(this.authentication.getToken())).map( this.httpUtil.extrairDados);
  }
}
