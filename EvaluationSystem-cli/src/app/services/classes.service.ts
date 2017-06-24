import { Injectable } from '@angular/core';
import { Http} from '@angular/http';
import { Observable } from 'rxjs/Observable';
import {HttpUtilService} from './http-util.service';
import {Router} from '@angular/router';


@Injectable()
export class ClassesService {

  constructor( private router: Router,
               private http: Http,
               private httpUtil: HttpUtilService) {
  }

  // GET /api/teachers/{teacher_id}/classes
  getAllClassesByTeacher(teacherId: number): Observable<any> {
    return this.http.get( this.httpUtil.url('/teachers/' + teacherId + '/classes'), this.httpUtil.headers() )
               .map( this.httpUtil.extrairDados );
  }

  // GET /api/classes/{id}
  getById(classeId: number): Observable<any> {
    return this.http.get( this.httpUtil.url('classes/' + classeId), this.httpUtil.headers() ).map( this.httpUtil.extrairDados );
  }

  // POST /api/teachers/{teacher_id}/classes
  createClasseByTeacher(teacherId: number, classeName: string, abbreviation: string ): Observable<any> {
    return this.http.post( this.httpUtil.url('api/teachers/' + teacherId + '/classes'),
            JSON.stringify({
              name: classeName,
              abbreviation: abbreviation
            }), this.httpUtil.headers())
            .map(this.httpUtil.extrairDados);
  }
}
