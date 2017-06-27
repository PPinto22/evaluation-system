import { Injectable } from '@angular/core';
import { Http} from '@angular/http';
import { Observable } from 'rxjs/Observable';
import {HttpUtilService} from './http-util.service';
import {AuthenticationService} from './authentication.service';


@Injectable()
export class ClassesService {

  constructor( private http: Http,
               private httpUtil: HttpUtilService,
               private authentication: AuthenticationService) {
  }

  // GET /api/users/{user_id}/classes
  getAllClassesByUser(userId: number): Observable<any> {
    return this.http.get( this.httpUtil.url('/api/users/' + userId + '/classes'),
                this.httpUtil.headers(this.authentication.getToken()) )
               .map( this.httpUtil.extrairDados );
  }

  // GET /api/classes/{id}
  getById(classeId: number): Observable<any> {
    return this.http.get( this.httpUtil.url('classes/' + classeId),
           this.httpUtil.headers(this.authentication.getToken()) ).map( this.httpUtil.extrairDados );
  }

  // POST /api/users/{user_id}/classes
  createClasseByUser(userId: number, classeName: string, abbreviation: string ): Observable<any> {
    return this.http.post( this.httpUtil.url('/api/users/' + userId + '/classes'),
            JSON.stringify({
              name: classeName,
              abbreviation: abbreviation
            }), this.httpUtil.headers(this.authentication.getToken()))
            .map(this.httpUtil.extrairDados);
  }
}
