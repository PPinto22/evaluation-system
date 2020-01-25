import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class BreadCrumbService {

  nameBreadCrum: any;
  breadCrumDate: Observable<[string]>;

  constructor() {
     this.breadCrumDate = new Observable(observer => {
       this.nameBreadCrum = observer;
    });
  }

  setBreadCrum(nameString: string[]) {
    this.nameBreadCrum.next(nameString);
  }

}
