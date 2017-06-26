import { Injectable } from '@angular/core';
import { Http} from '@angular/http';
import { Observable } from 'rxjs/Observable';
import {HttpUtilService} from './http-util.service';
import {Router} from '@angular/router';

@Injectable()
export class GroupService {

  constructor( private router: Router,
               private http: Http,
               private httpUtil: HttpUtilService) {
  }

  // GET /api/groups/{group_id}
  getGroupById( groupId: number): Observable<any> {
    return this.http.get( this.httpUtil.url('/api/groups/' + groupId ), this.httpUtil.headers() )
      .map( this.httpUtil.extrairDados );
  }

  // GET /api/classes/{class_id}/groups
  getGroupByClasse( classeId: number): Observable<any> {
    return this.http.get( this.httpUtil.url('/api/classes/' + classeId + '/groups'), this.httpUtil.headers() )
            .map( this.httpUtil.extrairDados );
  }

  // POST /api/classes/{class_id}/groups
  createGroupByClasse( classeId: number, groupName: string ): Observable<any> {
    return this.http.post( this.httpUtil.url('api/classes/' + classeId + '/groups'),
      JSON.stringify({
        name: groupName
      }), this.httpUtil.headers())
      .map(this.httpUtil.extrairDados);
  }
}
