import { Injectable } from '@angular/core';
import { Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class HttpUtilService {

  // private API_URL = 'http://127.0.0.1:8080';
  // private API_URL = 'http://localhost:8080';
  private API_URL = 'http://localhost:8080';

  url(path: string) {
    return this.API_URL + path;
  }

  headers(token: string) {
    const headersParams = {
      'Content-Type': 'application/json;charset=UTF-8'
        // 'Content-Type': 'application/x-www-form-urlencoded'
    };
    if (token) {
      headersParams['Authorization'] = 'Bearer ' + token;
    }
    const headers = new Headers(headersParams);
    const options = new RequestOptions({ headers: headers });
    console.log(options);
    return options;
  }

  extrairDados(response: Response) {
    const data = response.json();
    return data || {};
  }

  processarErros(erro: any) {
    return Observable.throw('Erro acessando servidor remoto.');
  }
}
