import { Injectable } from '@angular/core';

@Injectable()
export class Exception {

  errorHandlingCreateClass(error: any): boolean {
    const p = JSON.parse(error._body);
    if ( p.message === 'Class already exists') {
      return true;
    }
    return false;
  }

  errorHandlingCreateGroup(error: any): boolean {
    const p = JSON.parse(error._body);
    if ( p.message === 'Group already exists') {
      return true;
    }
    return false;
  }
}
