import { Injectable } from '@angular/core';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class NavbarService {

  public navbarObservable: Observable<boolean>;
  private navbarObserver: any;

  constructor() {
    this.navbarObservable = new Observable( observer => {
        this.navbarObserver = observer;
      }
    );
  }

  sendUpdate( value: boolean ): void {
    this.navbarObserver.next( value );
  }



}
